package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageAdDto {

    private String mimeType;
    private int pixelWidth;
    private int pixelHeight;
    private String imageUrl;
    private int previewPixelWidth;
    private int previewPixelHeight;
    private String previewImageUrl;
    private String name;
}
