package cn.newtol.springbootlogin.controller;

import cn.newtol.springbootlogin.entity.LoginDO;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.services.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:29 2019/1/25
 */

@RestController
public class loginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResultVO login(@Valid LoginDO loginDO){
        ResultVO resultVO = loginService.login(loginDO);
        return resultVO;
    }

}
