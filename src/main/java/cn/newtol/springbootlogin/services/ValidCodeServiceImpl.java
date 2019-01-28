package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.utils.DataUtil;
import cn.newtol.springbootlogin.utils.RedisUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 验证码储存的Hash
     */
    @Value("${defaultKey.ValidCode}")
    private String validCode;

    /**
     * 判断用户是否验证通过
     */
    private static final String VALID_SUCCESS = "success";
    private static final String VALID_FALSE = "false";


    /**
     * @param validCodeDO:用户注册的电话或者邮箱
     * @Author:
     * @Description: 获取邮箱或者手机验证码
     * @Date: Created in 15:05 2019/1/26
     * @Param:
     * @return:
     */
    @Override
    public ResultVO getVaildCode(HttpServletRequest request,ValidCodeDO validCodeDO) throws Exception {

        // 验证是否通过图片验证
        String isValid = (String) request.getSession().getAttribute(IS_VALID);
        if(isValid == null){
            return ResultUtil.error(ResultEnum.ValidCode_EXPIRED);
        }

        System.out.println(isValid);
        if (!Objects.equals(isValid,VALID_SUCCESS)){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }

        // 判断用户的注册方式
        String account = null;
        String code = DataUtil.getRandomNum();
        if (validCodeDO.getEmail() != null) {
            account = validCodeDO.getEmail();
            messageService.sendEmail(account,"注册验证码","感谢您的注册，您的注册验证码为："+code);
        }
        else if (validCodeDO.getPhoneNum() != null) {
            account = validCodeDO.getPhoneNum();
            String templateid = "1309010131";
            messageService.sendSMS(account,code,templateid);
        }
        redisUtil.setHash(validCode, account, code);
        return ResultUtil.success();
    }

    /**
     * @param account:用户的电话号码或者邮箱
     * @param code: 验证码
     * @Author:
     * @Description: 验证验证码是否正确
     * @Date: Created in 16:17 2019/1/26
     * @return: 返回是否验证成功
     */
    @Override
    public boolean isVaildCode(String account, String code) {
        String str = redisUtil.getHash(validCode, account);
        if (code.equals(str)) {
            return true;
        } else {
            return false;
        }
    }
}
