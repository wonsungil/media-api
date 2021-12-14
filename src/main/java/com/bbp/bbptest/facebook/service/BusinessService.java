package com.bbp.bbptest.facebook.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Business;

@Service(value = "fbBusinessService")
public class BusinessService {

    private APIContext facebookApiContext;

    public BusinessService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public Business getBusiness(long businessId) throws Exception {
        List<String> fields = Arrays.asList("id","name");

        Business business = new Business(businessId, facebookApiContext).get().requestFields(fields).execute();
        return business;
    }

    public APINodeList<AdAccount> getOwnedAdAccounts(long businessId) throws Exception {
        Business business = getBusiness(businessId);
        APINodeList<AdAccount> ownedAdAccounts = business.getOwnedAdAccounts().execute();
        for (AdAccount adAccount : ownedAdAccounts) {
            System.out.println(adAccount.toString());
        }
        return ownedAdAccounts;
    }

    public APINodeList<AdAccount> getOwnedAdAccounts(Business business) throws Exception {
        List<String> fields = Arrays.asList("id", "account_id", "account_status");
        APINodeList<AdAccount> ownedAdAccounts = business.getOwnedAdAccounts().requestFields(fields).execute();
        return ownedAdAccounts;
    }
}
