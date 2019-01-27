package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.LoginDO;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.repository.LoginRepository;
import cn.newtol.springbootlogin.utils.DataUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Description:
 * @Date: Created in 18:33 2019/1/25
 */
@Service
@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;


    @Override
    public ResultVO login(LoginDO loginDO) {
        UserInfo userInfo = null;
        String pwd = DataUtil.getPassWord(loginDO.getPassWord());
        // 选择登录方式
        if(loginDO.getEmail() != null){
            userInfo = loginRepository.findUserInfoByEmailAndPassWord(loginDO.getEmail(),pwd);
        }
        else if (loginDO.getPhoneNum() != null){
            userInfo = loginRepository.findUserInfoByPhoneNumAndPassWord(loginDO.getPhoneNum(),pwd);
        }

        // 判断是否登录成功
        if(userInfo != null){
            return ResultUtil.success(userInfo.getUserId());
        }else{
            return ResultUtil.error(ResultEnum.PASSWOED_ERROR);
        }
    }
}
