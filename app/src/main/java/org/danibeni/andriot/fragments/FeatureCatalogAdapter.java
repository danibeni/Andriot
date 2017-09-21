package org.danibeni.andriot.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.danibeni.andriot.R;
import org.danibeni.andriot.model.DeviceFeature;

import java.util.ArrayList;

public class FeatureCatalogAdapter extends RecyclerView.Adapter<FeatureCatalogAdapter.ViewHolder> {

    private static final String TAG = FeatureCatalogAdapter.class.getSimpleName();
    private ArrayList<DeviceFeature> features;
    private int mRowIndex = -1;
    private final DeviceCatalogFragment.OnDeviceCatalogFragmentInteractionListener mListener;

    public FeatureCatalogAdapter(DeviceCatalogFragment.OnDeviceCatalogFragmentInteractionListener mListener)
    {
        this.features = new ArrayList<>();
        this.mListener = mListener;
    }

    public void setData(ArrayList<DeviceFeature> data) {
        if (features != data) {
            features = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feature_catalog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = features.get(position);
        holder.tvFeatureName.setText(features.get(position).getName());


        holder.btnAddFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onDeviceCatalogFragmentAddFeatureToProject(holder.mItem);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View featureView;
        public final TextView tvFeatureName;
        public final Button btnAddFeature;
        public DeviceFeature mItem;

        public ViewHolder(View view) {
            super(view);
            featureView = view;
            tvFeatureName = (TextView) view.findViewById(R.id.tv_device_feature_name);
            btnAddFeature = (Button) view.findViewById(R.id.btn_add_device_feature);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvFeatureName.getText() + "'";
        }
    }
}
