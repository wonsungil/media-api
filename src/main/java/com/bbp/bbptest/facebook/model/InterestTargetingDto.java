package com.bbp.bbptest.facebook.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InterestTargetingDto {

    private String id;
    private String name;
    private long audienceSizeLowerBound;
    private long audienceSizeUpperBound;
    private long audienceSize;
    private List<String> path;
    private String description;
    private String topic;
}
