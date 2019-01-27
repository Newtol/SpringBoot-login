package cn.newtol.springbootlogin.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 18:28 2019/1/25
 */
@Data
@Component
public class LoginDO {
    /**
    用户电话
     */
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "请输入正确的电话号码格式")
    private String  phoneNum;
    /**
    用户邮箱
     */
    @Email(message = "请输入正确的邮件格式")
    private String email;
    /**
    用户密码
     */
    @NotEmpty(message = "密码不能为空")
    private String passWord;
}
