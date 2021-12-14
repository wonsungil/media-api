package com.bbp.bbptest.facebook.model;

import java.util.List;

import com.facebook.ads.sdk.Ad.EnumExecutionOptions;
import com.google.auto.value.AutoValue.Builder;

import lombok.Getter;

@Builder
@Getter
public class AdReq {
    private Long adAccountId;
    private Long adSetId;
    private String adSetSpec;
    private String audienceId;
    private Long bidAmount;
    private String conversionDomain;
    private String creative;
    private String dateFormat;
    private Long displaySequence;
    private String draftAdgroupId;
    private Boolean engagementAudience;
    private List<EnumExecutionOptions> executionOptions;
    private Boolean includeDemolinkHashes;
    private Long priority;
    private String sourceAdId;
    private String status;
    private String name;
    private String trackingSpecs;

}
