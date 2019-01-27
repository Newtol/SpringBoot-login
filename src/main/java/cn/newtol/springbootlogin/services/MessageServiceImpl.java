package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.HttpClientResult;
import cn.newtol.springbootlogin.utils.EncryptUtil;
import cn.newtol.springbootlogin.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    @Autowired
    private JavaMailSender mailSender;

    /**
    * @Author:
    * @Description:
    * @Date: Created in 16:51 2019/1/26
    * @Param: 通过邮件发送
    * @return:
    */

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    @Value("${defaultKey.SMSUrl}")
    private String url;

    @Value("${defaultKey.SMSAccountSid}")
    private  String accountSid;

    @Value("${defaultKey.SMSAuthToken}")
    private  String authToken;

    @Override
    public void sendSMS(String to, String content,String templateid) throws Exception {
        HashMap<String,String> map= new HashMap();
        Date today=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmms");
        String time=f.format(today);
        String sig = EncryptUtil.md5(accountSid+authToken+time);
        map.put("accountSid",accountSid);
        map.put("templateid",templateid);
        map.put("param",content);
        map.put("to",to);
        map.put("timestamp",time);
        map.put("sig",sig);
        String jsonData = JSON.toJSONString(map);
        HttpClientResult httpClientResult = HttpClientUtil.doPostForJson(url,map,jsonData);
        System.out.println(JSON.toJSONString(httpClientResult));

    }
}
