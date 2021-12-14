package com.bbp.bbptest.google.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.AdGroupCriterionDto;
import com.bbp.bbptest.google.model.AdGroupTargetingReq;
import com.bbp.bbptest.google.model.CampaignCriterionDto;
import com.bbp.bbptest.google.model.CampaignTargetingReq;
import com.bbp.bbptest.google.model.mapper.AdGroupCriterionMapper;
import com.bbp.bbptest.google.model.mapper.CampaignCriterionMapper;
import com.bbp.bbptest.google.service.AdGroupTargetingService;
import com.bbp.bbptest.google.service.CampaignTargetingService;
import com.google.ads.googleads.v9.resources.AdGroupCriterion;
import com.google.ads.googleads.v9.resources.CampaignCriterion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/google/targeting")
@Controller(value = "ggTargetingController")
public class TargetingController {

    private final CampaignTargetingService ggCampaignTargetingService;
    private final AdGroupTargetingService ggAdGroupTargetingService;
    private final CampaignCriterionMapper campaignCriterionMapper;
    private final AdGroupCriterionMapper adGroupCriterionMapper;

    public TargetingController(CampaignTargetingService ggCampaignTargetingService,
                               AdGroupTargetingService ggAdGroupTargetingService,
                               CampaignCriterionMapper campaignCriterionMapper,
                               AdGroupCriterionMapper adGroupCriterionMapper) {
        this.ggCampaignTargetingService = ggCampaignTargetingService;
        this.ggAdGroupTargetingService = ggAdGroupTargetingService;
        this.campaignCriterionMapper = campaignCriterionMapper;
        this.adGroupCriterionMapper = adGroupCriterionMapper;
    }


    @PostMapping(value = "/campaign/create")
    public @ResponseBody ResponseEntity creatCampaignCriterion(@RequestBody CampaignTargetingReq param) {
        ggCampaignTargetingService.addTargeting(param);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/campaign/{customerId}/{campaignId}")
    public @ResponseBody ResponseEntity getCampaignCriterion(
            @PathVariable("customerId") long customerId,
            @PathVariable("campaignId") long campaignId) {
        List<CampaignCriterion> campaignCriterionList = ggCampaignTargetingService
                .getCampaignCriterion(customerId, campaignId);
        List<CampaignCriterionDto> campaignCriterionDtoList =
                campaignCriterionList.stream()
                .map(campaignCriterionMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(campaignCriterionDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/adGroup/create")
    public @ResponseBody ResponseEntity createAdGroupCriterion(@RequestBody AdGroupTargetingReq param) {
        ggAdGroupTargetingService.addAdGroupTargeting(param);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/adGroup/{customerId}/{adGroupId}")
    public @ResponseBody ResponseEntity<AdGroupCriterionDto> getAdGroupCriterion(
            @PathVariable("customerId") long customerId,
            @PathVariable("adGroupId") long adGroupId) {
        List<AdGroupCriterion> campaignCriterionList = ggAdGroupTargetingService.getAdGroupCriterion(customerId, adGroupId);
        List<AdGroupCriterionDto> adGroupCriterionDtos =
                campaignCriterionList.stream()
                                     .map(adGroupCriterionMapper::toDto)
                                     .collect(Collectors.toList());

        return new ResponseEntity<AdGroupCriterionDto>((AdGroupCriterionDto) null, HttpStatus.OK);
    }
}
