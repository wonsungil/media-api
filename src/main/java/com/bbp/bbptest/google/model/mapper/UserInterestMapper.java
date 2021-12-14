package com.bbp.bbptest.google.model.mapper;

import org.mapstruct.Mapper;

import com.bbp.bbptest.google.model.UserInterestDto;
import com.bbp.bbptest.mapper.GenericMapper;
import com.google.ads.googleads.v9.resources.UserInterest;

@Mapper(componentModel = "spring")
public interface UserInterestMapper extends GenericMapper<UserInterestDto, UserInterest> {
}
