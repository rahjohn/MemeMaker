package com.rachelelisejohnson.mememaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

import java.io.IOException;

public class EditActivity extends AppCompatActivity implements IAdobeAuthClientCredentials {
    private static final String CREATIVE_SDK_CLIENT_ID      = "9ac6ea61db4e404f81caa7eb1d8701b9";
    private static final String CREATIVE_SDK_CLIENT_SECRET  = "76e7b3e0-9b37-43ad-ac73-0a52227416d7";
    private static final String CREATIVE_SDK_REDIRECT_URI   = "ams+d9d6d1d8ac86f89db6db6bfbddbf1e550c011e46://adobeid/9ac6ea61db4e404f81caa7eb1d8701b9";
    private static final String[] CREATIVE_SDK_SCOPES       = {"email", "profile", "address"};

    Bitmap bitmap = null;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        Uri uri = Uri.parse(intent.getExtras().getString("Uri Image"));
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            ImageView image = (ImageView) findViewById(R.id.edit_image);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

