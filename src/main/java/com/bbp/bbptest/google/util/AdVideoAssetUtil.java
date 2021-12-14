package com.bbp.bbptest.google.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.ads.googleads.v9.common.AdVideoAsset;

public class AdVideoAssetUtil {

    public static List<AdVideoAsset> convertToAdVideoAssets(List<String> videos) {
        if (videos == null || videos.isEmpty()) {
            return Collections.emptyList();
        }

        return videos.stream()
                     .map(x->AdVideoAsset.newBuilder().setAsset(x).build())
                     .collect(Collectors.toList());
    }

}
