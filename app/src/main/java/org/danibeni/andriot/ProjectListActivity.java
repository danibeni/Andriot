package org.danibeni.andriot;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.danibeni.andriot.fragments.DeviceCatalogFragment;
import org.danibeni.andriot.fragments.EditFeatureFragment;
import org.danibeni.andriot.fragments.EditProjectFragment;
import org.danibeni.andriot.fragments.ProjectFragment;
import org.danibeni.andriot.fragments.ProjectListFragment;
import org.danibeni.andriot.model.Device;
import org.danibeni.andriot.model.DeviceFeature;
import org.danibeni.andriot.model.Project;
import org.danibeni.andriot.model.ProjectListStorage;
import org.danibeni.andriot.model.ProjectListVolatileStorage;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity implements ProjectListFragment.OnProjectListFragmentInteractionListener,
        EditProjectFragment.OnEditProjectFragmentInteractionListener, ProjectFragment.OnProjectFragmentInteractionListener,
        DeviceCatalogFragment.OnDeviceCatalogFragmentInteractionListener, EditFeatureFragment.OnEditFeatureFragmentInteractionListener {
    // Logcat tag
    private static final String TAG = ProjectListActivity.class.getSimpleName();

    static final int GET_NEW_PROJECT_INFO = 1000;
    private ProjectListStorage projectsStorage;
    private Project activeProject;

    private int project_id = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //projects = Project.ProjectListSample();
        projectsStorage = (ProjectListVolatileStorage) new ProjectListVolatileStorage();
        projectsStorage.addProjects(Project.ProjectListSample());
        activeProject = projectsStorage.getProject(0);
        setContentView(R.layout.activity_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.project_list_toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getFragmentManager();


        //ProjectListFragment projectListFragment = ProjectListFragment.newInstance(projects);
        //transaction.add(R.id.left_container, projectListFragment);
        showProjectListFragment();
        //transaction.addToBackStack(null);
        showProjectFragment(activeProject);

        /*if (((findViewById(R.id.content_small_screen_size)) != null) &&
                (getFragmentManager().findFragmentById(R.id.content_small_screen_size) == null)) {
            ProjectListFragment defaultFragment = new ProjectListFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.content_small_screen_size, defaultFragment)
                    .commit();
        }*/

        //projectsDB = ProjectListSQLiteStorage.getInstance(this);
        //projects = projectsDB.getProjects();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.clear();
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.project_list_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Show ProjectFragment
    public void showProjectFragment(Project project) {
        ProjectFragment projectFragment = (ProjectFragment) getFragmentManager().findFragmentByTag(ProjectFragment.PROJECT_FRAGMENT_TAG);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (projectFragment == null) {
            projectFragment = ProjectFragment.newInstance(project);
            transaction.add(R.id.right_container, projectFragment);
        } else {
            projectFragment.updateProjectView(project);
            transaction.replace(R.id.right_container, projectFragment);
            transaction.detach(projectFragment);
            transaction.attach(projectFragment);

        }

        //transaction.addToBackStack(null);
        transaction.commit();

    }

    // Show ProjectListFragment
    public void showProjectListFragment() {
        ProjectListFragment projectListFragment = (ProjectListFragment) getFragmentManager().findFragmentByTag(ProjectListFragment.PROJECT_LIST_FRAGMENT_TAG);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (projectListFragment == null) {
            projectListFragment = ProjectListFragment.newInstance(projectsStorage.getProjects());
            transaction.add(R.id.left_container, projectListFragment);
        } else {
            transaction.replace(R.id.left_container, projectListFragment);
        }
        //transaction.addToBackStack(null);
        transaction.commit();

    }

    // Show ProjectListFragment
    public void showEditProjectFragment(Project project) {
        EditProjectFragment editProjectFragment = (EditProjectFragment) getFragmentManager().findFragmentByTag(EditProjectFragment.EDIT_PROJECT_FRAGMENT_TAG);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (editProjectFragment == null) {
            editProjectFragment = EditProjectFragment.newInstance(project);
            transaction.add(R.id.left_container, editProjectFragment);
        } else {
            editProjectFragment.updateProjectData(project);
            transaction.replace(R.id.left_container, editProjectFragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();

    }

    // Show DeviceCatalogFragment
    public void showDeviceCatalogFragment(ArrayList<Device> devices) {
        DeviceCatalogFragment deviceCatalogFragment = (DeviceCatalogFragment) getFragmentManager().findFragmentByTag(DeviceCatalogFragment.DEVICE_CATALOG_FRAGMENT_TAG);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (deviceCatalogFragment == null) {
            deviceCatalogFragment = DeviceCatalogFragment.newInstance(devices);
            transaction.add(R.id.right_container, deviceCatalogFragment);
        } else {
            transaction.replace(R.id.right_container, deviceCatalogFragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Show EditFeatureFragment
    public void showEditFeatureFragment(DeviceFeature feature) {
        EditFeatureFragment editFeatureFragment = (EditFeatureFragment) getFragmentManager().findFragmentByTag(EditFeatureFragment.EDIT_FEATURE_FRAGMENT_TAG);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (editFeatureFragment == null) {
            editFeatureFragment = EditFeatureFragment.newInstance(feature);
            transaction.add(R.id.right_container, editFeatureFragment);
        } else {
            editFeatureFragment.updateFeatureData(feature);
            transaction.replace(R.id.right_container, editFeatureFragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Implementation of interface with ProjectListFragment to show ProjectFragment
    @Override
    public void onProjectListFragmentShowProject(Project project) {
        activeProject = project;
        showProjectFragment(activeProject);
    }

    // Implementation of interface with ProjectListFragment to show EditProjectFragment
    @Override
    public void onProjectListFragmentShowEditProject(Project project) {
        showEditProjectFragment(project);

    }

    // Implementation of interface with EditProjectFragment to show ProjectListFragment
    @Override
    public void onEditProjectFragmentShowProjectList(Project project) {
        long project_id = project.getId();
        if (projectsStorage.existsProject(project_id)) {
            projectsStorage.updateProject(project_id, project);
        } else {
            if (project != Project.PROJECT_EMPTY) {
                project.setId(count);
                projectsStorage.addProject(project);
                count++;
            }
        }
        showProjectListFragment();
    }

    // Implementation of interface with ProjectListFragment to delete a Project
    @Override
    public void onProjectListFragmentDeleteProject(Project project) {
        projectsStorage.deleteProject(project);
        showProjectListFragment();
    }

    // Implementation of interface with ProjectFragment to show DeviceCatalogFragment
    @Override
    public void onProjectFragmentShowDeviceCatalog() {
        ArrayList<Device> devices = Device.DeviceListSample();
        showDeviceCatalogFragment(devices);
    }

    // Implementation of interface with ProjectFragment to show EditFeatureFragment
    @Override
    public void onProjectFragmentEditFeature(DeviceFeature feature) {
        showEditFeatureFragment(feature);
    }

    // Implementation of interface with DeviceCatalogFragment to add all features of a device to a Project
    @Override
    public void onDeviceCatalogFragmentAddDeviceToProject(Device device) {
        activeProject.addDeviceFeatures(device.getDeviceFeatures());
        showProjectFragment(activeProject);
        Log.i(TAG, "Added device with id " + device.getId());
    }

    // Implementation of interface with DeviceCatalogFragment to add a device feature to the Project
    @Override
    public void onDeviceCatalogFragmentAddFeatureToProject(DeviceFeature feature) {
        Log.i(TAG, "Added feature with id " + feature.getId());
        activeProject.addDeviceFeature(feature);
        showProjectFragment(activeProject);

    }

    // Implementation of interface with EditFeatureFragment to edit a device feature included into a project
    @Override
    public void onEditFeatureFragmentShowProject(DeviceFeature feature) {
        if (feature != DeviceFeature.DEVICE_FEATURE_EMPTY) {
            activeProject.replaceDeviceFeature(feature.getId(), feature);
            projectsStorage.updateProject(activeProject.getId(), activeProject);
        }
        showProjectFragment(activeProject);
    }
}
