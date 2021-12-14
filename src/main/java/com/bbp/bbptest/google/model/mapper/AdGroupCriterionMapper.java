package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AdGroupCriterionDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AdGroupCriterion;

@Mapper(componentModel = "spring")
public interface AdGroupCriterionMapper extends GenericMapper<AdGroupCriterionDto, AdGroupCriterion> {
}
