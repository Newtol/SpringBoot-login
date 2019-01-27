package cn.newtol.springbootlogin.handle;


import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.exceptions.TestException;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 13:55 2018/11/10
 */

@ControllerAdvice
public class ExceptionHandle {


    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    /**
    * @Author: 公众号：Newtol
    * @Description: 拦截一些自定义的警告
    * @Date: Created in 0:07
    * @param:
    */
    @ExceptionHandler(TestException.class)
    @ResponseBody
    public ResultVO testExceptionHandler(TestException e){
        return ResultUtil.error(e.getErrorCode(),e.getMessage());
    }

    /**
    * @Author: 公众号：Newtol
    * @Description: 拦截参数验证错误
    * @Date: Created in 0:07
    * @param:
    */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO validExceptionHandler(BindException e){
        String  msg  = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.info("参数验证错误:"+msg);
        return ResultUtil.error(ResultEnum.LACK_PARAMETER.getErrorCode(),msg);
    }

    /**
    * @Author: 公众号：Newtol
    * @Description: 系统未知错误拦截
    * @Date: Created in 0:15
    * @param:
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO systemExceptionHandler(Exception e){
        logger.error("系统未知错误:"+e.getMessage());
        return ResultUtil.error(ResultEnum.UNKONW_ERROR);
    }


}
