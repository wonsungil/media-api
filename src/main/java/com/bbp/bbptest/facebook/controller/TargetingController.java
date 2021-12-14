package com.bbp.bbptest.facebook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbp.bbptest.facebook.model.DeviceTargetingDto;
import com.bbp.bbptest.facebook.model.GeoTargetingDto;
import com.bbp.bbptest.facebook.model.InterestTargetingDto;
import com.bbp.bbptest.facebook.service.TargetingService;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINode;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.Targeting;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RequestMapping(value = "/facebook/targeting")
@Controller
public class TargetingController {

    private TargetingService fbTargetingService;

    public TargetingController(TargetingService fbTargetingService) {
        this.fbTargetingService = fbTargetingService;
    }

    @GetMapping(value = "/geoTargeting")
    public ResponseEntity<List<GeoTargetingDto>> getGeoTargeting(
            @RequestParam Map<String, Object> params) throws APIException {

        APINodeList<Targeting> tgt = fbTargetingService.searchTargeting(params);

        List<GeoTargetingDto> tgtList =
                tgt.stream()
                            .map(APINode::getRawResponseAsJsonObject)
                            .map(g -> GeoTargetingDto
                                    .builder()
                                    .key(g.get("key").getAsString())
                                    .name(g.get("name").getAsString())
                                    .type(g.get("type").getAsString())
                                    .countryCode(g.get("country_code").getAsString())
                                    .countryName(g.get("country_name").getAsString())
                                    .supportsRegion(g.get("supports_region").getAsBoolean())
                                    .supportCity(g.get("supports_city").getAsBoolean())
                                    .build())
                            .collect(Collectors.toList());

        return new ResponseEntity<List<GeoTargetingDto>>(tgtList, HttpStatus.OK);
    }

    @GetMapping(value = "/interestTargeting")
    public ResponseEntity<List<InterestTargetingDto>> genderTargeting(
            @RequestParam Map<String, Object> params) throws APIException {

        APINodeList<Targeting> tgt = fbTargetingService.searchTargeting(params);

        List<InterestTargetingDto> tgtList =
                tgt.stream()
                            .map(APINode::getRawResponseAsJsonObject)
                            .map(g -> InterestTargetingDto
                                    .builder()
                                    .id(g.get("id").getAsString())
                                    .name(g.get("name").getAsString())
                                    .audienceSizeUpperBound(g.get("audience_size_upper_bound").getAsLong())
                                    .audienceSizeLowerBound(g.get("audience_size_lower_bound").getAsLong())
                                    .path(new Gson().fromJson(g.get("path"), ArrayList.class))
                                    .description(Optional.ofNullable(g.get("description"))
                                                         .filter(JsonElement::isJsonObject)
                                                         .map(JsonElement::getAsString)
                                                         .orElse(""))
                                    .topic(g.get("topic").getAsString())
                                    .build())
                            .collect(Collectors.toList());

        return new ResponseEntity<List<InterestTargetingDto>>(tgtList, HttpStatus.OK);
    }

    @GetMapping(value = "/deviceTargeting")
    public ResponseEntity<List<DeviceTargetingDto>> deviceTargeting(
            @RequestParam Map<String, Object> params) throws APIException {

        APINodeList<Targeting> tgt = fbTargetingService.searchTargeting(params);

        List<DeviceTargetingDto> tgtList =
                tgt.stream()
                   .map(APINode::getRawResponseAsJsonObject)
                   .map(d -> DeviceTargetingDto
                           .builder()
                           .name(d.get("name").getAsString())
                           .description(d.get("description").getAsString())
                           .platform(d.get("platform").getAsString())
                           .audienceSize(d.get("audience_size").getAsLong())
                           .audienceSizeLowerBound(d.get("audience_size_lower_bound").getAsLong())
                           .audienceSizeUpperBound(d.get("audience_size_upper_bound").getAsLong())
                           .build())
                   .filter(d->d.getName()
                               .toUpperCase()
                               .contains(String.valueOf(params.get("q")).toUpperCase()))
                   .collect(Collectors.toList());

        return new ResponseEntity<List<DeviceTargetingDto>>(tgtList, HttpStatus.OK);
    }
}
