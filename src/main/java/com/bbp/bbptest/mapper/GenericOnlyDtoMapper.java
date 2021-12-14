package com.bbp.bbptest.mapper;

public interface GenericOnlyDtoMapper<D, E> {
        D toDto(E e);
}