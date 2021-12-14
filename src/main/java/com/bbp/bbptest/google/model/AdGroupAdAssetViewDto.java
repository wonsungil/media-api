package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdGroupAdAssetViewDto {
    private String resourceName;
    private PolicySummary policySummary;
    private boolean enabled;
    private String performanceLabel;
    private String adGroupAd;
    private String asset;

    @Getter
    @Builder
    public static class PolicySummary {
        private String reviewStatus;
    }
}
