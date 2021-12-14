package com.bbp.bbptest.facebook.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdSetReq {
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
    private String promotedObject;
    private String sourceAdSetId;
    private String rfPredictionId;
    private String status;
    private Long ageMin;
    private Long ageMax;
    private List<Long> genders;

    @Override
    public String toString() {
        return "AdSetReq{" +
               "adAccountId=" + adAccountId +
               ", name='" + name + '\'' +
               ", campaignId=" + campaignId +
               ", bidAmount=" + bidAmount +
               ", bidStrategy='" + bidStrategy + '\'' +
               ", billingEvent='" + billingEvent + '\'' +
               ", dailyBudget='" + dailyBudget + '\'' +
               ", destinationType='" + destinationType + '\'' +
               ", startTime='" + startTime + '\'' +
               ", endTime='" + endTime + '\'' +
               ", optimizationGoal='" + optimizationGoal + '\'' +
               ", promotedObject='" + promotedObject + '\'' +
               ", sourceAdSetId='" + sourceAdSetId + '\'' +
               ", rfPredictionId='" + rfPredictionId + '\'' +
               ", status='" + status + '\'' +
               ", ageMin=" + ageMin +
               ", ageMax=" + ageMax +
               ", genders=" + genders +
               '}';
    }
}
