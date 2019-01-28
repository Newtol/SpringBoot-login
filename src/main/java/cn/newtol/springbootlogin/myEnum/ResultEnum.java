package cn.newtol.springbootlogin.myEnum;

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
    ValidCode_ERROR(4,"验证码错误"),

    /**
     * 验证码过期
     */
    ValidCode_EXPIRED(5,"验证码过期"),

    /**
     * 验证码为空
     */
    ValidCcde_EMPTY(6,"验证码为空"),

    /**
     * 未经过验证
     */
    ValidCode_REFRESH(7,"请刷新验证码");

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

