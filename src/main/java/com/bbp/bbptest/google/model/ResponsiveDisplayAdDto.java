package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponsiveDisplayAdDto {
    private List<AdImageAssetDto> marketingImages;
    private List<AdImageAssetDto> squareMarketingImages;
    private List<AdTextAssetDto> headlines;
    private AdTextAssetDto longHeadline;
    private List<AdTextAssetDto> descriptions;
    private List<YoutubeVideosAssetDto> youtubeVideos;
    private String formatSetting;
    private String businessName;
    private boolean allowFlexibleColor;
    private ControlSpecDto controlSpec;
    private String callToActionText;

}
