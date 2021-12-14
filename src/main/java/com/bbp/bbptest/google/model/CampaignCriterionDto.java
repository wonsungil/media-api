package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignCriterionDto {
    private String resourceName;
    private String type;
    private long bidModifier;

    private Device device;
    private Language language;

//    private AdScheduleInfo adSchedule;
//    private AgeRangeInfo ageRange;
//    private CarrierInfo carrier;
//    private CombinedAudienceInfo combinedAudience;
//    private ContentLabelInfo contentLabel;
//    private CustomAffinityInfo customAffinity;
//    private DeviceInfo device;
//    private GenderInfo gender;
//    private IncomeRangeInfo incomeRange;
//    private IpBlockInfo ipBlock;
//    private KeywordInfo keyword;
//    private LanguageInfo language;
//    private ListingScopeInfo listingScope;
//    private LocationInfo location;
//    private LocationGroupInfo locationGroup;
//    private MobileAppCategoryInfo mobileAppCategory;
//    private MobileApplicationInfo mobileApplication;
//    private MobileDeviceInfo mobileDevice;
//    private OperatingSystemVersionInfo operatingSystemVersion;
//    private ParentalStatusInfo parentalStatus;
//    private PlacementInfo placement;
//    private ProximityInfo proximity;
//    private TopicInfo topic;
//    private UserInterestInfo userInterest;
//    private UserListInfo userList;
//    private WebpageInfo webpage;
//    private YouTubeChannelInfo youTubeChannel;
//    private YouTubeVideoInfo youTubeVideo;

    private String status;
    private String campaign;
    private long criterionId;
    private boolean negative;
    private String displayName;

    @Getter
    @Builder
    public static class Device {
        private String type;
    }

    @Getter
    @Builder
    public static class Language {
        private String languageConstant;
    }
}
