package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:48 2019/1/25
 * @params:
 */
@Component
@Service
public interface RegisterService {
    /**
    * @Author:
    * @Description: 用户注册
    * @Date: Created in 21:49 2019/1/25
    * @Param:
    * @return: 返回注册的信息
    */
    ResultVO register(HttpServletRequest request,UserInfo userinfo);

    /**
    * @Author:
    * @Description: 判断用户是否已经注册
    * @Date: Created in 14:41 2019/1/26
    * @Param:
    * @return:
    */
    Boolean isRegister(UserInfo userInfo);


}
