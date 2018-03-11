package com.example.gavri.koveah;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addProject;
    ListView projectsView;
    static AppDatabase DB;
    List<Project> projects;
    static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        createDB();
        this.projects = DB.projectsDao().getAllProjects();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        projectsView = (ListView) findViewById(R.id.projects);
        addProject = (Button) findViewById(R.id.projectButton);

//        projects.setAdapter(new ArrayAdapter<String>(this, R.layout.projects_detail, sefarim));

        ProjectsAdaptor projectsAdaptor = new ProjectsAdaptor(this, projects);
        projectsView.setAdapter(projectsAdaptor);

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addProjectActivity = new Intent(getApplicationContext(), AddNewProject.class);
                startActivity(addProjectActivity);
            }
        });

        projectsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("projects_index", i);
                startActivity(showDetailActivity);
            }
        });
    }

//    private Projects fetchData(int id, Context c) {
//        AppDatabase DB = AppDatabase.getInMemoryDatabase(c.getApplicationContext());
//        return DB.projectsDao().findProjectById(id);
//    }

    public void createDB() {
        DB = AppDatabase.getInMemoryDatabase(this.getApplication());
    }

    public static void addProject(String name, int page) {
        Project p = new Project(id, name, page, null, 0, null, null);
        DB.projectsDao().insert(p);
        id++;
    }


}
