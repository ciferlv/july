package com.july.controller;

import com.july.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.july.entity.User;
/**
 * Created by Crow on 2016/6/3.
 */
@Controller
public class FollowController {
    UserService userService;
    @RequestMapping(value="/followControlInJson",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String followControlInJson( @RequestParam(value="type") Integer type,
                                       @RequestParam(value="aim_user_email") String aim_user_email )
    {
        System.out.println(type);
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        User current_user = userService.getSessionUser();
        User aim_user = userService.getUserByEmail(aim_user_email);
        boolean success = false;
        if(type==1) //1代表关注
        {
            userService.addFollowing(current_user,aim_user);
            userService.addFollower(aim_user,current_user);
            success = true;
            jo.addProperty("success",success);
        }
        else        //取消关注
        {
            userService.removeFollowing(current_user,aim_user);
            userService.removeFollower(aim_user,current_user);
            success = true;
            jo.addProperty("success",success);
            return gson.toJson(jo);
        }
        return gson.toJson(jo);
    }
}
