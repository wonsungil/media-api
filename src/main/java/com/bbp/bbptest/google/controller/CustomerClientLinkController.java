package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.service.CustomerClientLinkService;
import com.google.ads.googleads.v9.resources.CustomerClientLink;

@RequestMapping(value = "/google/customer-client-link")
@Controller
public class CustomerClientLinkController {

    private final CustomerClientLinkService ggCustomerClientLinkService;

    public CustomerClientLinkController(CustomerClientLinkService ggCustomerClientLinkService) {
        this.ggCustomerClientLinkService = ggCustomerClientLinkService;
    }

    /**
     * CustomerClientId로 CustomerClientLink 조회
     * @param customerClientId
     * @return
     */
    @RequestMapping(value = "/customer-client/{customerClientId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<CustomerClientLink> getCustomerClientLinkByClientId(@PathVariable("customerClientId") Long customerClientId) {
        return new ResponseEntity<CustomerClientLink>(ggCustomerClientLinkService.getCustomerClientListByCustomerClientId(customerClientId), HttpStatus.OK);
    }

    /**
     * CustomerClientLint 상태 변경
     * @param customerClientId
     * @param managerLinkId
     * @param status
     * @return
     */
    @RequestMapping(value = "/modifyLinkStatus", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomerClientLink> getCustomerClientLinkByClientId(
            @RequestParam("customerClientId") Long customerClientId,
            @RequestParam("managerLinkId") Long managerLinkId,
            @RequestParam("status") String status
    ) {
        return new ResponseEntity<CustomerClientLink>(ggCustomerClientLinkService.modifyStatus(customerClientId, managerLinkId, status), HttpStatus.OK);
    }
}