package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.CampaignBudget;
import com.google.ads.googleads.v9.services.CampaignBudgetServiceClient;
import com.google.ads.googleads.v9.utils.ResourceNames;

@Service(value = "ggCampaignBudgetService")
public class CampaignBudgetService {

    private final GoogleAdsClient googleAdsClient;

    public CampaignBudgetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public void getCampaignBudgets() {

    }

    public CampaignBudget getCampaignBudget(long customerId, long campaignBudgetId) {
        try (CampaignBudgetServiceClient client =
                googleAdsClient.getLatestVersion().createCampaignBudgetServiceClient()) {
            return client.getCampaignBudget(ResourceNames.campaignBudget(customerId,campaignBudgetId));
        }
    }
}
