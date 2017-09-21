package org.danibeni.andriot.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.danibeni.andriot.R;
import org.danibeni.andriot.fragments.DeviceCatalogFragment.OnDeviceCatalogFragmentInteractionListener;
import org.danibeni.andriot.model.Device;

import java.util.ArrayList;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Device} and makes a call to the
 * specified {@link OnDeviceCatalogFragmentInteractionListener}.
 */
public class DeviceCatalogAdapter extends RecyclerView.Adapter<DeviceCatalogAdapter.ViewHolder> {

    private static final String TAG = DeviceCatalogAdapter.class.getSimpleName();
    private final ArrayList<Device> devices;
    private final OnDeviceCatalogFragmentInteractionListener mListener;
    private static RecyclerView featureList;

    public DeviceCatalogAdapter(ArrayList<Device> items, OnDeviceCatalogFragmentInteractionListener listener) {
        devices = items;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dev_catalog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.device = devices.get(position);
        holder.tvDeviceName.setText(holder.device.getName());
        holder.featureListAdapter.setData(holder.device.getDeviceFeatures());
        holder.featureListAdapter.setRowIndex(position);

        holder.btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onDeviceCatalogFragmentAddDeviceToProject(holder.device);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDeviceName;
        public final Button btnAddDevice;
        public Device device;
        private FeatureCatalogAdapter featureListAdapter;

        public ViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            mView = view;
            tvDeviceName = (TextView) view.findViewById(R.id.tv_device_name);
            btnAddDevice = (Button) view.findViewById(R.id.btn_add_device);
            //tvFeatureName = (TextView) view.findViewById(R.id.tv_device_feature_name);
            //btnAddFeature = (Button) view.findViewById(R.id.btn_add_dev_feature);
            featureList = (RecyclerView) itemView.findViewById(R.id.device_feature_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setAutoMeasureEnabled(true);
            featureList.setLayoutManager(layoutManager);
            featureListAdapter = new FeatureCatalogAdapter(mListener);
            featureList.setAdapter(featureListAdapter);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvDeviceName.getText() + "'";
        }
    }
}
