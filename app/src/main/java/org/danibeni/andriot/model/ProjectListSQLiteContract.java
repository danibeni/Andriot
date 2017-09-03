package org.danibeni.andriot.model;

import android.provider.BaseColumns;

/**
 * Created by dbenitez on 09/08/2017.
 */

public final class ProjectListSQLiteContract {

    // Private Constructor to prevent someone from accidentally instantiating the contract class,
    private ProjectListSQLiteContract() {}

    public static class ProjectsTable implements BaseColumns {
        public static final String TABLE_NAME = "projects";
        public static final String KEY_NAME = "name";
        public static final String KEY_DESCRIP = "description";
        public static final String KEY_CREATED_AT = "created_at";
    }

    public static class DevicesTable implements BaseColumns {
        public static final String TABLE_NAME = "devices";
        public static final String KEY_NAME = "name";
        public static final String KEY_DESCRIP = "description";
        public static final String KEY_COMM_PROTOCOL = "comm_protocol";
        public static final String KEY_ADDRESS_CONNECT = "address_connect";
        public static final String KEY_PORT_CONNECT = "port_connect";
        public static final String KEY_ONLINE = "online";
        public static final String KEY_CREATED_AT = "created_at";
    }

    public static class DevicesFeaturesTable implements BaseColumns {
        public static final String TABLE_NAME = "devices_features";
        public static final String KEY_NAME = "name";
        public static final String KEY_TYPE = "type";
        public static final String KEY_COMMAND = "command";
        public static final String KEY_DEVICE_ID = "device_id";
        public static final String KEY_JSON_PARAMS = "feature_json_params";
        public static final String KEY_CREATED_AT = "created_at";
    }

    public static class ProjectsDevicesFeaturesTable implements BaseColumns {
        public static final String TABLE_NAME = "projects_device_features";
        public static final String KEY_PROJECT_ID = "project_id";
        public static final String KEY_DEVICE_ID = "device_id";
        public static final String KEY_FEATURE_ID = "feature_id";
        public static final String KEY_FEATURE_GUI_ID = "feature_gui_id";
        public static final String KEY_CATEGORY = "category";
        public static final String KEY_FEATURE_GUI_JSON_PARAMS = "feature_gui_json_params";
    }

    public static class FeaturesGUITable implements BaseColumns {
        public static final String TABLE_NAME = "features_gui";
        public static final String KEY_TYPE = "type";
        public static final String KEY_FEATURE_GUI_JSON_PARAMS = "feature_gui_json_params";
    }

    public static class FeaturesValuesTable implements BaseColumns {
        public static final String TABLE_NAME = "features_values";
        public static final String KEY_VALUE = "value";
        public static final String KEY_TIMESTAMP = "timestamp";
    }
}
