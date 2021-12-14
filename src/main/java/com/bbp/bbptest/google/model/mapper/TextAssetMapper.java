package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.TextAssetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Asset;

@Mapper(componentModel = "spring")
public interface TextAssetMapper extends GenericMapper<TextAssetDto, Asset> {
}
