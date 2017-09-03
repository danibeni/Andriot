package org.danibeni.andriot.model;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class DeviceFeatureParams {
    private float maxValue = 0;
    private float minValue = 0;
    private String units = "";

    public final static DeviceFeatureParams DEVICE_FEATURE_PARAMS_EMPTY = new DeviceFeatureParams(0, 0, "");

    private DeviceFeatureParams(float maxValue, float minValue, String units) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.units = units;
    }

    /******************************************************************
     * Builder pattern for DeviceFeatureGUIParams object
     ******************************************************************/
    public static class DeviceFeatureParamsBuilder {
        private float maxValue = 0;
        private float minValue = 0;
        private String units = "";

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

}
