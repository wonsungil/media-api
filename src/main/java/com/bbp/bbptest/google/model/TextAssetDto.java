package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TextAssetDto extends AssetDto {
    private TextAsset textAsset;

    @Getter
    @Builder
    public static class TextAsset {
        private String text;
    }
}
