package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignTargetingReq {
    private long customerId;
    private long campaignId;

    private List<Device> deviceList;

    @Getter
    @Builder
    public static class Device {
        private long bidModifier;
        private int type;
    }
}
