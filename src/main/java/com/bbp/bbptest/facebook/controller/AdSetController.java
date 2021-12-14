package com.bbp.bbptest.facebook.controller;

import com.bbp.bbptest.facebook.model.CampaignDto;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bbp.bbptest.facebook.model.AdSetDto;
import com.bbp.bbptest.facebook.model.AdSetReq;
import com.bbp.bbptest.facebook.model.mapper.AdSetMapper;
import com.bbp.bbptest.facebook.service.AdSetService;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdSet;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping(value = "/facebook/ad-set")
@Controller
public class AdSetController {

    private AdSetService fbAdSetService;
    private AdSetMapper adSetMapper;

    public AdSetController(AdSetService fbAdSetService, AdSetMapper adSetMapper) {
        this.fbAdSetService = fbAdSetService;
        this.adSetMapper = adSetMapper;
    }

    /**
     * 광고세트 리스트 조회
     * @param adAccountId
     * @return
     * @throws APIException
     */
    @GetMapping(value = "/{adAccountId}/adsets")
    public ResponseEntity<List<AdSetDto>> getAdSets(@PathVariable("adAccountId") Long adAccountId)
            throws APIException {
        APINodeList<AdSet> adSetAPINodeList = fbAdSetService.getAdSets(adAccountId);
        List<AdSetDto> adSetList =
                adSetAPINodeList
                        .stream()
                        .map(at -> AdSetDto.builder()
                                .name(at.getFieldName())
                                .configuredStatus(at.getFieldConfiguredStatus().name())
                                .effectiveStatus(at.getFieldEffectiveStatus().name())
                                .id(at.getFieldId())
                                .build()
                        )
                        .collect(Collectors.toList());
        return new ResponseEntity<List<AdSetDto>>(adSetList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AdSetDto> create(@RequestBody AdSetReq req) throws APIException {
        log.info(req.toString());
        AdSet create = fbAdSetService.create(req);
        AdSetDto adSetDto = adSetMapper.toDto(create);
        return new ResponseEntity<AdSetDto>(adSetDto, HttpStatus.OK);
    }
}
