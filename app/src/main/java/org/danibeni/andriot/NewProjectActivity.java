package org.danibeni.andriot;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProjectActivity extends AppCompatActivity {
    // Logcat tag
    private static final String LOG = "NewProjectActivity";


    private EditText etProjectName, etProjectDescription;
    private TextInputLayout layoutProjectName, layoutProjectDescription;
    private Button btnAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        layoutProjectName = (TextInputLayout) findViewById(R.id.layout_project_name);
        layoutProjectDescription = (TextInputLayout) findViewById(R.id.layout_project_description);
        etProjectName = (EditText) findViewById(R.id.et_project_name);
        etProjectDescription = (EditText) findViewById(R.id.et_project_description);
        btnAddProject = (Button) findViewById(R.id.btn_add_project);

        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitNewProjectResponse();
            }
        });

    }

    private boolean checkProjectName() {
        if (etProjectName.getText().toString().trim().isEmpty()) {
            layoutProjectName.setErrorEnabled(true);
            layoutProjectName.setError(getString(R.string.err_project_name_empty));
            etProjectName.setError(getString(R.string.err_input_required));
            return false;
        }
        layoutProjectName.setErrorEnabled(false);
        return true;
    }

    private void submitNewProjectResponse() {
        if (checkProjectName()) {
            Intent intent = new Intent();
            intent.putExtra("project_name", etProjectName.getText().toString());
            intent.putExtra("project_description", etProjectDescription.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
