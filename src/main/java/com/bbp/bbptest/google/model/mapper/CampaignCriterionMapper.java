package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.bbp.bbptest.google.model.CampaignCriterionDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.CampaignCriterion;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CampaignCriterionMapper extends GenericMapper<CampaignCriterionDto, CampaignCriterion> {
}
