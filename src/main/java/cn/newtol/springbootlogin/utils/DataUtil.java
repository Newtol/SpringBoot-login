package cn.newtol.springbootlogin.utils;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 23:26 2019/1/25
 * @params:
 */
public class DataUtil {
    /**
    * @Author:
    * @Description: 加密用户密码
    * @Date: Created in 23:27 2019/1/25
    * @Param:
    * @return:
    */
    public static String getPassWord(String str){
        return EncryptUtil.sha1(EncryptUtil.MD5(str+"cheer_vote"));
    }

    /**
    * @Author:
    * @Description: 初始化用户的UserId
    * @Date: Created in 23:39 2019/1/25
    * @Param:
    * @return:
    */

    public static String getUserId(String string){
        long current=System.currentTimeMillis();
        return EncryptUtil.MD5(string+current+"cheer_vote");
    }

    /**
    * @Author:
    * @Description: 获取随机生成的字符串
    * @Date: Created in 15:16 2019/1/26
    * @Param:
    * @return:
    */
    public static String getRandomString(){
        String str = RandomStringUtils.randomAlphanumeric(6);
        return str;
    }




}
