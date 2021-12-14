package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignBudgetDto {
    private String resourceName;
    private String status;
    private String deliveryMethod;
    private String period;
    private String type;
    private long id;
    private String name;
    private long amountMicros;
    private long totalAmountMicros;
    private boolean explicitlyShared;
    private long referenceCount;
    private boolean hasRecommendedBudget;
    private long recommendedBudgetAmountMicros;
    private long recommendedBudgetEstimatedChangeWeeklyClicks;
    private long recommendedBudgetEstimatedChangeWeeklyCostMicros;
    private long recommendedBudgetEstimatedChangeWeeklyInteractions;
    private long recommendedBudgetEstimatedChangeWeeklyViews;
}
