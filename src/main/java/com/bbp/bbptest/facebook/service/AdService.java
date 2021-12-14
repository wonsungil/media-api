package com.bbp.bbptest.facebook.service;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.facebook.model.AdReq;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdAccount;

@Service(value = "fbAdService")
public class AdService {

    private APIContext facebookApiContext;

    public AdService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public Ad create(AdReq param) throws APIException {
        Ad ad = new AdAccount(param.getAdAccountId(), facebookApiContext)
                .createAd()
                .setAudienceId(param.getAudienceId())
                .setBidAmount(param.getBidAmount())
                .setConversionDomain(param.getConversionDomain())
                .setCreative(param.getCreative())
                .setDateFormat(param.getDateFormat())
                .setDisplaySequence(param.getDisplaySequence())
                .setDraftAdgroupId(param.getDraftAdgroupId())
                .setEngagementAudience(param.getEngagementAudience())
                .setExecutionOptions(param.getExecutionOptions())
                .setIncludeDemolinkHashes(param.getIncludeDemolinkHashes())
                .setName(param.getName())
                .setPriority(param.getPriority())
                .setSourceAdId(param.getSourceAdId())
                .setStatus(param.getStatus())
                .setTrackingSpecs(param.getTrackingSpecs())
                .execute();

        return ad;
    }

//    public AdSet getAdSet() {
//
//    }

}
