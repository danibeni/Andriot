package org.danibeni.andriot.model;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class DeviceFeatureGUIParams {
    private String label = "";
    private float maxValue = 0;
    private float minValue = 0;
    private String units = "";
    private String bgColor = "";
    private String color = "";
    private float readingRate = 0;
    private float step = 0;

    public final static DeviceFeatureGUIParams DEVICE_FEATURE_GUI_EMPTY = new DeviceFeatureGUIParams("", 0, 0, "", "", "", 0, 0);

    private DeviceFeatureGUIParams(String label, float maxValue, float minValue, String units, String bgColor, String color, float readingRate, float step) {
        this.label = label;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.units = units;
        this.bgColor = bgColor;
        this.color = color;
        this.readingRate = readingRate;
        this.step = step;
    }

    /******************************************************************
     * Builder pattern for DeviceFeatureGUIParams object
     ******************************************************************/
    public static class DeviceFeatureGUIParamsBuilder {
        private String label = "";
        private float maxValue = 0;
        private float minValue = 0;
        private String units = "";
        private String bgColor = "";
        private String color = "";
        private float readingRate = 0;
        private float step = 0;

        public DeviceFeatureGUIParamsBuilder withLabel(String label) {
            this.label = label;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withMaxValue(float maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withMinValue(float minValue) {
            this.minValue = minValue;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withUnits(String units) {
            this.units = units;
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

        public DeviceFeatureGUIParamsBuilder withReadingRate(float readingRate) {
            this.readingRate = readingRate;
            return this;
        }

        public DeviceFeatureGUIParamsBuilder withStep(float step) {
            this.step = step;
            return this;
        }

        public DeviceFeatureGUIParams build() {
            return new DeviceFeatureGUIParams(label, maxValue, minValue, units, bgColor, color, readingRate, step);
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

    public float getReadingRate() {
        return readingRate;
    }

    public void setReadingRate(float readingRate) {
        this.readingRate = readingRate;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}
