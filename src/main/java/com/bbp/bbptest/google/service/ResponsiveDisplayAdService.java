package com.bbp.bbptest.google.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.ResponsiveDisplayAdReq;
import com.bbp.bbptest.google.util.AdImageAssetUtil;
import com.bbp.bbptest.google.util.AdTextAssetUtil;
import com.google.ads.googleads.v9.common.AdImageAsset;
import com.google.ads.googleads.v9.common.AdTextAsset;
import com.google.ads.googleads.v9.common.ResponsiveDisplayAdControlSpec;
import com.google.ads.googleads.v9.common.ResponsiveDisplayAdInfo;
import com.google.ads.googleads.v9.enums.DisplayAdFormatSettingEnum;
import com.google.ads.googleads.v9.enums.DisplayAdFormatSettingEnum.DisplayAdFormatSetting;

@Service(value = "ggResponsiveDisplayAdService")
public class ResponsiveDisplayAdService {

    public ResponsiveDisplayAdInfo buildResponsiveDisplayAd(ResponsiveDisplayAdReq param) {
        List<AdImageAsset> marketingImages = AdImageAssetUtil.convertToAdImageAssets(param.getMarketingImages());
        List<AdImageAsset> squareMarketingIMages = AdImageAssetUtil.convertToAdImageAssets(param.getSquareMarketingImages());
        List<AdImageAsset> logoImages = AdImageAssetUtil.convertToAdImageAssets(param.getLogoImages());
        List<AdImageAsset> squareLogoImages = AdImageAssetUtil.convertToAdImageAssets(param.getSquareLogoImages());
        List<AdTextAsset> headlines = AdTextAssetUtil.convertToAdTextAssets(param.getHeadlines());
        AdTextAsset longHeadline = AdTextAssetUtil.convertToAdTextAsset(param.getLongHeadline());
        List<AdTextAsset> descriptions = AdTextAssetUtil.convertToAdTextAssets(param.getDescriptions());
//        List<YoutubeVideoAsset> Youtube 에셋 스킵
        DisplayAdFormatSetting formatSetting = DisplayAdFormatSettingEnum.DisplayAdFormatSetting.valueOf(param.getFormatSetting());
        ResponsiveDisplayAdControlSpec controlSpec = ResponsiveDisplayAdControlSpec.newBuilder()
                                                                                   .setEnableAssetEnhancements(param.getControlSpecReq().isEnableAssetEnhancements())
                                                                                   .setEnableAutogenVideo(param.getControlSpecReq().isEnableAutogenVideo())
                                                                                   .build();
        return ResponsiveDisplayAdInfo
                .newBuilder()
                .addAllMarketingImages(marketingImages)
                .addAllSquareMarketingImages(squareMarketingIMages)
                .addAllLogoImages(logoImages)
                .addAllSquareLogoImages(squareLogoImages)
                .addAllHeadlines(headlines)
                .setLongHeadline(longHeadline)
                .addAllDescriptions(descriptions)
                .setFormatSetting(formatSetting)
                .setControlSpec(controlSpec)
                .setBusinessName(param.getBusinessName())
                .setMainColor(param.getMainColor())
                .setAccentColor(param.getAccentColor())
                .setAllowFlexibleColor(param.isAllowFlexibleColor())
                .setCallToActionText(param.getCallToActionText())
//                .setPricePrefix(param.getPricePrefix())
//                .setPromoText(param.getPromoText())
                .build();
    }

}
