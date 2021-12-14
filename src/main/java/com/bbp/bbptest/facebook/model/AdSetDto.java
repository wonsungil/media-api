package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdSetDto {
    private Long adAccountId;
    private String name;
    private Long campaignId;
    private Long bidAmount;
    private String bidStrategy;
    private String billingEvent;
    private String dailyBudget;
    private String destinationType;
    private String startTime;
    private String endTime;
    private String optimizationGoal;
    private String promotionObject;
    private String sourceAdSetId;
    private String rfPredictionId;
    private String status;
    private String targeting;

    private String configuredStatus;
    private String effectiveStatus;
    private String id;

    @Override
    public String toString() {
        return "AdSetDto{" +
                "name='" + name + '\'' +
                ", configuredStatus='" + configuredStatus + '\'' +
                ", effectiveStatus='" + effectiveStatus + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
