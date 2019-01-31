package cn.newtol.springbootlogin.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 20:20 2019/1/31
 * @params:
 */
@Data
@Component
public class PassWordDO extends ValidCodeDO {
    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    private String newPassWord;
}



