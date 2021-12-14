package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.CampaignFeed;
import com.google.ads.googleads.v9.services.CampaignFeedServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.utils.ResourceNames;

@Service(value = "ggCampaignFeed")
public class CampaignFeedService {

    private final GoogleAdsClient googleAdsClient;

    public CampaignFeedService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public void getAllCampaignFeed(long customerId) {
        String query = "SELECT campaign_feed.resource_name, campaign_feed.status, campaign_feed.placeholder_types, campaign_feed.matching_function.right_operands, campaign_feed.matching_function.operator, campaign_feed.matching_function.left_operands, campaign_feed.matching_function.function_string, campaign_feed.feed, campaign_feed.campaign FROM campaign_feed";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            System.out.println("####");
            for (GoogleAdsRow row : response.iterateAll()) {
                System.out.println(row.getCampaignFeed());
            }
            System.out.println("####");
        }
    }

    public CampaignFeed getCampaignFeed(long customerId, long campaignId, long feedId) {
        String rn = ResourceNames.campaignFeed(customerId, campaignId, feedId);
        try (CampaignFeedServiceClient client = googleAdsClient.getLatestVersion().createCampaignFeedServiceClient()) {
            CampaignFeed campaignFeed = client.getCampaignFeed(rn);
            return campaignFeed;
        }
    }
}
