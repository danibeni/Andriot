package org.danibeni.andriot.model;

import java.util.ArrayList;

/**
 * Created by dbenitez on 11/09/2017.
 */

public class ProjectListVolatileStorage implements ProjectListStorage {
    ArrayList<Project> projects = new ArrayList<>();
    ArrayList<Device> devices = new ArrayList<>();

    public void addProjects(ArrayList<Project> projects) {
        for(Project project : projects) {
            this.projects.add(project);
        }
    }

    @Override
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @Override
    public Project getProject(long id) {
        for(Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return Project.PROJECT_EMPTY;
    }

    @Override
    public boolean addProject(Project project) {
        projects.add(project);
        return true;
    }

    @Override
    public boolean updateProject(long id, Project project) {
        for(Project proj : projects) {
            if (proj.getId() == id) {
                projects.set(projects.indexOf(proj), project);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteProject(Project project) {
        projects.remove(project);
    }

    @Override
    public boolean existsProject(long id) {
        if (getProject(id) != Project.PROJECT_EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Device getDevice(long id) {
        return null;
    }

    @Override
    public boolean insertDevice(Device device) {
        devices.add(device);
        return true;
    }

    @Override
    public boolean updateDevice(long id, Device device) {
        for(Device dev : devices) {
            if (device.getId() == id) {
                devices.set(devices.indexOf(dev), device);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteDevice(long id) {
        for(Device device : devices) {
            if (device.getId() == id) {
                devices.remove(devices.indexOf(device));
            }
        }
    }

    @Override
    public DeviceFeature getDeviceFeature(long id) {
        return null;
    }

    @Override
    public boolean insertDeviceFeature(DeviceFeature feature) {
        return false;
    }

    @Override
    public boolean updateDeviceFeature(long id, DeviceFeature feature) {
        return false;
    }

    @Override
    public void deleteDeviceFeature(long id) {

    }

    @Override
    public DeviceFeature getDeviceFeatureFromProject(long id, Project project) {
        return null;
    }

    @Override
    public boolean addDeviceFeatureToProject(Project project, DeviceFeature feature) {
        return false;
    }

    @Override
    public boolean removeDeviceFeatureFromProject(long id, Project project) {
        return false;
    }

    @Override
    public ArrayList<DeviceFeature> getAllDeviceFeaturesInProject(Project project) {
        return null;
    }
}
