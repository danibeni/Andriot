package org.danibeni.andriot;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 01/09/2017.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Project> projects;
    protected View.OnClickListener onClickListener;

    public ProjectListAdapter(Context context, ArrayList<Project> projects) {
        this.projects = projects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.project_list_item, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.projectName.setText(projects.get(i).getName());
        holder.projectDescription.setText(projects.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName, projectDescription;
        public CardView projectCardView;

        ViewHolder(View itemView) {
            super(itemView);
            projectCardView = (CardView) itemView.findViewById(R.id.cv_project_item);
            projectName= (TextView) itemView.findViewById(R.id.tv_project_name);
            projectDescription = (TextView) itemView.findViewById(R.id.tv_project_description);
        }
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
