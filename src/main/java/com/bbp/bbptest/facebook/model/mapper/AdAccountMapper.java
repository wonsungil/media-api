package com.bbp.bbptest.facebook.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.facebook.model.AdAccountDto;
import com.bbp.bbptest.mapper.GenericOnlyDtoMapper;
import com.facebook.ads.sdk.AdAccount;

@Mapper(componentModel = "spring")
public interface AdAccountMapper extends GenericOnlyDtoMapper<AdAccountDto, AdAccount> {

    @Mapping(target = "accountId", source = "fieldAccountId")
    @Mapping(target = "accountStatus", source = "fieldAccountStatus")
    @Mapping(target = "name", source = "fieldName")
    @Mapping(target = "timeZoneId", source = "fieldTimezoneId")
    @Mapping(target = "currency", source = "fieldCurrency")
    @Mapping(target = "endAdvertiser", source = "fieldEndAdvertiser")
    @Override
    AdAccountDto toDto(AdAccount adAccount);
}
