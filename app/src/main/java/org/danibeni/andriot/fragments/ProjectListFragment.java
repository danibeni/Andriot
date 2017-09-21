package org.danibeni.andriot.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.danibeni.andriot.ProjectListSingleton;
import org.danibeni.andriot.R;
import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 02/09/2017.
 */

public class ProjectListFragment extends Fragment {
    private static final String TAG = ProjectListFragment.class.getSimpleName();
    private static final String PROJECT_LIST_BUNDLE_KEY = "project_list_key";
    public static final String PROJECT_LIST_FRAGMENT_TAG = "project_list_fragment";

    private Context context;
    private Activity activity;
    private ArrayList<Project> projects;

    //RecyclerView Projects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProjectListRecyclerViewAdapter projectsAdapter;

    private OnProjectListFragmentInteractionListener mListener;

    public static ProjectListFragment newInstance(ArrayList<Project> projects) {
        ProjectListFragment projectListFragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PROJECT_LIST_BUNDLE_KEY, projects);
        projectListFragment.setArguments(bundle);

        return projectListFragment;
}

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        if (getArguments() != null) {
            // Get Project data from activity
            projects = getArguments().getParcelableArrayList(PROJECT_LIST_BUNDLE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.project_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        projectsAdapter = new ProjectListRecyclerViewAdapter(getActivity(), projects, mListener);
        recyclerView.setAdapter(projectsAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onProjectListFragmentShowEditProject(Project.PROJECT_EMPTY);
            }
        });
        return view;
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            projects = Project.ProjectListSample();
            try {
                mListener = (OnProjectListFragmentInteractionListener) activity;
            } catch (ClassCastException cce) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");

            }
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
            projects = Project.ProjectListSample();
            try {
                mListener = (OnProjectListFragmentInteractionListener) activity;
            } catch (ClassCastException cce) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");

            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Interface for container activity of this Fragment
    public interface OnProjectListFragmentInteractionListener {
        public void onProjectListFragmentShowProject(Project project);
        public void onProjectListFragmentShowEditProject(Project project);
        public void onProjectListFragmentDeleteProject(Project project);
    }
}
