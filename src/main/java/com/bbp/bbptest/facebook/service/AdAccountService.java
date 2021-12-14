package com.bbp.bbptest.facebook.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.APIRequest;
import com.facebook.ads.sdk.AdAccount;

@Service(value = "fbAdAccountService")
public class AdAccountService {

    private APIContext facebookApiContext;

    public AdAccountService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public AdAccount getAdAccount(long adAccountId) throws Exception {
        List<String> fields = Arrays.asList("id", "account_id", "account_status", "name", "timezone_id", "currency", "partner", "end_advertiser","media_agency");
        AdAccount adAccount = new AdAccount(adAccountId, facebookApiContext).get().requestFields(fields).execute();
        return adAccount;
    }

    /**
     *
     * @param name
     * @param timeZoneId TZ_ASIA_SEOUL(79) //https://developers.facebook.com/docs/marketing-api/reference/ad-account/timezone-ids/v12.0
     * @param currency KRW
     * @param partner
     * @param endAdvertiser 1341994392882236
     * @param mediaAgency
     * @param invoice
     * @throws APIException
     */
    public AdAccount create(long businessId, String name, long timeZoneId, String currency, String partner
            , String endAdvertiser, String mediaAgency,  boolean invoice) throws APIException {
        List<String> fields = Arrays.asList("id", "account_id", "account_status", "name", "timezone_id", "currency", "partner", "end_advertiser","media_agency");

        Map<String,Object> params = new HashMap<>();
        params.put("name", name);
        params.put("timezone_id", timeZoneId);
        params.put("currency", currency);
        params.put("partner", null);
        params.put("end_advertiser", businessId);
        params.put("media_agency", null);
        params.put("invoice", false);

        APIRequest<AdAccount> request = new APIRequest<AdAccount>(facebookApiContext, String.valueOf(businessId), "/adaccount", "POST", AdAccount.getParser());
        APINodeList<AdAccount> response = (APINodeList<AdAccount>) request.execute(params);

        return response.get(0);
//        AdAccount adAccount = new Business(businessId, facebookApiContext)
//                .createAdAccount()
//                .setName(name)
//                .setTimezoneId(timeZoneId)
//                .setCurrency(currency)
//                .setPartner(partner)
//                .setEndAdvertiser(endAdvertiser)
//                .setMediaAgency(mediaAgency)
//                .setInvoice(invoice)
//                .requestFields(fields)
//                .execute();
//
//        return adAccount;
    }
}
