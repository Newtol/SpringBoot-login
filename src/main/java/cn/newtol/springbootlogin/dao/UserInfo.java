package cn.newtol.springbootlogin.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 17:19 2019/1/25
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table(name = "userinfo")
public class UserInfo extends BaseDO {

    /**
     * 用户ID
     */
    @Column(nullable = false,unique = true)
    private String userId;

    /**
     * 用户电话
     */
    @Column(unique = true)
    private String phoneNum;

    /**
     * 用户邮箱
     */
    @Column(unique = true)
    private String email;

    /**
     * 用户昵称
     */
    @Column(nullable = false)
    @NotEmpty(message = "昵称为空")
    private String nickName;

    /**
     * 用户头像
     */
    @Column(nullable = false)
    @NotEmpty(message = "头像为空")
    private String headImgUrl;

    /**
     * 用户密码
     */
    @Column(nullable = false)
    @NotEmpty(message = "密码为空")
    private String passWord;

    /**
     * 用户注册时的验证码
     */
    @NotEmpty(message = "验证码不能为空")
    @Transient
    private String validCode;
}
