package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppAdReq {
    private String mandatoryAdText;
    private List<String> headlines;
    private List<String> descriptions;
    private List<String> images;
    private List<String> youtubeVideos;
    private List<String> html5MediaBundles;
}
