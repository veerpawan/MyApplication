package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 2/6/2017.
 */

public class CheckAppUpdate {


    @SerializedName("app_id")
    private int app_id;

    @SerializedName("app_name")
    private String app_name;

    @SerializedName("app_packge_name")
    private String app_packge_name;

    @SerializedName("app_version_code")
    private String app_version_code;

    @SerializedName("app_version_name")
    private String app_version_name;

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_packge_name() {
        return app_packge_name;
    }

    public void setApp_packge_name(String app_packge_name) {
        this.app_packge_name = app_packge_name;
    }

    public String getApp_version_code() {
        return app_version_code;
    }

    public void setApp_version_code(String app_version_code) {
        this.app_version_code = app_version_code;
    }

    public String getApp_version_name() {
        return app_version_name;
    }

    public void setApp_version_name(String app_version_name) {
        this.app_version_name = app_version_name;
    }
}
