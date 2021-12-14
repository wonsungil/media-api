package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.CampaignAssetSetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.CampaignAssetSet;

@Mapper(componentModel = "spring")
public interface CampaignAssetSetMapper extends GenericMapper<CampaignAssetSetDto, CampaignAssetSet> {
}
