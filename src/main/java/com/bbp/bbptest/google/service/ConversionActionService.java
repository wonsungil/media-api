package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.ConversionActionReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.enums.AttributionModelEnum.AttributionModel;
import com.google.ads.googleads.v9.enums.ConversionActionCategoryEnum.ConversionActionCategory;
import com.google.ads.googleads.v9.enums.ConversionActionCountingTypeEnum.ConversionActionCountingType;
import com.google.ads.googleads.v9.enums.ConversionActionStatusEnum.ConversionActionStatus;
import com.google.ads.googleads.v9.enums.ConversionActionTypeEnum.ConversionActionType;
import com.google.ads.googleads.v9.resources.ConversionAction;
import com.google.ads.googleads.v9.resources.ConversionAction.AttributionModelSettings;
import com.google.ads.googleads.v9.resources.ConversionAction.ValueSettings;
import com.google.ads.googleads.v9.services.ConversionActionOperation;
import com.google.ads.googleads.v9.services.ConversionActionServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateConversionActionsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggConversionActionService")
public class ConversionActionService {

    private final GoogleAdsClient googleAdsClient;

    public ConversionActionService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public ConversionAction create(ConversionActionReq param) {
        try(ConversionActionServiceClient client =
                googleAdsClient.getLatestVersion().createConversionActionServiceClient()) {

            ConversionAction.Builder caBuilder = ConversionAction
                    .newBuilder()
                    .setStatus(ConversionActionStatus.valueOf(param.getStatus()))
                    .setType(ConversionActionType.valueOf(param.getConversionActionType()))
                    .setPrimaryForGoal(param.isPrimaryForGoal())
                    .setCategory(ConversionActionCategory.valueOf(param.getCategory()))
                    .setValueSettings(ValueSettings.newBuilder()
                                                   .setDefaultValue(param.getValueSetting().getDefaultValue())
                                                   .setDefaultCurrencyCode(param.getValueSetting().getDefaultCurrencyCode())
                                                   .setAlwaysUseDefaultValue(param.getValueSetting().isAlwaysUseDefaultValue())
                                                   .build())
                    .setCountingType(ConversionActionCountingType.valueOf(param.getCountingType()))
                    .setName(param.getName())
                    .setIncludeInConversionsMetric(param.isIncludeInConversionsMetric())
                    .setClickThroughLookbackWindowDays(param.getClickThroughLookBackWindowDays())
                    .setViewThroughLookbackWindowDays(param.getViewThroughLookBackWindowDays());
            
            if (ConversionActionType.GOOGLE_PLAY_DOWNLOAD.name().equals(param.getConversionActionType())) {
                caBuilder.setAppId(param.getAppId());
            } else if (ConversionActionType.WEBPAGE.name().equals(param.getConversionActionType())) {
                caBuilder.setAttributionModelSettings(AttributionModelSettings.newBuilder()
                                                                     .setAttributionModel(AttributionModel.valueOf(param.getAttributionModelSetting()))
                                                                     .build());
            }

            ConversionActionOperation operation = ConversionActionOperation
                    .newBuilder()
                    .setCreate(caBuilder.build())
                    .build();

            MutateConversionActionsResponse response =
                    client.mutateConversionActions(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getConversionAction();
        }
    }

    public ConversionAction getConversionAction(String resourceName) {
        try (ConversionActionServiceClient client =
                     googleAdsClient.getLatestVersion().createConversionActionServiceClient()) {
            return client.getConversionAction(resourceName);
        }
    }

    public ConversionAction getConversionAction(long customerId, long conversionActionId) {
        return getConversionAction(ResourceNames.conversionAction(customerId, conversionActionId));
    }

    public List<ConversionAction> getAllConversionActions(long customerId) {

        List<ConversionAction> conversionActions = new ArrayList<>();

        /**
         * TODO : ConversionAction Qyery로 변경
         */
        String query = "SELECT "
                       + " conversion_action.value_settings.default_value"
                       + ", conversion_action.view_through_lookback_window_days"
                       + ", conversion_action.value_settings.always_use_default_value"
                       + ", conversion_action.value_settings.default_currency_code"
                       + ", conversion_action.type"
                       + ", conversion_action.third_party_app_analytics_settings.provider_name"
                       + ", conversion_action.third_party_app_analytics_settings.event_name"
                       + ", conversion_action.tag_snippets"
                       + ", conversion_action.status"
                       + ", conversion_action.resource_name"
                       + ", conversion_action.primary_for_goal"
                       + ", conversion_action.phone_call_duration_seconds"
                       + ", conversion_action.owner_customer"
                       + ", conversion_action.name"
                       + ", conversion_action.origin"
                       + ", conversion_action.include_in_conversions_metric"
                       + ", conversion_action.mobile_app_vendor"
                       + ", conversion_action.firebase_settings.project_id"
                       + ", conversion_action.id"
                       + ", conversion_action.counting_type"
                       + ", conversion_action.firebase_settings.event_name"
                       + ", conversion_action.category"
                       + ", conversion_action.click_through_lookback_window_days"
                       + ", conversion_action.attribution_model_settings.attribution_model"
                       + ", conversion_action.attribution_model_settings.data_driven_model_status"
                       + ", conversion_action.app_id "
                       + " FROM conversion_action";

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                conversionActions.add(row.getConversionAction());
            }
        }
        return conversionActions;
    }

}
