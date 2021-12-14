package com.bbp.bbptest.google.model;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CampaignDto {
    private long id;
    private String name;
    private String resourceName;
    private String status;
    private String servingStatus;
    private String adServingOptimizationStatus;
    private String advertisingChannelType;
    private String advertisingChannelSubType;
    private String startDate;
    private String endDate;
    private String experimentType;
    private String biddingStrategyType;
    private NetworkSettings networkSettings;
    private ManualCpc manualCpc;
//    private ManualCpm manualCpm;
    private GeoTargetTypeSetting geoTargetTypeSetting;
    private String paymentMode;
    private String campaignBudget;
    private List<CustomerParameter> urlCustomParameters;
    private AppCampaignSetting appCampaignSetting;
    private String trackingUrlTemplate;
    private String finalUrlSuffix;

    @Builder
    @Getter
    public static class NetworkSettings {
        private boolean targetGoogleSearch;
        private boolean targetSearchNetwork;
        private boolean targetContentNetwork;
        private boolean targetPartnerSearchNetwork;
    }

    @Builder
    @Getter
    public static class ManualCpc {
        private boolean enhancedCpcEnabled;
    }

    @Getter
    @Builder
    public static class ManualCpm {
    }



    @Builder
    @Getter
    public static class GeoTargetTypeSetting {
        private String positiveGeoTargetType;
        private String negativeGeoTargetType;
    }

    @Getter
    @Builder
    public static class CustomerParameter {
        private String key;
        private String value;
    }

    @Getter
    @Builder
    public static class AppCampaignSetting {
        private String biddingStrategyGoalType;
        private String appStore;
        private String appId;
    }

}

