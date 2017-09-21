package org.danibeni.andriot.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.danibeni.andriot.R;
import org.danibeni.andriot.model.DeviceFeature;
import org.danibeni.andriot.model.Project;

/**
 * Created by dbenitez on 03/09/2017.
 */

public class ProjectFragment2 extends Fragment {
    private static final String TAG = ProjectFragment2.class.getSimpleName();
    private static final String PROJECT_BUNDLE_KEY = "project_key";
    public static final String PROJECT_FRAGMENT_TAG = "project_fragment";

    private TextView project_name;
    private Activity activity;
    private Context context;
    private Project project;

    private OnProjectFragmentInteractionListener mListener;



    public static ProjectFragment2 newInstance(Project project) {
        ProjectFragment2 projectFragment = new ProjectFragment2();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PROJECT_BUNDLE_KEY, project);
        projectFragment.setArguments(bundle);

        return projectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        // Get Project data from activity
        project = getArguments().getParcelable(PROJECT_BUNDLE_KEY);

        Log.i(TAG, "Project name: " + project.getName());
        for(DeviceFeature feature: project.getDeviceFeatures()) {
            Log.i(TAG, "Feature " + feature.getId() + " --- " + feature.getName() + " -------- " + feature.getDeviceFeatureParams().getCommand());
        }

    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        //projectListSingleton = ProjectListSingleton.getInstance(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            try {
                mListener = (OnProjectFragmentInteractionListener) activity;
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
            //projectListSingleton = ProjectListSingleton.getInstance(context);
            try {
                mListener = (OnProjectFragmentInteractionListener) activity;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_project2, container, false);
        project_name = (TextView) view.findViewById(R.id.tv_project_name2);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        project_name.setText(project.getName());
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
            mListener.onProjectFragmentShowDeviceCatalog2();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

    }

    public void updateProjectView(Project project) {

    }

    //Interface for container activity of this Fragment
    public interface OnProjectFragmentInteractionListener {
        public void onProjectFragmentShowDeviceCatalog2();
    }
}
