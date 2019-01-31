package cn.newtol.springbootlogin.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:06 2019/1/30
 * @params:
 */
@Component
@Data
public class SMSMessage {
    /**
     * 第三方发送短信的请求接口
     */
    private String url;

    /**
     * 第三方账号id
     */
    private  String accountSid;

    /**
     * 第三方账号的authToken
     */
    private  String authToken;

    /**
     * 第三方平台消息模板id
     */
    private String templateid;

    /**
     * 模板消息中的参数，多个消息采用英文逗号分隔
     */
    private String param;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 接收方手机号
     */
    private String to;

    /**
     * 验证签名
     */
    private String sig;

}
