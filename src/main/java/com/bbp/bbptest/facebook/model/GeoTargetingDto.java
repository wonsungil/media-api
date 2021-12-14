package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GeoTargetingDto {
    private String key;
    private String name;
    private String type;
    private String countryCode;
    private String countryName;
    private boolean supportsRegion;
    private boolean supportCity;
}
