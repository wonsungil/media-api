package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.RemarketingAction;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;

@Service(value = "ggRemarketingActionService")
public class RemarketingActionService {

    private GoogleAdsClient googleAdsClient;

    public RemarketingActionService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    public List<RemarketingAction> getRemarketingActions(long customerId) {
        List<RemarketingAction> remarketingActionList = new ArrayList<>();
        String query = "SELECT remarketing_action.id, remarketing_action.name, remarketing_action.resource_name, remarketing_action.tag_snippets FROM remarketing_action";
        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
            for (GoogleAdsRow row : response.iterateAll()) {
                remarketingActionList.add(row.getRemarketingAction());
            }
            return remarketingActionList;
        }
    }
}
