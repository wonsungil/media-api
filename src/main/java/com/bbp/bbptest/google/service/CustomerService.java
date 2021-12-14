package com.bbp.bbptest.google.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.Customer;
import com.google.ads.googleads.v9.services.CustomerServiceClient;
import com.google.ads.googleads.v9.services.ListAccessibleCustomersRequest;
import com.google.ads.googleads.v9.services.ListAccessibleCustomersResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;

/**
 * Customer Service
 */
@Service(value = "ggCustomerService")
public class CustomerService {

    private final GoogleAdsClient googleAdsClient;
    private final Resource googleCredential;

    public CustomerService(GoogleAdsClient googleAdsClient, Resource googleCredential) {
        this.googleAdsClient = googleAdsClient;
        this.googleCredential = googleCredential;
    }

    /**
     * Customer 조회
     * @param customerId
     * @return
     */
    public Customer getCustomer(long customerId) {
        try (CustomerServiceClient customerServiceClient = googleAdsClient.getLatestVersion().createCustomerServiceClient()) {
            return customerServiceClient.getCustomer(ResourceNames.customer(customerId));
        }
    }

    /**
     * 접근 가능한 Customer List 조회
     * @return
     */
    public List<Object> getAccessibleCustomers() {
        try (CustomerServiceClient customerServiceClient = googleAdsClient.getLatestVersion().createCustomerServiceClient()) {
            ListAccessibleCustomersResponse response
                    = customerServiceClient.listAccessibleCustomers(ListAccessibleCustomersRequest.newBuilder().build());
            return Arrays.asList(response.getResourceNamesList().toArray());
        }
    }
}
