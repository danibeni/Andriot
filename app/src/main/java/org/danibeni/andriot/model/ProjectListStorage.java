package org.danibeni.andriot.model;

import java.util.ArrayList;

/**
 * Created by dbenitez on 09/08/2017.
 */

public interface ProjectListStorage {

    void addProjects(ArrayList<Project> projects);
    ArrayList<Project> getProjects();
    Project getProject(long id);
    boolean addProject(Project project);
    boolean updateProject(long id, Project project);
    void deleteProject(Project project);
    boolean existsProject(long id);
    Device getDevice(long id);
    boolean insertDevice(Device device);
    boolean updateDevice(long id, Device device);
    void deleteDevice(long id);
    DeviceFeature getDeviceFeature(long id);
    boolean insertDeviceFeature(DeviceFeature feature);
    boolean updateDeviceFeature(long id, DeviceFeature feature);
    void deleteDeviceFeature(long id);
    DeviceFeature getDeviceFeatureFromProject(long id, Project project);
    boolean addDeviceFeatureToProject(Project project, DeviceFeature feature);
    boolean removeDeviceFeatureFromProject(long id, Project project);
    ArrayList<DeviceFeature> getAllDeviceFeaturesInProject(Project project);

}
