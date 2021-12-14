package com.bbp.bbptest.google.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.CustomerClientDto;
import com.bbp.bbptest.google.model.mapper.CustomerClientMapper;
import com.bbp.bbptest.google.service.CustomerClientService;
import com.google.ads.googleads.v9.resources.Customer;
import com.google.ads.googleads.v9.resources.CustomerClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/google/customer-client")
@Controller
public class CustomerClientController {
    private final CustomerClientService ggCustomerClientService;
    private final CustomerClientMapper customerClientMapper;

    public CustomerClientController(CustomerClientService ggCustomerClientService, CustomerClientMapper customerClientMapper) {
        this.ggCustomerClientService = ggCustomerClientService;
        this.customerClientMapper = customerClientMapper;
    }

    /**
     * CustomerClient 조회
     * @param customerClientId
     * @return
     */
    @RequestMapping(value = "/{customerClientId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<CustomerClientDto> detail(@PathVariable("customerClientId") long customerClientId) {
        CustomerClient customerClient = ggCustomerClientService.getCustomerClient(customerClientId);
        CustomerClientDto customerClientDto = customerClientMapper.toDto(customerClient);
        return new ResponseEntity<CustomerClientDto>(customerClientDto, HttpStatus.OK);
    }

    /**
     * CustomerClient 생성
     * @param advertiserName
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomerClientDto> create(@RequestParam("advertiserName") String advertiserName) {
        Customer customer = ggCustomerClientService.create(advertiserName);
        CustomerClient customerClient = ggCustomerClientService.getCustomerClient(customer.getId());
        CustomerClientDto customerClientDto = customerClientMapper.toDto(customerClient);
        return new ResponseEntity<CustomerClientDto>(customerClientDto, HttpStatus.OK);
    }

}
