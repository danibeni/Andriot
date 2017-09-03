package org.danibeni.andriot.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by dbenitez on 01/08/2017.
 */

public class DeviceFeature {
    private long id = -1;
    private String name = "";
    private String type = "";
    public DeviceFeatureParams deviceFeatureParams = DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY;
    public DeviceFeatureGUIParams deviceFeatureGUIParams = DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY;
    private String date = "";

    public final static DeviceFeature DEVICE_FEATURE_EMPTY = new DeviceFeature(-1, "", "", DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY, DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY, "");

    private DeviceFeature(long id, String name, String type, DeviceFeatureParams deviceFeatureParams, DeviceFeatureGUIParams deviceFeatureGUIParams, String date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.deviceFeatureParams = deviceFeatureParams;
        this.deviceFeatureGUIParams = deviceFeatureGUIParams;
        this.date = date;
    }

    /******************************************************************
     * Builder pattern for DeviceFeature object
     ******************************************************************/
    public static class DeviceFeatureBuilder {
        private long id = -1;
        private String name = "";
        private String type = "";
        public DeviceFeatureParams deviceFeatureParams = DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY;
        private DeviceFeatureGUIParams deviceFeatureGUIParams = DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY;
        private String date = "";

        public DeviceFeatureBuilder withId (long id) {
            this.id = id;
            return this;
        }

        public DeviceFeatureBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DeviceFeatureBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public DeviceFeatureBuilder withDeviceFeatureParams(DeviceFeatureParams deviceFeatureParams) {
            this.deviceFeatureParams = deviceFeatureParams;
            return this;
        }

        public DeviceFeatureBuilder withDeviceFeatureGUI(DeviceFeatureGUIParams deviceFeatureGUIParams) {
            this.deviceFeatureGUIParams = deviceFeatureGUIParams;
            return this;
        }

        public DeviceFeatureBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public DeviceFeature build() {
            return new DeviceFeature(id, name, type, deviceFeatureParams, deviceFeatureGUIParams, date);
        }
    }

    /*******************************************************************
     * Getters And Setters
     *******************************************************************/
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DeviceFeatureParams getDeviceFeatureParams() {
        return deviceFeatureParams;
    }

    public void setDeviceFeatureParams(DeviceFeatureParams deviceFeatureParams) {
        this.deviceFeatureParams = deviceFeatureParams;
    }

    public DeviceFeatureGUIParams getDeviceFeatureGUIParams() {
        return deviceFeatureGUIParams;
    }

    public void setDeviceFeatureGUIParams(DeviceFeatureGUIParams deviceFeatureGUIParams) {
        this.deviceFeatureGUIParams = deviceFeatureGUIParams;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /***************************************************
     * Functions to transform JSON String Device Feature
     * Parameters into a DeviceFeatureParams object
     ***************************************************/
    public String getJsonStringFromDeviceFeatureParams() {
        String json_params = "";
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureParams>() {}.getType();
        json_params = gson.toJson(deviceFeatureParams, type);
        return json_params;
    }

    public void setDeviceFeatureParamsFromJsonString(String params_json) {
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureParams>() {}.getType();

        this.deviceFeatureParams = gson.fromJson(params_json, type);
    }

    /***************************************************
     * Functions to transform JSON String Device Feature
     * GUI Parameters into a DeviceFeatureGUIParams object
     ***************************************************/
    public String getJsonStringFromDeviceFeatureGUIParams() {
        String json_params = "";
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureGUIParams>() {}.getType();
        json_params = gson.toJson(deviceFeatureGUIParams, type);
        return json_params;
    }

    public void setDeviceFeatureGUIParamsFromJsonString(String params_json) {
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureGUIParams>() {}.getType();

        this.deviceFeatureGUIParams = gson.fromJson(params_json, type);
    }
}
