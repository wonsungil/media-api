package com.bbp.bbptest.google.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.ConversionActionDto;
import com.bbp.bbptest.google.model.ConversionActionReq;
import com.bbp.bbptest.google.model.mapper.ConversionActionMapper;
import com.bbp.bbptest.google.service.ConversionActionService;
import com.google.ads.googleads.v9.resources.ConversionAction;

@RequestMapping(value = "/google/conversion-action")
@Controller
public class ConversionActionController {

    private final ConversionActionService ggConversionActionService;
    private final ConversionActionMapper conversionActionMapper;

    public ConversionActionController(ConversionActionService ggConversionActionService
            , ConversionActionMapper conversionActionMapper) {
        this.ggConversionActionService = ggConversionActionService;
        this.conversionActionMapper = conversionActionMapper;
    }

    //customers/6583317631/conversionActions/816057018"
    @GetMapping(value = "/{customerId}/{conversionActionId}")
    public @ResponseBody ResponseEntity<ConversionActionDto> getConversionAction(
            @PathVariable("customerId") long customerId,
            @PathVariable("conversionActionId") long conversionActionId) {
        ConversionAction conversionAction = ggConversionActionService.getConversionAction(customerId, conversionActionId);
        System.out.println(conversionAction.toString());
        ConversionActionDto conversionActionDto = conversionActionMapper.toDto(conversionAction);

        return new ResponseEntity<ConversionActionDto>(conversionActionDto, HttpStatus.OK);
    }

    @GetMapping(value = "/conversionActions/{customerId}")
    public @ResponseBody ResponseEntity getConversionActions(@PathVariable("customerId") long customerId) {

        List<ConversionAction> conversionActions =
                ggConversionActionService.getAllConversionActions(customerId);
        System.out.println("------------");
        for (ConversionAction conversionAction : conversionActions) {
            System.out.println(conversionAction.toString());
        }
        System.out.println("------------");

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<ConversionActionDto> create(@RequestBody ConversionActionReq param) {
        ConversionAction created = ggConversionActionService.create(param);
        ConversionAction conversionAction = ggConversionActionService.getConversionAction(created.getResourceName());
        ConversionActionDto conversionActionDto = conversionActionMapper.toDto(conversionAction);
        return new ResponseEntity<ConversionActionDto>(conversionActionDto, HttpStatus.OK);
    }
}
