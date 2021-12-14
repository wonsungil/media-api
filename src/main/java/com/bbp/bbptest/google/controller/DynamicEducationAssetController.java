package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.DynamicEducationAssetDto;
import com.bbp.bbptest.google.model.DynamicEducationAssetReq;
import com.bbp.bbptest.google.model.mapper.DynamicEducationAssetMapper;
import com.bbp.bbptest.google.service.AssetService;
import com.google.ads.googleads.v9.resources.Asset;

@RequestMapping(value = "/google/dynamic-education-asset")
@Controller
public class DynamicEducationAssetController {

    private final AssetService ggAssetService;
    private final DynamicEducationAssetMapper dynamicEducationAssetMapper;

    public DynamicEducationAssetController(AssetService ggAssetService,
                                           DynamicEducationAssetMapper dynamicEducationAssetMapper) {
        this.ggAssetService = ggAssetService;
        this.dynamicEducationAssetMapper = dynamicEducationAssetMapper;
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<DynamicEducationAssetDto> create(@RequestBody DynamicEducationAssetReq param) {
        String rn = ggAssetService.create(param);
        Asset deAsset = ggAssetService.getAsset(rn);
        DynamicEducationAssetDto ded = dynamicEducationAssetMapper.toDto(deAsset);
        return new ResponseEntity<DynamicEducationAssetDto>(ded, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{assetId}")
    public @ResponseBody ResponseEntity<DynamicEducationAssetDto> getAsset(
            @PathVariable("customerId") long customerId,
            @PathVariable("assetId") long assetId) {
        Asset asset = ggAssetService.getAsset(customerId, assetId);
        DynamicEducationAssetDto ded = dynamicEducationAssetMapper.toDto(asset);
        return new ResponseEntity<DynamicEducationAssetDto>(ded, HttpStatus.OK);
    }
}
