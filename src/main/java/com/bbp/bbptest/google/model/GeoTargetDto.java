package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GeoTargetDto {
    private GeoTargetConstant geoTargetConstant;
    private String locale;
    private String reach;
    private String searchTerm;

    @Getter
    @Builder
    public static class GeoTargetConstant {
        private long id;
        private String resourceName;
        private String status;
        private String name;
        private String countryCode;
        private String targetType;
        private String canonicalName;
    }
}
