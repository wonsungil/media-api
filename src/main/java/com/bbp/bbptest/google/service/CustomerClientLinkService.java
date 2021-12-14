package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.config.Constants;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.lib.utils.FieldMasks;
import com.google.ads.googleads.v9.enums.ManagerLinkStatusEnum;
import com.google.ads.googleads.v9.resources.CustomerClientLink;
import com.google.ads.googleads.v9.services.CustomerClientLinkOperation;
import com.google.ads.googleads.v9.services.CustomerClientLinkServiceClient;
import com.google.ads.googleads.v9.services.GoogleAdsRow;
import com.google.ads.googleads.v9.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v9.services.MutateCustomerClientLinkResponse;
import com.google.ads.googleads.v9.utils.ResourceNames;

/**
 * CustomerClientLink Service
 */
@Service(value = "ggCustomerClientLinkService")
public class CustomerClientLinkService {

    private final GoogleAdsClient googleAdsClient;

    public CustomerClientLinkService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    /**
     * CustomerClientId로 CustomerClientLink 조회
     * @param customerClientId
     * @return
     */
    public CustomerClientLink getCustomerClientListByCustomerClientId(long customerClientId) {
        String query = "SELECT customer_client_link.resource_name, " +
                "customer_client_link.status, " +
                "customer_client_link.client_customer, " +
                "customer_client_link.manager_link_id " +
                "FROM customer_client_link " +
                "WHERE customer_client_link.client_customer = '" + ResourceNames.customer(customerClientId) +"'";

        try (GoogleAdsServiceClient client = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), query);
            GoogleAdsRow result = response.iterateAll().iterator().next();
            return result.getCustomerClientLink();
        }
    }

    public CustomerClientLink modifyStatus(long customerClientLink, long managerLinkId, String status) {
        try (CustomerClientLinkServiceClient client =
                     googleAdsClient.getLatestVersion().createCustomerClientLinkServiceClient()) {

            // 변경 전
            CustomerClientLink oldLink =
                    client.getCustomerClientLink(ResourceNames.customerClientLink(Constants.GOOGLE_ROOT_CUSTOMER_ID, customerClientLink, managerLinkId));
            // 변경 후
            CustomerClientLink updateLink =
                    oldLink.toBuilder().setStatus(ManagerLinkStatusEnum.ManagerLinkStatus.valueOf(status)).build();

            CustomerClientLinkOperation linkUpdateOp =
                    CustomerClientLinkOperation.newBuilder()
                            .setUpdate(updateLink)
                            .setUpdateMask(FieldMasks.compare(oldLink, updateLink))
                            .build();

            MutateCustomerClientLinkResponse response =
                    client.mutateCustomerClientLink(String.valueOf(Constants.GOOGLE_ROOT_CUSTOMER_ID), linkUpdateOp);
            return client.getCustomerClientLink(response.getResult().getResourceName());
        }
    }
}