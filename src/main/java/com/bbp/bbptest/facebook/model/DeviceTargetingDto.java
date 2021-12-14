package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeviceTargetingDto {
    private String name;
    private String description;
    private String platform;
    private long audienceSize;
    private String type;
    private long audienceSizeLowerBound;
    private long audienceSizeUpperBound;
}
