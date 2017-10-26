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

import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

import java.io.IOException;

public class EditActivity extends AppCompatActivity {
    private static final int REQ_CODE_CREATIVE_SDK = 1997;

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
        Intent editor_intent = new AdobeImageIntent.Builder(EditActivity.this).setData(uri).build();
        startActivityForResult(editor_intent, REQ_CODE_CREATIVE_SDK);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_CREATIVE_SDK && resultCode == RESULT_OK && null != data && data.getData() != null) {
            Uri edited_image = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), edited_image);
                ImageView image = (ImageView) findViewById(R.id.edit_image);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}

