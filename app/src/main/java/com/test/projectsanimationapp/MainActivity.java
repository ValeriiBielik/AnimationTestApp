package com.test.projectsanimationapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.projectsanimationapp.adapter.ProjectsAdapter;
import com.test.projectsanimationapp.databinding.ActivityMainBinding;
import com.test.projectsanimationapp.model.Project;
import com.test.projectsanimationapp.model.State;
import com.test.projectsanimationapp.util.ToolbarAnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProjectsAdapter adapter;
    private ToolbarAnimationUtil animationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new ProjectsAdapter();
        setUpRecyclerView();
        animationUtil = new ToolbarAnimationUtil(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateDataList(generateProjects());
    }

    private void setUpRecyclerView() {
        binding.rvProjects.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProjects.setAdapter(adapter);
        binding.rvProjects.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) {
                    animationUtil.resizeToMax(binding.toolbar);
                } else {
                    animationUtil.resizeToMin(binding.toolbar);
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (animationUtil.getState() == State.REDUCED) {
                        animationUtil.resizeToNormal(binding.toolbar);
                    }
                }
            }
        });
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