package cn.newtol.springbootlogin.controller;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import cn.newtol.springbootlogin.services.ValidCodeService;
import cn.newtol.springbootlogin.utils.ValidCodeImgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:40 2019/1/26
 * @params:
 */

@Controller
public class VaildCodeController {

    @Resource
    private ValidCodeService validCodeService;

    @Resource
    private ValidCodeImgUtil validCodeImgUtil;



    /**
     * 发送手机、邮箱验证码
     * @param validCodeDO
     * @return 返回是否验证码发送成功
     */
    @PostMapping("/getValidCode")
    @ResponseBody
    public ResultVO sendVaildCode(HttpServletRequest request ,ValidCodeDO validCodeDO) throws Exception {
        return  validCodeService.getVaildCode(request, validCodeDO);
    }


    /**
     * 获取图形验证码
     * @param request
     * @param response
     */
    @GetMapping("/getValidCodeImg")
    public void getValidCodeImg(HttpServletRequest request, HttpServletResponse response) {
        validCodeImgUtil.getVaildCodeImg(request,response);
    }

    @PostMapping("/isValidCodeImg")
    @ResponseBody
    public ResultVO isValidCodeImg(HttpServletRequest request, @RequestParam String code){
        return  validCodeImgUtil.isVaildCode(request,code);
    }


}
