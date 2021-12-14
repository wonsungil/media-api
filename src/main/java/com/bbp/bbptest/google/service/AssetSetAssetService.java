package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AssetSetAssetReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.AssetSetAsset;
import com.google.ads.googleads.v9.services.AssetSetAssetOperation;
import com.google.ads.googleads.v9.services.AssetSetAssetServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateAssetSetAssetsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggAssetSetAssetService")
public class AssetSetAssetService {

    private GoogleAdsClient googleAdsClient;

    public AssetSetAssetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public String create(AssetSetAssetReq param) {
        try (AssetSetAssetServiceClient client =
                googleAdsClient.getLatestVersion().createAssetSetAssetServiceClient()) {
            AssetSetAsset assetSetAsset =
                    AssetSetAsset.newBuilder()
                    .setAsset(ResourceNames.asset(param.getCustomerId(), param.getAssetId()))
                    .setAssetSet(ResourceNames.assetSet(param.getCustomerId(), param.getAssetSetId()))
                    .build();

            AssetSetAssetOperation operation = AssetSetAssetOperation.newBuilder()
                                                                .setCreate(assetSetAsset)
                                                                .build();
            MutateAssetSetAssetsResponse response = client.mutateAssetSetAssets(
                    String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }

    public AssetSetAsset getAssetSetAsset(long customerId, long assetSetId, long assetSetAssetId) {
        String query = "SELECT asset_set_asset.status, asset_set_asset.resource_name, asset_set_asset.asset_set, asset_set_asset.asset"
                       + " FROM asset_set_asset"
                       + " WHERE asset_set_asset.resource_name = '" + ResourceNames.assetSetAsset(customerId, assetSetId, assetSetAssetId) + "'";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            return response.iterateAll().iterator().next().getAssetSetAsset();
        }
    }
}
