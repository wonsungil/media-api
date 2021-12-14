package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdReq {
    private List<String> finalUrls;
    private List<FinalAppUrlReq> finalAppUrls;
    private List<String> finalMobileUrls;
    private List<CustomParameter> urlCustomParameters;
    private String devicePreference;
    private List<String> urlCollections;
    private String trackingUrlTemplate;
    private String finalUrlSuffix;
    private String displayUrl;
    private String name;
    private int adType;
    private long customerId;

    /**
     * 19 responsive display ad
     */
    private ResponsiveDisplayAdReq responsiveDisplayAdReq;

    private AppAdReq appAdReq;
    private AppEngagementAdReq appEngagementAdReq;
    private AppPreRegistrationAdReq appPreRegistrationAdReq;

    @Getter
    @Builder
    public static class FinalAppUrlReq {
        private String osType;
        private String url;
    }

    @Getter
    @Builder
    public static class CustomParameter {
        private String key;
        private String value;
    }

}
