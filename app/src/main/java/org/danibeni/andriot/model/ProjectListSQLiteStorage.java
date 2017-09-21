package org.danibeni.andriot.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by dbenitez on 09/08/2017.
 */


public class ProjectListSQLiteStorage extends SQLiteOpenHelper implements ProjectListStorage {

    // Logcat tag
    private static final String LOG = "ProjectListSQLiteStorage";

    // Singleton pattern
    private static ProjectListSQLiteStorage sInstance;

    // If the database schema is changed, the database version must be increased.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProjectsList.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_PROJECTS =
            "CREATE TABLE " + ProjectListSQLiteContract.ProjectsTable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.ProjectsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.ProjectsTable.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.ProjectsTable.KEY_DESCRIP + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.ProjectsTable.KEY_CREATED_AT + TEXT_TYPE + " )";

    private static final String SQL_CREATE_TABLE_DEVICES =
            "CREATE TABLE " + ProjectListSQLiteContract.DevicesTable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.DevicesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.DevicesTable.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_DESCRIP + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_COMM_PROTOCOL + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_ADDRESS_CONNECT + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_PORT_CONNECT + INTEGER_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_ONLINE + INTEGER_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesTable.KEY_CREATED_AT + TEXT_TYPE + " )";

    private static final String SQL_CREATE_TABLE_DEVICES_FEATURES =
            "CREATE TABLE " + ProjectListSQLiteContract.DevicesFeaturesTable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.DevicesFeaturesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.DevicesFeaturesTable.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesFeaturesTable.KEY_TYPE + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesFeaturesTable.KEY_COMMAND + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.DevicesFeaturesTable.KEY_JSON_PARAMS + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY (" + ProjectListSQLiteContract.DevicesFeaturesTable.KEY_DEVICE_ID + ") REFERENCES " + ProjectListSQLiteContract.DevicesTable.TABLE_NAME + " (" + ProjectListSQLiteContract.DevicesTable._ID + ")" + " )";

    private static final String SQL_CREATE_TABLE_PROJECTS_DEVICES_FEATURES =
            "CREATE TABLE " + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.ProjectsDevicesFeaturesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_FEATURE_GUI_JSON_PARAMS + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY (" + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_PROJECT_ID + ") REFERENCES " + ProjectListSQLiteContract.ProjectsTable.TABLE_NAME + " (" + ProjectListSQLiteContract.ProjectsTable._ID + ")" + COMMA_SEP +
                    "FOREIGN KEY (" + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_DEVICE_ID + ") REFERENCES " + ProjectListSQLiteContract.DevicesTable.TABLE_NAME + " (" + ProjectListSQLiteContract.DevicesTable._ID + ")" + COMMA_SEP +
                    "FOREIGN KEY (" + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_FEATURE_ID + ") REFERENCES " + ProjectListSQLiteContract.DevicesFeaturesTable.TABLE_NAME + " (" + ProjectListSQLiteContract.DevicesFeaturesTable._ID + ")" + COMMA_SEP +
                    "FOREIGN KEY (" + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.KEY_FEATURE_GUI_ID + ") REFERENCES " + ProjectListSQLiteContract.FeaturesGUITable.TABLE_NAME + " (" + ProjectListSQLiteContract.FeaturesGUITable._ID + ")" + " )";

    private static final String SQL_CREATE_TABLE_FEATURES_GUI =
            "CREATE TABLE " + ProjectListSQLiteContract.FeaturesGUITable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.FeaturesGUITable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.FeaturesGUITable.KEY_TYPE + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.FeaturesGUITable.KEY_FEATURE_GUI_JSON_PARAMS + TEXT_TYPE + " )";

    private static final String SQL_CREATE_TABLE_FEATURES_VALUES =
            "CREATE TABLE " + ProjectListSQLiteContract.FeaturesValuesTable.TABLE_NAME + " (" +
                    ProjectListSQLiteContract.FeaturesValuesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ProjectListSQLiteContract.FeaturesValuesTable.KEY_VALUE + TEXT_TYPE + COMMA_SEP +
                    ProjectListSQLiteContract.FeaturesValuesTable.KEY_TIMESTAMP + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_PROJECTS =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.ProjectsTable.TABLE_NAME;
    private static final String SQL_DELETE_TABLE_DEVICES =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.DevicesTable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_DEVICES_FEATURES =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.DevicesFeaturesTable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_PROJECTS_DEVICES_FEATURES =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.ProjectsDevicesFeaturesTable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_FEATURES_GUI =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.FeaturesGUITable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_FEATURES_VALUES =
            "DROP TABLE IF EXISTS " + ProjectListSQLiteContract.FeaturesValuesTable.TABLE_NAME;


    public static synchronized ProjectListSQLiteStorage getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new ProjectListSQLiteStorage(context.getApplicationContext());
        }
        return sInstance;
    }

    private ProjectListSQLiteStorage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // PROJECTS table create statement
        db.execSQL(SQL_CREATE_TABLE_PROJECTS);

        // DEVICES table create statement
        db.execSQL(SQL_CREATE_TABLE_DEVICES);

        // DEVICES FEATURES table create statement
        db.execSQL(SQL_CREATE_TABLE_DEVICES_FEATURES);

        // PROJECTS DEVICES FEATURES table create statement
        db.execSQL(SQL_CREATE_TABLE_PROJECTS_DEVICES_FEATURES);

        // FEATURES GUI table create statement
        db.execSQL(SQL_CREATE_TABLE_FEATURES_GUI);

        // FEATURES VALUES table create statement
        db.execSQL(SQL_CREATE_TABLE_FEATURES_VALUES);

        /*
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS projects (" +
            "project_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, description TEXT, created LONG)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS devices (" +
                "device_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, type TEXT, module TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS projects_devices (" +
                "projects_id INTEGER REFERENCES projects (project_id), " +
                "devices_id INTEGER REFERENCES devices (device_id), " +
                "PRIMARY KEY(projects_id, devices_id))");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL(SQL_DELETE_TABLE_PROJECTS);
        db.execSQL(SQL_DELETE_TABLE_DEVICES);
        db.execSQL(SQL_DELETE_TABLE_DEVICES_FEATURES);
        db.execSQL(SQL_DELETE_TABLE_PROJECTS_DEVICES_FEATURES);
        db.execSQL(SQL_DELETE_TABLE_FEATURES_GUI);
        db.execSQL(SQL_DELETE_TABLE_FEATURES_VALUES);


        // create new tables
        onCreate(db);
    }

    @Override
    public void addProjects(ArrayList<Project> projects) {

    }

    @Override
    public ArrayList<Project> getProjects() {
        ArrayList<Project> projects= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;

        Cursor c = db.query(
                ProjectListSQLiteContract.ProjectsTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                Project project = new Project.ProjectBuilder().build();
                project.setId(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable._ID)));
                project.setName(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_NAME)));
                project.setDescription(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_DESCRIP)));
                project.setDate(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_CREATED_AT)));
                projects.add(project);
                c.moveToNext();
            }
        }

        return projects;
    }

    @Override
    public Project getProject(long id) {
        Project project = new Project.ProjectBuilder().build();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = null;
        String selection = ProjectListSQLiteContract.ProjectsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor c = db.query(
                ProjectListSQLiteContract.ProjectsTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            project.setId(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable._ID)));
            project.setName(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_NAME)));
            project.setDescription(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_DESCRIP)));
            project.setDate(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_CREATED_AT)));
        }

        return project;
    }

    @Override
    public boolean addProject(Project project) {
        boolean isInserted = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_NAME, project.getName());
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_DESCRIP, project.getDescription());
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_CREATED_AT, System.currentTimeMillis());

        long project_id = db.insert(ProjectListSQLiteContract.ProjectsTable.TABLE_NAME, null, values);
        if (project_id != -1) {
            project.setId(project_id);
            isInserted = true;
        }

        return isInserted;
    }

    @Override
    public boolean updateProject(long id, Project project) {
        boolean isUpdated = false;

        ArrayList<DeviceFeature> deviceFeatures = project.getDeviceFeatures();
        SQLiteDatabase db = this.getReadableDatabase();

        // New values for Projects Table
        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_NAME, project.getName());
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_DESCRIP, project.getDescription());

        // Which rows to update
        String selection = ProjectListSQLiteContract.ProjectsTable._ID;
        String[] selectionArgs = {String.valueOf(id)};
        int count = db.update(
                ProjectListSQLiteContract.ProjectsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        /*
        // If the project row in the table has been updated correctly, proceed to insert its Device deviceFeatures into the Database
        if (count > 0) {
            if (!deviceFeatures.isEmpty()) {
                insertProjectDevices(project.getId(), deviceFeatures);
            }
            isUpdated = true;
        }
        */
        return isUpdated;
    }

    @Override
    public void deleteProject(Project project) {

    }

    @Override
    public boolean existsProject(long id) {
        return false;
    }

    @Override
    public Device getDevice(long id) {
        Device device = new Device.DeviceBuilder().build();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = null;
        String selection = ProjectListSQLiteContract.DevicesTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor c = db.query(
                ProjectListSQLiteContract.ProjectsTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            device.setId(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable._ID)));
            device.setName(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_NAME)));
            device.setDescription(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_DESCRIP)));
            device.setCommProtocol(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_COMM_PROTOCOL)));
            device.setAddress(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_ADDRESS_CONNECT)));
            device.setPort(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_PORT_CONNECT)));
            device.setOnline(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_ONLINE)));
            device.setDate(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_CREATED_AT)));
        }

        return device;
    }

    @Override
    public boolean insertDevice(Device device) {
        boolean isInserted = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_NAME, device.getName());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DESCRIP, device.getDescription());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_COMM_PROTOCOL, device.getCommProtocol());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_ADDRESS_CONNECT, device.getAddress());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_PORT_CONNECT, device.getPort());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_ONLINE, device.getOnline());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_CREATED_AT, System.currentTimeMillis());

        long device_id = db.insert(ProjectListSQLiteContract.DevicesTable.TABLE_NAME, null, values);
        if (device_id != -1) {
            device.setId(device_id);
            isInserted = true;
        }

        return isInserted;
    }

    @Override
    public boolean updateDevice(long id, Device device) {
        boolean isUpdated = false;

        SQLiteDatabase db = this.getReadableDatabase();

        // New values for Device in Devices Table
        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_NAME, device.getName());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DESCRIP, device.getDescription());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_COMM_PROTOCOL, device.getCommProtocol());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_ADDRESS_CONNECT, device.getAddress());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_PORT_CONNECT, device.getPort());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_ONLINE, device.getOnline());


        // Which rows to update
        String selection = ProjectListSQLiteContract.DevicesTable._ID;
        String[] selectionArgs = {String.valueOf(id)};
        int count = db.update(
                ProjectListSQLiteContract.DevicesTable.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        // Verify if the device row in the table has been updated correctly
        if (count > 0) {
           isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public void deleteDevice(long id) {

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

    /*
    @Override
    public DeviceFeature getDeviceFeature(int id) {
        DeviceFeature deviceFeature = new DeviceFeature.DeviceFeatureBuilder().build();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = null;
        String selection = ProjectListSQLiteContract.DeviceFeaturesTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor c = db.query(
                ProjectListSQLiteContract.DeviceFeaturesTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            deviceFeature.setId(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.DeviceFeaturesTable._ID)));
            deviceFeature.setName(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DeviceFeaturesTable.KEY_NAME)));
            deviceFeature.setType(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DeviceFeaturesTable.KEY_TYPE)));
            deviceFeature.setDeviceFeatureParamsFromJsonString(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DeviceFeaturesTable.KEY_JSON_PARAMS)));
            deviceFeature.setDate(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DeviceFeaturesTable.KEY_CREATED_AT)));

        } else {
            deviceFeature = DeviceFeature.DEVICE_FEATURE_EMPTY;
        }

        return deviceFeature;
    }

    @Override
    public ArrayList<DeviceFeature> getDevices() {
        ArrayList<DeviceFeature> deviceFeatures = new ArrayList<DeviceFeature>();
        DeviceFeature deviceFeature = new DeviceFeature.DeviceBuilder().build();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query all deviceFeatures in Devices Table
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        Cursor c = db.query(
                ProjectListSQLiteContract.DevicesTable.TABLE_DEVICES,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Store all the deviceFeatures fetched from the database into a List of Devices object
        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                deviceFeature = new DeviceFeature.DeviceBuilder().build();
                deviceFeature.setId(c.getInt(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable._ID)));
                deviceFeature.setName(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_NAME)));
                deviceFeature.setType(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_TYPE)));
                deviceFeature.setModule(c.getString(c.getColumnIndex(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_MODULE)));
                deviceFeature.setDate(c.getString(c.getColumnIndex(ProjectListSQLiteContract.ProjectsTable.KEY_PROJECT_CREATED_AT)));
                deviceFeatures.add(deviceFeature);
                c.moveToNext();
            }
        }

        return deviceFeatures;

    }

    @Override
    public boolean insertDevice(DeviceFeature deviceFeature) {
        boolean isInserted = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_NAME, deviceFeature.getName());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_TYPE, deviceFeature.getType());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_MODULE, deviceFeature.getModule());
        values.put(ProjectListSQLiteContract.ProjectsTable.KEY_PROJECT_CREATED_AT, System.currentTimeMillis());

        long device_id = db.insert(ProjectListSQLiteContract.DevicesTable.TABLE_DEVICES, null, values);
        if (device_id != -1) {
            deviceFeature.setId(device_id);
            isInserted = true;
        }

        return isInserted;
    }

    @Override
    public boolean updateDevice(DeviceFeature deviceFeature) {
        boolean isUpdated = false;
        SQLiteDatabase db = this.getReadableDatabase();

        // New values for Projects Table
        ContentValues values = new ContentValues();
        values.put(ProjectListSQLiteContract.DevicesTable.TABLE_DEVICES, deviceFeature.getName());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_TYPE, deviceFeature.getType());
        values.put(ProjectListSQLiteContract.DevicesTable.KEY_DEVICES_MODULE, deviceFeature.getModule());

        // Which rows to update
        String selection = ProjectListSQLiteContract.DevicesTable._ID;
        String[] selectionArgs = {String.valueOf(deviceFeature.getId())};
        int count = db.update(
                ProjectListSQLiteContract.DevicesTable.TABLE_DEVICES,
                values,
                selection,
                selectionArgs
        );
        if (count > 0) {
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public void deleteDeviceFeature(long id) {

    }

    @Override
    public ArrayList<DeviceFeature> getProjectDevices(long project_id) {
        return null;
    }

    @Override
    public boolean insertProjectDevices(long project_id, ArrayList<DeviceFeature> deviceFeatures) {
        boolean isInDB;
        boolean isInserted = false;
        SQLiteDatabase db = this.getWritableDatabase();

        for (DeviceFeature deviceFeature : deviceFeatures) {
            isInDB = true;
            //Insert new DeviceFeature into Devices Table if is new or update otherwise
            if (getDevice(deviceFeature.getId()) == DeviceFeature.MODULE_FEATURE_EMPTY) {
                isInDB = insertDevice(deviceFeature);
            }
            if (isInDB) {
                ContentValues values = new ContentValues();
                values.put(ProjectListSQLiteContract.ProjectsDevicesTable.KEY_PROJECTS_ID, project_id);
                values.put(ProjectListSQLiteContract.ProjectsDevicesTable.KEY_DEVICES_ID, deviceFeature.getId());
                db.execSQL("PRAGMA foreign_keys = ON");
                long id = db.insert(ProjectListSQLiteContract.ProjectsDevicesTable.TABLE_PROJECTS_DEVICES, null, values);
                if (id != -1) {
                    isInserted = true;
                }
            }
        }
        return isInserted;
    }

    public boolean insertProjectDevice(long project_id, DeviceFeature deviceFeature) {
        boolean isInDB;
        boolean isInserted = false;
        SQLiteDatabase db = this.getWritableDatabase();

        isInDB = true;
        //Insert new DeviceFeature into Devices Table if is new
        if (getDevice(deviceFeature.getId()) == DeviceFeature.MODULE_FEATURE_EMPTY) {
            isInDB = insertDevice(deviceFeature);
        }
        if (isInDB) {
            ContentValues values = new ContentValues();
            values.put(ProjectListSQLiteContract.ProjectsDevicesTable.KEY_PROJECTS_ID, project_id);
            values.put(ProjectListSQLiteContract.ProjectsDevicesTable.KEY_DEVICES_ID, deviceFeature.getId());
            db.execSQL("PRAGMA foreign_keys = ON");
            long id = db.insert(ProjectListSQLiteContract.ProjectsDevicesTable.TABLE_PROJECTS_DEVICES, null, values);
            if (id != -1) {
                isInserted = true;
            }
        }

        return isInserted;
    }

    @Override
    public void updateProjectDevices(long project_id, ArrayList<DeviceFeature> deviceFeatures) {

    }
*/
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
