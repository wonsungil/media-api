package com.bbp.bbptest.google.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.service.AdGroupAssetService;
import com.google.ads.googleads.v9.resources.AdGroupAsset;

@RequestMapping(value = "/google/ad-group-asset")
@Controller
public class AdGroupAssetController {

    private AdGroupAssetService ggAdGroupAssetService;

    public AdGroupAssetController(AdGroupAssetService ggAdGroupAssetService) {
        this.ggAdGroupAssetService = ggAdGroupAssetService;
    }

    @GetMapping(value = "/adGroupAssets/{customerId}")
    public @ResponseBody ResponseEntity getAdGroupAssets(@PathVariable("customerId") long customerId) {
        List<AdGroupAsset> adGroupAssetList = ggAdGroupAssetService.getAdGroupAssets(customerId);

        for (AdGroupAsset adGroupAsset : adGroupAssetList) {
            System.out.println(adGroupAsset.toString());
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
