package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.CampaignFeedDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.CampaignFeed;

@Mapper(componentModel = "spring")
public interface CampaignFeedMapper extends GenericMapper<CampaignFeedDto, CampaignFeed> {

    @Mapping(source = "matchingFunction.leftOperandsList", target = "matchingFunction.leftOperands")
    @Mapping(source = "matchingFunction.rightOperandsList", target = "matchingFunction.rightOperands")
    @Override
    CampaignFeedDto toDto(CampaignFeed campaignFeed);
}
