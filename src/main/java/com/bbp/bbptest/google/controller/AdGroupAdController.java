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

import com.bbp.bbptest.google.model.AdDto;
import com.bbp.bbptest.google.model.AdGroupAdDto;
import com.bbp.bbptest.google.model.AdGroupAdReq;
import com.bbp.bbptest.google.model.mapper.AdGroupAdMapper;
import com.bbp.bbptest.google.model.mapper.AdMapper;
import com.bbp.bbptest.google.service.AdGroupAdService;
import com.google.ads.googleads.v9.resources.Ad;
import com.google.ads.googleads.v9.resources.AdGroupAd;

@RequestMapping(value = "/google/ad-group-ad")
@Controller
public class AdGroupAdController {

    private final AdGroupAdService ggAdGroupAdService;
    private final AdMapper adMapper;
    private final AdGroupAdMapper adGroupAdMapper;

    public AdGroupAdController(AdGroupAdService ggAdGroupAdService,
                               AdMapper adMapper,
                               AdGroupAdMapper adGroupAdMapper) {
        this.ggAdGroupAdService = ggAdGroupAdService;
        this.adMapper = adMapper;
        this.adGroupAdMapper = adGroupAdMapper;
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity create(@RequestBody AdGroupAdReq param) {
        AdGroupAd adGroupAd = ggAdGroupAdService.create(param);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "/ads/{customerId}")
    public @ResponseBody ResponseEntity<List<AdDto>> getAds(@PathVariable("customerId") long customerId) {
        List<Ad> adList = ggAdGroupAdService.getAds(customerId);
        for (Ad ad : adList) {
            System.out.println(ad.toString());
        }
        List<AdDto> adDtoList = adList.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<AdDto>>(adDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/adGroupAds/{customerId}")
    public @ResponseBody ResponseEntity<List<AdGroupAdDto>> getAdGroupAds(@PathVariable("customerId") long customerId) {
        List<AdGroupAd> adGroupAdList = ggAdGroupAdService.getAdGroupAds(customerId);
        List<AdGroupAdDto> adGroupAdDtoList = adGroupAdList.stream()
                                      .map(adGroupAdMapper::toDto)
                                      .collect(Collectors.toList());
        return new ResponseEntity<List<AdGroupAdDto>>(adGroupAdDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{adGroupId}/{adId}")
    public @ResponseBody ResponseEntity<AdGroupAdDto> getAdGroupAd(
            @PathVariable("customerId") long customerId,
            @PathVariable("adGroupId") long adGroupId,
            @PathVariable("adId") long adId) {

        AdGroupAd adGroupAd = ggAdGroupAdService.getAdGroupAd(customerId, adGroupId, adId);
        AdGroupAdDto adGroupAdDto = adGroupAdMapper.toDto(adGroupAd);
        return new ResponseEntity<AdGroupAdDto>(adGroupAdDto, HttpStatus.OK);
    }
}
