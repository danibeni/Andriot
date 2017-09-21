package org.danibeni.andriot.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dbenitez on 01/08/2017.
 */

public class DeviceFeature implements Parcelable{
    private long id = -1;
    private String name = "";
    private String value = "";
    private String type = "";
    private long deviceId = -1;
    public DeviceFeatureParams deviceFeatureParams = DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY;
    public DeviceFeatureGUIParams deviceFeatureGUIParams = DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY;
    private String date = "";

    public final static DeviceFeature DEVICE_FEATURE_EMPTY = new DeviceFeature(-1, -1, "", "", "", DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY, DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY, "");

    private DeviceFeature(long id, long deviceId, String name, String value, String type,DeviceFeatureParams deviceFeatureParams, DeviceFeatureGUIParams deviceFeatureGUIParams, String date) {
        this.id = id;
        this.deviceId = deviceId;
        this.name = name;
        this.value = value;
        this.type = type;
        this.deviceFeatureParams = deviceFeatureParams;
        this.deviceFeatureGUIParams = deviceFeatureGUIParams;
        this.date = date;
    }

    protected DeviceFeature(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DeviceFeature> CREATOR = new Creator<DeviceFeature>() {
        @Override
        public DeviceFeature createFromParcel(Parcel in) {
            return new DeviceFeature(in);
        }

        @Override
        public DeviceFeature[] newArray(int size) {
            return new DeviceFeature[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.deviceFeatureParams, i);
        parcel.writeParcelable(this.deviceFeatureGUIParams, i);
    }

    private void readFromParcel(Parcel in) {
        this.deviceFeatureParams = (DeviceFeatureParams) in.readParcelable(DeviceFeatureParams.class.getClassLoader());
        this.deviceFeatureGUIParams = (DeviceFeatureGUIParams) in.readParcelable(DeviceFeatureGUIParams.class.getClassLoader());
        this.id = in.readLong();
        this.name = in.readString();
        this.value = in.readString();
        this.type = in.readString();
        this.deviceId = in.readLong();
        this.date = in.readString();

    }
    /******************************************************************
     * Builder pattern for DeviceFeature object
     ******************************************************************/
    public static class DeviceFeatureBuilder {
        private long id = -1;
        private String name = "";
        private String value = "";
        private String type = "";
        private long deviceId = -1;
        public DeviceFeatureParams deviceFeatureParams = DeviceFeatureParams.DEVICE_FEATURE_PARAMS_EMPTY;
        private DeviceFeatureGUIParams deviceFeatureGUIParams = DeviceFeatureGUIParams.DEVICE_FEATURE_GUI_EMPTY;
        private String date = "";

        public DeviceFeatureBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DeviceFeatureBuilder withDeviceId(long deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public DeviceFeatureBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DeviceFeatureBuilder withValue(String value) {
            this.value = value;
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
            return new DeviceFeature(id, deviceId, name, value, type, deviceFeatureParams, deviceFeatureGUIParams, date);
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

    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() { return this.value; }

    public void setValue(String value) { this.value = value; }

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
        Type type = new TypeToken<DeviceFeatureParams>() {
        }.getType();
        json_params = gson.toJson(deviceFeatureParams, type);
        return json_params;
    }

    public void setDeviceFeatureParamsFromJsonString(String params_json) {
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureParams>() {
        }.getType();

        this.deviceFeatureParams = gson.fromJson(params_json, type);
    }

    /***************************************************
     * Functions to transform JSON String Device Feature
     * GUI Parameters into a DeviceFeatureGUIParams object
     ***************************************************/
    public String getJsonStringFromDeviceFeatureGUIParams() {
        String json_params = "";
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureGUIParams>() {
        }.getType();
        json_params = gson.toJson(deviceFeatureGUIParams, type);
        return json_params;
    }

    public void setDeviceFeatureGUIParamsFromJsonString(String params_json) {
        Gson gson = new Gson();
        Type type = new TypeToken<DeviceFeatureGUIParams>() {
        }.getType();

        this.deviceFeatureGUIParams = gson.fromJson(params_json, type);
    }

    /************************************************************************
     * Project list sample data
     ************************************************************************/
    public static ArrayList<DeviceFeature> DeviceFeatureListSample() {
        ArrayList<DeviceFeature> features = new ArrayList<>();
        DeviceFeatureParams params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(100)
                .withUnits("ºC")
                .withCommand("get 1")
                .build();

        features.add(new DeviceFeatureBuilder()
                .withId(1)
                .withDeviceId(0)
                .withName("Feature 1")
                .withValue("0")
                .withType("Sensor")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(100)
                .withUnits("%")
                .withCommand("get 1")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(2)
                .withDeviceId(1)
                .withName("Feature 2")
                .withValue("50")
                .withType("Sensor")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(100)
                .withUnits("lx")
                .withCommand("get 1")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(3)
                .withDeviceId(2)
                .withName("Feature 3")
                .withType("Sensor")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(1)
                .withCommand("get 1")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(4)
                .withDeviceId(0)
                .withName("Feature 4")
                .withType("OnOff")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withCommand("set 1")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(5)
                .withDeviceId(0)
                .withName("Feature 5")
                .withType("OnOff")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(1)
                .withCommand("get 2")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(6)
                .withDeviceId(1)
                .withName("Feature 6")
                .withType("OnOff")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMaxValue(100)
                .withMinValue(0)
                .withCommand("set 2")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(7)
                .withDeviceId(3)
                .withName("Feature 7")
                .withType("Actuator")
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(500)
                .withUnits("K")
                .withCommand("get 3")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(8)
                .withDeviceId(4)
                .withName("Feature 8")
                .withType("Sensor")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMaxValue(50)
                .withMinValue(-50)
                .withCommand("set 3")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(9)
                .withDeviceId(4)
                .withName("Feature 9")
                .withType("Actuator")
                .withDeviceFeatureParams(params)
                .build());
        params = new DeviceFeatureParams.DeviceFeatureParamsBuilder()
                .withMinValue(0)
                .withMaxValue(10000)
                .withUnits("mbar")
                .withCommand("get 3")
                .build();
        features.add(new DeviceFeatureBuilder()
                .withId(10)
                .withDeviceId(3)
                .withName("Feature 10")
                .withType("Sensor")
                .build());

        return features;
    }
}
