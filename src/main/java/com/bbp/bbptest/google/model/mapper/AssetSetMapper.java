package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AssetSetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AssetSet;

@Mapper(componentModel = "spring")
public interface AssetSetMapper extends GenericMapper<AssetSetDto, AssetSet> {
}
