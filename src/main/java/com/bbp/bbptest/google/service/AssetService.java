package com.bbp.bbptest.google.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.DynamicEducationAssetReq;
import com.bbp.bbptest.google.model.ImageAssetReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v7.utils.ResourceNames;
import com.google.ads.googleads.v9.common.DynamicEducationAsset;
import com.google.ads.googleads.v9.common.ImageAsset;
import com.google.ads.googleads.v9.enums.AssetTypeEnum.AssetType;
import com.google.ads.googleads.v9.resources.Asset;
import com.google.ads.googleads.v9.services.AssetOperation;
import com.google.ads.googleads.v9.services.AssetServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateAssetsResponse;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import com.google.protobuf.ByteString;

@Service(value = "ggAssetService")
public class AssetService {

    private final GoogleAdsClient googleAdsClient;

    public AssetService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<Asset> getAssets(long customerId, List<String> typeList) {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT "
                       + " asset.book_on_google_asset, asset.call_asset.ad_schedule_targets"
                       + ", asset.call_asset.call_conversion_action"
                       + ", asset.youtube_video_asset.youtube_video_title"
                       + ", asset.youtube_video_asset.youtube_video_id"
                       + ", asset.url_custom_parameters"
                       + ", asset.type"
                       + ", asset.tracking_url_template"
                       + ", asset.text_asset.text"
                       + ", asset.structured_snippet_asset.values"
                       + ", asset.structured_snippet_asset.header"
                       + ", asset.sitelink_asset.start_date"
                       + ", asset.sitelink_asset.link_text"
                       + ", asset.sitelink_asset.end_date"
                       + ", asset.sitelink_asset.description2"
                       + ", asset.sitelink_asset.description1"
                       + ", asset.sitelink_asset.ad_schedule_targets"
                       + ", asset.resource_name"
                       + ", asset.promotion_asset.start_date"
                       + ", asset.promotion_asset.redemption_start_date"
                       + ", asset.promotion_asset.redemption_end_date"
                       + ", asset.promotion_asset.promotion_target"
                       + ", asset.promotion_asset.percent_off"
                       + ", asset.promotion_asset.promotion_code"
                       + ", asset.promotion_asset.orders_over_amount.currency_code"
                       + ", asset.promotion_asset.orders_over_amount.amount_micros"
                       + ", asset.promotion_asset.occasion"
                       + ", asset.promotion_asset.money_amount_off.currency_code"
                       + ", asset.promotion_asset.money_amount_off.amount_micros"
                       + ", asset.promotion_asset.language_code"
                       + ", asset.promotion_asset.end_date"
                       + ", asset.promotion_asset.discount_modifier"
                       + ", asset.promotion_asset.ad_schedule_targets"
                       + ", asset.price_asset.type"
                       + ", asset.price_asset.price_qualifier"
                       + ", asset.price_asset.price_offerings"
                       + ", asset.price_asset.language_code"
                       + ", asset.policy_summary.review_status"
                       + ", asset.policy_summary.policy_topic_entries"
                       + ", asset.policy_summary.approval_status"
                       + ", asset.page_feed_asset.page_url"
                       + ", asset.page_feed_asset.labels"
                       + ", asset.name"
                       + ", asset.mobile_app_asset.start_date"
                       + ", asset.mobile_app_asset.link_text"
                       + ", asset.mobile_app_asset.end_date"
                       + ", asset.mobile_app_asset.app_store"
                       + ", asset.mobile_app_asset.app_id"
                       + ", asset.lead_form_asset.privacy_policy_url"
                       + ", asset.lead_form_asset.post_submit_headline"
                       + ", asset.lead_form_asset.post_submit_description"
                       + ", asset.lead_form_asset.post_submit_call_to_action_type"
                       + ", asset.lead_form_asset.headline"
                       + ", asset.lead_form_asset.fields"
                       + ", asset.lead_form_asset.desired_intent"
                       + ", asset.lead_form_asset.description"
                       + ", asset.lead_form_asset.delivery_methods"
                       + ", asset.lead_form_asset.custom_disclosure"
                       + ", asset.lead_form_asset.call_to_action_type"
                       + ", asset.lead_form_asset.call_to_action_description"
                       + ", asset.lead_form_asset.business_name"
                       + ", asset.lead_form_asset.background_image_asset"
                       + ", asset.image_asset.mime_type"
                       + ", asset.image_asset.full_size.width_pixels"
                       + ", asset.image_asset.full_size.height_pixels"
                       + ", asset.image_asset.full_size.url"
                       + ", asset.image_asset.file_size, asset.id"
                       + ", asset.hotel_callout_asset.text"
                       + ", asset.hotel_callout_asset.language_code"
                       + ", asset.final_urls"
                       + ", asset.final_url_suffix"
                       + ", asset.final_mobile_urls"
                       + ", asset.dynamic_education_asset.thumbnail_image_url"
                       + ", asset.dynamic_education_asset.subject"
                       + ", asset.dynamic_education_asset.similar_program_ids"
                       + ", asset.dynamic_education_asset.school_name"
                       + ", asset.dynamic_education_asset.program_name"
                       + ", asset.dynamic_education_asset.program_id"
                       + ", asset.dynamic_education_asset.program_description"
                       + ", asset.dynamic_education_asset.location_id"
                       + ", asset.dynamic_education_asset.ios_app_store_id"
                       + ", asset.dynamic_education_asset.ios_app_link"
                       + ", asset.dynamic_education_asset.image_url"
                       + ", asset.dynamic_education_asset.contextual_keywords"
                       + ", asset.dynamic_education_asset.android_app_link"
                       + ", asset.dynamic_education_asset.address"
                       + ", asset.callout_asset.start_date"
                       + ", asset.callout_asset.end_date"
                       + ", asset.callout_asset.callout_text"
                       + ", asset.callout_asset.ad_schedule_targets"
                       + ", asset.call_to_action_asset.call_to_action"
                       + ", asset.call_asset.phone_number"
                       + ", asset.call_asset.country_code"
                       + ", asset.call_asset.call_conversion_reporting_state "
                       + " FROM asset";

        if (!typeList.isEmpty()) {
            query += " WHERE asset.type IN (" + Strings.join(typeList, ',').toString()+")";
        }

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                assets.add(row.getAsset());
            }
            return assets;
        }
    }

    public String create(DynamicEducationAssetReq param) {
        System.out.println(param.toString());
        DynamicEducationAsset deAsset =
                DynamicEducationAsset.newBuilder()
                                     .setSchoolName(param.getSchoolName())
                                     .setAddress(param.getAddress())
                                     .setProgramName(param.getProgramName())
                                     .setSubject(param.getSubject())
                                     .setProgramDescription(param.getProgramDescription())
                                     .setProgramId(param.getProgramId())
                                     .setLocationId(param.getLocationId())
                                     .setImageUrl(param.getImageUrl())
                                     .setAndroidAppLink(param.getAndroidAppLink())
                                     .setIosAppLink(param.getIosAppLink())
                                     .setIosAppStoreId(param.getIosAppStoreId())
                                     .build();

        Asset asset = Asset.newBuilder().setDynamicEducationAsset(deAsset).build();
        AssetOperation operation = AssetOperation.newBuilder().setCreate(asset).build();

        try (AssetServiceClient client = googleAdsClient.getLatestVersion().createAssetServiceClient()) {
            MutateAssetsResponse response =
                    client.mutateAssets(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }

    public String create(ImageAssetReq param) throws IOException {
        byte[] imageData = ByteStreams.toByteArray(new URL(param.getImageUrl()).openStream());

        ImageAsset imageAsset = ImageAsset.newBuilder().setData(ByteString.copyFrom(imageData)).build();
        Asset asset = Asset.newBuilder().setType(AssetType.IMAGE).setImageAsset(imageAsset).build();

        AssetOperation operation = AssetOperation.newBuilder().setCreate(asset).build();

        try (AssetServiceClient client = googleAdsClient.getLatestVersion().createAssetServiceClient()) {
            MutateAssetsResponse response =
                    client.mutateAssets(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }

    public Asset getAsset(long customerId, long assetId) {
        return getAsset(ResourceNames.asset(customerId, assetId));
    }

    public Asset getAsset(String resourceName) {
        try(AssetServiceClient client = googleAdsClient.getLatestVersion().createAssetServiceClient()) {
            return client.getAsset(resourceName);
        }
    }
}
