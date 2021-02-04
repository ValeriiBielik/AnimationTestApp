package com.test.projectsanimationapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
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
    private MotionLayout parentMotionLayout, motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        adapter = new ProjectsAdapter();
        setUpTransitionListeners();
        setUpRecyclerView();
    }

    private void initViews() {
        parentMotionLayout = findViewById(R.id.motion_parent);
        motionLayout = findViewById(R.id.motionLayout);
        rvProjects = findViewById(R.id.rv_projects);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateDataList(generateProjects());
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public int scrollVerticallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
                int scrollRange = super.scrollVerticallyBy(dx, recycler, state);
                int overScroll = dx - scrollRange;
                if (overScroll > 0) {
                    parentMotionLayout.transitionToState(R.id.reduced);
                    motionLayout.transitionToState(R.id.reduced);
                }
                return scrollRange;
            }
        };
        rvProjects.setLayoutManager(linearLayoutManager);
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

    private void setUpTransitionListeners() {
        parentMotionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (i == R.id.reduced) {
                    motionLayout.transitionToState(R.id.normal);
                } else if (i == R.id.normal) {
                    motionLayout.setTransition(R.id.base_transition);
                    motionLayout.transitionToStart();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
            }
        });
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (i == R.id.reduced) {
                    motionLayout.transitionToState(R.id.normal);
                } else if (i == R.id.normal) {
                    motionLayout.setTransition(R.id.base_transition);
                    motionLayout.transitionToStart();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }
}