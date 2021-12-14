package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.GeoTargetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.services.GeoTargetConstantSuggestion;

@Mapper(componentModel = "spring")
public interface GeoTargetConstantMapper extends GenericMapper<GeoTargetDto, GeoTargetConstantSuggestion> {
}
