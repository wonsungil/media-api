package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.FeedDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.Feed;

@Mapper(componentModel = "spring")
public interface FeedMapper extends GenericMapper<FeedDto, Feed> {
    @Mapping(source = "attributesList", target = "attributes")
    @Override
    FeedDto toDto(Feed feed);
}
