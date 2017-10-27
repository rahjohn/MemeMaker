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
import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.adobe.creativesdk.aviary.internal.filters.ToolsFactory;
import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import java.io.IOException;

import static com.adobe.creativesdk.aviary.internal.filters.ToolsFactory.Tools.*;

public class EditActivity extends AppCompatActivity {
    private static final int REQ_CODE_CREATIVE_SDK = 1997;

    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        Uri uri = Uri.parse(intent.getExtras().getString("Uri Image"));
        // Only enable specific tools
        ToolsFactory.Tools[] tools = {CROP, MEME, ORIENTATION};

        Intent editor_intent = new AdobeImageIntent.Builder(EditActivity.this)
                // Launch the meme maker right away
                .saveWithNoChanges(false)
                .setData(uri)
                .withNoExitConfirmation(true)
                .withVibrationEnabled(false)
                .withToolList(tools)
                .build();
        startActivityForResult(editor_intent, REQ_CODE_CREATIVE_SDK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_CREATIVE_SDK && resultCode == RESULT_OK && null != data) {
            Uri edit_image = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), edit_image);
                ImageView image = (ImageView) findViewById(R.id.edit_image);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            finish();
        }
    }

}

