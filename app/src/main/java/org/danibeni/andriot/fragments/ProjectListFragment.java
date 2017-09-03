package org.danibeni.andriot.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.danibeni.andriot.AndriotApplication;
import org.danibeni.andriot.ProjectActivity;
import org.danibeni.andriot.ProjectListActivity;
import org.danibeni.andriot.ProjectListAdapter;
import org.danibeni.andriot.ProjectListSingleton;
import org.danibeni.andriot.R;
import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 02/09/2017.
 */

public class ProjectListFragment extends Fragment {

    private Context context;
    private Activity activity;
    private ArrayList<Project> projects;

    //RecyclerView Projects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProjectListAdapter projectsAdapter;
    private ProjectListSingleton projectListSingleton;


    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        projectListSingleton = ProjectListSingleton.getInstance(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            projects = ProjectListSingleton.getInstance(context).getProjects();
        }
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
    */
    @SuppressWarnings("deprecation")
    @Override
    public final void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
    */
    protected void onAttachToContext(Context context) {
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            projectListSingleton = ProjectListSingleton.getInstance(context);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_project_list, container, false);

        recyclerView = (RecyclerView) vista.findViewById(R.id.project_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        projectsAdapter = new ProjectListAdapter(getActivity(), projectListSingleton.getProjects());
        projectListSingleton.setProjectListAdapter(projectsAdapter);
        recyclerView.setAdapter(projectListSingleton.getProjectListAdapter());

        projectsAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProjectListActivity) activity).showProject(recyclerView.getChildAdapterPosition(v));
            }
        });
        return vista;
    }
}
