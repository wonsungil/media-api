package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdGroupTargetingReq {

    private long customerId;
    private long adGroupId;

    private List<Integer> genderList;
    private List<Integer> ageRangeList;
    private List<Integer> parentalList;
    private List<Integer> incomeRangeList;
}
