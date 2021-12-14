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

import com.bbp.bbptest.google.model.BiddingStrategyReq;
import com.bbp.bbptest.google.service.BiddingStrategyService;

@RequestMapping(value = "/google/biddingStrategy")
@Controller
public class BiddingStrategyController {

    private BiddingStrategyService ggBiddingStrategyService;

    public BiddingStrategyController(BiddingStrategyService ggBiddingStrategyService) {
        this.ggBiddingStrategyService = ggBiddingStrategyService;
    }

    @GetMapping(value = "/getBiddingStrategies/{customerId}")
    public @ResponseBody
    ResponseEntity getBiddingStrategies(@PathVariable("customerId") long customerId) {
        ggBiddingStrategyService.getBiddingStrategy(customerId);
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity createBiddingStrategy(@RequestBody BiddingStrategyReq param) {
        ggBiddingStrategyService.createBiddingStrategy(param);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping(value = "/updateCampaignBiddingStrategy")
    public @ResponseBody ResponseEntity updateCampaignBiddingStrategy(@RequestBody BiddingStrategyReq param) {
        ggBiddingStrategyService.updateCampaignBiddingStrategy(param);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
