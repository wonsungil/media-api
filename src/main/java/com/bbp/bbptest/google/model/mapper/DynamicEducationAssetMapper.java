package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.DynamicEducationAssetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Asset;

@Mapper(componentModel = "spring")
public interface DynamicEducationAssetMapper extends GenericMapper<DynamicEducationAssetDto, Asset>  {
}
