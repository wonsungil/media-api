package com.bbp.bbptest.google.service;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AppAdReq;
import com.bbp.bbptest.google.model.AppEngagementAdReq;
import com.bbp.bbptest.google.model.AppPreRegistrationAdReq;
import com.bbp.bbptest.google.util.AdImageAssetUtil;
import com.bbp.bbptest.google.util.AdMediaBundleAssetUtil;
import com.bbp.bbptest.google.util.AdTextAssetUtil;
import com.bbp.bbptest.google.util.AdVideoAssetUtil;
import com.google.ads.googleads.v9.common.AppAdInfo;
import com.google.ads.googleads.v9.common.AppEngagementAdInfo;
import com.google.ads.googleads.v9.common.AppPreRegistrationAdInfo;

@Service(value = "ggAppAdService")
public class AppAdService {

    public AppAdInfo buildAppAd(AppAdReq param) {
        return AppAdInfo.newBuilder()
                        .setMandatoryAdText(AdTextAssetUtil.convertToAdTextAsset(param.getMandatoryAdText()))
                        .addAllHeadlines(AdTextAssetUtil.convertToAdTextAssets(param.getHeadlines()))
                        .addAllDescriptions(AdTextAssetUtil.convertToAdTextAssets(param.getDescriptions()))
                        .addAllImages(AdImageAssetUtil.convertToAdImageAssets(param.getImages()))
                        .addAllYoutubeVideos(AdVideoAssetUtil.convertToAdVideoAssets(param.getYoutubeVideos()))
                        .addAllHtml5MediaBundles(AdMediaBundleAssetUtil.convertToAdMediaBundleAssets(param.getHtml5MediaBundles()))
                        .build();
    }

    public AppEngagementAdInfo buildAppEngagement(AppEngagementAdReq param) {
        return AppEngagementAdInfo.newBuilder()
                                  .addAllHeadlines(AdTextAssetUtil.convertToAdTextAssets(param.getHeadlines()))
                                  .addAllDescriptions(AdTextAssetUtil.convertToAdTextAssets(param.getDescriptions()))
                                  .addAllImages(AdImageAssetUtil.convertToAdImageAssets(param.getImages()))
                                  .addAllVideos(AdVideoAssetUtil.convertToAdVideoAssets(param.getVideos()))
                                  .build();
    }

    public AppPreRegistrationAdInfo buildAppPreRegistration(AppPreRegistrationAdReq param) {
        return AppPreRegistrationAdInfo.newBuilder()
                                       .addAllHeadlines(AdTextAssetUtil.convertToAdTextAssets(param.getHeadlines()))
                                       .addAllDescriptions(AdTextAssetUtil.convertToAdTextAssets(param.getDescriptions()))
                                       .addAllImages(AdImageAssetUtil.convertToAdImageAssets(param.getImages()))
                                       .addAllYoutubeVideos(AdVideoAssetUtil.convertToAdVideoAssets(param.getYoutubeVideos()))
                                       .build();
    }
}
