package com.example.yair.testrecordingapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private Button recordButton = null;
    private Button playButton = null;
    private Chronometer timer = null;
    private boolean recording = false;
    private boolean playing = false;


    //Copied from android docs.
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String mFileName = "";
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;

    private boolean permissionToRecordAccepted = false;


    //Copied from android docs
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordButton = new Button(this);
        playButton = new Button(this);
        timer = new Chronometer(this);

        recordButton.setText("Record");
        playButton.setText("Play");

        //Copied from Android docs
        mFileName += getFilesDir().getAbsolutePath() + "/recording.mp4";
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recording) {
                    stopRecording();
                } else {
                    startRecording();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!playing) {
                    playRecording();
                } else {
                    stopPlaying();
                }
            }
        });

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(recordButton, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        layout.addView(timer, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        layout.addView(playButton, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));

        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(layout);


    }

    private void startRecording() {
        //Reset Text
        recordButton.setText("Stop");

        //Disable button
        playButton.setEnabled(false);

        //Copied from android docs
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        recording = true;
    }

    private void stopRecording() {
        recordButton.setText("Record");
        playButton.setEnabled(true);

        //Copied from android docs
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        timer.stop();

        recording = false;
    }

    private void playRecording() {
        mPlayer = new MediaPlayer();
        playButton.setText("Stop");
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            Log.e(LOG_TAG, "prepare() failed");
        }
        playing = true;
    }

    private void stopPlaying() {
        mPlayer.stop();
        playButton.setText("Play");
        playing = false;
    }
}
