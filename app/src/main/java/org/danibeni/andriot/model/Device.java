package org.danibeni.andriot.model;

import java.util.ArrayList;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class Device {
    private long id = -1;
    private String name = "";
    private String description = "";
    private String commProtocol = "";
    private String address = "";
    private int port = 0;
    private int online = 0;
    private ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
    private String date;

    public final static Device DEVICE_EMPTY = new Device(-1, "", "", "", "", 0, 0, new ArrayList<DeviceFeature>(), "");

    private Device(long id, String name, String description, String commProtocol, String address, int port, int online, ArrayList<DeviceFeature> deviceFeatures, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.commProtocol = commProtocol;
        this.address = address;
        this.online = online;
        this.deviceFeatures = deviceFeatures;
        this.date = date;
    }

    /******************************************************************
     * Builder pattern for Device object
     ******************************************************************/
    public static class DeviceBuilder {
        private long id = -1;
        private String name = "";
        private String description = "";
        private String comm_protocol = "";
        private String address = "";
        private int port = 0;
        private int online = 0;
        private ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
        private String date;

        public DeviceBuilder withId (long id) {
            this.id = id;
            return this;
        }

        public DeviceBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DeviceBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public DeviceBuilder withCommProtocol(String comm_protocol) {
            this.comm_protocol = comm_protocol;
            return this;
        }

        public DeviceBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public DeviceBuilder withPort(int port) {
            this.port = port;
            return this;
        }

        public DeviceBuilder witnOnline(int online) {
            this.online = online;
            return this;
        }

        public DeviceBuilder withDeviceFeatures(ArrayList<DeviceFeature> deviceFeatures) {
            this.deviceFeatures = deviceFeatures;
            return this;
        }

        public DeviceBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public Device build() {
            return new Device(id, name, description, comm_protocol, address, port, online, deviceFeatures, date);
        }
    }

    /*******************************************************************
     * Getters And Setters
     *******************************************************************/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommProtocol() { return commProtocol; }

    public void setCommProtocol(String comm_protocol) {this.commProtocol = comm_protocol; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
