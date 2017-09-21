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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.danibeni.andriot.ProjectListSingleton;
import org.danibeni.andriot.R;
import org.danibeni.andriot.model.Device;
import org.danibeni.andriot.model.DeviceFeature;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnDeviceCatalogFragmentInteractionListener}
 * interface.
 */
public class DeviceCatalogFragment extends Fragment {
    private static final String TAG = DeviceCatalogFragment.class.getSimpleName();
    private static final String DEVICE_CATALOG_BUNDLE_KEY = "device_catalog_key";
    public static final String DEVICE_CATALOG_FRAGMENT_TAG = "project_fragment";
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    OnDeviceCatalogFragmentInteractionListener mListener;
    private ArrayList<Device> devices;

    //Device Catalog fragment Initialization
    public static DeviceCatalogFragment newInstance(ArrayList<Device> devices) {
        DeviceCatalogFragment deviceCatalogFragment = new DeviceCatalogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DEVICE_CATALOG_BUNDLE_KEY, devices);
        deviceCatalogFragment.setArguments(bundle);

        return deviceCatalogFragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeviceCatalogFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //devices = Device.DeviceListSample();
        devices = getArguments().getParcelableArrayList(DEVICE_CATALOG_BUNDLE_KEY);
        for(Device device: devices) {
            for(DeviceFeature feature: device.getDeviceFeatures()) {
                Log.i(TAG, feature.getName());
            }
        }
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_catalog_list, container, false);

        RecyclerView.LayoutManager layoutManager;
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                layoutManager = new LinearLayoutManager(context);
                layoutManager.setAutoMeasureEnabled(true);
                recyclerView.setLayoutManager(layoutManager);
            } else {
                layoutManager = new GridLayoutManager(context, mColumnCount);
                recyclerView.setLayoutManager(layoutManager);
            }
            recyclerView.setAdapter(new DeviceCatalogAdapter(devices, mListener));

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
        menu.removeGroup(R.id.dev_catalog_menu_group);
        inflater.inflate(R.menu.menu_dev_catalog, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_device_menu) {
            AddDeviceDialogFragment addDeviceDialog = new AddDeviceDialogFragment();
            addDeviceDialog.show(getFragmentManager(), "Add New device to catalog");
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
        if (context instanceof OnDeviceCatalogFragmentInteractionListener) {
            mListener = (OnDeviceCatalogFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeviceCatalogInteractionListener");
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
        if (context instanceof OnDeviceCatalogFragmentInteractionListener) {
            mListener = (OnDeviceCatalogFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeviceCatalogInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     *
     */
    public interface OnDeviceCatalogFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDeviceCatalogFragmentAddDeviceToProject(Device device);
        void onDeviceCatalogFragmentAddFeatureToProject(DeviceFeature feature);
    }
}
