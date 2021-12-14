package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AdGroupAdAssetViewDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AdGroupAdAssetView;

@Mapper(componentModel = "spring")
public interface AdGroupAdAssetViewMapper extends GenericMapper<AdGroupAdAssetViewDto, AdGroupAdAssetView> {

}
