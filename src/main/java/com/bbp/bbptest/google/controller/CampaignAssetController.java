package com.bbp.bbptest.google.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.service.CampaignAssetService;
import com.google.ads.googleads.v9.resources.CampaignAsset;

@RequestMapping(value = "/google/campaign-asset")
@Controller
public class CampaignAssetController {

    private CampaignAssetService ggCampaignAssetService;

    public CampaignAssetController(CampaignAssetService ggCampaignAssetService) {
        this.ggCampaignAssetService = ggCampaignAssetService;
    }

    @GetMapping(value = "/campaignAssets/{customerId}")
    public @ResponseBody
    ResponseEntity getCampaignAssets(@PathVariable("customerId") long customerId) {
        List<CampaignAsset> campaignAssetList = ggCampaignAssetService.getCampaignAssets(customerId);

        for (CampaignAsset campaignAsset : campaignAssetList) {
            System.out.println(campaignAsset.toString());
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }


}
