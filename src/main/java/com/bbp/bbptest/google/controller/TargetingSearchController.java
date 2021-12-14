package com.bbp.bbptest.google.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.GeoTargetDto;
import com.bbp.bbptest.google.model.TopicConstantDto;
import com.bbp.bbptest.google.model.UserInterestDto;
import com.bbp.bbptest.google.model.mapper.GeoTargetConstantMapper;
import com.bbp.bbptest.google.model.mapper.TopicConstantMapper;
import com.bbp.bbptest.google.model.mapper.UserInterestMapper;
import com.bbp.bbptest.google.service.TargetingSearchService;
import com.google.ads.googleads.v9.resources.TopicConstant;
import com.google.ads.googleads.v9.resources.UserInterest;
import com.google.ads.googleads.v9.services.GeoTargetConstantSuggestion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/google/targeting-search")
@Controller(value = "ggTargetingSearchController")
public class TargetingSearchController {

    private final TargetingSearchService ggTargetingSearchService;
    private final GeoTargetConstantMapper geoTargetConstantMapper;
    private final TopicConstantMapper topicConstantMapper;
    private final UserInterestMapper userInterestMapper;

    public TargetingSearchController(TargetingSearchService ggTargetingSearchService
            , GeoTargetConstantMapper geoTargetConstantMapper
            , TopicConstantMapper topicConstantMapper
            , UserInterestMapper userInterestMapper) {
        this.ggTargetingSearchService = ggTargetingSearchService;
        this.geoTargetConstantMapper = geoTargetConstantMapper;
        this.topicConstantMapper = topicConstantMapper;
        this.userInterestMapper = userInterestMapper;
    }

    /**
     * 위치 조회
     * @param locale
     * @param countryCode
     * @param locationNames
     * @return
     */
    @GetMapping(value = "/searchGeoTarget")
    public @ResponseBody ResponseEntity<List<GeoTargetDto>> searchGeoTarget(
            @RequestParam(required = true) String locale,
            @RequestParam(required = false) String countryCode,
            @RequestParam(required = false) List<String> locationNames) {

        log.info("LOCALE" +  locale);
        log.info("countryCode" + countryCode);
        log.info("locationNames" + locationNames.toString());

        List<GeoTargetConstantSuggestion> geoTargetConstants = ggTargetingSearchService.getGeoTarget(locale, countryCode, locationNames);
        List<GeoTargetDto> geoTargetDtos = geoTargetConstants
                .stream()
                .map(geoTargetConstantMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<GeoTargetDto>>(geoTargetDtos, HttpStatus.OK);
    }

    /**
     * 상위 토픽 조회
     * @return
     */
    @GetMapping(value = "/searchTopicTarget")
    public @ResponseBody ResponseEntity<List<TopicConstantDto>> searchParentTopicTarget() {
        List<TopicConstant> topicConstants = ggTargetingSearchService.getTopLevelTopicTarget();
        List<TopicConstantDto> topicConstantDtos = topicConstants
                .stream()
                .map(topicConstantMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<TopicConstantDto>>(topicConstantDtos, HttpStatus.OK);
    }

    /**
     * 하위 토픽 조회
     * @param parentTopicId
     * @return
     */
    @GetMapping(value = "/searchTopicTarget/{parentTopicId}")
    public @ResponseBody ResponseEntity<List<TopicConstantDto>> searchChildTopicTarget(@PathVariable("parentTopicId") long parentTopicId) {
        List<TopicConstant> topicConstants = ggTargetingSearchService.getChildTopicTarget(parentTopicId);
        List<TopicConstantDto> topicConstantDtos = topicConstants
                .stream()
                .map(topicConstantMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<TopicConstantDto>>(topicConstantDtos, HttpStatus.OK);
    }

    /**
     * 상위 관심사 조회
     * @return
     */
    @GetMapping(value = "/searchUserInterestTarget")
    public @ResponseBody ResponseEntity<List<UserInterestDto>> searchUserInterestTarget() {
        List<UserInterest> userInterests = ggTargetingSearchService.getTopLevelUserInterestTarget();
        List<UserInterestDto> userInterestDtoList = userInterests
                .stream()
                .map(userInterestMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<UserInterestDto>>(userInterestDtoList, HttpStatus.OK);
    }

    /**
     * 하위 관심사 조회
     * @param parentUserInterestId
     * @return
     */
    @GetMapping(value = "/searchUserInterestTarget/{parentUserInterestId}")
    public @ResponseBody ResponseEntity<List<UserInterestDto>> searchChildUserInterestTarget(
            @PathVariable("parentUserInterestId") long parentUserInterestId) {

        List<UserInterest> userInterests = ggTargetingSearchService
                .getChildUserInterestTarget(parentUserInterestId);
        List<UserInterestDto> userInterestDtoList = userInterests
                .stream()
                .map(userInterestMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<UserInterestDto>>(userInterestDtoList, HttpStatus.OK);
    }



}
