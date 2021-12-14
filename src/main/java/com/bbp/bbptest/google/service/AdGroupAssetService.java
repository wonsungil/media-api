package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.AdGroupAsset;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;

@Service(value = "ggAdGroupAssetService")
public class AdGroupAssetService {

    private GoogleAdsClient googleAdsClient;

    public AdGroupAssetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<AdGroupAsset> getAdGroupAssets(long customerId) {
        List<AdGroupAsset> adGroupAssets = new ArrayList<>();
        String query = "SELECT ad_group_asset.status, ad_group_asset.resource_name, ad_group_asset.field_type, ad_group_asset.asset, ad_group_asset.ad_group FROM ad_group_asset";

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);

            for(GoogleAdsRow row : response.iterateAll()) {
                adGroupAssets.add(row.getAdGroupAsset());
            }
            return adGroupAssets;
        }
    }
}
