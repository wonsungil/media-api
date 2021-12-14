package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopicConstantDto {
    private long id;
    private String resourceName;
    private String topicConstantParent;
    private List<String> pathList;
}
