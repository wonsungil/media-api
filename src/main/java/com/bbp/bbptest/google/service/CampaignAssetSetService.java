package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.CampaignAssetSetReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.CampaignAssetSet;
import com.google.ads.googleads.v9.services.CampaignAssetSetOperation;
import com.google.ads.googleads.v9.services.CampaignAssetSetServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateCampaignAssetSetsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggCampaignAssetSetService")
public class CampaignAssetSetService {
    
    private GoogleAdsClient googleAdsClient;

    public CampaignAssetSetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }
    
    public String create(CampaignAssetSetReq param) {
        try (CampaignAssetSetServiceClient client = googleAdsClient.getLatestVersion()
                                                                   .createCampaignAssetSetServiceClient()) {

            CampaignAssetSet campaignAssetSet =
                    CampaignAssetSet.newBuilder()
                                    .setCampaign(ResourceNames.campaign(param.getCustomerId(), param.getCampaignId()))
                                    .setAssetSet(ResourceNames.assetSet(param.getCustomerId(), param.getAssetSetId()))
                                    .build();

            CampaignAssetSetOperation operation = CampaignAssetSetOperation.newBuilder()
                                                                  .setCreate(campaignAssetSet)
                                                                  .build();
            MutateCampaignAssetSetsResponse response =
                    client.mutateCampaignAssetSets(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }

    public CampaignAssetSet getCampaignAssetSet(long customerId, long campaignId, long assetSetId) {
        String query = "SELECT campaign_asset_set.status, campaign_asset_set.resource_name, campaign_asset_set.campaign, campaign_asset_set.asset_set"
                       + " FROM campaign_asset_set "
                       + " WHERE campaign_asset_set.resource_name = '" + ResourceNames.campaignAssetSet(customerId, campaignId, assetSetId) + "'";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            return response.iterateAll().iterator().next().getCampaignAssetSet();
        }

    }

    public List<CampaignAssetSet> getCampaignAssetSets(long customerId) {
        List<CampaignAssetSet> caaList = new ArrayList<>();
        String query = "SELECT campaign_asset_set.status, campaign_asset_set.resource_name, campaign_asset_set.campaign, campaign_asset_set.asset_set"
                       + " FROM campaign_asset_set";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                caaList.add(row.getCampaignAssetSet());
            }
            return caaList;
        }
    }
}
