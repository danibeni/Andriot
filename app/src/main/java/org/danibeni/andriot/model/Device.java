package org.danibeni.andriot.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dbenitez on 17/08/2017.
 */

public class Device implements Parcelable{
    private static final String TAG = Device.class.getSimpleName();
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

    protected Device(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.commProtocol);
        parcel.writeString(this.address);
        parcel.writeInt(this.port);
        parcel.writeInt(this.online);
        parcel.writeList(this.deviceFeatures);
        parcel.writeString(this.date);
    }

    private void readFromParcel(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.commProtocol = in.readString();
        this.address = in.readString();
        this.port = in.readInt();
        this.online = in.readInt();
        this.deviceFeatures = in.readArrayList(null);
        this.date = in.readString();
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

        public DeviceBuilder withId(long id) {
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

    public void addDeviceFeature(DeviceFeature feature) {
        deviceFeatures.add(feature);
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

    public String getCommProtocol() {
        return commProtocol;
    }

    public void setCommProtocol(String comm_protocol) {
        this.commProtocol = comm_protocol;
    }

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

    public ArrayList<DeviceFeature> getDeviceFeatures() {
        return deviceFeatures;
    }

    public void setDeviceFeatures(ArrayList<DeviceFeature> deviceFeatures) {
        this.deviceFeatures = deviceFeatures;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /************************************************************************
     * Project list sample data
     ************************************************************************/
    public static ArrayList<Device> DeviceListSample() {
        ArrayList<Device> devices = new ArrayList<>();
        ArrayList<DeviceFeature> featuresAll = DeviceFeature.DeviceFeatureListSample();
        ArrayList<DeviceFeature> features = new ArrayList<>();

        devices.add(new DeviceBuilder()
                .withId(0)
                .withName("Device 1")
                .withDescription("Device 1 description")
                .withCommProtocol("coap")
                .withAddress("192.180.1.40")
                .withPort(80)
                .witnOnline(1)
                .build());
        devices.add(new DeviceBuilder()
                .withId(1)
                .withName("Device 2")
                .withDescription("Device 2 description")
                .withCommProtocol("coap")
                .withAddress("192.180.1.41")
                .withPort(80)
                .witnOnline(1)
                .build());
        devices.add(new DeviceBuilder()
                .withId(2)
                .withName("Device 3")
                .withDescription("Device 3 description")
                .withCommProtocol("coap")
                .withAddress("192.180.1.42")
                .withPort(80)
                .witnOnline(1)
                .build());
        devices.add(new DeviceBuilder()
                .withId(3)
                .withName("Device 4")
                .withDescription("Device 4 description")
                .withCommProtocol("coap")
                .withAddress("192.180.1.43")
                .withPort(80)
                .witnOnline(1)
                .build());
        devices.add(new DeviceBuilder()
                .withId(4)
                .withName("Device 5")
                .withDescription("Device 5 description")
                .withCommProtocol("coap")
                .withAddress("192.180.1.44")
                .withPort(80)
                .witnOnline(1)
                .build());

        for(DeviceFeature feature: featuresAll) {
            for(Device device: devices) {
                if (feature.getDeviceId() == device.getId()) {
                    device.addDeviceFeature(feature);
                    break;
                }
            }
        }

        return devices;
    }
}
