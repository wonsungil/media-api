package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private long id;
    private String name;
}
