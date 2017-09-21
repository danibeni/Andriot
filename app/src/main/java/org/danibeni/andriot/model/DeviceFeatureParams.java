package org.danibeni.andriot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class DeviceFeatureParams implements Parcelable{
    private float maxValue = 0;
    private float minValue = 0;
    private String units = "";
    private String command = "";

    public final static DeviceFeatureParams DEVICE_FEATURE_PARAMS_EMPTY = new DeviceFeatureParams(0, 0, "", "");

    private DeviceFeatureParams(float maxValue, float minValue, String units, String command) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.units = units;
        this.command = command;
    }

    private DeviceFeatureParams(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DeviceFeatureParams> CREATOR = new Creator<DeviceFeatureParams>() {
        @Override
        public DeviceFeatureParams createFromParcel(Parcel in) {
            return new DeviceFeatureParams(in);
        }

        @Override
        public DeviceFeatureParams[] newArray(int size) {
            return new DeviceFeatureParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.maxValue);
        parcel.writeFloat(this.minValue);
        parcel.writeString(this.units);
        parcel.writeString(this.command);
    }

    private void readFromParcel(Parcel in) {
        this.maxValue = in.readFloat();
        this.minValue = in.readFloat();
        this.units = in.readString();
        this.command = in.readString();
    }

    /******************************************************************
     * Builder pattern for DeviceFeatureGUIParams object
     ******************************************************************/
    public static class DeviceFeatureParamsBuilder {
        private float maxValue = 0;
        private float minValue = 0;
        private String units = "";
        private String command = "";

        public DeviceFeatureParamsBuilder withMaxValue(float maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public DeviceFeatureParamsBuilder withMinValue(float minValue) {
            this.minValue = minValue;
            return this;
        }

        public DeviceFeatureParamsBuilder withUnits(String units) {
            this.units = units;
            return this;
        }

        public DeviceFeatureParamsBuilder withCommand(String command) {
            this.command = command;
            return this;
        }

        public DeviceFeatureParams build() {
            return new DeviceFeatureParams(maxValue, minValue, units, command);
        }
    }

    /*******************************************************************
     * Getters And Setters
     *******************************************************************/
    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCommand() { return command; }

    public void setCommand(String command) { this.command = command; }

}
