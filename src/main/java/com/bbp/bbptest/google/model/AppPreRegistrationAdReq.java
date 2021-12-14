package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppPreRegistrationAdReq {
    private List<String> headlines;
    private List<String> descriptions;
    private List<String> images;
    private List<String> youtubeVideos;
}
