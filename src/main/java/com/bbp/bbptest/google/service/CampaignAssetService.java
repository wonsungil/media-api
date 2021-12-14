package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.CampaignAsset;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient.SearchPagedResponse;

@Service(value = "ggCampaignAssetService")
public class CampaignAssetService {
    private final GoogleAdsClient googleAdsClient;

    public CampaignAssetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<CampaignAsset> getCampaignAssets(long customerId) {
        String query = "SELECT campaign_asset.status, campaign_asset.resource_name, campaign_asset.field_type, campaign_asset.campaign, campaign_asset.asset FROM campaign_asset";
        try (GoogleAdsServiceClient client =
                     googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            // Issues the search request.
            SearchPagedResponse searchPagedResponse = client.search(String.valueOf(customerId), query);

            List<CampaignAsset> campaignAssetList = new ArrayList<>();

            for (GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll()) {
                CampaignAsset campaignAsset = googleAdsRow.getCampaignAsset();
                campaignAssetList.add(campaignAsset);
            }
            return campaignAssetList;
        }
    }
}
