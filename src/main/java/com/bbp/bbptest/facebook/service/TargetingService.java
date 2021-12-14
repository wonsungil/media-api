package com.bbp.bbptest.facebook.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.APIRequest;
import com.facebook.ads.sdk.Targeting;

@Service(value = "fbTargetingService")
public class TargetingService {
    private APIContext facebookApiContext;

    public TargetingService(APIContext facebookApiContext) {
        this.facebookApiContext = facebookApiContext;
    }

    public APINodeList<Targeting> searchTargeting(Map<String, Object> params) throws APIException {
        APIRequest<Targeting> request = new APIRequest<Targeting>(facebookApiContext, "search", "/", "GET", Targeting.getParser());

        for( Map.Entry<String, Object> param : params.entrySet())
            request.setParam(param.getKey(), param.getValue());

        return (APINodeList<Targeting>) request.execute();
    }
}
