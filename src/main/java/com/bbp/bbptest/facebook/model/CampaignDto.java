package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CampaignDto {

    private long id;
    private long adAccountId;
    private long adStrategyId;
    private String name;
    private String status;
    private String objective;

    @Override
    public String toString() {
        return "CampaignDto{" +
               "id=" + id +
               ", adAccountId=" + adAccountId +
               ", adStrategyId=" + adStrategyId +
               ", name='" + name + '\'' +
               ", status='" + status + '\'' +
               ", objective='" + objective + '\'' +
               '}';
    }
}
