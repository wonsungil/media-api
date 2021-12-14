package com.bbp.bbptest.facebook.utils;

public class FacebookUtil {

    public static String getAdAccountId(long campaignId) {
        return "act_" + campaignId;
    }

    public static String toEnumValueName(String enumName) {
        return "VALUE_"+enumName;
    }
}
