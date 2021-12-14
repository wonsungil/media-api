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

import com.bbp.bbptest.google.model.CampaignAssetSetDto;
import com.bbp.bbptest.google.model.CampaignAssetSetReq;
import com.bbp.bbptest.google.model.mapper.CampaignAssetSetMapper;
import com.bbp.bbptest.google.service.CampaignAssetSetService;
import com.google.ads.googleads.v9.resources.CampaignAssetSet;

@RequestMapping(value = "google/campaign-asset-set")
@Controller
public class CampaignAssetSetController {
    private CampaignAssetSetService ggCampaignAssetSetService;
    private CampaignAssetSetMapper campaignAssetSetMapper;

    public CampaignAssetSetController(CampaignAssetSetService ggCampaignAssetSetService,
                                      CampaignAssetSetMapper campaignAssetSetMapper) {
        this.ggCampaignAssetSetService = ggCampaignAssetSetService;
        this.campaignAssetSetMapper = campaignAssetSetMapper;
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<CampaignAssetSetDto> create(@RequestBody CampaignAssetSetReq param) {
        String rn = ggCampaignAssetSetService.create(param);
        CampaignAssetSet campaignAssetSet = ggCampaignAssetSetService.getCampaignAssetSet(
                param.getCustomerId(), param.getCampaignId(), param.getAssetSetId());

        CampaignAssetSetDto campaignAssetSetDto = campaignAssetSetMapper.toDto(campaignAssetSet);

        return new ResponseEntity<CampaignAssetSetDto>(campaignAssetSetDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{campaignId}/{assetSetId}")
    public @ResponseBody ResponseEntity<CampaignAssetSetDto> getCampaignAssetSet(
            @PathVariable("customerId") long customerId,
            @PathVariable("campaignId") long campaignId,
            @PathVariable("assetSetId") long assetSetId) {
        CampaignAssetSet campaignAssetSet = ggCampaignAssetSetService.getCampaignAssetSet(customerId, campaignId, assetSetId);
        CampaignAssetSetDto campaignAssetSetDto = campaignAssetSetMapper.toDto(campaignAssetSet);
        return new ResponseEntity<CampaignAssetSetDto>(campaignAssetSetDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}")
    public @ResponseBody ResponseEntity<List<CampaignAssetSetDto>> getCampaignAssetSets(
            @PathVariable("customerId") long customerId) {

        List<CampaignAssetSet> campaignAssetSetList = ggCampaignAssetSetService.getCampaignAssetSets(customerId);
        List<CampaignAssetSetDto> campaignAssetSetDtoList = campaignAssetSetList
                .stream()
                .map(campaignAssetSetMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<CampaignAssetSetDto>>(campaignAssetSetDtoList, HttpStatus.OK);
    }

}
