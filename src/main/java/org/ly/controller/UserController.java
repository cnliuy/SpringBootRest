package org.ly.controller;

import java.util.List;
import org.ly.pojo.UserInfo;
import org.ly.repository.UserInfoRepository;
import org.ly.utils.ResultMsg;
import org.ly.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired  
    private UserInfoRepository userInfoRepositoy;  
      
    @RequestMapping("getuser")  
    public Object getUser(int id) {  
    	System.out.println("id："+id);
        UserInfo userEntity = userInfoRepositoy.findUserInfoById(id);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);  
        return resultMsg;  
    }  
      
    @RequestMapping("getusers")  
    public Object getUsers(String role)   {  
        List<UserInfo> userEntities = userInfoRepositoy.findUserInfoByRole(role);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntities);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("adduser")  
    public Object addUser(@RequestBody UserInfo userEntity)      {  
        userInfoRepositoy.save(userEntity);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("updateuser")  
    public Object updateUser(@RequestBody UserInfo userEntity)      {  
        UserInfo user = userInfoRepositoy.findUserInfoById(userEntity.getId());  
        if (user != null)  
        {  
            user.setName(userEntity.getName());  
            userInfoRepositoy.save(user);  
        }  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("deleteuser")  
    public Object deleteUser(int id)      {  
        userInfoRepositoy.delete(id);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
}
