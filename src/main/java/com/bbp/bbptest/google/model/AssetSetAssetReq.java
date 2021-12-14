package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssetSetAssetReq {
    private long customerId;
    private long assetId;
    private long assetSetId;
}
