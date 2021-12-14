package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.AssetSetAssetDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.AssetSetAsset;

@Mapper(componentModel = "spring")
public interface AssetSetAssetMapper extends GenericMapper<AssetSetAssetDto, AssetSetAsset> {
}
