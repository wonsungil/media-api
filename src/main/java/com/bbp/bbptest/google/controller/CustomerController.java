package com.bbp.bbptest.google.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.CustomerClientDto;
import com.bbp.bbptest.google.model.CustomerDto;
import com.bbp.bbptest.google.model.mapper.CustomerClientMapper;
import com.bbp.bbptest.google.model.mapper.CustomerMapper;
import com.bbp.bbptest.google.service.CustomerClientService;
import com.bbp.bbptest.google.service.CustomerService;
import com.google.ads.googleads.v9.resources.Customer;
import com.google.ads.googleads.v9.resources.CustomerClient;

@RequestMapping(value = "/google/customer")
@Controller
public class CustomerController {

    private final CustomerService ggCustomerService;
    private final CustomerClientService ggCustomerClientService;
    private final CustomerMapper customerMapper;
    private final CustomerClientMapper customerClientMapper;

    public CustomerController(CustomerService ggCustomerService, CustomerClientService ggCustomerClientService
            , CustomerMapper customerMapper, CustomerClientMapper customerClientMapper) {
        this.ggCustomerService = ggCustomerService;
        this.ggCustomerClientService = ggCustomerClientService;
        this.customerMapper = customerMapper;
        this.customerClientMapper = customerClientMapper;
    }

    /**
     * Customer 조회
     * @param customerId CustomerId
     * @return
     */
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") Long customerId) {
        Customer customer = ggCustomerService.getCustomer(customerId);
        CustomerDto customerDto = customerMapper.toDto(customer);
        return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
    }

    /**
     * 접근 가능한 customer List 조회
     * @return
     */
    @RequestMapping(value = "/accessible-customers", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Object>> accessibleCustomers() {
        return new ResponseEntity<>(ggCustomerService.getAccessibleCustomers(), HttpStatus.OK);
    }

    /**
     * 하위 계정 리스트 조회
     * @return
     */
    @RequestMapping(value = "/sub-customers", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<CustomerClientDto>> subCustomer() {
        List<CustomerClient> customerClients = ggCustomerClientService.getSubCustomers();
        List<CustomerClientDto> customerClientDtos = customerClients.stream()
                .map(customerClientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerClientDtos, HttpStatus.OK);
    }
}
