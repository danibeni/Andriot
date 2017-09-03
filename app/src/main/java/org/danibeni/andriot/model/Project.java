package org.danibeni.andriot.model;

import java.util.ArrayList;

/**
 * Created by dbenitez on 01/08/2017.
 */

public class Project {
    private long id = -1;
    private String name = "";
    private String description = "";
    private ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
    private String date = "";

    private Project(long id, String name, String description, ArrayList<DeviceFeature> deviceFeatures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deviceFeatures = deviceFeatures;
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

        public ProjectBuilder withDevices(ArrayList<DeviceFeature> deviceFeatures) {
            this.deviceFeatures = deviceFeatures;
            return this;
        }

        public Project build() {
            return new Project(id, name, description, deviceFeatures);
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
        projects.add(new ProjectBuilder()
                .withId(0)
                .withName("Proyecto 1")
                .withDescription("Descripción de proyecto 1")
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
