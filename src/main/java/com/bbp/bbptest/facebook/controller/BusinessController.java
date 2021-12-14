package com.bbp.bbptest.facebook.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbp.bbptest.facebook.model.AdAccountDto;
import com.bbp.bbptest.facebook.model.BusinessDto;
import com.bbp.bbptest.facebook.service.BusinessService;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Business;

@RequestMapping(value = "/facebook/business")
@Controller
public class BusinessController {

    private BusinessService fbBusinessService;

    public BusinessController(BusinessService fbBusinessService) {
        this.fbBusinessService = fbBusinessService;
    }

    @GetMapping(value = "/{businessId}")
    public ResponseEntity<BusinessDto> getBusiness(@PathVariable("businessId") Long businessId) throws Exception {
        Business business = fbBusinessService.getBusiness(businessId);
        BusinessDto businessDto = BusinessDto.builder()
                .id(Long.parseLong(business.getFieldId()))
                .name(business.getFieldName())
                .build();
        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{businessId}/owned-ad-accounts")
    public ResponseEntity getOwnedAdAccount(@PathVariable("businessId") Long businessId) throws Exception {
        Business business = fbBusinessService.getBusiness(businessId);
        APINodeList<AdAccount> adAccounts = fbBusinessService.getOwnedAdAccounts(business);

        List<AdAccountDto> ownedAdAccounts = adAccounts.stream()
                .map(aa ->
                        AdAccountDto.builder()
                                .id(Long.parseLong(aa.getFieldId()))
                                .accountId(Long.parseLong(aa.getFieldAccountId()))
                                .accountStatus(aa.getFieldAccountStatus())
                                .build())
                .collect(Collectors.toList());

        BusinessDto businessDto = BusinessDto.builder()
                .id(Long.parseLong(business.getFieldId()))
                .name(business.getFieldName())
                .ownedAdAccounts(ownedAdAccounts)
                .build();

        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }
}
