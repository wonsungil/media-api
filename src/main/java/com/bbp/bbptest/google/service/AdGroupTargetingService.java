package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AdGroupTargetingReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.common.AgeRangeInfo;
import com.google.ads.googleads.v9.common.GenderInfo;
import com.google.ads.googleads.v9.common.IncomeRangeInfo;
import com.google.ads.googleads.v9.common.ParentalStatusInfo;
import com.google.ads.googleads.v9.resources.AdGroupCriterion;
import com.google.ads.googleads.v9.services.AdGroupCriterionOperation;
import com.google.ads.googleads.v9.services.AdGroupCriterionServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient.SearchPagedResponse;
import com.google.ads.googleads.v9.services.MutateAdGroupCriteriaResponse;
import com.google.ads.googleads.v9.services.SearchGoogleAdsRequest;
import com.google.ads.googleads.v9.utils.ResourceNames;

@Service(value = "ggAdGroupTargetingService")
public class AdGroupTargetingService {

    private final GoogleAdsClient googleAdsClient;

    public AdGroupTargetingService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    /**
     * UNSPECIFIED = 0;
     * UNKNOWN = 1;
     * MALE = 10;
     * FEMALE = 11;
    **/
    public AdGroupCriterion buildGenderCriterion(String adGroupName, int genderValue) {

        return AdGroupCriterion
                .newBuilder()
                .setAdGroup(adGroupName)
                .setGender(GenderInfo.newBuilder()
                                     .setTypeValue(genderValue)
                                     .build())
                .build();

    }

    /**
     * AgeRangeType.UNSPECIFIED = 0;
     * AgeRangeType.UNKNOWN = 1;
     * AgeRangeType.AGE_RANGE_18_24 = 503001;
     * AgeRangeType.AGE_RANGE_25_34 = 503002;
     * AgeRangeType.AGE_RANGE_35_44 = 503003;
     * AgeRangeType.GE_RANGE_45_54 = 503004;
     * AgeRangeType.AGE_RANGE_55_64 = 503005;
     * AgeRangeType.AGE_RANGE_65_UP = 503006;
     * AgeRangeType.AGE_RANGE_UNDETERMINED = 503999;
     */
    public AdGroupCriterion buildAgeCriterion(String adGroupName, int ageTypeValue) {
        return AdGroupCriterion
                .newBuilder()
                .setAdGroup(adGroupName)
                .setAgeRange(AgeRangeInfo.newBuilder()
                                         .setTypeValue(ageTypeValue)
                                         .build())
                .build();
    }

    /**
     * UNSPECIFIED = 0
     * UNKNOWN = 1
     * PARENT = 300
     * NOT_A_PARENT = 301
     * UNDETERMINED = 302
     *
     * @param adGroupName
     * @param parentalTypeValue
     * @return
     */
    public AdGroupCriterion buildParentalCriterion(String adGroupName, int parentalTypeValue) {
        return AdGroupCriterion
                .newBuilder()
                .setAdGroup(adGroupName)
                .setParentalStatus(ParentalStatusInfo.newBuilder()
                                                     .setTypeValue(parentalTypeValue)
                                                     .build())
                .build();
    }

    /**
     * UNSPECIFIED = 0
     * UNKNOWN = 1
     * INCOME_RANGE_0_50 = 510001
     * INCOME_RANGE_50_60 = 510002
     * INCOME_RANGE_60_70 = 510003
     * INCOME_RANGE_70_80 = 510004
     * INCOME_RANGE_80_90 = 510005
     * INCOME_RANGE_90_UP = 510006
     * INCOME_RANGE_UNDETERMINED = 510000
     */
    public AdGroupCriterion buildIncomeRangeCriterion(String adGroupName, int incomeRangeTypeValue) {
        return AdGroupCriterion
                .newBuilder()
                .setAdGroup(adGroupName)
                .setIncomeRange(IncomeRangeInfo.newBuilder()
                                               .setTypeValue(incomeRangeTypeValue)
                                               .build())
                .build();
    }

    public AdGroupCriterion buildAudienceCriterion(String adGroupName) {
        return AdGroupCriterion
                .newBuilder()
                .setAdGroup(adGroupName)
                .build();

    }

    public void addAdGroupTargeting(AdGroupTargetingReq param) {
        List<AdGroupCriterionOperation> operations = new ArrayList<>();
        String adGroupResourceName = ResourceNames.adGroup(param.getCustomerId(), param.getAdGroupId());
        for (int gender: param.getGenderList()) {
            operations.add(AdGroupCriterionOperation.newBuilder()
                                           .setCreate(buildGenderCriterion(adGroupResourceName, gender))
                                           .build());
        }

        for (int ageRange : param.getAgeRangeList()) {
            operations.add(AdGroupCriterionOperation.newBuilder()
                                                    .setCreate(buildAgeCriterion(adGroupResourceName, ageRange))
                                                    .build());
        }

        for (int parental : param.getParentalList()) {
            operations.add(AdGroupCriterionOperation.newBuilder()
                                                    .setCreate(buildParentalCriterion(adGroupResourceName, parental))
                                                    .build());
        }

        for (int income : param.getIncomeRangeList()) {
            operations.add(AdGroupCriterionOperation.newBuilder()
                                                    .setCreate(buildIncomeRangeCriterion(adGroupResourceName, income))
                                                    .build());
        }

        try (AdGroupCriterionServiceClient client = googleAdsClient.getLatestVersion()
                                                                   .createAdGroupCriterionServiceClient()) {
            MutateAdGroupCriteriaResponse response =
                    client.mutateAdGroupCriteria(String.valueOf(param.getCustomerId()), operations);
            System.out.println(response.toString());
        }
    }

