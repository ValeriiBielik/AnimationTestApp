package com.test.projectsanimationapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.projectsanimationapp.adapter.ProjectsAdapter;
import com.test.projectsanimationapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProjectsAdapter adapter;
    private RecyclerView rvProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvProjects = findViewById(R.id.rv_projects);
        adapter = new ProjectsAdapter();
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateDataList(generateProjects());
    }

    private void setUpRecyclerView() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        rvProjects.setAdapter(adapter);
    }

    //todo for test purposes
    private List<Project> generateProjects() {
        List<Project> projects = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            projects.add(new Project(i, "Project " + i, "Jan 21, 2021",
                    ContextCompat.getDrawable(this, R.drawable.ic_1)));
        }
        return projects;
    }
}