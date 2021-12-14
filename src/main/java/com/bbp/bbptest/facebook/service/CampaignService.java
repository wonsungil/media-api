package com.bbp.bbptest.facebook.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.facebook.utils.FacebookUtil;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.Campaign.EnumSpecialAdCategories;
import com.facebook.ads.sdk.Campaign.EnumStatus;

@Service(value = "fbCampaignService")
public class CampaignService {

    private APIContext facebookApiContext;

    public CampaignService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    /**
     * 캠페인 생성
     * @param adAccountId
     * @param name
     * @param objective
     * @param status
     * @return
     * @throws Exception
     */
    public Campaign create(long adAccountId, String name, String objective,
                           String status, List<EnumSpecialAdCategories> specialAdCategories) throws Exception {
        List<String> fields = Arrays.asList("id", "account_id", "name", "status", "objective");
        Campaign campaign = new AdAccount(adAccountId, facebookApiContext).createCampaign()
                .setName(name)
                .setObjective(Campaign.EnumObjective.valueOf(FacebookUtil.toEnumValueName(objective)))
                .setStatus(Campaign.EnumStatus.valueOf(FacebookUtil.toEnumValueName(status)))
                .setParam("special_ad_categories", "[]")
                .requestFields(fields)
                .execute();

        return campaign;
    }

    public Campaign create(long adAccountId, String name, String objective,
                          String status) throws Exception {
        return create(adAccountId, name, objective, status, new ArrayList<EnumSpecialAdCategories>());
    }

    public Campaign update(long campaignId, String name, String objective,
                           String status) throws APIException {
        List<String> fields = Arrays.asList("id", "account_id", "name", "status", "objective");
        Campaign campaign = new Campaign(campaignId, facebookApiContext).update()
              .setName(name)
              .setObjective(Campaign.EnumObjective.valueOf(FacebookUtil.toEnumValueName(objective)))
              .setStatus(EnumStatus.valueOf(FacebookUtil.toEnumValueName(status)))
              .setParam("special_ad_categories", "[]")
              .requestFields(fields)
              .execute();

        return campaign;
    }

    public Campaign delete(long campaignId) throws APIException {
        Campaign campaign = new Campaign(campaignId, facebookApiContext);
        campaign.delete().execute();
        return campaign;
    }

    /**
     * 캠페인 조회
     * @param campaignId
     */
    public Campaign getCampaign(long campaignId) throws Exception {
        List<String> fields = Arrays.asList("id", "account_id", "name", "status", "objective");
        Campaign campaign = new Campaign(campaignId, facebookApiContext)
                .get().requestFields(fields).execute();

        return campaign;
    }

    /**
     * 캠페인 리스트 조회
     * @param adAccountId
     * @return
     * @throws APIException
     */
    public APINodeList<Campaign> getCampaigns(long adAccountId) throws APIException {
        List<String> fields = Arrays.asList("id", "account_id", "name", "status", "objective");

        APINodeList<Campaign> campaigns = new AdAccount(adAccountId, facebookApiContext)
                .getCampaigns().requestFields(fields).execute();

        return campaigns;
    }
}
