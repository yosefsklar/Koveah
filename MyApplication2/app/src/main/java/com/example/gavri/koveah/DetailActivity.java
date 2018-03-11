package com.example.gavri.koveah;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        projectView = (TextView) findViewById(R.id.projectView);
        sessionView = (TextView) findViewById(R.id.sessionView);
        makomView = (TextView) findViewById(R.id.makomView);
        summaryView = (TextView) findViewById(R.id.summaryView);

        projectView.setText(sefarim[index]);
        sessionView.setText(sessions[index]);
        makomView.setText(daf[index]);
        summaryView.setText(summaries[index]);

//        SessionAdaptor sessionAdaptor = new SessionAdaptor(this, sefarim[index], daf[index], sessions[index], summaries[index]);


    }
}
