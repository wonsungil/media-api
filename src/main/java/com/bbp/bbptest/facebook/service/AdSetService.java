package com.bbp.bbptest.facebook.service;

import java.util.Arrays;
import java.util.List;

import com.facebook.ads.sdk.*;
import org.springframework.stereotype.Service;

import com.bbp.bbptest.facebook.model.AdSetReq;
import com.bbp.bbptest.facebook.utils.FacebookUtil;

@Service(value = "fbAdSetService")
public class AdSetService {

    private APIContext facebookApiContext;

    public AdSetService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public AdSet getAdSet(long adSetId) {
//        APIRequest<AdSet> request = new APIRequest<AdSet>(facebookApiContext);
        return null;
    }

    public AdSet create(AdSetReq param) throws APIException {

        AdSet adSet = new AdAccount(param.getAdAccountId(), facebookApiContext)
                .createAdSet()
                .setCampaignId(String.valueOf(param.getCampaignId()))
                .setName(param.getName())
                .setBidAmount(param.getBidAmount())
                .setBidStrategy(AdSet.EnumBidStrategy.valueOf(FacebookUtil.toEnumValueName(param.getBidStrategy())))
                .setBillingEvent(AdSet.EnumBillingEvent.valueOf(FacebookUtil.toEnumValueName(param.getBillingEvent())))
                .setDailyBudget(param.getDailyBudget())
//                .setDestinationType(AdSet.EnumDestinationType.valueOf(FacebookUtil.toEnumValㅈㅈeName(param.getDestinationType())))
                .setStartTime(param.getStartTime())
                .setEndTime(param.getEndTime())
                .setOptimizationGoal(AdSet.EnumOptimizationGoal.valueOf(FacebookUtil.toEnumValueName(param.getOptimizationGoal())))
//                .setPromotedObject(param.getPromotedObject())
                .setTargeting(
                        new Targeting()
                                .setFieldAgeMax(param.getAgeMax())
                                .setFieldAgeMin(param.getAgeMin())
                                .setFieldGenders(param.getGenders())
                                .setFieldGeoLocations(
                                        new TargetingGeoLocation()
                                            .setFieldCountries(Arrays.asList("KR"))
                                )
                                .setFieldPublisherPlatforms(Arrays.asList("facebook", "audience_network"))

//                                .setFieldDevicePlatforms() // 디바이스 플랫폼
//                                .setFieldUserOs() // OS 타게팅
//                                .setFieldUserDevice() // Device
//                                .setFieldExcludedUserDevice() //
//                                .
                )
                .setStatus(AdSet.EnumStatus.valueOf(FacebookUtil.toEnumValueName(param.getStatus())))
//                .setTimeStart()
//                .setTimeStop()
//                .setTuneForCategory()
//                .setTimeBasedAdRotationIdBlocks()
//                .setTimeBasedAdRotationIntervals()
//                .setAdSetSchedule() //1일간 delivery schedule
//                .setAttributionSpec() // 전환


//                .setDailyImps() // Campaign.buying_type=FIXED_CPM 인경우만 유효
//                .setDailyMinSpendTarget()
//                .setDailySpendCap()
//                .setExecutionOptions(Collections.singletonList(EnumExecutionOptions.VALUE_VALIDATE_ONLY))
//                .setExistingCustomerBudgetPercentage()
//                .setFrequencyControlSpecs() // 현재 노출만 가능
//                .setIsDynamicCreative()
//                .setLifetimeBudget()
//                .setLifetimeImps() // Campaign.buying_type=FIXED_CPM
//                .setLifetimeMinSpendTarget()
//                .setLifetimeSpendCap()
//                .setMultiOptimizationGoalWeight()
//                .setOptimizationSubEvent()
//                .setPacingType()
                .execute();
        return adSet;
    }

    /**
     * 광고세트 리스트 조회
     * @param adAccountId
     * @return
     * @throws APIException
     */
    public APINodeList<AdSet> getAdSets(long adAccountId) throws APIException {
        List<String> fields = Arrays.asList("name", "configured_status", "effective_status", "id");

        APINodeList<AdSet> adSets = new AdAccount(adAccountId, facebookApiContext).getAdSets()
                .requestFields(fields).execute();
        return adSets;
    }
}
