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

import com.bbp.bbptest.google.model.CampaignDto;
import com.bbp.bbptest.google.model.CampaignReq;
import com.bbp.bbptest.google.model.mapper.CampaignMapper;
import com.bbp.bbptest.google.service.CampaignService;
import com.google.ads.googleads.v9.resources.Campaign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/google/campaign")
@Controller(value = "ggCampaignController")
public class CampaignController {

    private final CampaignService ggCampaignService;
    private final CampaignMapper campaignMapper;

    public CampaignController(CampaignService ggCampaignService, CampaignMapper campaignMapper) {
        this.ggCampaignService = ggCampaignService;
        this.campaignMapper = campaignMapper;
    }

    @GetMapping(value = "/allCampaigns/{customerId}")
    public @ResponseBody ResponseEntity<List<CampaignDto>> getAllCampaigns(
            @PathVariable("customerId") long customerId) {
        List<Campaign> campaignList = ggCampaignService.getAllCampaigns(customerId);
        List<CampaignDto> campaignDtoList = campaignList.stream()
                                                        .map(campaignMapper::toDto)
                                                        .collect(Collectors.toList());
        return new ResponseEntity<List<CampaignDto>>(campaignDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<CampaignDto> create(@RequestBody CampaignReq param) {
        String created = ggCampaignService.create(param);
        Campaign campaign = ggCampaignService.getCampaign(created);
        CampaignDto campaignDto = campaignMapper.toDto(campaign);
        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{campaignId}")
    public @ResponseBody ResponseEntity<CampaignDto> getCampaign(
            @PathVariable(name = "customerId") long customerId,
            @PathVariable(name = "campaignId") long campaignId) {
        Campaign campaign = ggCampaignService.getCampaign(customerId, campaignId);
        CampaignDto campaignDto = campaignMapper.toDto(campaign);
        System.out.println(campaign.toString());
        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public @ResponseBody ResponseEntity<CampaignDto> updateCampaign(@RequestBody CampaignReq param) {
//        ggCampaignService.updateBiddingStrategy(param);
        ggCampaignService.updateCampaign(param);
        Campaign campaign = ggCampaignService.getCampaign(param.getCustomerId(), param.getCampaignId());
        CampaignDto campaignDto = campaignMapper.toDto(campaign);
        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }
}
