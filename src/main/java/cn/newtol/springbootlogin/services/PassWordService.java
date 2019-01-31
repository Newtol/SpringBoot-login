package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.PassWordDO;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:14 2019/1/29
 * @params:
 */
public interface PassWordService {
    /**
     * 注册时设置登陆密码
     * @param passWord：用户设置的密码
     * @return
     */
    String encryptPassWord(String passWord);



    /**
     * 找回密码
     * @param passWordDO
     * @return
     */
     ResultVO retrievePassWord(HttpServletRequest request,PassWordDO passWordDO);
}
