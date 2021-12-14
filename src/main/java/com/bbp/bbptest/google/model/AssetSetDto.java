package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssetSetDto {
    private String resourceName;
    private String name;
    private String type;
    private String status;
}
