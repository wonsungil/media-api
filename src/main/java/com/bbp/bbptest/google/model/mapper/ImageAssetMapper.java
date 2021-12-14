package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.ImageAssetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Asset;

@Mapper(componentModel = "spring")
public interface ImageAssetMapper extends GenericMapper<ImageAssetDto, Asset> {
}
