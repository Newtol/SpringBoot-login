package cn.newtol.springbootlogin.repository;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.LoginDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 公众号：Newtol
 * @Description: 用户登录
 * @Date: Created in 18:35 2019/1/25
 */
public interface LoginRepository extends JpaRepository<UserInfo,String> {
    /**
    * @Author:
    * @Description: 用户电话登录
    * @Date: Created in 19:28 2019/1/25
    * @Param:
    * @return:
    */
    UserInfo findUserInfoByPhoneNumAndPassWord(String phone,String passWord);

   /**
   * @Author:
   * @Description: 用户邮箱登录
   * @Date: Created in 19:27 2019/1/25
   * @Param:
   * @return:
   */
    UserInfo findUserInfoByEmailAndPassWord(String email,String passWord);
}
