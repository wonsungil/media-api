package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponsiveDisplayAdReq {
    private List<String> marketingImages;
    private List<String> squareMarketingImages;
    private List<String> logoImages;
    private List<String> squareLogoImages;
    private List<String> headlines;
    private String longHeadline;
    private List<String> descriptions;
    private List<String> youtubeVideos;
    private String formatSetting;
    private ResponsiveDisplayAdControlSpecReq controlSpecReq;
    private String businessName;
    private String mainColor;
    private String accentColor;
    private boolean allowFlexibleColor;
    private String callToActionText;
    private String pricePrefix;
    private String promoText;

    @Getter
    @Builder
    public static class ResponsiveDisplayAdControlSpecReq {
        private boolean enableAssetEnhancements;
        private boolean enableAutogenVideo;
    }
}
