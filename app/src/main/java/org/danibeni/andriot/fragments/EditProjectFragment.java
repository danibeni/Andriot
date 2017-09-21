package org.danibeni.andriot.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.danibeni.andriot.R;
import org.danibeni.andriot.model.Project;

/**
 * Created by dbenitez on 03/09/2017.
 */

public class EditProjectFragment extends Fragment {
    private static final String TAG = EditProjectFragment.class.getSimpleName();
    public static final String EDIT_PROJECT_BUNDLE_KEY = "edit_project_key";
    public static final String EDIT_PROJECT_FRAGMENT_TAG = "edit_project_fragment";

    private Context context;
    private Activity activity;
    private Project project;

    private EditText etProjectName, etProjectDescription;
    private TextInputLayout layoutProjectName, layoutProjectDescription;
    private Button btnEditProject, btnCancel;

    private OnEditProjectFragmentInteractionListener mListener;


    public static EditProjectFragment newInstance(Project project) {
        EditProjectFragment editProjectFragment = new EditProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EDIT_PROJECT_BUNDLE_KEY, project);
        editProjectFragment.setArguments(bundle);

        return editProjectFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        project = getArguments().getParcelable(EDIT_PROJECT_BUNDLE_KEY);
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            try {
                mListener = (OnEditProjectFragmentInteractionListener) activity;
            } catch (ClassCastException cce) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnNewProjectFragmentListener");

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
            try {
                mListener = (OnEditProjectFragmentInteractionListener) activity;
            } catch (ClassCastException cce) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnNewProjectFragmentListener");

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_project, container, false);
        layoutProjectName = (TextInputLayout) view.findViewById(R.id.layout_project_name);
        layoutProjectDescription = (TextInputLayout) view.findViewById(R.id.layout_project_description);
        etProjectName = (EditText) view.findViewById(R.id.et_project_name);
        etProjectDescription = (EditText) view.findViewById(R.id.et_project_description);
        btnEditProject = (Button) view.findViewById(R.id.btn_add_edit_project);

        etProjectName.setText(project.getName());
        etProjectDescription.setText(project.getDescription());
        if (this.project == Project.PROJECT_EMPTY) {
            btnEditProject.setText(R.string.add_project);
        } else {
            btnEditProject.setText(R.string.edit_project);
        }

        btnEditProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
            }
        });

        btnCancel = (Button) view.findViewById(R.id.btn_cancel_new_project);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNewProject();
            }
        });

        return view;
    }

    private boolean checkProjectName() {
        if (etProjectName.getText().toString().trim().isEmpty()) {
            layoutProjectName.setErrorEnabled(true);
            layoutProjectName.setError(getString(R.string.err_project_name_empty));
            etProjectName.setError(getString(R.string.err_input_required));
            return false;
        }
        layoutProjectName.setErrorEnabled(false);
        return true;
    }

    private void submitProject() {
        if (checkProjectName()) {
            if (this.project == Project.PROJECT_EMPTY) {
                this.project = new Project.ProjectBuilder()
                        .withName(etProjectName.getText().toString())
                        .withDescription(etProjectDescription.getText().toString())
                        .build();
                mListener.onEditProjectFragmentShowProjectList(this.project);

            } else {
                this.project.setName(etProjectName.getText().toString());
                this.project.setDescription(etProjectDescription.getText().toString());
                mListener.onEditProjectFragmentShowProjectList(this.project);
            }

        }
    }

    private void cancelNewProject() {
        mListener.onEditProjectFragmentShowProjectList(project);
    }

    public void updateProjectData(Project project) {
        this.project = project;
    }
    
    //Interface for container activity of this Fragment
    public interface OnEditProjectFragmentInteractionListener {
        public void onEditProjectFragmentShowProjectList(Project project);
    }
}
