package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.HttpClientResult;
import cn.newtol.springbootlogin.entity.SMSMessage;
import cn.newtol.springbootlogin.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:18 2019/1/26
 * @params:
 */
@Service
@Component
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender mailSender;

    /**
    * @Author:
    * @Description:
    * @Date: Created in 16:51 2019/1/26
    * @Param: 通过邮件发送
    * @return:
    */

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送短信
     * @param smsMessage：短信内容
     * @throws Exception
     */
    @Override
    public void sendSMS(SMSMessage smsMessage) throws Exception {
        String jsonString = JSON.toJSONString(smsMessage);
        HttpClientResult httpClientResult = HttpClientUtil.doPostWithJson(smsMessage.getUrl(),null,jsonString);
        System.out.println(JSON.toJSONString(httpClientResult));
    }
}
