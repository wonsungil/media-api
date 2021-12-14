package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdDto {
    private String type;
    private ResponsiveDisplayAdDto responsiveDisplayAd;
    private AppAdDto appAd;
    private ImageAdDto imageAd;
    private String resourceName;
    private long id;
    private List<String> finalUrls;
    private boolean adddedByGoogle;
}
