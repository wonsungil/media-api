package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConversionActionReq {
    private long customerId;

    private String status;
    private String conversionActionType;;
    private boolean primaryForGoal;
    private String category;
    private ValueSetting valueSetting;
    private String countingType;
    private String attributionModelSetting;
    private String name;
    private boolean includeInConversionsMetric;
    private long clickThroughLookBackWindowDays;
    private long viewThroughLookBackWindowDays;
    private long phoneCallDurationSeconds;
    private String appId;

    @Getter
    @Builder
    public static class ValueSetting {
        private double defaultValue;
        private String defaultCurrencyCode;
        private boolean alwaysUseDefaultValue;
    }
}
