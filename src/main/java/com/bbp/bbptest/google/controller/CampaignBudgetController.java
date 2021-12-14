package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.CampaignBudgetDto;
import com.bbp.bbptest.google.model.mapper.CampaignBudgetMapper;
import com.bbp.bbptest.google.service.CampaignBudgetService;
import com.google.ads.googleads.v9.resources.CampaignBudget;

@RequestMapping(value = "/google/campaign-budget")
@Controller
public class CampaignBudgetController {

    private CampaignBudgetService ggCampaignBudgetService;
    private CampaignBudgetMapper campaignBudgetMapper;

    public CampaignBudgetController(CampaignBudgetService ggCampaignBudgetService,
                                    CampaignBudgetMapper campaignBudgetMapper) {
        this.ggCampaignBudgetService = ggCampaignBudgetService;
        this.campaignBudgetMapper = campaignBudgetMapper;
    }

    @GetMapping(value = "/{customerId}/{campaignBudgetId}")
    public @ResponseBody
    ResponseEntity<CampaignBudgetDto> getCampaignBudget(
            @PathVariable(name = "customerId") long customerId,
            @PathVariable(name = "campaignBudgetId") long campaignBudgetId) {

        CampaignBudget campaignBudget = ggCampaignBudgetService.getCampaignBudget(customerId, campaignBudgetId);
        CampaignBudgetDto campaignBudgetDto = campaignBudgetMapper.toDto(campaignBudget);

        return new ResponseEntity<CampaignBudgetDto>(campaignBudgetDto, HttpStatus.OK);
    }

}
