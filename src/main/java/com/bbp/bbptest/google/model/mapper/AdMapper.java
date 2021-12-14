package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.AdDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper extends GenericMapper<AdDto, Ad> {

    @Mapping(source = "responsiveDisplayAd.marketingImagesList", target = "responsiveDisplayAd.marketingImages")
    @Mapping(source = "responsiveDisplayAd.headlinesList", target = "responsiveDisplayAd.headlines")
    @Mapping(source = "responsiveDisplayAd.squareMarketingImagesList", target = "responsiveDisplayAd.squareMarketingImages")
    @Mapping(source = "responsiveDisplayAd.descriptionsList", target = "responsiveDisplayAd.descriptions")
    @Mapping(source = "responsiveDisplayAd.youtubeVideosList", target = "responsiveDisplayAd.youtubeVideos")
    @Mapping(source = "responsiveDisplayAd.callToActionText", target = "responsiveDisplayAd.callToActionText")
    @Mapping(source = "appAd.headlinesList", target = "appAd.headlines")
    @Mapping(source = "appAd.descriptionsList", target = "appAd.descriptions")
    @Mapping(source = "appAd.imagesList", target = "appAd.images")
    @Mapping(source = "appAd.youtubeVideosList", target = "appAd.youtubeVideos")
    @Mapping(source = "finalUrlsList", target = "finalUrls")
    @Override
    AdDto toDto(Ad ad);
}
