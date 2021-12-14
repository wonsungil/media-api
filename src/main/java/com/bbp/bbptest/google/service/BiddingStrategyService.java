package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.BiddingStrategyReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.lib.utils.FieldMasks;
import com.google.ads.googleads.v9.common.MaximizeConversions;
import com.google.ads.googleads.v9.enums.BiddingStrategyTypeEnum.BiddingStrategyType;
import com.google.ads.googleads.v9.resources.BiddingStrategy;
import com.google.ads.googleads.v9.resources.Campaign;
import com.google.ads.googleads.v9.services.BiddingStrategyOperation;
import com.google.ads.googleads.v9.services.BiddingStrategyServiceClient;
import com.google.ads.googleads.v9.services.CampaignOperation;
import com.google.ads.googleads.v9.services.CampaignServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateBiddingStrategiesResponse;
import com.google.ads.googleads.v9.services.MutateCampaignsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggBiddingStrategyService")
public class BiddingStrategyService {
    private final GoogleAdsClient googleAdsClient;

    public BiddingStrategyService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<BiddingStrategy> getBiddingStrategy(long customerId) {

        List<BiddingStrategy> biddingStrategies = new ArrayList<>();

        String query = "SELECT "
                       + " bidding_strategy.type"
                       + ", bidding_strategy.target_spend.target_spend_micros"
                       + ", bidding_strategy.target_spend.cpc_bid_ceiling_micros, bidding_strategy.target_roas.target_roas"
                       + ", bidding_strategy.target_roas.cpc_bid_floor_micros"
                       + ", bidding_strategy.target_roas.cpc_bid_ceiling_micros"
                       + ", bidding_strategy.target_impression_share.location_fraction_micros"
                       + ", bidding_strategy.target_impression_share.location"
                       + ", bidding_strategy.target_impression_share.cpc_bid_ceiling_micros"
                       + ", bidding_strategy.target_cpa.target_cpa_micros, bidding_strategy.target_cpa.cpc_bid_floor_micros"
                       + ", bidding_strategy.target_cpa.cpc_bid_ceiling_micros, bidding_strategy.status, bidding_strategy.resource_name"
                       + ", bidding_strategy.non_removed_campaign_count, bidding_strategy.name, bidding_strategy.maximize_conversions.target_cpa"
                       + ", bidding_strategy.maximize_conversions.cpc_bid_ceiling_micros, bidding_strategy.maximize_conversions.cpc_bid_floor_micros"
                       + ", bidding_strategy.maximize_conversion_value.cpc_bid_floor_micros, bidding_strategy.enhanced_cpc"
                       + ", bidding_strategy.maximize_conversion_value.target_roas, bidding_strategy.maximize_conversion_value.cpc_bid_ceiling_micros"
                       + ", bidding_strategy.id, bidding_strategy.effective_currency_code, bidding_strategy.currency_code"
                       + ", bidding_strategy.campaign_count "
                       + " FROM bidding_strategy ";
//                       + "WHERE customer.id = '"+customerId+"'";

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            System.out.println("####");
            for (GoogleAdsRow row : response.iterateAll()) {
                biddingStrategies.add(row.getBiddingStrategy());
                System.out.println(row.getBiddingStrategy());
            }
            System.out.println("####");
        }
        return biddingStrategies;
    }


    public BiddingStrategy createBiddingStrategy(BiddingStrategyReq param) {

        try (BiddingStrategyServiceClient client = googleAdsClient.getLatestVersion()
                                                                  .createBiddingStrategyServiceClient()) {
            BiddingStrategy biddingStrategy =
                    BiddingStrategy.newBuilder()
                                   .setName("Test Bidding Strategy 3")
                                   .setMaximizeConversions(MaximizeConversions.newBuilder()
                                                                              .build())
//                                   .setCurrencyCode("KRW")
                                   .build();
            BiddingStrategyOperation operation =
                    BiddingStrategyOperation.newBuilder().setCreate(biddingStrategy).build();
            MutateBiddingStrategiesResponse response = client.mutateBiddingStrategies(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getBiddingStrategy();
        }
    }

    public BiddingStrategy updateBiddingStrategy(BiddingStrategyReq param) {
        try(BiddingStrategyServiceClient client = googleAdsClient.getLatestVersion()
                                                                 .createBiddingStrategyServiceClient()) {
            BiddingStrategy biddingStrategy =
                    BiddingStrategy.newBuilder()
                                   .setResourceName(ResourceNames.biddingStrategy(param.getCustomerId(), param.getBiddingStrategyId()))
                                   .setType(BiddingStrategyType.MAXIMIZE_CONVERSIONS)
                                   .setMaximizeConversions(MaximizeConversions.newBuilder()
                                                                              .build())
                                   .build();
            List<BiddingStrategyOperation> operations = ImmutableList.of(
                    BiddingStrategyOperation
                            .newBuilder()
                            .setUpdate(biddingStrategy)
                            .build()
            );
            MutateBiddingStrategiesResponse response = client.mutateBiddingStrategies(String.valueOf(param.getCustomerId()), operations);
            return response.getResults(0).getBiddingStrategy();
        }
    }

    public BiddingStrategy updateCampaignBiddingStrategy(BiddingStrategyReq param) {
        try(CampaignServiceClient client = googleAdsClient.getLatestVersion().createCampaignServiceClient()) {

            Campaign update = Campaign.newBuilder()
                                      .setResourceName(ResourceNames.campaign(param.getCustomerId(), param.getCampaignId()))
                                      .setBiddingStrategy(ResourceNames.biddingStrategy(param.getCustomerId(), param.getBiddingStrategyId()))
                                      .build();

            CampaignOperation operation = CampaignOperation.newBuilder()
                                                           .setUpdate(update)
                                                           .setUpdateMask(FieldMasks.allSetFieldsOf(update))
                                                           .build();
            MutateCampaignsResponse response = client.mutateCampaigns(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return null;
        }
    }
}
