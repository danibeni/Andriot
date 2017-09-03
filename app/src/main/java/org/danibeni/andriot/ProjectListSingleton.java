package org.danibeni.andriot;

import android.content.Context;

import org.danibeni.andriot.model.Project;

import java.util.ArrayList;

/**
 * Created by dbenitez on 02/09/2017.
 */

public class ProjectListSingleton {
    private Context context;
    private ArrayList<Project> projects;
    private ProjectListAdapter projectListAdapter;

    private static ProjectListSingleton ourInstance;

    private ProjectListSingleton(Context context) {
        this.context = context;
    }

    public static ProjectListSingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ProjectListSingleton(context);
        }
        return ourInstance;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ProjectListAdapter getProjectListAdapter() {
        return projectListAdapter;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setProjectListAdapter(ProjectListAdapter projectListAdapter) {
        this.projectListAdapter = projectListAdapter;
    }
}
