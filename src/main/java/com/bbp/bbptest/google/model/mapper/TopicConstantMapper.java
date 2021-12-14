package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bbp.bbptest.google.model.TopicConstantDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.TopicConstant;

@Mapper(componentModel = "spring")
public interface TopicConstantMapper extends GenericMapper<TopicConstantDto, TopicConstant> {

    @Override
    TopicConstantDto toDto(TopicConstant topicConstant);

    @Mapping(target = "pathList", ignore = true)
    @Override
    TopicConstant toEntity(TopicConstantDto topicConstantDto);
}
