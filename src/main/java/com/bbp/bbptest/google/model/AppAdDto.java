package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppAdDto {
    private List<AdTextAssetDto> headlines;
    private List<AdTextAssetDto> descriptions;
    private List<AdImageAssetDto> images;
    private List<YoutubeVideosAssetDto> youtubeVideos;
}
