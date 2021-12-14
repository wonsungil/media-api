package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInterestDto {
    private String userInterestParent;
    private String resourceName;
    private long userInterestId;
    private String taxonomyType;
    private String name;
    private String launchedToAll;
//    private String availabilities;
}
