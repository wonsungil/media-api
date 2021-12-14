package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConversionActionDto {
    private String resourceName;
    private String status;
    private String type;
    private String category;
    private ValueSettings valueSettings;
    private String countingType;
    private AttributionModelSettings attributionModelSettings;
    private List<TagSnippet> tagSnippets;
    private String mobileAppVendor;
    private long id;
    private String name;
    private String ownerCustomer;
    private boolean includeInConversionMetric;
    private long clickThroughLookbackWindowDays;
    private long viewThroughLookbackWindowDays;
    private long phoneCallDurationSeconds;
    private String origin;
    private boolean primaryForGoal;

    @Getter
    @Builder
    public static class ValueSettings {
        private double defaultValue;
        private String defaultCurrencyCode;
        private boolean alwaysUseDefaultValue;
    }

    @Getter
    @Builder
    public static class AttributionModelSettings {
        private String attributionModel;
        private String dataDrivenModelStatus;
    }

    @Getter
    @Builder
    public static class TagSnippet {
        private String type;
        private String pageFormat;
        private String globalSiteTag;
        private String eventSnippet;
    }
}
