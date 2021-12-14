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

import com.bbp.bbptest.google.model.YoutubeVideoAssetDto;
import com.bbp.bbptest.google.model.mapper.YoutubeVideoAssetMapper;
import com.bbp.bbptest.google.service.AssetService;
import com.google.ads.googleads.v9.resources.Asset;

@RequestMapping(value = "/google/youtube-video-asset")
@Controller
public class YoutubeVideoAssetController {

    private AssetService ggAssetService;
    private YoutubeVideoAssetMapper youtubeVideoAssetMapper;

    public YoutubeVideoAssetController(AssetService ggAssetService,
                                YoutubeVideoAssetMapper youtubeVideoAssetMapper) {
        this.youtubeVideoAssetMapper = youtubeVideoAssetMapper;
        this.ggAssetService = ggAssetService;
    }

    @GetMapping(value = "/assets/{customerId}")
    public @ResponseBody
    ResponseEntity<List<YoutubeVideoAssetDto>> getAssets(@PathVariable("customerId") long customerId) {
        List<Asset> assets = ggAssetService.getAssets(customerId, Collections.singletonList("YOUTUBE_VIDEO"));
        List<YoutubeVideoAssetDto> assetDtoList = assets
                .stream()
                .map(youtubeVideoAssetMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<YoutubeVideoAssetDto>>(assetDtoList, HttpStatus.OK);
    }
}
