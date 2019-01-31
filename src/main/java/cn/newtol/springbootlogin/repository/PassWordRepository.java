package cn.newtol.springbootlogin.repository;

import cn.newtol.springbootlogin.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 公众号：Newtol
 * @Description: 密码管理
 * @Date: Created in 21:14 2019/1/31
 * @params:
 */

public interface PassWordRepository extends JpaRepository<UserInfo,String> {

    /**
     * 根据电话重置密码
     * @param password
     * @param phone_num
     * @return
     */
    @Query(value = "update userinfo set pass_word = ? where phone_num = ? ",nativeQuery = true)
    @Modifying
    @Transactional(rollbackFor = Exception.class )
    Integer resetPasswordByPhoneNum(String password,String phone_num);

    /**
     * 根据邮箱重置密码
     * @param password
     * @param email
     * @return
     */
    @Query(value = "update userinfo set pass_word = ? where email = ? ",nativeQuery = true)
    @Modifying
    @Transactional(rollbackFor = Exception.class )
    Integer resetPassWordByEmail(String password,String email);
}
