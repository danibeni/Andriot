package org.danibeni.andriot.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import org.danibeni.andriot.R;
import org.danibeni.andriot.fragments.ProjectFragment.OnProjectFragmentInteractionListener;
import org.danibeni.andriot.model.DeviceFeature;
import org.danibeni.andriot.model.DeviceFeatureTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DeviceFeature} and makes a call to the
 * specified {@link OnProjectFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private final List<DeviceFeature> features;
    private ArrayList<DeviceFeature> selectedItems = new ArrayList<>();
    private boolean multiSelect = false;
    private final OnProjectFragmentInteractionListener mListener;

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            multiSelect = true;
            menu.add("Delete");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            for (DeviceFeature featureItem : selectedItems) {
                features.remove(featureItem);
            }
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            multiSelect = false;
            selectedItems.clear();
            notifyDataSetChanged();
        }
    };

    public ProjectAdapter(List<DeviceFeature> items, OnProjectFragmentInteractionListener listener) {
        features = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project_feature, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.update(features.get(position));
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView featureName;
        public DeviceFeature feature;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            featureName = (TextView) view.findViewById(R.id.tv_project_feature_name);

        }

        void selectItem(DeviceFeature item) {
            if (multiSelect) {
                if (selectedItems.contains(item)) {
                    selectedItems.remove(item);
                    mView.setBackgroundColor(Color.WHITE);
                } else {
                    selectedItems.add(item);
                    mView.setBackgroundColor(Color.LTGRAY);
                }
            }
        }

        void includeFeatureTypeLayout (DeviceFeature item) {
            int layout_id;
            View featureTypeView;
            String type = item.getType().toLowerCase();
            switch (type) {
                case DeviceFeatureTypes.SENSOR_TYPE:
                    layout_id = R.layout.feature_type_sensor;
                    ViewStub vsFeature = mView.findViewById(R.id.vs_project_feature);
                    if (vsFeature != null) {
                        vsFeature.setLayoutResource(layout_id);
                        vsFeature.inflate();
                        TextView tvFeatureValue = mView.findViewById(R.id.tv_feature_sensor_value);
                        TextView tvFeatureUnits = mView.findViewById(R.id.tv_feature_sensor_units);
                        tvFeatureValue.setText(item.getValue());
                        tvFeatureUnits.setText(item.getDeviceFeatureParams().getUnits());
                    }
                    break;
                default:
                    layout_id = R.layout.feature_type_sensor;
            }
        }

        void update(final DeviceFeature itemFeature) {
            featureName.setText(itemFeature.getName());
            includeFeatureTypeLayout(itemFeature);
            if (selectedItems.contains(itemFeature)) {
                mView.setBackgroundColor(Color.LTGRAY);
            } else {
                mView.setBackgroundColor(Color.WHITE);
            }
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (multiSelect) {
                        selectItem(itemFeature);
                    } else {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onProjectFragmentEditFeature(itemFeature);
                        }
                    }
                }
            });

            mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
                    selectItem(itemFeature);
                    return true;
                }
            });
        }
        @Override
        public String toString() {
            return super.toString() + " '" + featureName.getText() + "'";
        }

    }
}
