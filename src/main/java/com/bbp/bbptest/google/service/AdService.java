package com.bbp.bbptest.google.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bbp.bbptest.google.model.AdReq;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v9.common.CustomParameter;
import com.google.ads.googleads.v9.common.FinalAppUrl;
import com.google.ads.googleads.v9.enums.AdTypeEnum.AdType;
import com.google.ads.googleads.v9.enums.AppUrlOperatingSystemTypeEnum.AppUrlOperatingSystemType;
import com.google.ads.googleads.v9.enums.DeviceEnum.Device;
import com.google.ads.googleads.v9.resources.Ad;
import com.google.ads.googleads.v9.services.AdServiceClient;
import com.google.ads.googleads.v9.utils.ResourceNames;

@Service(value = "ggAdService")
public class AdService {

    private final GoogleAdsClient googleAdsClient;
    private final ResponsiveDisplayAdService ggResponsiveDisplayAdService;
    private final AppAdService ggAppAdService;

    public AdService(GoogleAdsClient googleAdsClient,
                     ResponsiveDisplayAdService ggResponsiveDisplayAdService,
                     AppAdService ggAppAdService) {
        this.googleAdsClient = googleAdsClient;
        this.ggResponsiveDisplayAdService = ggResponsiveDisplayAdService;
        this.ggAppAdService = ggAppAdService;
    }

    public Ad.Builder commonAdBuilder(AdReq param) {

        List<FinalAppUrl> finalAppUrls = param.getFinalAppUrls()
                                              .stream()
                                              .map(x -> FinalAppUrl.newBuilder()
                                                                   .setOsType(AppUrlOperatingSystemType.valueOf(x.getOsType()))
                                                                   .setUrl(x.getUrl())
                                                                   .build())
                                              .collect(Collectors.toList());
        List<CustomParameter> urlCustomParameters = param.getUrlCustomParameters()
                                                         .stream()
                                                         .map(x -> CustomParameter.newBuilder()
                                                                                  .setKey(x.getKey())
                                                                                  .setValue(x.getValue())
                                                                                  .build())
                                                         .collect(Collectors.toList());

        return Ad.newBuilder()
                .addAllFinalUrls(param.getFinalUrls())
                .addAllFinalAppUrls(finalAppUrls)
                .addAllFinalMobileUrls(param.getFinalMobileUrls())
                .addAllUrlCustomParameters(urlCustomParameters)
                .setDevicePreference(Device.UNSPECIFIED)
//                .addAllUrlCollections()
                .setTrackingUrlTemplate(param.getTrackingUrlTemplate())
                .setFinalUrlSuffix(param.getFinalUrlSuffix())
//                .setDisplayUrl(param.getDisplayUrl())
                .setName(param.getName());
    }

    public Ad createResponsiveDisplayAd(AdReq param) {
        return commonAdBuilder(param)
                .setResponsiveDisplayAd(ggResponsiveDisplayAdService.buildResponsiveDisplayAd(param.getResponsiveDisplayAdReq()))
                .build();
    }

    public Ad createAppAd(AdReq param) {
        return commonAdBuilder(param)
                .setAppAd(ggAppAdService.buildAppAd(param.getAppAdReq()))
                .build();
    }

    public Ad createAppEngagementAd(AdReq param) {
        return commonAdBuilder(param)
                .setAppEngagementAd(ggAppAdService.buildAppEngagement(param.getAppEngagementAdReq()))
                .build();
    }

    public Ad createAppPreRegistrationAd(AdReq param) {
        return commonAdBuilder(param)
                .setAppPreRegistrationAd(ggAppAdService.buildAppPreRegistration(param.getAppPreRegistrationAdReq()))
                .build();
    }

    public Ad buildAd(AdReq param) {
        Ad adReq = null;
        switch (param.getAdType()) {
            case AdType.RESPONSIVE_DISPLAY_AD_VALUE:
                adReq = createResponsiveDisplayAd(param); break;
            case AdType.APP_AD_VALUE:
                adReq = createAppAd(param); break;
            case AdType.APP_ENGAGEMENT_AD_VALUE:
                adReq =  createAppEngagementAd(param); break;
            case AdType.APP_PRE_REGISTRATION_AD_VALUE:
                adReq =  createAppPreRegistrationAd(param); break;
        }
        return adReq;
    }


    public Ad getAd(long customerId, long adId) {
        try (AdServiceClient client =
                googleAdsClient.getLatestVersion().createAdServiceClient()) {
            Ad ad = client.getAd(ResourceNames.ad(customerId, adId));
            return ad;
        }
    }
}
