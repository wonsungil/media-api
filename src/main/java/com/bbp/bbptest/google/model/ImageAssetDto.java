package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ImageAssetDto extends AssetDto {
    private ImageAsset imageAsset;

    @Getter
    @Builder
    public static class ImageAsset {
        private String mimeType;
        private long fileSize;
        private FullSize fullSize;

        @Getter
        @Builder
        public static class FullSize {
            private int heightPixels;
            private int widthPixels;
            private String url;
        }
    }
}
