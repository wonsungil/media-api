package com.bbp.bbptest.facebook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbp.bbptest.facebook.model.AdAccountDto;
import com.bbp.bbptest.facebook.model.AdAccountReq;
import com.bbp.bbptest.facebook.model.mapper.AdAccountMapper;
import com.bbp.bbptest.facebook.service.AdAccountService;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdAccount;

@RequestMapping(value = "/facebook/ad-account")
@Controller
public class AdAccountController {

    private AdAccountService fbAdAccountService;
    private AdAccountMapper adAccountMapper;

    public AdAccountController(AdAccountService fbAdAccountService, AdAccountMapper adAccountMapper) {
        this.fbAdAccountService = fbAdAccountService;
        this.adAccountMapper = adAccountMapper;
    }

    @GetMapping(value = "/{adAccountId}")
    public ResponseEntity<AdAccountDto> getAdAccount(@PathVariable("adAccountId") Long adAccountId) throws Exception {
        AdAccount adAccount = fbAdAccountService.getAdAccount(adAccountId);
        AdAccountDto adAccountDto = adAccountMapper.toDto(adAccount);
//        AdAccountMapperImpl
//                AdAccountDto.builder()
//                            .id(Long.parseLong(adAccount.getFieldId()))
//                            .accountId(Long.parseLong(adAccount.getFieldAccountId()))
//                            .accountStatus(adAccount.getFieldAccountStatus())
//                            .name(adAccount.getFieldName())
//                            .timeZoneId(adAccount.getFieldTimezoneId())
//                            .currency(adAccount.getFieldCurrency())
//                            .partner(adAccount.getFieldPartner())
//                            .endAdvertiser(adAccount.getFieldEndAdvertiser())
//                            .mediaAgency(adAccount.getFieldMediaAgency())
//                            .build();

        return new ResponseEntity<AdAccountDto>(adAccountDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<AdAccountDto> create(@RequestBody AdAccountReq req) throws APIException {
        AdAccount adAccount = fbAdAccountService.create(req.getBusinessId(), req.getName(), req.getTimeZoneId()
                , req.getCurrency(), req.getPartner(), req.getEndAdvertiser(), req.getMediaAgency(), req.isInvoice());

        AdAccountDto adAccountDto =
                AdAccountDto.builder()
                            .id(Long.parseLong(adAccount.getFieldId()))
                            .accountId(Long.parseLong(adAccount.getFieldAccountId()))
                            .accountStatus(adAccount.getFieldAccountStatus())
                            .name(adAccount.getFieldName())
                            .timeZoneId(adAccount.getFieldTimezoneId())
                            .currency(adAccount.getFieldCurrency())
                            .partner(adAccount.getFieldPartner())
                            .endAdvertiser(adAccount.getFieldEndAdvertiser())
                            .mediaAgency(adAccount.getFieldMediaAgency())
                            .build();

        return new ResponseEntity<AdAccountDto>(adAccountDto, HttpStatus.OK);
    }
}
