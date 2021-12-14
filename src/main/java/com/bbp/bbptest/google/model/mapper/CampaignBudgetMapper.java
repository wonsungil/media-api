package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.CampaignBudgetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.CampaignBudget;

@Mapper(componentModel = "spring")
public interface CampaignBudgetMapper extends GenericMapper<CampaignBudgetDto, CampaignBudget> {
}
