package cn.newtol.springbootlogin.controller;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.services.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:44 2019/1/25
 * @params:
 */
@RestController
public class registerController {
    @Resource
    RegisterService registerService;

    /**
    * @Author:
    * @Description: 注册
    * @Date: Created in 16:39 2019/1/26
    * @Param:
    * @return:
    */
    @PostMapping("/register")
    public ResultVO register(HttpServletRequest request,@Valid UserInfo userInfo){
        return registerService.register(request,userInfo);
    }



}
