package cn.newtol.springbootlogin.controller;

import cn.newtol.springbootlogin.entity.PassWordDO;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.repository.PassWordRepository;
import cn.newtol.springbootlogin.services.PassWordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:39 2019/1/31
 * @params:
 */
@RestController
public class PassWordController {
   @Resource
   private PassWordService passWordService;

    /**
     * 修改面膜
     * @param request：用于验证用户验证码是否正确
     * @param passWordDO：用户密码信息等
     * @return
     */
    @PostMapping("resetPassword")
    public ResultVO resetPassWord(HttpServletRequest request,@Valid  PassWordDO passWordDO){
        return  passWordService.retrievePassWord(request,passWordDO);
    }
}
