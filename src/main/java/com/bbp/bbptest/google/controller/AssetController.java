package com.bbp.bbptest.google.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.AssetDto;
import com.bbp.bbptest.google.service.AssetService;
import com.google.ads.googleads.v9.resources.Asset;

@RequestMapping(value = "/google/asset")
@Controller
public class AssetController {

    private final AssetService ggAssetService;

    public AssetController(AssetService ggAssetService) {
        this.ggAssetService = ggAssetService;
    }

    @GetMapping(value = "/{customerId}/assets")
    public @ResponseBody ResponseEntity<List<AssetDto>> getAssets(@PathVariable("customerId") long customerId) {
        List<Asset> assets = ggAssetService.getAssets(customerId, Collections.EMPTY_LIST);
        for (Asset a : assets) {
            System.out.println(a.toString());
        }
//        List<AssetDto> assetDtoList = assetMapper.toDto(assets);
        return new ResponseEntity<List<AssetDto>>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{assetId}")
    public @ResponseBody ResponseEntity getAsset(
            @PathVariable("customerId") long customerId,
            @PathVariable("assetId") long assetId) {
        Asset asset = ggAssetService.getAsset(customerId, assetId);
//        AssetDto assetDto = assetMapper.toDto(asset);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
