package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.FeedReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.enums.FeedAttributeTypeEnum.FeedAttributeType;
import com.google.ads.googleads.v9.enums.FeedOriginEnum.FeedOrigin;
import com.google.ads.googleads.v9.enums.FeedStatusEnum.FeedStatus;
import com.google.ads.googleads.v9.resources.Feed;
import com.google.ads.googleads.v9.resources.FeedAttribute;
import com.google.ads.googleads.v9.services.FeedOperation;
import com.google.ads.googleads.v9.services.FeedServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateFeedsResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;
import com.google.common.collect.ImmutableList;

@Service(value = "ggFeedService")
public class FeedService {

    private final GoogleAdsClient googleAdsClient;

    public FeedService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<Feed> getFeeds(long customerId) {
        List<Feed> feeds = new ArrayList<>();
        String query = "SELECT feed.status, feed.resource_name, feed.places_location_feed_data.label_filters, feed.places_location_feed_data.email_address, feed.places_location_feed_data.category_filters, feed.places_location_feed_data.business_name_filter, feed.origin, feed.name, feed.id, feed.attributes, feed.affiliate_location_feed_data.relationship_type, feed.affiliate_location_feed_data.chain_ids FROM feed";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                feeds.add(row.getFeed());
            }
        }
        return feeds;
    }

    public Feed getFeed(long customerId, long feedId) {
        return getFeed(ResourceNames.feed(customerId, feedId));
    }
    public Feed getFeed(String resourceName) {
        try (FeedServiceClient client = googleAdsClient.getLatestVersion().createFeedServiceClient()) {
            Feed feed = client.getFeed(resourceName);
            return feed;
        }
    }

    public String create(FeedReq param) {
        try (FeedServiceClient client = googleAdsClient.getLatestVersion().createFeedServiceClient()) {

            List<FeedAttribute> attributesList = param.getAttributes()
                                                      .stream()
                                                      .map(x -> FeedAttribute.newBuilder()
                                                                             .setName(x.getName())
                                                                             .setType(FeedAttributeType.valueOf(x.getType()))
                                                                             .setIsPartOfKey(x.isPartOfKey())
                                                                             .build())
                                                      .collect(Collectors.toList());

            Feed feed = Feed.newBuilder()
                            .setName(param.getName())
                            .setOrigin(FeedOrigin.valueOf(param.getOrigin()))
                            .setStatus(FeedStatus.valueOf(param.getStatus()))
                            .addAllAttributes(attributesList)
                            .build();

            FeedOperation operation = FeedOperation.newBuilder()
                                                   .setCreate(feed)
                                                   .build();

            MutateFeedsResponse response = client.mutateFeeds(String.valueOf(param.getCustomerId()), ImmutableList.of(operation));
            return response.getResults(0).getResourceName();
        }
    }
}
