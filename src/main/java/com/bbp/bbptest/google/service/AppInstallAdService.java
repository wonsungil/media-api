package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.Ad;
import com.google.ads.googleads.v9.resources.AdGroupAd;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;

@Service(value = "ggAppInstallAdService")
public class AppInstallAdService {

    private final GoogleAdsClient googleAdsClient;

    public AppInstallAdService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<GoogleAdsRow> getRows(long customerId) {
        List<GoogleAdsRow> rows = new ArrayList<>();
        String query = "SELECT "
                       + " ad_group_ad.action_items, ad_group_ad.ad.added_by_google_ads"
                       + ", ad_group_ad.ad.app_ad.headlines, ad_group_ad.ad.app_ad.descriptions"
                       + ", ad_group_ad.ad.app_ad.html5_media_bundles, ad_group_ad.ad.app_ad.images"
                       + ", ad_group_ad.ad.app_ad.mandatory_ad_text, ad_group_ad.ad.app_ad.youtube_videos"
                       + ", ad_group_ad.ad.app_engagement_ad.descriptions"
                       + ", ad_group_ad.ad.app_engagement_ad.headlines, ad_group_ad.ad.app_engagement_ad.images"
                       + ", ad_group_ad.ad.app_pre_registration_ad.youtube_videos"
                       + ", ad_group_ad.ad.app_pre_registration_ad.images"
                       + ", ad_group_ad.ad.app_pre_registration_ad.headlines"
                       + ", ad_group_ad.ad.app_pre_registration_ad.descriptions"
                       + ", ad_group_ad.ad.app_engagement_ad.videos, ad_group_ad.ad.display_url, ad_group_ad.status"
                       + ", ad_group_ad.resource_name, ad_group_ad.labels, ad_group_ad.ad_strength"
                       + ", ad_group_ad.ad_group, ad_group_ad.ad.url_custom_parameters"
                       + ", ad_group_ad.ad.url_collections, ad_group_ad.ad.type"
                       + ", ad_group_ad.ad.tracking_url_template"
                       + ", ad_group_ad.ad.system_managed_resource_source"
                       + ", ad_group_ad.ad.smart_campaign_ad.headlines"
                       + ", ad_group_ad.ad.smart_campaign_ad.descriptions"
                       + ", ad_group_ad.ad.resource_name, ad_group_ad.ad.name, ad_group_ad.ad.id"
                       + ", ad_group_ad.ad.final_urls, ad_group_ad.ad.final_url_suffix"
                       + ", ad_group_ad.ad.final_mobile_urls, ad_group_ad.ad.final_app_urls "
                       + " FROM ad_group_ad";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                rows.add(row);
            }
            return rows;
        }
    }

    public List<Ad> getAds(long customerId) {
        List<GoogleAdsRow> rows = getRows(customerId);
        List<Ad> ads = rows.stream()
                           .map(x->x.getAdGroupAd().getAd())
                           .collect(Collectors.toList());
        return ads;
    }

    public List<AdGroupAd> getAdGroupAds(long customerId) {
        List<GoogleAdsRow> rows = getRows(customerId);
        List<AdGroupAd> adGroupAds = rows.stream()
                           .map(x->x.getAdGroupAd())
                           .collect(Collectors.toList());
        return adGroupAds;
    }
}
