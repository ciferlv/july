package com.july.service;

import com.july.controller.form.UserCreateForm;
import com.july.entity.User;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

/**
 * Created by kuangjun on 2016/5/7.
 */

public interface UserService {


    User getUserByEmail(String email);

    User create(UserCreateForm form);

    void createVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    User getOAuthUser(Principal principal, String type);

    //重新登录
    void reloadSessionUser(User user);

    //获得当前登陆用户
    User getSessionUser();

    User getOAuthUserByAccount(String type, String ob);

    void update(User user);

    List<User> getUserByNickName(String nickName);

    User getUserById(BigInteger id);
}
