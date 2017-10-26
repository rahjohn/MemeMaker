package com.rachelelisejohnson.mememaker;

import android.app.Application;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

/**
 * Created by rachel on 10/26/17.
 */


public class MainApplication extends Application implements IAdobeAuthClientCredentials {

    private static final String CREATIVE_SDK_CLIENT_ID      = "9ac6ea61db4e404f81caa7eb1d8701b9";
    private static final String CREATIVE_SDK_CLIENT_SECRET  = "76e7b3e0-9b37-43ad-ac73-0a52227416d7";
    private static final String CREATIVE_SDK_REDIRECT_URI   = "ams+d9d6d1d8ac86f89db6db6bfbddbf1e550c011e46://adobeid/9ac6ea61db4e404f81caa7eb1d8701b9";
    private static final String[] CREATIVE_SDK_SCOPES       = {"email", "profile", "address"};

    @Override
    public void onCreate() {
        super.onCreate();
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }

    @Override
    public String[] getAdditionalScopesList() {
        return CREATIVE_SDK_SCOPES;
    }

    @Override
    public String getRedirectURI() {
        return CREATIVE_SDK_REDIRECT_URI;
    }
}

