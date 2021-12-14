package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CampaignReq {
    private Long campaignId;
    private Long customerId;
    private String name;
    private String channelType;
    private String channelSubType;
    private Long amount;
    private String startDate;
    private String endDate;
    private String campaignBudgetName;
    private NetworkSetting networkSetting;
    private String paymentMode;
    private String biddingStrategyType;
    private long biddingStrategyId;
    private String biddingType;
    private AppCampaignSetting appCampaignSetting;
    private long targetCpa;

    @Getter
    @Builder
    public static class NetworkSetting {
        private boolean targetGoogleSearch;
        private boolean targetSearchNetwork;
        private boolean targetContentNetwork;
        private boolean targetPartnerSearchNetwork;
    }

    @Getter
    @Builder
    public static class AppCampaignSetting {
        private String biddingStrategyGoalType;
        private String appStore;
        private String appId;
    }
}
