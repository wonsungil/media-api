package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AdGroupAdDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AdGroupAd;

@Mapper(componentModel = "spring")
public interface AdGroupAdMapper extends GenericMapper<AdGroupAdDto, AdGroupAd> {
}
