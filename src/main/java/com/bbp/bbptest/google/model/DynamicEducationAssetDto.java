package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DynamicEducationAssetDto extends AssetDto {

    private PolicySummary policySummary;
    private DynamicEducationAsset dynamicEducationAsset;

    @Getter
    @Builder
    public static class PolicySummary {
        private String reviewStatus;
    }

    @Getter
    @Builder
    public static class DynamicEducationAsset {
        private String programId;
        private String locationId;
        private String programName;
        private String subject;
        private String programDescription;
        private String schoolName;
        private String address;
        private String imageUrl;
    }
}
