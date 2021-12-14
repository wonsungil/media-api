package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.CampaignDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Campaign;

@Mapper(componentModel = "spring")
public interface CampaignMapper extends GenericMapper<CampaignDto, Campaign> {

    @Override
    CampaignDto toDto(Campaign campaign);

    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "resourceNameBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "statusValue", ignore = true)
    @Mapping(target = "advertisingChannelTypeValue", ignore = true)
    @Mapping(target = "advertisingChannelSubTypeValue", ignore = true)
    @Override
    Campaign toEntity(CampaignDto campaignDto);
}
