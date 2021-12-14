package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedDto {
    private long id;
    private List<Attirubute> attributes;
    private String origin;
    private String status;
    private String name;

    @Getter
    @Builder
    public static class Attirubute {
        private String type;
        private String name;
        private long id;
        private boolean isPartOfKey;
    }
}
