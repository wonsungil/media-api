package com.bbp.bbptest.facebook.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BusinessDto {

    private long id;
    private String name;
    private List<AdAccountDto> ownedAdAccounts;
}
