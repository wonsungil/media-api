package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.AdGroupAdAssetView;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;

@Service(value = "ggAdGroupAdAssetViewService")
public class AdGroupAdAssetViewService {

    private final GoogleAdsClient googleAdsClient;

    public AdGroupAdAssetViewService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<AdGroupAdAssetView> getAdGroupAdAssetViews(long customerId) {
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {

            List<AdGroupAdAssetView> adGroupAdAssetViewList = new ArrayList<>();
            String query = "SELECT ad_group_ad_asset_view.resource_name, ad_group_ad_asset_view.policy_summary"
                           + ", ad_group_ad_asset_view.performance_label, ad_group_ad_asset_view.enabled"
                           + ", ad_group_ad_asset_view.field_type, ad_group_ad_asset_view.asset"
                           + ", ad_group_ad_asset_view.ad_group_ad "
                           + " FROM ad_group_ad_asset_view";
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);

            for(GoogleAdsRow row : response.iterateAll()) {
                adGroupAdAssetViewList.add(row.getAdGroupAdAssetView());
            }
            return adGroupAdAssetViewList;
        }
    }
}
