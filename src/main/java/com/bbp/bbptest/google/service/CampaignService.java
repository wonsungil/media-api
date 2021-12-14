package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.config.Constants;
import com.bbp.bbptest.google.model.CampaignReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.lib.utils.FieldMasks;
import com.google.ads.googleads.v9.common.ManualCpc;
import com.google.ads.googleads.v9.common.ManualCpm;
import com.google.ads.googleads.v9.common.MaximizeConversions;
import com.google.ads.googleads.v9.common.TargetCpa;
import com.google.ads.googleads.v9.common.TargetSpend;
import com.google.ads.googleads.v9.enums.AdvertisingChannelSubTypeEnum.AdvertisingChannelSubType;
import com.google.ads.googleads.v9.enums.AdvertisingChannelTypeEnum.AdvertisingChannelType;
import com.google.ads.googleads.v9.enums.AppCampaignAppStoreEnum.AppCampaignAppStore;
import com.google.ads.googleads.v9.enums.AppCampaignBiddingStrategyGoalTypeEnum.AppCampaignBiddingStrategyGoalType;
import com.google.ads.googleads.v9.enums.BiddingStrategyTypeEnum.BiddingStrategyType;
import com.google.ads.googleads.v9.enums.BudgetDeliveryMethodEnum;
import com.google.ads.googleads.v9.enums.CampaignStatusEnum.CampaignStatus;
import com.google.ads.googleads.v9.enums.NegativeGeoTargetTypeEnum.NegativeGeoTargetType;
import com.google.ads.googleads.v9.enums.PaymentModeEnum.PaymentMode;
import com.google.ads.googleads.v9.enums.PositiveGeoTargetTypeEnum.PositiveGeoTargetType;
import com.google.ads.googleads.v9.resources.Campaign;
import com.google.ads.googleads.v9.resources.Campaign.AppCampaignSetting;
import com.google.ads.googleads.v9.resources.Campaign.GeoTargetTypeSetting;
import com.google.ads.googleads.v9.resources.CampaignBudget;
import com.google.ads.googleads.v9.services.CampaignBudgetOperation;
import com.google.ads.googleads.v9.services.CampaignBudgetServiceClient;
import com.google.ads.googleads.v9.services.CampaignOperation;
import com.google.ads.googleads.v9.services.CampaignServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateCampaignBudgetsResponse;
import com.google.ads.googleads.v9.services.MutateCampaignsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.FieldMask;

@Service(value = "ggCampaignService")
public class CampaignService {

    private final GoogleAdsClient googleAdsClient;
    public CampaignService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public String createCampaignBudget(long customerId, String name, long amount) {
        CampaignBudget campaignBudget =
                CampaignBudget.newBuilder()
                              .setName(name + LocalDateTime.now())
                              .setDeliveryMethod(BudgetDeliveryMethodEnum.BudgetDeliveryMethod.STANDARD)
                              .setAmountMicros(amount)
                              .setExplicitlyShared(false)
                              .build();
        CampaignBudgetOperation operation = CampaignBudgetOperation.newBuilder().setCreate(campaignBudget).build();

        try (CampaignBudgetServiceClient client = googleAdsClient.getLatestVersion().createCampaignBudgetServiceClient()) {
            MutateCampaignBudgetsResponse response =
                    client.mutateCampaignBudgets(String.valueOf(customerId), ImmutableList.of(operation));
            String budgetResourceName = response.getResults(0).getResourceName();
            return budgetResourceName;
        }
    }

