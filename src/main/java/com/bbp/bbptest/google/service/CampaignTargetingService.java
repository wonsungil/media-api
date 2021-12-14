package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.CampaignTargetingReq;
import com.bbp.bbptest.google.model.CampaignTargetingReq.Device;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.common.AgeRangeInfo;
import com.google.ads.googleads.v9.common.DeviceInfo;
import com.google.ads.googleads.v9.common.GenderInfo;
import com.google.ads.googleads.v9.resources.CampaignCriterion;
import com.google.ads.googleads.v9.resources.CampaignCriterion.CriterionCase;
import com.google.ads.googleads.v9.services.CampaignCriterionOperation;
import com.google.ads.googleads.v9.services.CampaignCriterionServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient.SearchPagedResponse;
import com.google.ads.googleads.v9.services.MutateCampaignCriteriaResponse;
import com.google.ads.googleads.v9.services.SearchGoogleAdsRequest;
import com.google.ads.googleads.v9.utils.ResourceNames;

@Service(value = "ggCampaignTargetingService")
public class CampaignTargetingService {

    private final GoogleAdsClient googleAdsClient;

    public CampaignTargetingService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
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
    public CampaignCriterion buildAgeRangeCriterion(String campResourceName ,int ageRangeValue) {
        return CampaignCriterion
                .newBuilder()
                .setCampaign(campResourceName)
                .setAgeRange(AgeRangeInfo.newBuilder()
                                         .setTypeValue(ageRangeValue)
                                         .build())
                .build();
    }

    /**
     * UNSPECIFIED = 0
     * UNKNOWN = 1
     * MALE = 10
     * FEMALE = 11
     * UNDETERMINED = 20
     * @param genderValue
     * @return
     */
    public CampaignCriterion buildGenderCriterion(String campResourceName, int genderValue) {
        return CampaignCriterion
                .newBuilder()
                .setCampaign(campResourceName)
                .setGender(GenderInfo.newBuilder()
                                     .setTypeValue(genderValue)
                                     .build())
                .build();
    }

    /**
     * UNSPECIFIED = 0
     * UNKNOWN = 1
     * MOBILE = 2
     * TABLET = 3
     * DESKTOP = 4
     * CONNECTED_TV = 6
     * OTHER = 5
    **/
    public CampaignCriterion buildDeviceCriterion(String campResourceName, Device device) {
        return CampaignCriterion
                .newBuilder()
                .setCampaign(campResourceName)
                .setBidModifier(device.getBidModifier())
                .setDevice(DeviceInfo.newBuilder()
                                     .setTypeValue(device.getType())
                                     .build())
                .build();
    }

    public void addTargeting(CampaignTargetingReq param) {

        String campaignResourceName = ResourceNames.campaign(param.getCustomerId(), param.getCampaignId());
        List<CampaignCriterionOperation> operations = new ArrayList<>();

        // Add Device Targeting
        for (Device device : param.getDeviceList()) {
            operations.add(
                    CampaignCriterionOperation.newBuilder()
                                              .setCreate(buildDeviceCriterion(campaignResourceName, device))
                                              .build()
            );
        }

        try (CampaignCriterionServiceClient client =
                googleAdsClient.getLatestVersion().createCampaignCriterionServiceClient()) {
            MutateCampaignCriteriaResponse response =
                    client.mutateCampaignCriteria(String.valueOf(param.getCustomerId()), operations);
            System.out.println(response.toString());
        }
    }

