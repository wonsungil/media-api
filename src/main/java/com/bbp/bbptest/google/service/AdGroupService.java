package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AdGroupReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.enums.AdGroupStatusEnum.AdGroupStatus;
import com.google.ads.googleads.v9.enums.AdGroupTypeEnum.AdGroupType;
import com.google.ads.googleads.v9.resources.AdGroup;
import com.google.ads.googleads.v9.services.AdGroupOperation;
import com.google.ads.googleads.v9.services.AdGroupServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateAdGroupsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggAdGroupService")
public class AdGroupService {

    private final GoogleAdsClient googleAdsClient;

    public AdGroupService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public AdGroup create(AdGroupReq param) {
        AdGroup createReq =
                AdGroup.newBuilder()
                       .setName(param.getName())
                       .setStatus(AdGroupStatus.valueOf(param.getStatus()))
                       .setCampaign(ResourceNames.campaign(param.getCustomerId(), param.getCampaignId()))
                       .setType(AdGroupType.valueOf(param.getType()))
                       .setCpcBidMicros(param.getCpcBidMicros())
                       .build();

        AdGroup created = null;
        AdGroupOperation adGroupOperation = AdGroupOperation.newBuilder().setCreate(createReq).build();

        try (AdGroupServiceClient adGroupServiceClient =
                googleAdsClient.getLatestVersion().createAdGroupServiceClient()) {
            MutateAdGroupsResponse response =
                    adGroupServiceClient.mutateAdGroups(String.valueOf(param.getCustomerId()), ImmutableList
                            .of(adGroupOperation));
            created = response.getResults(0).getAdGroup();
        }

        return created;
    }

    public AdGroup getAdGroup(long customerId, long adGroupId) {
        AdGroup adGroup = null;
        try (AdGroupServiceClient adGroupServiceClient =
                googleAdsClient.getLatestVersion().createAdGroupServiceClient()) {
            adGroup = adGroupServiceClient.getAdGroup(ResourceNames.adGroup(customerId, adGroupId));
        }
        return adGroup;
    }

    public List<AdGroup> getAdGroups(long customerId) {

        List<AdGroup> adGroups = new ArrayList<>();

        String query = ""
                       + "SELECT "
                       + "  ad_group.type, ad_group.url_custom_parameters, ad_group.tracking_url_template"
                       + "  , ad_group.targeting_setting.target_restrictions, ad_group.target_roas"
                       + "  , ad_group.target_cpm_micros, ad_group.target_cpa_micros, ad_group.status"
                       + "  , ad_group.resource_name, ad_group.percent_cpc_bid_micros, ad_group.name"
                       + "  , ad_group.labels, ad_group.id, ad_group.final_url_suffix"
                       + "  , ad_group.explorer_auto_optimizer_setting.opt_in, ad_group.excluded_parent_asset_field_types"
                       + "  , ad_group.effective_target_roas_source, ad_group.effective_target_roas"
                       + "  , ad_group.effective_target_cpa_source, ad_group.effective_target_cpa_micros"
                       + "  , ad_group.display_custom_bid_dimension, ad_group.cpv_bid_micros"
                       + "  , ad_group.cpm_bid_micros, ad_group.cpc_bid_micros, ad_group.campaign"
                       + "  , ad_group.base_ad_group, ad_group.ad_rotation_mode "
                       + "FROM ad_group "
                       + "WHERE customer.id = " + customerId;

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);

            for(GoogleAdsRow row : response.iterateAll()) {
                adGroups.add(row.getAdGroup());
            }
            return adGroups;
        }
    }
}
