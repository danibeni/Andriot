package org.danibeni.andriot;

import android.app.Application;
import android.util.Log;

import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 02/09/2017.
 */

public class AndriotApplication extends Application {

    private ArrayList<Project> projects;

    @Override
    public void onCreate() {
        projects = Project.ProjectListSample();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }
}
