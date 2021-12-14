package com.bbp.bbptest.google.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.ads.googleads.v9.common.AdMediaBundleAsset;

public class AdMediaBundleAssetUtil {

    public static List<AdMediaBundleAsset> convertToAdMediaBundleAssets(List<String> mediaBundles) {

        if (mediaBundles == null || mediaBundles.isEmpty()) {
            return Collections.emptyList();
        }

        return mediaBundles.stream()
                           .map(x -> AdMediaBundleAsset.newBuilder()
                                                       .setAsset(x)
                                                       .build())
                           .collect(Collectors.toList());

    }
}
