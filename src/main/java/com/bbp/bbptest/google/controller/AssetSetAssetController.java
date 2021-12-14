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

import com.bbp.bbptest.google.model.AssetSetAssetDto;
import com.bbp.bbptest.google.model.AssetSetAssetReq;
import com.bbp.bbptest.google.model.mapper.AssetSetAssetMapper;
import com.bbp.bbptest.google.service.AssetSetAssetService;
import com.google.ads.googleads.v9.resources.AssetSetAsset;

@RequestMapping(value = "/google/asset-set-asset")
@Controller
public class AssetSetAssetController {

    private AssetSetAssetService ggAssetSetAssetService;
    private AssetSetAssetMapper assetSetAssetMapper;

    public AssetSetAssetController(AssetSetAssetService ggAssetSetAssetService,
                                   AssetSetAssetMapper assetSetAssetMapper) {
        this.ggAssetSetAssetService = ggAssetSetAssetService;
        this.assetSetAssetMapper = assetSetAssetMapper;
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity create(@RequestBody AssetSetAssetReq param) {
        String rn = ggAssetSetAssetService.create(param);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{assetSetId}/{assetSetAssetId}")
    public @ResponseBody ResponseEntity<AssetSetAssetDto> getAssetSetAsset(
            @PathVariable("customerId") long customerId,
            @PathVariable("assetSetId") long assetSetId,
            @PathVariable("assetSetAssetId") long assetSetAssetId) {

        AssetSetAsset assetSetAsset = ggAssetSetAssetService.getAssetSetAsset(customerId, assetSetId, assetSetAssetId);
        AssetSetAssetDto assetSetAssetDto = assetSetAssetMapper.toDto(assetSetAsset);
        return new ResponseEntity<AssetSetAssetDto>(assetSetAssetDto, HttpStatus.OK);
    }
}