    public List<CampaignCriterion> getCampaignCriterion(long customerId, long campaignId) {
        try (GoogleAdsServiceClient googleAdsServiceClient =
                     googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            // Creates a request that will retrieve all campaign criteria using pages of the specified
            // page size.
            SearchGoogleAdsRequest request =
                    SearchGoogleAdsRequest.newBuilder()
                                          .setCustomerId(Long.toString(customerId))
                                          .setPageSize(100)
                                          .setQuery(
                                                  String.format(
                                                          "SELECT "
                                                          + " campaign_criterion.ad_schedule.day_of_week, campaign_criterion.ad_schedule.start_hour"
                                                          + ", campaign_criterion.ad_schedule.end_hour, campaign_criterion.ad_schedule.end_minute"
                                                          + ", campaign_criterion.ad_schedule.start_minute, campaign_criterion.age_range.type"
                                                          + ", campaign_criterion.youtube_video.video_id, campaign_criterion.youtube_channel.channel_id"
                                                          + ", campaign_criterion.webpage.sample.sample_urls"
                                                          + ", campaign_criterion.webpage.coverage_percentage"
                                                          + ", campaign_criterion.webpage.criterion_name, campaign_criterion.user_list.user_list"
                                                          + ", campaign_criterion.webpage.conditions, campaign_criterion.topic.topic_constant"
                                                          + ", campaign_criterion.type, campaign_criterion.user_interest.user_interest_category"
                                                          + ", campaign_criterion.proximity.radius_units, campaign_criterion.resource_name"
                                                          + ", campaign_criterion.topic.path, campaign_criterion.status, campaign_criterion.proximity.geo_point.latitude_in_micro_degrees"
                                                          + ", campaign_criterion.proximity.geo_point.longitude_in_micro_degrees, campaign_criterion.proximity.radius"
                                                          + ", campaign_criterion.proximity.address.street_address, campaign_criterion.proximity.address.province_name"
                                                          + ", campaign_criterion.proximity.address.postal_code, campaign_criterion.proximity.address.country_code"
                                                          + ", campaign_criterion.proximity.address.province_code, campaign_criterion.proximity.address.city_name,"
                                                          + " campaign_criterion.parental_status.type, campaign_criterion.placement.url"
                                                          + ", campaign_criterion.operating_system_version.operating_system_version_constant"
                                                          + ", campaign_criterion.negative, campaign_criterion.mobile_device.mobile_device_constant"
                                                          + ", campaign_criterion.mobile_app_category.mobile_app_category_constant"
                                                          + ", campaign_criterion.location_group.feed, campaign_criterion.mobile_application.app_id"
                                                          + ", campaign_criterion.mobile_application.name, campaign_criterion.listing_scope.dimensions"
                                                          + ", campaign_criterion.location_group, campaign_criterion.location.geo_target_constant"
                                                          + ", campaign_criterion.keyword_theme.free_form_keyword_theme"
                                                          + ", campaign_criterion.language.language_constant, campaign_criterion.keyword_theme.keyword_theme_constant"
                                                          + ", campaign_criterion.ip_block.ip_address, campaign_criterion.keyword.text, campaign_criterion.income_range.type"
                                                          + ", campaign_criterion.keyword.match_type, campaign_criterion.gender.type, campaign_criterion.device.type"
                                                          + ", campaign_criterion.display_name, campaign_criterion.custom_affinity.custom_affinity"
                                                          + ", campaign_criterion.custom_audience.custom_audience, campaign_criterion.carrier.carrier_constant"
                                                          + ", campaign_criterion.campaign, campaign_criterion.bid_modifier, campaign_criterion.combined_audience.combined_audience"
                                                          + ", campaign_criterion.content_label.type, campaign_criterion.criterion_id "
                                                          + " FROM campaign_criterion"
                                                          + " WHERE campaign.id = %s",
                                                          Long.toString(campaignId)))
                                          .build();
            // Issues the search request.
            SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);

            List<CampaignCriterion> campaignCriterionList = new ArrayList<>();

            for (GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll()) {
                CampaignCriterion campaignCriterion = googleAdsRow.getCampaignCriterion();
                campaignCriterionList.add(campaignCriterion);

                if (CriterionCase.DEVICE.equals(campaignCriterion.getCriterionCase())) {
                    System.out.println(campaignCriterion.toString());
                }
            }
            return campaignCriterionList;
        }
    }
}
