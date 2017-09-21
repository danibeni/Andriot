package org.danibeni.andriot.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.danibeni.andriot.R;
import org.danibeni.andriot.model.DeviceFeature;
import org.danibeni.andriot.model.Project;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnProjectFragmentInteractionListener}
 * interface.
 */
public class ProjectFragment extends Fragment {
    private static final String TAG = ProjectFragment2.class.getSimpleName();
    private static final String PROJECT_BUNDLE_KEY = "project_key";
    public static final String PROJECT_FRAGMENT_TAG = "project_fragment";
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private Context context;
    private Project project;

    private OnProjectFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProjectFragment newInstance(Project project) {
        ProjectFragment projectFragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PROJECT_BUNDLE_KEY, project);
        projectFragment.setArguments(bundle);

        return projectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            project = getArguments().getParcelable(PROJECT_BUNDLE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_project_features_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ProjectAdapter(project.getDeviceFeatures(), mListener));
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeGroup(R.id.project_menu_group);
        inflater.inflate(R.menu.menu_project, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dev_catalog_menu) {
            mListener.onProjectFragmentShowDeviceCatalog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnProjectFragmentInteractionListener) {
            mListener = (OnProjectFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProjectFragmentInteractionListener");
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
        this.context = context;
        if (context instanceof Activity) {
            if (context instanceof OnProjectFragmentInteractionListener) {
                mListener = (OnProjectFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnProjectFragmentInteractionListener");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateProjectView(Project project) {

    }

    public interface OnProjectFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onProjectFragmentShowDeviceCatalog();
        public void onProjectFragmentEditFeature(DeviceFeature feature);
    }
}
