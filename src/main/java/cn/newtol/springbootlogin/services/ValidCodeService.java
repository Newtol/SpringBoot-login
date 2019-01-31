package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:11 2019/1/26
 * @params:
 */
@Service
public interface ValidCodeService {
    /**
     * @Author:
     * @Description: 获取验证码
     * @Date: Created in 15:05 2019/1/26
     * @Param:
     * @return:
     */
    ResultVO getValidCode(HttpServletRequest request, ValidCodeDO validCodeDO) throws Exception;

    /**
    * @Author:
    * @Description: 验证验证码是否正确
    * @Date: Created in 16:17 2019/1/26
    * @Param:
    * @return:
    */
    boolean isValidCode(HttpServletRequest request, ValidCodeDO validCodeDO);

    /**
     * @Author:
     * @Description: 获取随机的6位数字
     * @Date: Created in 15:21 2019/1/26
     * @Param:
     * @return:
     */
    String getRandomNum();

    /**
     * 发送邮箱验证码
     * @param toUserName：接收方邮箱
     * @param code: 验证码
     */
    void sendEmailValidCode(String toUserName,String code) throws Exception;

    /**
     * @param toUserName: 接收方电话号码
     * @param code： 验证码
     */
    void sendSMSValidCode(String toUserName,String code) throws Exception;

}
