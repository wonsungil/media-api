package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AdGroupDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AdGroup;

@Mapper(componentModel = "spring")
public interface AdGroupMapper extends GenericMapper<AdGroupDto, AdGroup> {

}