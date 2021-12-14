package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ControlSpecDto {
    private boolean enableAssetEnhancements;
    private boolean enableAutogenVideo;
}