    public List<AdGroupCriterion> getAdGroupCriterion(long customerId, long adGroupId) {
        try (GoogleAdsServiceClient googleAdsServiceClient =
                     googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {

            String query = "SELECT ad_group_criterion.youtube_video.video_id"
                           + ", ad_group_criterion.youtube_channel.channel_id, ad_group_criterion.webpage.sample.sample_urls, ad_group_criterion.webpage.criterion_name"
                           + ", ad_group_criterion.webpage.coverage_percentage"
                           + ", ad_group_criterion.webpage.conditions, ad_group_criterion.user_list.user_list, ad_group_criterion.user_interest.user_interest_category"
                           + ", ad_group_criterion.url_custom_parameters"
                           + ", ad_group_criterion.type, ad_group_criterion.tracking_url_template"
                           + ", ad_group_criterion.topic.topic_constant, ad_group_criterion.topic.path, ad_group_criterion.system_serving_status"
                           + ", ad_group_criterion.status, ad_group_criterion.resource_name, ad_group_criterion.quality_info.search_predicted_ctr"
                           + ", ad_group_criterion.quality_info.quality_score, ad_group_criterion.quality_info.post_click_quality_score"
                           + ", ad_group_criterion.quality_info.creative_quality_score, ad_group_criterion.position_estimates.top_of_page_cpc_micros"
                           + ", ad_group_criterion.position_estimates.first_position_cpc_micros"
                           + ", ad_group_criterion.position_estimates.first_page_cpc_micros, ad_group_criterion.position_estimates.estimated_add_cost_at_first_position_cpc"
                           + ", ad_group_criterion.position_estimates.estimated_add_clicks_at_first_position_cpc, ad_group_criterion.placement.url, ad_group_criterion.percent_cpc_bid_micros"
                           + ", ad_group_criterion.parental_status.type, ad_group_criterion.negative, ad_group_criterion.mobile_application.name, ad_group_criterion.mobile_application.app_id"
                           + ", ad_group_criterion.mobile_app_category.mobile_app_category_constant, ad_group_criterion.listing_group.type, ad_group_criterion.listing_group.parent_ad_group_criterion"
                           + ", ad_group_criterion.listing_group.case_value.product_type.value, ad_group_criterion.listing_group.case_value.product_custom_attribute.index"
                           + ", ad_group_criterion.listing_group.case_value.product_custom_attribute.value, ad_group_criterion.listing_group.case_value.product_item_id.value"
                           + ", ad_group_criterion.listing_group.case_value.product_type.level, ad_group_criterion.listing_group.case_value.product_brand.value"
                           + ", ad_group_criterion.listing_group.case_value.product_channel.channel, ad_group_criterion.listing_group.case_value.product_channel_exclusivity.channel_exclusivity"
                           + ", ad_group_criterion.listing_group.case_value.product_condition.condition, ad_group_criterion.listing_group.case_value.hotel_id.value"
                           + ", ad_group_criterion.listing_group.case_value.hotel_state.state_criterion, ad_group_criterion.listing_group.case_value.product_bidding_category.country_code"
                           + ", ad_group_criterion.listing_group.case_value.product_bidding_category.id, ad_group_criterion.listing_group.case_value.product_bidding_category.level, ad_group_criterion.labels"
                           + ", ad_group_criterion.listing_group.case_value.hotel_city.city_criterion, ad_group_criterion.listing_group.case_value.hotel_class.value"
                           + ", ad_group_criterion.listing_group.case_value.hotel_country_region.country_region_criterion, ad_group_criterion.income_range.type, ad_group_criterion.keyword.text"
                           + ", ad_group_criterion.final_mobile_urls"
                           + ", ad_group_criterion.final_url_suffix, ad_group_criterion.final_urls, ad_group_criterion.gender.type"
                           + ", ad_group_criterion.keyword.match_type, ad_group_criterion.effective_percent_cpc_bid_source, ad_group_criterion.effective_cpm_bid_source, ad_group_criterion.effective_cpv_bid_micros"
                           + ", ad_group_criterion.effective_cpv_bid_source, ad_group_criterion.effective_percent_cpc_bid_micros, ad_group_criterion.criterion_id, ad_group_criterion.display_name"
                           + ", ad_group_criterion.disapproval_reasons, ad_group_criterion.custom_intent.custom_intent, ad_group_criterion.custom_audience.custom_audience, ad_group_criterion.custom_affinity.custom_affinity"
                           + ", ad_group_criterion.effective_cpc_bid_source, ad_group_criterion.effective_cpm_bid_micros, ad_group_criterion.effective_cpc_bid_micros, ad_group_criterion.cpv_bid_micros, ad_group_criterion.cpm_bid_micros"
                           + ", ad_group_criterion.cpc_bid_micros, ad_group_criterion.combined_audience.combined_audience, ad_group_criterion.bid_modifier, ad_group_criterion.app_payment_model.type, ad_group_criterion.approval_status"
                           + ", ad_group_criterion.age_range.type, ad_group_criterion.ad_group "
                           + " FROM ad_group_criterion"
                           + " WHERE ad_group.id='"+adGroupId+"'";

            SearchGoogleAdsRequest request =
                    SearchGoogleAdsRequest.newBuilder()
                                          .setCustomerId(Long.toString(customerId))
                                          .setPageSize(100)
                                          .setQuery(query)
                                          .build();
            // Issues the search request.
            SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);

            List<AdGroupCriterion> adGroupCriteria = new ArrayList<>();

            for (GoogleAdsRow row : searchPagedResponse.iterateAll()) {
                adGroupCriteria.add(row.getAdGroupCriterion());
                System.out.println(row.getAdGroupCriterion());
            }
            return adGroupCriteria;
        }


    }
}
