package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssetSetAssetDto {
    private String resourceName;
    private String assetSet;
    private String asset;
    private String status;
}