    public String create(CampaignReq param) {
        Campaign.NetworkSettings networkSettings =
                Campaign.NetworkSettings.newBuilder()
                        .setTargetGoogleSearch(param.getNetworkSetting().isTargetGoogleSearch())
                        .setTargetSearchNetwork(param.getNetworkSetting().isTargetSearchNetwork())
                        .setTargetContentNetwork(param.getNetworkSetting().isTargetContentNetwork())
                        .setTargetPartnerSearchNetwork(param.getNetworkSetting().isTargetPartnerSearchNetwork())
                        .build();

        Campaign.Builder campBuilder =
                Campaign.newBuilder()
                        .setName(param.getName())
                        .setAdvertisingChannelType(AdvertisingChannelType.valueOf(param.getChannelType()))
                        .setStatus(CampaignStatus.PAUSED)
                        .setMaximizeConversions(MaximizeConversions.newBuilder().build())
                        .setCampaignBudget(createCampaignBudget(param.getCustomerId(), param.getCampaignBudgetName(), param.getAmount()))
                        .setNetworkSettings(networkSettings)
                        .setStartDate(param.getStartDate())
                        .setEndDate(param.getEndDate())
                        .setPaymentMode(PaymentMode.valueOf(param.getPaymentMode()))
                        .setGeoTargetTypeSetting(GeoTargetTypeSetting.newBuilder()
                                                                     .setPositiveGeoTargetType(PositiveGeoTargetType.PRESENCE)
                                                                     .setNegativeGeoTargetType(NegativeGeoTargetType.PRESENCE_OR_INTEREST)
                                                                     .build());

        if (BiddingStrategyType.MANUAL_CPM.name().equals(param.getBiddingType())) {
            campBuilder.setManualCpm(ManualCpm.newBuilder().build());
        }

        if (param.getChannelSubType() != null && !param.getChannelSubType().equals("")) {
            campBuilder.setTargetCpa(TargetCpa.newBuilder()
                                              .setTargetCpaMicros(param.getTargetCpa()).build());
            campBuilder.setAdvertisingChannelSubType(AdvertisingChannelSubType.valueOf(param.getChannelSubType()));
            campBuilder.setAppCampaignSetting(AppCampaignSetting.newBuilder()
                                                                .setBiddingStrategyGoalType(
                                                                        AppCampaignBiddingStrategyGoalType.valueOf(param.getAppCampaignSetting().getBiddingStrategyGoalType()))
                                                                .setAppStore(AppCampaignAppStore.valueOf(param.getAppCampaignSetting().getAppStore()))
                                                                .setAppId(param.getAppCampaignSetting().getAppId())
                                                                .build());
        }

        CampaignOperation campaignOperation = CampaignOperation.newBuilder().setCreate(campBuilder.build()).build();

        try(CampaignServiceClient campaignServiceClient = googleAdsClient.getLatestVersion().createCampaignServiceClient()) {
            MutateCampaignsResponse mutateCampaignsResponse = campaignServiceClient.mutateCampaigns(String.valueOf(param.getCustomerId()), ImmutableList.of(campaignOperation));
            return mutateCampaignsResponse.getResults(0).getResourceName();
        }

    }

    public Campaign getCampaign(long customerId, long campaignId) {
        return getCampaign(ResourceNames.campaign(customerId, campaignId));
    }

    public Campaign getCampaign(long campaignId) {
        return getCampaign(ResourceNames.campaign(Constants.GOOGLE_ROOT_CUSTOMER_ID, campaignId));
    }

    public Campaign getCampaign(String resourceName) {
        try (CampaignServiceClient campaignServiceClient = googleAdsClient.getLatestVersion().createCampaignServiceClient()) {
            Campaign campaign = campaignServiceClient.getCampaign(resourceName);
            return campaign;
        }
    }

    public Campaign updateCampaign(CampaignReq param) {
        try (CampaignServiceClient client = googleAdsClient.getLatestVersion().createCampaignServiceClient()) {

            Campaign.Builder campaignBuilder =
                    Campaign.newBuilder()
                            .setResourceName(ResourceNames.campaign(param.getCustomerId(), param.getCampaignId()));

            FieldMask fieldMask = null;

            if (BiddingStrategyType.MAXIMIZE_CONVERSIONS.name().equals(param.getBiddingType())) {
                campaignBuilder.setMaximizeConversions(MaximizeConversions.newBuilder().build());
//                               .setPaymentMode(PaymentMode.valueOf(param.getPaymentMode()));

            } else if (BiddingStrategyType.TARGET_CPA.name().equals(param.getBiddingType())) {

                campaignBuilder.setTargetCpa(TargetCpa.newBuilder()
                                                      .setTargetCpaMicros(1000000000).build())
                               .setPaymentMode(PaymentMode.valueOf(param.getPaymentMode()));

            } else if (BiddingStrategyType.MANUAL_CPM.name().equals(param.getBiddingType())) {

                campaignBuilder.setManualCpm(ManualCpm.newBuilder().build())
                               .setPaymentMode(PaymentMode.valueOf(param.getPaymentMode()));

            } else if (BiddingStrategyType.MANUAL_CPC.name().equals(param.getBiddingType())) {

                campaignBuilder.setManualCpc(ManualCpc.newBuilder()
                                                      .setEnhancedCpcEnabled(false)
                                                      .build())
                               .setPaymentMode(PaymentMode.valueOf(param.getPaymentMode()));
            }

            Campaign campaign = campaignBuilder.build();

            final FieldMask.Builder fieldMaskBuilder = FieldMasks.allSetFieldsOf(campaign).toBuilder();

            if( campaign.hasMaximizeConversions() && !fieldMaskBuilder.getPathsList().contains( "maximize_conversions" ) ) {
                fieldMaskBuilder.addPaths( "maximize_conversions.target_cpa" );
            }

            List<CampaignOperation> operations = ImmutableList.of(CampaignOperation
                                                                          .newBuilder()
                                                                          .setUpdate(campaign)
                                                                          .setUpdateMask(fieldMaskBuilder.build())
                                                                          .build());
            MutateCampaignsResponse response = client.mutateCampaigns(String.valueOf(param.getCustomerId()), operations);
            return response.getResults(0).getCampaign();
        }
    }

