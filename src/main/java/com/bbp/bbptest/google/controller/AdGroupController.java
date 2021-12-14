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

import com.bbp.bbptest.google.model.AdGroupDto;
import com.bbp.bbptest.google.model.AdGroupReq;
import com.bbp.bbptest.google.model.mapper.AdGroupMapper;
import com.bbp.bbptest.google.service.AdGroupService;
import com.google.ads.googleads.v9.resources.AdGroup;

@RequestMapping(value = "/google/ad-group")
@Controller
public class AdGroupController {

    private final AdGroupService ggAdGroupService;
    private final AdGroupMapper adGroupMapper;

    public AdGroupController(AdGroupService ggAdGroupService, AdGroupMapper adGroupMapper) {
        this.ggAdGroupService = ggAdGroupService;
        this.adGroupMapper = adGroupMapper;
    }

    @GetMapping(value = "/allAdGroups/{customerId}")
    public @ResponseBody ResponseEntity<List<AdGroupDto>> allAdGroups(
            @PathVariable("customerId") Long customerId) {
        List<AdGroup> adgroupList = ggAdGroupService.getAdGroups(customerId);
        List<AdGroupDto> adGroupDtoList = adgroupList.stream()
                                                     .map(adGroupMapper::toDto)
                                                     .collect(Collectors.toList());
        return new ResponseEntity<List<AdGroupDto>>(adGroupDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/{adGroupId}")
    public @ResponseBody ResponseEntity<AdGroupDto> getAdSet(
            @PathVariable("customerId") Long customerId
            , @PathVariable("adGroupId") Long adGroupId) {
        AdGroup adGroup = ggAdGroupService.getAdGroup(customerId, adGroupId);
        AdGroupDto adGroupDto = adGroupMapper.toDto(adGroup);
        return new ResponseEntity<AdGroupDto>(adGroupDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}")
    public @ResponseBody ResponseEntity getAdSetList(@PathVariable("customerId") Long customerId) {
        List<AdGroup> adGroups = ggAdGroupService.getAdGroups(customerId);
        List<AdGroupDto> adGroupDtoList = adGroups
                .stream()
                .map(adGroupMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity(adGroupDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<AdGroupDto> create(@RequestBody AdGroupReq param) {
        AdGroupDto adGroupDto = adGroupMapper.toDto(ggAdGroupService.create(param));
        return new ResponseEntity<AdGroupDto>(adGroupDto, HttpStatus.OK);
    }
}
