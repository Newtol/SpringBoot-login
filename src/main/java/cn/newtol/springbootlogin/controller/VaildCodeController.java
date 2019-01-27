package cn.newtol.springbootlogin.controller;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.VaildCodeDO;
import cn.newtol.springbootlogin.services.VaildCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:40 2019/1/26
 * @params:
 */

@RestController
public class VaildCodeController {

    @Resource
    private VaildCodeService vaildCodeService;

    /**
     * 获取验证码
     * @param vaildCodeDO
     * @return 返回是否验证码发送成功
     */
    @PostMapping("/getVaildCode")
    public ResultVO getVaildCode(VaildCodeDO vaildCodeDO) throws Exception {
        return  vaildCodeService.getVaildCode(vaildCodeDO);
    }

}
