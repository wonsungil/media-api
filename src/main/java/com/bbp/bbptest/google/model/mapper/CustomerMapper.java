package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.CustomerDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<CustomerDto, Customer> {

    @Mapping(target = "payPerConversionEligibilityFailureReasonsList", ignore = true)
    @Mapping(target = "payPerConversionEligibilityFailureReasonsValueList", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "resourceNameBytes", ignore = true)
    @Mapping(target = "descriptiveNameBytes", ignore = true)
    @Mapping(target = "currencyCodeBytes", ignore = true)
    @Mapping(target = "timeZoneBytes", ignore = true)
    @Mapping(target = "trackingUrlTemplate", ignore = true)
    @Mapping(target = "trackingUrlTemplateBytes", ignore = true)
    @Mapping(target = "finalUrlSuffix", ignore = true)
    @Mapping(target = "finalUrlSuffixBytes", ignore = true)
    @Mapping(target = "autoTaggingEnabled", ignore = true)
    @Mapping(target = "hasPartnersBadge", ignore = true)
    @Mapping(target = "testAccount", ignore = true)
    @Mapping(target = "callReportingSetting", ignore = true)
    @Mapping(target = "mergeCallReportingSetting", ignore = true)
    @Mapping(target = "conversionTrackingSetting", ignore = true)
    @Mapping(target = "mergeConversionTrackingSetting", ignore = true)
    @Mapping(target = "remarketingSetting", ignore = true)
    @Mapping(target = "mergeRemarketingSetting", ignore = true)
    @Mapping(target = "optimizationScore", ignore = true)
    @Mapping(target = "optimizationScoreWeight", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Override
    Customer toEntity(CustomerDto customerDto);
}
