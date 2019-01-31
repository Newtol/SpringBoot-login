package cn.newtol.springbootlogin.services;

import cn.newtol.springbootlogin.entity.PassWordDO;
import cn.newtol.springbootlogin.entity.ResultVO;
import cn.newtol.springbootlogin.entity.ValidCodeDO;
import cn.newtol.springbootlogin.myEnum.ResultEnum;
import cn.newtol.springbootlogin.repository.PassWordRepository;
import cn.newtol.springbootlogin.utils.EncryptUtil;
import cn.newtol.springbootlogin.utils.ResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:15 2019/1/29
 * @params:
 */

@Service
@Component
public class PassWordServiceImpl implements PassWordService {

    @Resource
    private ValidCodeService validCodeService;


    @Resource
    private PassWordRepository passWordRepository;

    /**
     * 加密用户的密码
     * @param passWord：用户设置的密码
     * @return
     */
    @Override
    public String encryptPassWord(String passWord){
        // 对用户密码进行加密后返回
        return EncryptUtil.sha1(EncryptUtil.MD5(passWord+"cheer_vote"));
    }


    /**
     * 找回密码
     *
     * @param passWordDO
     * @return
     */
    @Override
    public ResultVO retrievePassWord(HttpServletRequest request,PassWordDO passWordDO) {

        ValidCodeDO validCodeDO = new ValidCodeDO();
        String validCode = passWordDO.getValidCode();
        String phoneNum = passWordDO.getPhoneNum();
        String email = passWordDO.getEmail();
        validCodeDO.setValidCode(validCode);

        // 判断输入的账户是否为空·
        if(phoneNum == null && email == null){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }

        if(phoneNum != null){
            validCodeDO.setPhoneNum(phoneNum);
        } else if(email != null){
            validCodeDO.setEmail(email);
        }

        // 判断验证码是否正确
        boolean re = validCodeService.isValidCode(request,validCodeDO);
        if(!re){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }

        // 加密新密码
        String pwd = encryptPassWord(passWordDO.getNewPassWord());

        // 更新密码
        Integer res = null;
        if(phoneNum != null){
            res = passWordRepository.resetPasswordByPhoneNum(pwd,phoneNum);
        } else if(email != null){
            res = passWordRepository.resetPassWordByEmail(pwd,email);
        }
        // 判断是否更新成功
        if (res == 0){
            return ResultUtil.error(ResultEnum.ResetPassWord_ERROR);
        }
        return ResultUtil.success();

    }


}
