package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.LoginDO;
import cn.newtol.springbootlogin.entity.ResultVO;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 17:14 2019/1/25
 */
public interface LoginService {
    ResultVO login(LoginDO loginDO);

}
