package com.example.gavri.koveah;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button addProject;
    ListView projects;
    String[] sefarim;
    String[] daf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        projects = (ListView) findViewById(R.id.projects);
        addProject = (Button) findViewById(R.id.projectButton);
        sefarim = res.getStringArray(R.array.sefarim);
        daf = res.getStringArray(R.array.daf);

//        projects.setAdapter(new ArrayAdapter<String>(this, R.layout.projects_detail, sefarim));

        ProjectsAdaptor projectsAdaptor = new ProjectsAdaptor(this, sefarim, daf);
        projects.setAdapter(projectsAdaptor);

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button pressed");
                Intent addPorjectActivity = new Intent(MainActivity.this, AddNewProject.class);
                startActivity(addPorjectActivity);
            }
        });

        projects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("projects_index", i);
                startActivity(showDetailActivity);
            }
        });
    }
}
