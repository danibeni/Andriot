package org.danibeni.andriot.model;

import java.util.ArrayList;

/**
 * Created by dbenitez on 09/08/2017.
 */

public interface ProjectListStorage {

    ArrayList<Project> getProjects();
    Project getProject(long id);
    boolean insertProject(Project project);
    boolean updateProject(long id, Project project);
    void deleteProject(long id);
    Device getDevice(long id);
    boolean insertDevice(Device device);
    boolean updateDevice(long id, Device device);
    void deleteDevice(long id);

}
