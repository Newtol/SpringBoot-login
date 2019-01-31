package cn.newtol.springbootlogin.services;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.SMSMessage;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.utils.EncryptUtil;
import cn.newtol.springbootlogin.utils.RedisUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:12 2019/1/26
 * @params:
 */
@Service
@Component
public class ValidCodeServiceImpl implements ValidCodeService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MessageService messageService;

    /**
     * 判断用户是否通过验证码验证
     */
    private static final String IS_VALID = "isValid";

    /**
     * session中存储6位验证码的Key
     */
    @Value("${defaultKey.ValidCode}")
    private String VALID_CODE_KEY;

    /**
     * 判断用户是否验证通过
     */
    private static final String VALID_SUCCESS = "success";

    /**
     *  获取验证码的次数
     */
    private static final String VALIDCODE_COUNT_NUM = "validCodeCountNum";

    private static final Integer VALIDCODE_COUNT_LIMITED = 2;

    /**
     * 邮件发送方
     */
    @Value("${spring.mail.username}")
    private String fromUserName;

    /**
     * 第三方发送短信的请求接口
     */
    @Value("${defaultKey.SMSUrl}")
    private String url;

    /**
     * 第三方账号id
     */
    @Value("${defaultKey.SMSAccountSid}")
    private  String accountSid;

    /**
     * 第三方账号的authToken
     */
    @Value("${defaultKey.SMSAuthToken}")
    private  String authToken;


    /**
     * @Author:
     * @Description: 获取随机的6位数字
     * @Date: Created in 15:21 2019/1/26
     * @Param:
     * @return:
     */
    @Override
    public  String getRandomNum(){
        return RandomStringUtils.randomNumeric(6);
    }

    /**
     * 发送邮箱验证码
     *
     * @param toUserName ：接收方账户
     */
    @Override
    public void sendEmailValidCode(String toUserName,String code)  {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromUserName);
        simpleMailMessage.setTo(toUserName);
        simpleMailMessage.setSubject("【xxx科技】注册验证");
        simpleMailMessage.setText("感谢您的注册，您的注册验证码为：" + code);
        messageService.sendEmail(simpleMailMessage);
    }

    @Override
    public void sendSMSValidCode(String toUserName, String code) throws Exception {
        SMSMessage smsMessage = new SMSMessage();
        // 设置发送方账户
        smsMessage.setAccountSid(accountSid);
        // 设置url
        smsMessage.setUrl(url);
        // 设置获取的手机号
        smsMessage.setTo(toUserName);
        // 设置模板id
        smsMessage.setTemplateid("1309010131");
        // 设置时间戳
        Date today = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = f.format(today);
        smsMessage.setTimestamp(timestamp);
        // 设置签名
        String sig = EncryptUtil.md5(accountSid+authToken+timestamp);
        smsMessage.setSig(sig);
        // 设置参数
        smsMessage.setParam(code);
        // 发送邮件
        messageService.sendSMS(smsMessage);
    }

    /**
     * @param validCodeDO:用户注册的电话或者邮箱
     * @Author:
     * @Description: 获取邮箱或者手机验证码
     * @Date: Created in 15:05 2019/1/26
     * @Param:
     * @return:
     */
    @Override
    public ResultVO getValidCode(HttpServletRequest request, ValidCodeDO validCodeDO) throws Exception {

        // 验证是否通过图片验证
        String isValid = (String) request.getSession().getAttribute(IS_VALID);
        if(isValid == null){
            return ResultUtil.error(ResultEnum.ValidCode_EXPIRED);
        }
        if (!Objects.equals(isValid,VALID_SUCCESS)){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }
        // 判断是否超过获取次数限制
        Integer countNum = (Integer) request.getSession().getAttribute(VALIDCODE_COUNT_NUM);
        if (countNum  == null) {
            countNum = 0;
        }
        if(countNum > VALIDCODE_COUNT_LIMITED){
            return ResultUtil.error(ResultEnum.ValidCodeCountNum_OverFlow);
        }else{
            countNum++;
        }
        request.getSession().setAttribute(VALIDCODE_COUNT_NUM,countNum);

        // 判断用户发送方式\
        String accountId = null;
        String code = getRandomNum();
        if (validCodeDO.getEmail() != null) {
            accountId = validCodeDO.getEmail();
            sendEmailValidCode(accountId,code);
        }
        else if (validCodeDO.getPhoneNum() != null) {
            accountId = validCodeDO.getPhoneNum();
            sendSMSValidCode(accountId,code);
        }
        Map<String,String> map = new HashMap<>(1);
        map.put(accountId,code);
        request.getSession().setAttribute(VALID_CODE_KEY,map);
        return ResultUtil.success();
    }

    /**
     * @param request:用于获取存在session中的验证码
     * @param validCodeDO: 用户信息
     * @Author:
     * @Description: 验证验证码是否正确
     * @Date: Created in 16:17 2019/1/26
     * @return: 返回是否验证成功
     */
    @Override
    public boolean isValidCode(HttpServletRequest request,ValidCodeDO validCodeDO) {
        Object object =  request.getSession().getAttribute(VALID_CODE_KEY);
        // 判断是否获取过验证码
        if (object == null){
            return false;
        }
        Map<String,String> map = (Map<String, String>) object;
        String code = validCodeDO.getValidCode();
        // 判断验证码是否为空
        if (code == null){
            return false;
        }
        // 判断存在sssion中的数据是否存在
        if (validCodeDO.getPhoneNum() != null){
            String phoneNum = validCodeDO.getPhoneNum();
            // 判断验证码是否正确
            if (Objects.equals(map.get(phoneNum), code)){
                return true;
            }
        }else if(validCodeDO.getEmail() != null ){
            String email = validCodeDO.getEmail();
            if(Objects.equals(map.get(email), code)){
                System.out.println(map.get(email)+"==="+code);
                return true;
            }
        }
        return false;
    }
}