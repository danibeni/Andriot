package org.danibeni.andriot.fragments;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.danibeni.andriot.R;
import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 01/09/2017.
 */

public class ProjectListRecyclerViewAdapter extends RecyclerView.Adapter<ProjectListRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = ProjectListRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Project> projects;
    private final ProjectListFragment.OnProjectListFragmentInteractionListener mListener;

    public ProjectListRecyclerViewAdapter(Context context, ArrayList<Project> projects, ProjectListFragment.OnProjectListFragmentInteractionListener listener) {
        this.context = context;
        this.projects = projects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.fragment_project_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.project = projects.get(position);
        holder.projectName.setText(projects.get(position).getName());
        holder.projectDescription.setText(projects.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onProjectListFragmentShowProject(holder.project);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.mView);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_context_project_list);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit_project:
                                //handle edit project
                                mListener.onProjectListFragmentShowEditProject(holder.project);
                                break;
                            case R.id.menu_delete_project:
                                //handle delete project
                                mListener.onProjectListFragmentDeleteProject(holder.project);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView projectName, projectDescription;
        public final CardView projectCardView;
        public Project project;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            projectCardView = (CardView) itemView.findViewById(R.id.cv_project_item);
            projectName= (TextView) itemView.findViewById(R.id.tv_project_name);
            projectDescription = (TextView) itemView.findViewById(R.id.tv_project_description);
        }
    }
}
