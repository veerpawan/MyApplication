package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 12/22/2016.
 */

public class LoginOtherBean {

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;


    @SerializedName("success")
    private int success;


    @SerializedName("message")
    private String message;


    @SerializedName("role")
    private int role;


    @SerializedName("bde_id")
    private int bde_id;

    public int getBde_id() {
        return bde_id;
    }

    public void setBde_id(int bde_id) {
        this.bde_id = bde_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

}
