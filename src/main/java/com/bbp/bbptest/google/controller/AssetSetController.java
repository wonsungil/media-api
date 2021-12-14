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

import com.bbp.bbptest.google.model.AssetSetDto;
import com.bbp.bbptest.google.model.AssetSetReq;
import com.bbp.bbptest.google.model.mapper.AssetSetMapper;
import com.bbp.bbptest.google.service.AssetSetService;
import com.google.ads.googleads.v9.resources.AssetSet;

@RequestMapping(value = "/google/asset-set")
@Controller
public class AssetSetController {

    private AssetSetService ggAssetSetService;
    private AssetSetMapper assetSetMapper;

    public AssetSetController(AssetSetService ggAssetSetService,
                              AssetSetMapper assetSetMapper) {
        this.ggAssetSetService = ggAssetSetService;
        this.assetSetMapper = assetSetMapper;
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity create(@RequestBody AssetSetReq param) {
        String rn = ggAssetSetService.create(param);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "/assetSets/{customerId}")
    public @ResponseBody ResponseEntity<List<AssetSetDto>> getAssetSets(@PathVariable("customerId") long customerId) {
        List<AssetSet> assetSetList = ggAssetSetService.getAssetSets(customerId);
        List<AssetSetDto> assetSetDtoList = assetSetList
                .stream()
                .map(assetSetMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<AssetSetDto>>(assetSetDtoList, HttpStatus.OK);
    }
    @GetMapping(value = "/assetSet/{customerId}/{assetSetId}")
    public @ResponseBody ResponseEntity<AssetSetDto> getAssetSet(
            @PathVariable("customerId") long customerId,
            @PathVariable("assetSetId") long assetSetId) {

        AssetSet assetSet = ggAssetSetService.getAssetSet(customerId, assetSetId);
        AssetSetDto assetSetDto = assetSetMapper.toDto(assetSet);

        return new ResponseEntity<AssetSetDto>(assetSetDto, HttpStatus.OK);
    }
}
