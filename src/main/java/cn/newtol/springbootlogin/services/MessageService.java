package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.SMSMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:17 2019/1/26
 * @params:
 */
@Service
public interface MessageService {
   /**
    * 发送邮件
    * @param simpleMailMessage：邮件内容
    */
   void sendEmail(SimpleMailMessage simpleMailMessage);

   /**
    * 发送短信
    * @param smsMessage
    * @throws Exception
    */
   void sendSMS(SMSMessage smsMessage) throws Exception;
}
