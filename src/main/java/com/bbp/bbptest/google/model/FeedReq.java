package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedReq {
    private List<FeedDto.Attirubute> attributes;
    private String origin;
    private String status;
    private String name;
    private long customerId;

    @Getter
    @Builder
    public static class Attirubute {
        private String type;
        private String name;
        private long id;
        private boolean isPartOfKey;
    }

}
