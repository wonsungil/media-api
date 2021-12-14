package com.bbp.bbptest.google.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.ads.googleads.v9.common.AdImageAsset;

public class AdImageAssetUtil {

    public static List<AdImageAsset> convertToAdImageAssets(List<String> images) {
        if (images==null || images.isEmpty()) {
            return Collections.emptyList();
        }
        return images.stream()
                     .map(x-> AdImageAsset.newBuilder()
                                          .setAsset(x)
                                          .build())
                     .collect(Collectors.toList());
    }

    public static AdImageAsset convertToAdImageAsset(String image) {
        return AdImageAsset.newBuilder()
                           .setAsset(image)
                           .build();
    }

}
