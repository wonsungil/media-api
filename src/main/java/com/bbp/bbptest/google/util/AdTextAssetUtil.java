package com.bbp.bbptest.google.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.ads.googleads.v9.common.AdTextAsset;

public class AdTextAssetUtil {

    public static List<AdTextAsset> convertToAdTextAssets(List<String> texts) {
        if (texts==null || texts.isEmpty()) {
            return Collections.emptyList();
        }
        return texts.stream()
                    .map(x -> AdTextAsset.newBuilder()
                                         .setText(x)
                                         .build())
                    .collect(Collectors.toList());
    }

    public static AdTextAsset convertToAdTextAsset(String text) {
        return AdTextAsset.newBuilder()
                          .setText(text)
                          .build();
    }

}
