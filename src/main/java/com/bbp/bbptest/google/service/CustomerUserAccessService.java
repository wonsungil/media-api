package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.enums.AccessRoleEnum;
import com.google.ads.googleads.v9.resources.CustomerUserAccessInvitation;
import com.google.ads.googleads.v9.services.CustomerUserAccessInvitationOperation;
import com.google.ads.googleads.v9.services.CustomerUserAccessInvitationServiceClient;
import com.google.ads.googleads.v9.services.MutateCustomerUserAccessInvitationResponse;

/**
 * CustomerUserAccess Service
 */
@Service(value = "ggCustomerUserAccessService")
public class CustomerUserAccessService {

    private final GoogleAdsClient googleAdsClient;

    public CustomerUserAccessService(GoogleAdsClient googleAdsClient) {
        this.googleAdsClient = googleAdsClient;
    }

    /**
     * 사용자 권한 포함 초대
     * @param customerId
     * @param emailAddress
     * @param accessRole
     * @return
     */
    public String inviteUser(long customerId, String emailAddress, String accessRole) {
        try (CustomerUserAccessInvitationServiceClient client = googleAdsClient.getLatestVersion().createCustomerUserAccessInvitationServiceClient()) {
            CustomerUserAccessInvitation invitation =
                    CustomerUserAccessInvitation.newBuilder()
                            .setEmailAddress(emailAddress)
                            .setAccessRole(AccessRoleEnum.AccessRole.valueOf(accessRole))
                            .build();

            CustomerUserAccessInvitationOperation inviteOp =
                    CustomerUserAccessInvitationOperation.newBuilder().setCreate(invitation).build();
            MutateCustomerUserAccessInvitationResponse response =
                    client.mutateCustomerUserAccessInvitation(String.valueOf(customerId), inviteOp);
            return response.getResult().getResourceName();
        }
    }

//    public List<String> getUsers(long customerId) {
//        List<String> userList = new ArrayList<>();
//        try {
//            GoogleAdsClient loginClient = GoogleAdsClient.newBuilder()
//                    .fromPropertiesFile(googleCredential.getFile())
////                    .setLoginCustomerId(customerId)
//                    .setLoginCustomerId(Constants.GOOGLE_ROOT_CUSTOMER_ID)
//                    .build();
//
//            String query = "SELECT " +
//                    "  customer_user_access.user_id, " +
//                    "  customer_user_access.email_address, " +
//                    "  customer_user_access.access_role, " +
//                    "  customer_user_access.access_creation_date_time, " +
//                    "  customer_user_access.inviter_user_email_address " +
//                    "FROM customer_user_access";
//            GoogleAdsServiceClient client = loginClient.getLatestVersion().createGoogleAdsServiceClient();
//
//            GoogleAdsServiceClient.SearchPagedResponse response = client.search(String.valueOf(customerId), query);
//            for (GoogleAdsRow row : response.iterateAll()) {
//                userList.add(row.toString());
//                System.out.println(row.toString());
//            }
//        } catch (IOException e) {
//        }
//
//        return userList;
//    }

}
