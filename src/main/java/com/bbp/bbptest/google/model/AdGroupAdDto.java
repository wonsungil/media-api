package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdGroupAdDto {
    private String resourceName;
    private String status;
    private AdDto ad;
    private String adGroup;
}
