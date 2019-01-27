package cn.newtol.springbootlogin.services;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:17 2019/1/26
 * @params:
 */
@Service
public interface MessageService {
   void sendEmail(String to, String subject, String content);

   void sendSMS(String to,String content,String templateid) throws Exception;
}
