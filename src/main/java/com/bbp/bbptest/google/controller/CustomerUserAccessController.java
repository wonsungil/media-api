package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.service.CustomerUserAccessService;

@RequestMapping(value = "/google/customer-user-access")
@Controller
public class CustomerUserAccessController {

    private final CustomerUserAccessService ggCustomerUserAccessService;

    public CustomerUserAccessController(CustomerUserAccessService ggCustomerUserAccessService) {
        this.ggCustomerUserAccessService = ggCustomerUserAccessService;
    }

    @RequestMapping(value = "/inviteUser", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> inviteUser(
            @RequestParam("customerId") Long customerId,
            @RequestParam("emailAddress") String emailAddress,
            @RequestParam("role") String role) {
        return new ResponseEntity<>(ggCustomerUserAccessService.inviteUser(customerId, emailAddress, role).toString(), HttpStatus.OK);
    }

}
