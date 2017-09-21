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
import org.danibeni.andriot.model.DeviceFeature;
import org.danibeni.andriot.model.Project;

/**
 * Created by dbenitez on 03/09/2017.
 */

public class EditFeatureFragment extends Fragment {
    private static final String TAG = EditFeatureFragment.class.getSimpleName();
    public static final String EDIT_FEATURE_BUNDLE_KEY = "edit_feature_key";
    public static final String EDIT_FEATURE_FRAGMENT_TAG = "edit_feature_fragment";

    private Context context;
    private Activity activity;
    private DeviceFeature feature;

    private EditText etFeatureName;
    private TextInputLayout layoutFeatureName;
    private Button btnDone, btnCancel;

    private OnEditFeatureFragmentInteractionListener mListener;


    public static EditFeatureFragment newInstance(DeviceFeature feature) {
        EditFeatureFragment editFeatureFragment = new EditFeatureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EDIT_FEATURE_BUNDLE_KEY, feature);
        editFeatureFragment.setArguments(bundle);

        return editFeatureFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feature = getArguments().getParcelable(EDIT_FEATURE_BUNDLE_KEY);
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            try {
                mListener = (OnEditFeatureFragmentInteractionListener) activity;
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
                mListener = (OnEditFeatureFragmentInteractionListener) activity;
            } catch (ClassCastException cce) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnNewProjectFragmentListener");

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_project_feature, container, false);
        layoutFeatureName = (TextInputLayout) view.findViewById(R.id.til_feature_name);
        etFeatureName = (EditText) view.findViewById(R.id.et_feature_name);
        btnDone = (Button) view.findViewById(R.id.btn_done_edit_feature);

        etFeatureName.setText(feature.getName());

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFeature();
            }
        });

        btnCancel = (Button) view.findViewById(R.id.btn_cancel_edit_feature);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelEditFeature();
            }
        });

        return view;
    }

    private boolean checkFeatureName() {
        if (etFeatureName.getText().toString().trim().isEmpty()) {
            layoutFeatureName.setErrorEnabled(true);
            layoutFeatureName.setError(getString(R.string.err_field_empty));
            etFeatureName.setError(getString(R.string.err_input_required));
            return false;
        }
        layoutFeatureName.setErrorEnabled(false);
        return true;
    }

    private void submitFeature() {
        if (checkFeatureName()) {
            feature.setName(etFeatureName.getText().toString());
            mListener.onEditFeatureFragmentShowProject(this.feature);
        }
    }

    private void cancelEditFeature() {
        this.feature = DeviceFeature.DEVICE_FEATURE_EMPTY;
        mListener.onEditFeatureFragmentShowProject(this.feature);
    }

    public void updateFeatureData(DeviceFeature feature) {
        this.feature = feature;
    }

    //Interface for container activity of this Fragment
    public interface OnEditFeatureFragmentInteractionListener {
        public void onEditFeatureFragmentShowProject(DeviceFeature feature);
    }
}
