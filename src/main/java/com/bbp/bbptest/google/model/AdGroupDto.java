package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdGroupDto {
    private long id;
    private String resourceName;
    private String status;
    private String type;
    private List<TargetRestrictionDto> targeting_setting;
    private String name;
    private String basedAdGroup;
    private String campaign;
    private long pcpBidMicros;
    private long cpmBidMicros;
    private long targetCpaMicros;
    private long cpvBidMicros;
    private long targetCpmMicros;
    private Long effectiveTargetCpaMicros;

    public static class TargetRestrictionDto {
        private String targetingDimension;
        private boolean bidOnly;
    }
}
