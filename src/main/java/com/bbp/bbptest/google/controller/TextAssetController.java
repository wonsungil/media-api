package com.bbp.bbptest.google.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.TextAssetDto;
import com.bbp.bbptest.google.model.mapper.TextAssetMapper;
import com.bbp.bbptest.google.service.AssetService;
import com.google.ads.googleads.v9.resources.Asset;

@RequestMapping("/google/text-asset")
@Controller
public class TextAssetController {

    private AssetService ggAssetService;
    private TextAssetMapper textAssetMapper;

    public TextAssetController(AssetService ggAssetService,
                               TextAssetMapper textAssetMapper) {
        this.ggAssetService = ggAssetService;
        this.textAssetMapper = textAssetMapper;
    }

    @GetMapping(value = "/assets/{customerId}")
    public @ResponseBody ResponseEntity<List<TextAssetDto>> getAssets(@PathVariable("customerId") long customerId) {
        List<Asset> assets = ggAssetService.getAssets(customerId, Collections.singletonList("TEXT"));
        List<TextAssetDto> assetDtoList = assets
                .stream()
                .map(textAssetMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<TextAssetDto>>(assetDtoList, HttpStatus.OK);
    }
}
