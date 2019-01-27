package cn.newtol.springbootlogin.repository;

import cn.newtol.springbootlogin.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:51 2019/1/25
 * @params:
 */
public interface RegisterRepository extends JpaRepository<UserInfo,String> {
    /**
    * @Author:
    * @Description: 通过邮件查询是否存在
    * @Date: Created in 14:56 2019/1/26
    * @Param:
    * @return:
    */
    UserInfo findUserInfoByEmail(String email);
    /**
    * @Author:
    * @Description: 通过电话号码查询是否存在
    * @Date: Created in 14:57 2019/1/26
    * @Param:
    * @return:
    */
    UserInfo findUserInfoByPhoneNum(String phoneNum);
}
