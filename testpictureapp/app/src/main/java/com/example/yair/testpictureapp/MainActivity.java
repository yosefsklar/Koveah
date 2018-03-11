package com.example.yair.testpictureapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    Button takePictureButton;
    private static final String LOG_TAG = "TakePictureTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);

        String[] permissions = {Manifest.permission.CAMERA};

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }

        takePictureButton = new Button(this);
        takePictureButton.setText("Take Picture");
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCamera();
            }
        });

        layout.setGravity(Gravity.CENTER_HORIZONTAL);
//        layout.addView(takePictureButton, new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                0));

        setContentView(layout);

    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void openCamera() {}
}
