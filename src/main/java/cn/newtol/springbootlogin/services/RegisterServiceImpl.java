package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.dao.UserInfo;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.repository.RegisterRepository;
import cn.newtol.springbootlogin.utils.DataUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:50 2019/1/25
 * @params:
 */

@Service
@Component
public class RegisterServiceImpl implements RegisterService{

    @Resource
    private RegisterRepository registerRepository;

    @Resource
    private
    ValidCodeService validCodeService;


    /**
     * @param userinfo
     * @Author:
     * @Description: 用户注册
     * @Date: Created in 21:49 2019/1/25
     * @Param:
     * @return: 返回注册的信息
     */
    @Override
    public ResultVO register(UserInfo userinfo) {
        // 判断用户是否已经存在
        Boolean isExist = isRegister(userinfo);
        if (isExist){
            return ResultUtil.error(ResultEnum.ACCOUNT_EXSIT);
        }

        // 验证验证码是否正确
        String account = null;
        String code = userinfo.getVaildCode();
        if(userinfo.getEmail() != null){
            account = userinfo.getEmail();
        }
        else if(userinfo.getPhoneNum() != null){
            account = userinfo.getPhoneNum();
        }
        boolean re = validCodeService.isValidCode(account,code);
        if(!re){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }

        //对用户密码进行加密
        String pwd = DataUtil.getPassWord(userinfo.getPassWord());
        userinfo.setPassWord(pwd);

        //随机产生用户的UserId
        String userId = DataUtil.getUserId(userinfo.getNickName());
        userinfo.setUserId(userId);

        //保存到数据库
        UserInfo userInfo = registerRepository.save(userinfo);
        return ResultUtil.success(userInfo.getUserId());
    }

    /**
     * @Author:
     * @Description: 判断用户是否已经注册
     * @Date: Created in 14:41 2019/1/26
     * @Param:
     * @return:
     */
    @Override
    public Boolean isRegister(UserInfo userInfo) {
        UserInfo result = null;
        // 判断用户的注册方式
        if(userInfo.getEmail() != null){
            result = registerRepository.findUserInfoByEmail(userInfo.getEmail());

        }
        if(userInfo.getPhoneNum() != null){
            result = registerRepository.findUserInfoByPhoneNum(userInfo.getPhoneNum());
        }

        if(result != null){
            return true;
        }
        return false;

    }

}
