package cn.newtol.springbootlogin.myEnum;

import cn.newtol.springbootlogin.entity.VaildCodeDO;
import lombok.Data;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:31 2019/1/25
 */


public enum ResultEnum {
    /**
     * 系统未知错误
     */
    UNKONW_ERROR(-1,"未知错误："),
    /**
     * 请求成功
     */
    SUCCESS(0,"success"),
    /**
     * 请求缺少参数
     */
    LACK_PARAMETER(1,"缺少参数"),
    /**
     * 账号或者密码错误
     */
    PASSWOED_ERROR(2,"账号或密码错误"),
    /**
     * 账号存在
     */
    ACCOUNT_EXSIT(3,"账号已经存在"),

    /**
     * 验证码错误
     */
    VaildCode_ERROR(4,"验证码错误");

    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误提示信息
     */
    private String errorMsg;

    ResultEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

