package com.example.gavri.koveah;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
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
    private static final String LOG_TAG_PIC = "TakePicture";


    Button newRecordingButton;
    Button playRecordingButton;
    private String mAudioFileName = "";
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private static final String LOG_TAG_AUDIO = "AudioRecordTest";
    private Chronometer timer = null;
    private boolean recording = false;
    private boolean playing = false;





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
        int index = in.getIntExtra("projects_index", 0);

        projectView = findViewById(R.id.projectView);
        sessionView = findViewById(R.id.sessionView);
        makomView = findViewById(R.id.makomView);
        summaryView = findViewById(R.id.summaryView);

        projectView.setText(sefarim[index]);
        sessionView.setText(sessions[index]);
        makomView.setText(daf[index]);
        summaryView.setText(summaries[index]);


        viewImageButton = findViewById(R.id.view_image);
        viewImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DetailActivity.this, ImageViewer.class);
                newIntent.putExtra("image_path", mCurrentPhotoPath);
                startActivity(newIntent);
            }
        });


        mAudioFileName += getFilesDir().getAbsolutePath() + "/recording.mp4";
        timer = findViewById(R.id.timer);
        takeImageButton = findViewById(R.id.take_image);
        takeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });



        newRecordingButton = findViewById(R.id.record_new);
        newRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recording) {
                    stopRecording();
                } else {
                    startRecording();
                }
            }
        });

        playRecordingButton = findViewById(R.id.play_recording);
        playRecordingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!playing) {
                    playRecording();
                } else {
                    stopPlaying();
                }
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
                Log.e(LOG_TAG_PIC, ex.getMessage());
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




    

    private void startRecording() {
        //Reset Text
        newRecordingButton.setText("Stop");

        //Disable button
        playRecordingButton.setEnabled(false);

        //Copied from android docs
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mAudioFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG_AUDIO, e.getMessage());
            Log.e(LOG_TAG_AUDIO, "prepare() failed");
        }

        mRecorder.start();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        recording = true;
    }

    private void stopRecording() {
        newRecordingButton.setText("Record");
        playRecordingButton.setEnabled(true);

        //Copied from android docs
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        timer.stop();

        recording = false;
    }

    private void playRecording() {
        mPlayer = new MediaPlayer();
        playRecordingButton.setText("Stop");
        try {
            mPlayer.setDataSource(mAudioFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG_AUDIO, e.getMessage());
            Log.e(LOG_TAG_AUDIO, "prepare() failed");
        }
        playing = true;
    }

    private void stopPlaying() {
        mPlayer.stop();
        playRecordingButton.setText("Play");
        playing = false;
    }

}
