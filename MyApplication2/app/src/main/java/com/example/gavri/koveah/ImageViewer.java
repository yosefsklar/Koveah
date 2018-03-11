package com.example.gavri.koveah;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageViewer extends AppCompatActivity {

    private ImageView picture;

    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        Intent recieving = getIntent();

        picture = (ImageView) findViewById(R.id.detailed_view);
        if (picture != null)
            picture.setImageBitmap(BitmapFactory.decodeFile(recieving.getStringExtra("image_path")));

    }
}
