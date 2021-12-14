package com.bbp.bbptest.facebook.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.User;

@Service(value = "fbUserService")
public class UserService {
    private APIContext facebookApiContext;

    public UserService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public User getUser(String userId) throws Exception {
        List<String> fields = Arrays.asList("id", "name");
        User user = new User(userId, facebookApiContext).get().requestFields(fields).execute();
        return user;
    }


    public User getUser(long userId) throws Exception {
        return getUser(String.valueOf(userId));
    }

    public User me() throws Exception {
        return getUser("me");
    }

}
