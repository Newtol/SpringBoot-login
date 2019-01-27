package cn.newtol.springbootlogin.exceptions;


import cn.newtol.springbootlogin.myEnum.ResultEnum;
import lombok.Data;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 13:35 2018/11/10
 */
@Data
public class TestException extends RuntimeException {
    private Integer errorCode;

    public TestException(ResultEnum resultEnum) {
        super(resultEnum.getErrorMsg());
        this.errorCode = resultEnum.getErrorCode();
    }

}
