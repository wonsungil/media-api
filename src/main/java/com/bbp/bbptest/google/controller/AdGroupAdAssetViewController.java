package com.bbp.bbptest.google.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.AdGroupAdAssetViewDto;
import com.bbp.bbptest.google.model.mapper.AdGroupAdAssetViewMapper;
import com.bbp.bbptest.google.service.AdGroupAdAssetViewService;
import com.google.ads.googleads.v9.resources.AdGroupAdAssetView;

@RequestMapping(value = "/google/adGroupAdAssetView")
@Controller
public class AdGroupAdAssetViewController {

    private AdGroupAdAssetViewService ggAdGroupAdAssetViewService;
    private AdGroupAdAssetViewMapper adGroupAdAssetViewMapper;

    public AdGroupAdAssetViewController(AdGroupAdAssetViewService ggAdGroupAdAssetViewService,
                                        AdGroupAdAssetViewMapper adGroupAdAssetViewMapper) {
        this.ggAdGroupAdAssetViewService = ggAdGroupAdAssetViewService;
        this.adGroupAdAssetViewMapper = adGroupAdAssetViewMapper;
    }

    @GetMapping(value = "/adGroupAdAssetViews/{customerId}")
    public @ResponseBody ResponseEntity<List<AdGroupAdAssetViewDto>> getAdGroupAdAssetViews(@PathVariable("customerId") long customerId) {
        List<AdGroupAdAssetView> adGroupAdAssetViewList =
                ggAdGroupAdAssetViewService.getAdGroupAdAssetViews(customerId);
        List<AdGroupAdAssetViewDto> adGroupAdAssetViewDtoList =
                adGroupAdAssetViewList.stream()
                .map(adGroupAdAssetViewMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<AdGroupAdAssetViewDto>>(adGroupAdAssetViewDtoList, HttpStatus.OK);
    }
}
