package com.bbp.bbptest.exception;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.facebook.ads.sdk.APIException;
import com.google.ads.googleads.v9.errors.GoogleAdsException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class BbpExceptionHandler {
    @ExceptionHandler(value = {APIException.class})
    protected ResponseEntity<ErrorResponse> facebookApiException(APIException e) {

        JsonObject error = e.getRawResponseAsJsonObject().get("error").getAsJsonObject();
        log.info("[FACEBOOK API ERROR] : " + error.toString());

        String message = Optional.ofNullable(error.get("message")).map(JsonElement::getAsString).orElse("");
        String type = Optional.ofNullable(error.get("type")).map(JsonElement::getAsString).orElse("");
        int code = Optional.ofNullable(error.get("code")).map(JsonElement::getAsInt).orElse(0);
        int subCode = Optional.ofNullable(error.get("error_subcode")).map(JsonElement::getAsInt).orElse(0);
        Boolean isTransient = Optional.ofNullable(error.get("is_transient")).map(JsonElement::getAsBoolean).orElse(null);
        String errorUserTitle = Optional.ofNullable(error.get("error_user_title")).map(JsonElement::getAsString).orElse("");
        String errorUserMsg = Optional.ofNullable(error.get("error_user_msg")).map(JsonElement::getAsString).orElse("");
        String fbtraceId = Optional.ofNullable(error.get("fbtrace_id")).map(JsonElement::getAsString).orElse("");



        return ErrorResponse
                .builder()
                .status(500)
                .error(type)
                .code(String.format("%o(%o)", code, subCode))
                .message(String.format("[%s][%s][%s]", message, errorUserTitle, errorUserMsg))
                .build()
                .toResponseEntity();
    }
    @ExceptionHandler(value = { GoogleAdsException.class })
    public ResponseEntity<ErrorResponse> googleApiException(GoogleAdsException e) {

        return ErrorResponse.builder()
                .status(500)
                .message(e.getLocalizedMessage())
                .build()
                .toResponseEntity();
    }
}
