package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AssetSetReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.enums.AssetSetTypeEnum.AssetSetType;
import com.google.ads.googleads.v9.resources.AssetSet;
import com.google.ads.googleads.v9.services.AssetSetOperation;
import com.google.ads.googleads.v9.services.AssetSetServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateAssetSetsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggAssetSetService")
public class AssetSetService {

    private final GoogleAdsClient googleAdsClient;

    public AssetSetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public String create(AssetSetReq param) {
        try (AssetSetServiceClient client = googleAdsClient.getLatestVersion().createAssetSetServiceClient()) {

            AssetSet assetSet = AssetSet.newBuilder()
                                        .setName(param.getName())
                                        .setType(AssetSetType.valueOf(param.getType()))
                                        .build();

            AssetSetOperation operation = AssetSetOperation.newBuilder()
                                                        .setCreate(assetSet)
                                                        .build();
            MutateAssetSetsResponse response = client.mutateAssetSets(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }

    public List<AssetSet> getAssetSets(long customerId) {
        List<AssetSet> assetSetList = new ArrayList<>();
        String query = "SELECT asset_set.type, asset_set.resource_name, asset_set.status, asset_set.merchant_center_feed.merchant_id, asset_set.name, asset_set.merchant_center_feed.feed_label FROM asset_set";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                assetSetList.add(row.getAssetSet());
            }
            return assetSetList;
        }
    }

    public AssetSet getAssetSet(long customerId, long assetSetId) {
        String query = "SELECT asset_set.type, asset_set.resource_name, asset_set.status, asset_set.merchant_center_feed.merchant_id, asset_set.name, asset_set.merchant_center_feed.feed_label "
                       + " FROM asset_set "
                       + " WHERE asset_set.resource_name = '" + ResourceNames.assetSet(customerId, assetSetId) + "'";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            return response.iterateAll().iterator().next().getAssetSet();
        }
    }
}