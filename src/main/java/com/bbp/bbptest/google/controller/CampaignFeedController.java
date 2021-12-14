package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.CampaignFeedDto;
import com.bbp.bbptest.google.model.mapper.CampaignFeedMapper;
import com.bbp.bbptest.google.service.CampaignFeedService;
import com.google.ads.googleads.v9.resources.CampaignFeed;

@RequestMapping(value = "/google/campaignFeed")
@Controller
public class CampaignFeedController {

    private CampaignFeedService ggCampaignFeedService;
    private CampaignFeedMapper campaignFeedMapper;

    public CampaignFeedController(CampaignFeedService ggCampaignFeedService,
                                  CampaignFeedMapper campaignFeedMapper) {
        this.ggCampaignFeedService = ggCampaignFeedService;
        this.campaignFeedMapper = campaignFeedMapper;
    }

    @GetMapping(value = "/campaignFeeds/{customerId}")
    public @ResponseBody
    ResponseEntity getCampaginFeeds(@PathVariable("customerId") long customerId) {
        ggCampaignFeedService.getAllCampaignFeed(customerId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "/campaignFeeds/{customerId}/{campaignId}/{feedId}")
    public @ResponseBody ResponseEntity<CampaignFeedDto> getCampaginFeed(
            @PathVariable("customerId") long customerId,
            @PathVariable("campaignId") long campaignId,
            @PathVariable("feedId") long feedId) {

        CampaignFeed campaignFeed = ggCampaignFeedService.getCampaignFeed(customerId, campaignId, feedId);
        CampaignFeedDto campaignFeedDto = campaignFeedMapper.toDto(campaignFeed);
        return new ResponseEntity<CampaignFeedDto>(campaignFeedDto, HttpStatus.OK);
    }
}
