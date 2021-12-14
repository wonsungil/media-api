package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageAssetReq {
    private String imageUrl;
    private long customerId;
}
