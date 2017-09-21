package org.danibeni.andriot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dbenitez on 01/08/2017.
 */

public class Project implements Parcelable {
    private long id = -1;
    private String name = "";
    private String description = "";
    private ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
    private String date = "";

    public final static Project PROJECT_EMPTY = new Project(-1, "", "", new ArrayList<DeviceFeature>());

    private Project(long id, String name, String description, ArrayList<DeviceFeature> deviceFeatures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deviceFeatures = deviceFeatures;
    }

    private Project(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
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
        parcel.writeList(this.deviceFeatures);
    }

    private void readFromParcel(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.deviceFeatures = in.readArrayList(null);
    }

    /******************************************************************
     * Builder pattern for Project object
     ******************************************************************/
    public static class ProjectBuilder {
        private long id = -1;
        private String name = "";
        private String description = "";
        private ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
        private String date = "";

        public ProjectBuilder withId (long id) {
            this.id = id;
            return this;
        }

        public ProjectBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProjectBuilder withDevicesFeatures(ArrayList<DeviceFeature> deviceFeatures) {
            this.deviceFeatures = deviceFeatures;
            return this;
        }

        public Project build() {
            return new Project(id, name, description, deviceFeatures);
        }
    }

    public void addDeviceFeature(DeviceFeature feature) {
        this.deviceFeatures.add(feature);
    }

    public void addDeviceFeatures(ArrayList<DeviceFeature> features) {
        for(DeviceFeature feature : features) {
            addDeviceFeature(feature);
        }
    }

    public boolean removeDeviceFeature(DeviceFeature feature) {
        boolean removed = false;
        for(DeviceFeature deviceFeature : deviceFeatures) {
            if (deviceFeature.getId() == feature.getId()) {
                deviceFeatures.remove(deviceFeatures.indexOf(deviceFeature));
                removed = true;
                break;
            }
        }
        return removed;
    }

    public void replaceDeviceFeature(long feature_id, DeviceFeature new_feature) {
        for(DeviceFeature deviceFeature : deviceFeatures) {
            if (deviceFeature.getId() == feature_id) {
                deviceFeatures.set(deviceFeatures.indexOf(deviceFeature), new_feature);
                break;
            }
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<DeviceFeature> getDeviceFeatures() {
        return this.deviceFeatures;
    }

    public void setDeviceFeatures(ArrayList<DeviceFeature> deviceFeatures) {
        this.deviceFeatures = deviceFeatures;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /************************************************************************
     * Project list sample data
     ************************************************************************/
    public static ArrayList<Project> ProjectListSample() {
        ArrayList<Project> projects = new ArrayList<>();
        ArrayList<DeviceFeature> featuresAll = DeviceFeature.DeviceFeatureListSample();
        ArrayList<DeviceFeature> features = new ArrayList<>();

        features.add(featuresAll.get(0));
        features.add(featuresAll.get(1));
        projects.add(new ProjectBuilder()
                .withId(0)
                .withName("Proyecto 1")
                .withDescription("Descripción de proyecto 1")
                .withDevicesFeatures(features)
                .build());
        projects.add(new ProjectBuilder()
                .withId(1)
                .withName("Proyecto 2")
                .withDescription("Descripción de proyecto 2")
                .build());
        projects.add(new ProjectBuilder()
                .withId(2)
                .withName("Proyecto 3")
                .withDescription("Descripción de proyecto 3")
                .build());
        projects.add(new ProjectBuilder()
                .withId(3)
                .withName("Proyecto 4")
                .withDescription("Descripción de proyecto 4")
                .build());


        return projects;
    }
}
