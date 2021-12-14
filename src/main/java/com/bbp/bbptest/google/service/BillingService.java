package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.v9.resources.BillingSetup;

@Service(value = "ggBillingService")
public class BillingService {

    public void test() {


        // payment account id

        BillingSetup billingSetup = BillingSetup.newBuilder()

                .build();

    }
}
