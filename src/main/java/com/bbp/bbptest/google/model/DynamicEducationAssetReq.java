package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DynamicEducationAssetReq {
    private String schoolName;
    private String address;
    private String programName;
    private String subject;
    private String programDescription;
    private String programId;
    private String locationId;
    private String imageUrl;
    private String androidAppLink;
    private String iosAppLink;
    private long iosAppStoreId;
    private long customerId;

    @Override
    public String toString() {
        return "DynamicEducationAssetReq{" +
               "schoolName='" + schoolName + '\'' +
               ", address='" + address + '\'' +
               ", programName='" + programName + '\'' +
               ", subject='" + subject + '\'' +
               ", programDescription='" + programDescription + '\'' +
               ", programId='" + programId + '\'' +
               ", locationId='" + locationId + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", androidAppLink='" + androidAppLink + '\'' +
               ", iosAppLink='" + iosAppLink + '\'' +
               ", iosAppStoreId=" + iosAppStoreId +
               ", customerId=" + customerId +
               '}';
    }
}
