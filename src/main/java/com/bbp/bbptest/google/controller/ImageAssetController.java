package com.bbp.bbptest.google.controller;

import java.io.IOException;
import java.util.Collections;
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

import com.bbp.bbptest.google.model.ImageAssetDto;
import com.bbp.bbptest.google.model.ImageAssetReq;
import com.bbp.bbptest.google.model.mapper.ImageAssetMapper;
import com.bbp.bbptest.google.service.AssetService;
import com.google.ads.googleads.v9.resources.Asset;

@RequestMapping(value = "/google/image-asset")
@Controller(value = "ggImageAssetController")
public class ImageAssetController {

    private AssetService ggAssetService;
    private ImageAssetMapper imageAssetMapper;

    public ImageAssetController(AssetService ggAssetService,
                                ImageAssetMapper imageAssetMapper) {
        this.imageAssetMapper = imageAssetMapper;
        this.ggAssetService = ggAssetService;
    }

    @GetMapping(value = "/assets/{customerId}")
    public @ResponseBody ResponseEntity<List<ImageAssetDto>> getAssets(@PathVariable("customerId") long customerId) {
        List<Asset> assets = ggAssetService.getAssets(customerId, Collections.singletonList("IMAGE"));
        List<ImageAssetDto> assetDtoList = assets
                .stream()
                .map(imageAssetMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<ImageAssetDto>>(assetDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<ImageAssetDto> create(@RequestBody ImageAssetReq param) throws IOException {
        String rn = ggAssetService.create(param);
        Asset asset = ggAssetService.getAsset(rn);
        ImageAssetDto imageAssetDto = imageAssetMapper.toDto(asset);
        return new ResponseEntity<ImageAssetDto>(imageAssetDto , HttpStatus.OK);
    }
}
