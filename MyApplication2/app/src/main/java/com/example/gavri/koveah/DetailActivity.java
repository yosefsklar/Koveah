package com.example.gavri.koveah;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    TextView projectView;
    TextView sessionView;
    TextView makomView;
    TextView summaryView;

    String[] sefarim;
    String[] daf;
    String[] sessions;
    String[] summaries;

    Button viewImageButton;
    Button takeImageButton;
    private String mCurrentPhotoPath;
    private static final String LOG_TAG = "TakePicture";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Resources res = getResources();
        sefarim = res.getStringArray(R.array.sefarim);
        daf = res.getStringArray(R.array.daf);
        sessions = res.getStringArray(R.array.time);
        summaries = res.getStringArray(R.array.summary);

        Intent in = getIntent();
        int index = in.getIntExtra("projects_index", -1);

        projectView = findViewById(R.id.projectView);
        sessionView = findViewById(R.id.sessionView);
        makomView = findViewById(R.id.makomView);
        summaryView = findViewById(R.id.summaryView);

        projectView.setText(sefarim[index]);
        sessionView.setText(sessions[index]);
        makomView.setText(daf[index]);
        summaryView.setText(summaries[index]);

//        SessionAdaptor sessionAdaptor = new SessionAdaptor(this, sefarim[index], daf[index], sessions[index], summaries[index]);

        viewImageButton = findViewById(R.id.view_image);
        viewImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DetailActivity.this, ImageViewer.class);
                newIntent.putExtra("image_path", mCurrentPhotoPath);
                startActivity(newIntent);
            }
        });


        takeImageButton = findViewById(R.id.take_image);
        takeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }


    //Copied from android docs
    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "takenpicture";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Copied from android docs
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(LOG_TAG, ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }


}
