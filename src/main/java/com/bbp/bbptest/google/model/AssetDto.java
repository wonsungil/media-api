package com.bbp.bbptest.google.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AssetDto {
    private long id;
    private String name;
    private String resourceName;
    private String type;
}
