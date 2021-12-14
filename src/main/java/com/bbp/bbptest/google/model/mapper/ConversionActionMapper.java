package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.ConversionActionDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.ConversionAction;

@Mapper(componentModel = "spring")
public interface ConversionActionMapper extends GenericMapper<ConversionActionDto, ConversionAction> {

    @Mapping(source = "tagSnippetsList", target = "tagSnippets")
    @Override
    ConversionActionDto toDto(ConversionAction conversionAction);
}
