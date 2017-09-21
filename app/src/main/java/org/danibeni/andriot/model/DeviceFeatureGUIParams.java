package org.danibeni.andriot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class DeviceFeatureGUIParams implements Parcelable{
    private String label = "";
    private String bgColor = "";
    private String color = "";

    public final static DeviceFeatureGUIParams DEVICE_FEATURE_GUI_EMPTY = new DeviceFeatureGUIParams("", "", "");

    private DeviceFeatureGUIParams(String label, String bgColor, String color) {
        this.label = label;
        this.bgColor = bgColor;
        this.color = color;
    }

    private DeviceFeatureGUIParams (Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DeviceFeatureGUIParams> CREATOR = new Creator<DeviceFeatureGUIParams>() {
        @Override
        public DeviceFeatureGUIParams createFromParcel(Parcel in) {
            return new DeviceFeatureGUIParams(in);
        }

        @Override
        public DeviceFeatureGUIParams[] newArray(int size) {
            return new DeviceFeatureGUIParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.label);
        parcel.writeString(this.bgColor);
        parcel.writeString(this.color);
    }

    private void readFromParcel(Parcel in) {
        this.label = in.readString();
        this.bgColor = in.readString();
        this.color = in.readString();
    }
    /******************************************************************
     * Builder pattern for DeviceFeatureGUIParams object
     ******************************************************************/
    public static class DeviceFeatureGUIParamsBuilder {
        private String label = "";
        private String bgColor = "";
        private String color = "";

        public DeviceFeatureGUIParamsBuilder withLabel(String label) {
            this.label = label;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withBgColor(String bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public DeviceFeatureGUIParams build() {
            return new DeviceFeatureGUIParams(label, bgColor, color);
        }
    }

    /*******************************************************************
     * Getters And Setters
     *******************************************************************/
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
