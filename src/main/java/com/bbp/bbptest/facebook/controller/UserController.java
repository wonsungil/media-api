package com.bbp.bbptest.facebook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbp.bbptest.facebook.model.UserDto;
import com.bbp.bbptest.facebook.service.UserService;
import com.facebook.ads.sdk.User;

@RequestMapping(value = "/facebook/user")
@Controller
public class UserController {

    private UserService fbUserService;

    public UserController(UserService fbUserService) {
        this.fbUserService = fbUserService;
    }

    @RequestMapping(value = "/me")
    public ResponseEntity<UserDto> me() throws Exception {
        User user = fbUserService.me();

        UserDto userDto =
                UserDto.builder()
                        .id(Long.parseLong(user.getFieldId()))
                        .name(user.getFieldName())
                        .build();
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }



}
