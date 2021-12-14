package com.bbp.bbptest.google.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.config.Constants;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.resources.Customer;
import com.google.ads.googleads.v9.resources.CustomerClient;
import com.google.ads.googleads.v9.services.CreateCustomerClientResponse;
import com.google.ads.googleads.v9.services.CustomerClientServiceClient;
import com.google.ads.googleads.v9.services.CustomerServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.SearchGoogleAdsRequest;
import com.google.ads.googleads.v9.utils.ResourceNames;

/**
 * Customer Client Service
 */
@Service(value = "ggCustomerClientService")
public class CustomerClientService {

    private final GoogleAdsClient googleAdsClient;
    private final Resource googleCredential;

    public CustomerClientService(GoogleAdsClient googleAdsClient, Resource googleCredential) {
        this.googleAdsClient = googleAdsClient;
        this.googleCredential = googleCredential;
    }

    /**
     * Customer Client 생성
     * @param advertiserName 광고주명
     * @return
     */
    public Customer create(String advertiserName) {
        try (CustomerServiceClient client = googleAdsClient.getLatestVersion().createCustomerServiceClient()) {
            Customer customer =
                    Customer.newBuilder()
                            .setDescriptiveName(advertiserName)
                            .setCurrencyCode(Constants.CURRENCY_CODE_KRW)
                            .setTimeZone(Constants.TIMEZONE_ASIA_SEOUL)
                            .build();

            CreateCustomerClientResponse response =
                    client.createCustomerClient(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), customer);
            return client.getCustomer(response.getResourceName());
        }
    }

    /**
     * CustomerClient 조회
     * @param customerClientId
     * @return
     */
    public CustomerClient getCustomerClient(long customerClientId) {
        try (CustomerClientServiceClient client = googleAdsClient.getLatestVersion().createCustomerClientServiceClient()) {
            return client.getCustomerClient(ResourceNames.customerClient(Constants.GOOGLE_ROOT_CUSTOMER_ID, customerClientId));
        }
    }


    /**
     * 서브 Customer List 조회
     * @param customerId
     * @return
     */
    public List<CustomerClient> getSubCustomers(long customerId) {
        try (GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            List<CustomerClient> subCustomerResourceNames = new ArrayList<>();

            String query = "SELECT customer_client.client_customer, customer_client.level, "
                    + "customer_client.manager, customer_client.descriptive_name, "
                    + "customer_client.currency_code, customer_client.time_zone, "
                    + "customer_client.id "
                    + "FROM customer_client "
                    + "WHERE customer_client.level <= 1";

            GoogleAdsServiceClient.SearchPagedResponse response =
                    googleAdsServiceClient.search(SearchGoogleAdsRequest.newBuilder()
                            .setQuery(query)
                            .setCustomerId(String.valueOf(customerId))
                            .build());

            for (GoogleAdsRow googleAdsRow : response.iterateAll()) {
                subCustomerResourceNames.add(googleAdsRow.getCustomerClient());
            }
            return subCustomerResourceNames;
        }
    }

    public List<CustomerClient> getSubCustomers() {
        return getSubCustomers(Constants.GOOGLE_ROOT_CUSTOMER_ID);
    }
}
