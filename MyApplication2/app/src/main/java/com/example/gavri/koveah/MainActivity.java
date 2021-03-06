package com.example.gavri.koveah;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
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
    public List<Project> projects;
    static int id = 1;
    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (!hasPermissions(this, permissions))
            ActivityCompat.requestPermissions(this, permissions, 1);

        createDB();
        this.projects = DB.projectsDao().getAllProjects();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        projectsView = findViewById(R.id.projects);
        addProject = findViewById(R.id.projectButton);

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
                showDetailActivity.putExtra("projects_index", projects.get(i).getId());
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

    public static Project getProject(int id) {
        return DB.projectsDao().findProjectById(id);
    }

    public List<Project> getProjects() {
        return this.projects;
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


}
