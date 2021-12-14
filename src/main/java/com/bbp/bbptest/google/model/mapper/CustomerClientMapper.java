package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.CustomerClientDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.CustomerClient;

@Mapper(componentModel = "spring")
public interface CustomerClientMapper extends GenericMapper<CustomerClientDto, CustomerClient> {

    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "resourceNameBytes", ignore = true)
    @Mapping(target = "clientCustomerBytes", ignore = true)
    @Mapping(target = "timeZoneBytes", ignore = true)
    @Mapping(target = "descriptiveNameBytes", ignore = true)
    @Mapping(target = "currencyCodeBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "appliedLabelsList", ignore = true)
    @Override
    CustomerClient toEntity(CustomerClientDto customerClientDto);
}
