package com.test.projectsanimationapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.projectsanimationapp.databinding.ItemProjectBinding;
import com.test.projectsanimationapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    private final List<Project> dataList = new ArrayList<>();

    public void updateDataList(List<Project> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProjectBinding binding = ItemProjectBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final ItemProjectBinding mBinding;

        public ProjectViewHolder(@NonNull ItemProjectBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Project project) {
            mBinding.setProject(project);
            mBinding.executePendingBindings();
        }
    }
}
