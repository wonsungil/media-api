package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignAssetSetDto {
    private String resourceName;
    private String campaign;
    private String assetSet;
    private String status;
}
