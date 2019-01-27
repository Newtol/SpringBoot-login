package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.VaildCodeDO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.utils.DataUtil;
import cn.newtol.springbootlogin.utils.RedisUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:12 2019/1/26
 * @params:
 */
@Service
@Component
public class VaildCodeServiceImpl implements VaildCodeService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MessageService messageService;

    /**
     * 验证码储存的Hash
     */
    @Value("${defaultKey.VaildCode}")
    private String vaildCode;


    /**
     * @param vaildCodeDO:用户注册的电话或者邮箱
     * @Author:
     * @Description: 获取邮箱或者手机验证码
     * @Date: Created in 15:05 2019/1/26
     * @Param:
     * @return:
     */
    @Override
    public ResultVO getVaildCode(VaildCodeDO vaildCodeDO) throws Exception {
        // 判断用户的注册方式
        String account = null;
        String code = DataUtil.getRandomNum();
        if (vaildCodeDO.getEmail() != null) {
            account = vaildCodeDO.getEmail();
            messageService.sendEmail(account,"注册验证码","感谢您的注册，您的注册验证码为："+code);
        }
        else if (vaildCodeDO.getPhoneNum() != null) {
            account = vaildCodeDO.getPhoneNum();
            String templateid = "1309010131";
            messageService.sendSMS(account,code,templateid);
        }
        redisUtil.setHash(vaildCode, account, code);
        return ResultUtil.success();
    }

    /**
     * @param account:用户的电话号码或者邮箱
     * @param code:               验证码
     * @Author:
     * @Description: 验证验证码是否正确
     * @Date: Created in 16:17 2019/1/26
     * @return: 返回是否验证成功
     */
    @Override
    public boolean isVaildCode(String account, String code) {
        String str = redisUtil.getHash(vaildCode, account);
        if (code.equals(str)) {
            return true;
        } else {
            return false;
        }
    }
}