    public List<Campaign> getAllCampaigns(long customerId) {
        List<Campaign> campaignList = new ArrayList<>();
        String query = "SELECT campaign.accessible_bidding_strategy, campaign.ad_serving_optimization_status, campaign.advertising_channel_sub_type, campaign.advertising_channel_type, campaign.app_campaign_setting.app_id, campaign.app_campaign_setting.app_store, campaign.app_campaign_setting.bidding_strategy_goal_type, campaign.base_campaign, campaign.bidding_strategy, campaign.bidding_strategy_type, campaign.video_brand_safety_suitability, campaign.vanity_pharma.vanity_pharma_text, campaign.vanity_pharma.vanity_pharma_display_url_mode, campaign.url_expansion_opt_out, campaign.url_custom_parameters, campaign.tracking_url_template, campaign.tracking_setting.tracking_url, campaign.targeting_setting.target_restrictions, campaign.target_spend.target_spend_micros, campaign.target_spend.cpc_bid_ceiling_micros, campaign.target_roas.target_roas, campaign.target_roas.cpc_bid_floor_micros, campaign.target_roas.cpc_bid_ceiling_micros, campaign.target_impression_share.location_fraction_micros, campaign.target_impression_share.location, campaign.target_impression_share.cpc_bid_ceiling_micros, campaign.target_cpm, campaign.target_cpa.target_cpa_micros, campaign.target_cpa.cpc_bid_floor_micros, campaign.target_cpa.cpc_bid_ceiling_micros, campaign.status, campaign.start_date, campaign.shopping_setting.sales_country, campaign.shopping_setting.merchant_id, campaign.shopping_setting.enable_local, campaign.shopping_setting.campaign_priority, campaign.serving_status, campaign.selective_optimization.conversion_actions, campaign.resource_name, campaign.real_time_bidding_setting.opt_in, campaign.percent_cpc.enhanced_cpc_enabled, campaign.percent_cpc.cpc_bid_ceiling_micros, campaign.payment_mode, campaign.optimization_score, campaign.optimization_goal_setting.optimization_goal_types, campaign.network_settings.target_search_network, campaign.network_settings.target_partner_search_network, campaign.network_settings.target_google_search, campaign.network_settings.target_content_network, campaign.name, campaign.maximize_conversions.target_cpa, campaign.maximize_conversion_value.target_roas, campaign.manual_cpv, campaign.manual_cpm, campaign.id, campaign.labels, campaign.local_campaign_setting.location_source_type, campaign.manual_cpc.enhanced_cpc_enabled, campaign.hotel_setting.hotel_center_id, campaign.geo_target_type_setting.positive_geo_target_type, campaign.geo_target_type_setting.negative_geo_target_type, campaign.frequency_caps, campaign.final_url_suffix, campaign.experiment_type, campaign.excluded_parent_asset_field_types, campaign.end_date, campaign.dynamic_search_ads_setting.use_supplied_urls_only, campaign.dynamic_search_ads_setting.language_code, campaign.dynamic_search_ads_setting.feeds, campaign.dynamic_search_ads_setting.domain_name, campaign.commission.commission_rate_micros, campaign.campaign_budget, customer.id, customer.descriptive_name FROM campaign";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);

            for(GoogleAdsRow row : response.iterateAll()) {
                campaignList.add(row.getCampaign());
            }
            return campaignList;
        }
    }
}
