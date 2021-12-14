package com.bbp.bbptest.facebook.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.facebook.model.AdSetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.facebook.ads.sdk.AdSet;

@Mapper(componentModel = "spring")
public interface AdSetMapper extends GenericMapper<AdSetDto, AdSet> {

}
