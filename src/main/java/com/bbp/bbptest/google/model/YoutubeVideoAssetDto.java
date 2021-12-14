package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class YoutubeVideoAssetDto extends AssetDto {

    private YoutubeVideoAsset youtubeVideoAsset;

    @Getter
    @Builder
    public static class YoutubeVideoAsset {
        private String youtubeVideoId;
        private String youtubeVideoTitle;
    }
}
