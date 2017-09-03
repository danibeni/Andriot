package org.danibeni.andriot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.danibeni.andriot.model.Project;
import org.danibeni.andriot.model.ProjectListSQLiteStorage;
import org.danibeni.andriot.model.ProjectListStorage;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity {
    // Logcat tag
    private static final String LOG = "ProjectListActivity";

    static final int GET_NEW_PROJECT_INFO = 1000;
    private ProjectListStorage projectsDB;
    private ArrayList<Project> projects;

    //RecyclerView Projects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProjectListAdapter projectsAdapter;
    private ProjectListSingleton projectListSingleton;

    private int project_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectListSingleton = ProjectListSingleton.getInstance(this);
        projectListSingleton.setProjects(Project.ProjectListSample());
        projects = projectListSingleton.getProjects();
        setContentView(R.layout.activity_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.project_list_toolbar);
        setSupportActionBar(toolbar);

        //projectsDB = ProjectListSQLiteStorage.getInstance(this);
        //projects = projectsDB.getProjects();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                lauchNewProjectActivity();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_NEW_PROJECT_INFO && resultCode == RESULT_OK) {
            Project project = new Project.ProjectBuilder()
                    .withName(data.getExtras().getString("project_name"))
                    .withDescription(data.getExtras().getString("project_description"))
                    .build();
            projects.add(project);
            Log.i(LOG, "Project name: " + projects.get(project_id).getName());
            Log.i(LOG, "Project description: " + projects.get(project_id).getDescription());
            project_id++;
            projectListSingleton.setProjects(projects);
            projectListSingleton.getProjectListAdapter().notifyDataSetChanged();
        }
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

    private void lauchNewProjectActivity() {
        Intent intent = new Intent(this, NewProjectActivity.class);
        startActivityForResult(intent, GET_NEW_PROJECT_INFO);
    }

    public void showProject(int id) {
        Intent i = new Intent(this, ProjectActivity.class);
        i.putExtra("id", (long) id);
        startActivity(i);
    }
}
