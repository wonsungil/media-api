package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.AdDto;
import com.bbp.bbptest.google.model.mapper.AdMapper;
import com.bbp.bbptest.google.service.AdService;
import com.google.ads.googleads.v9.resources.Ad;

@RequestMapping(value = "/google/ad")
@Controller
public class AdController {

    private AdService ggAdService;
    private AdMapper adMapper;

    public AdController(AdService ggAdService,
                        AdMapper adMapper) {
        this.ggAdService = ggAdService;;
        this.adMapper = adMapper;
    }

    @GetMapping(value = "/{customerId}/{adId}")
    public @ResponseBody
    ResponseEntity<AdDto> getAd(@PathVariable("customerId") long customerId,
                         @PathVariable("adId") long adId) {
        Ad ad = ggAdService.getAd(customerId, adId);;
        AdDto adDto = adMapper.toDto(ad);
        return new ResponseEntity<AdDto>(adDto, HttpStatus.OK);
    }
}
