package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.config.Constants;
import com.bbp.bbptest.google.config.GAQLQuery;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.common.PlacementInfo;
import com.google.ads.googleads.v9.resources.TopicConstant;
import com.google.ads.googleads.v9.resources.TopicConstantName;
import com.google.ads.googleads.v9.resources.UserInterest;
import com.google.ads.googleads.v9.resources.UserInterestName;
import com.google.ads.googleads.v9.services.GeoTargetConstantServiceClient;
import com.google.ads.googleads.v9.services.GeoTargetConstantSuggestion;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.SuggestGeoTargetConstantsRequest;
import com.google.ads.googleads.v9.services.SuggestGeoTargetConstantsResponse;

@Service(value = "ggTargetingSearchService")
public class TargetingSearchService {
    private final GoogleAdsClient googleAdsClient;

    public TargetingSearchService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    /**
     * GeoTarget 조회
     * @param locale
     * @param countryCode
     * @param locationNames
     * @return
     */
    public List<GeoTargetConstantSuggestion> getGeoTarget(String locale, String countryCode, List<String> locationNames) {
        try(GeoTargetConstantServiceClient geoTargetClient =
                googleAdsClient.getLatestVersion().createGeoTargetConstantServiceClient()) {

            SuggestGeoTargetConstantsRequest.Builder requestBuilder =
                    SuggestGeoTargetConstantsRequest.newBuilder();

            requestBuilder.setLocale(locale);
            requestBuilder.setCountryCode(countryCode);
            requestBuilder.getLocationNamesBuilder().addAllNames(locationNames);
            SuggestGeoTargetConstantsRequest request = requestBuilder.build();

            SuggestGeoTargetConstantsResponse response = geoTargetClient.suggestGeoTargetConstants(request);

            return response.getGeoTargetConstantSuggestionsList();
        }
    }

    /**
     * 잠재고객 타게팅
     * @return
     */
    public List<UserInterest> getUserInterestTarget(String query) {
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            List<UserInterest> userInterests = new ArrayList<>();
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                userInterests.add(row.getUserInterest());
            }
            return userInterests;
        }
    }

    public List<UserInterest> getTopLevelUserInterestTarget() {
        return getUserInterestTarget(GAQLQuery.TOP_LEVEL_USER_INTEREST_QUERY);
    }

    public List<UserInterest> getChildUserInterestTarget(long parentId) {
        String query =
                GAQLQuery.CHILD_USER_INTEREST_QUERY.replace("?"
                        , UserInterestName.format(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), String.valueOf(parentId)));
        return getUserInterestTarget(query);
    }

    /**
     * 게재위치 타게팅
     */
    public void getPlacementTarget(String query) {
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            List<PlacementInfo> placementInfos = new ArrayList<>();
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), query);
            for (GoogleAdsRow row : response.iterateAll()) {
//                placementInfos.add(row.getDetaild)
            }
        }
    }

    /**
     * 토픽 타게팅
     */
    public List<TopicConstant> getTopicTarget(String query) {
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            List<TopicConstant> topicConstants = new ArrayList<>();
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                topicConstants.add(row.getTopicConstant());
            }
            return topicConstants;
        }
    }

    public List<TopicConstant> getTopLevelTopicTarget() {
        return getTopicTarget(GAQLQuery.TOP_LEVEL_TOPIC_QUERY);
    }

    public List<TopicConstant> getChildTopicTarget(long parentId) {
        String query = GAQLQuery.CHILD_TOPIC_QUERY.replace("?", TopicConstantName.format(String.valueOf(parentId)));
        return getTopicTarget(query);
    }
}
