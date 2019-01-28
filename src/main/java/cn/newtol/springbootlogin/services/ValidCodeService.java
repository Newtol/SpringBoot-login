package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
     * @Description: 获取邮箱或者手机验证码
     * @Date: Created in 15:05 2019/1/26
     * @Param:
     * @return:
     */
    ResultVO getVaildCode(HttpServletRequest request,ValidCodeDO validCodeDO) throws Exception;

    /**
    * @Author:
    * @Description: 验证验证码是否正确
    * @Date: Created in 16:17 2019/1/26
    * @Param:
    * @return:
    */
    boolean isVaildCode(String account,String code);

}
