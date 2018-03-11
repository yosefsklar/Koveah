package com.example.gavri.koveah;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
                newIntent.putExtra("image_path", "/storage/emulated/0/Android/data/com.example.yair.testpictureapp/files/Pictures/takenpicture-1442695878.jpg");
                startActivity(newIntent);
            }
        });
    }
}
