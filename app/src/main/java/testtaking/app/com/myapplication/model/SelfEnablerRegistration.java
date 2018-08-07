package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PAWAN on 10/5/2017.
 */

public class SelfEnablerRegistration {

    @SerializedName("student")
    private student sstudent;
    @SerializedName("success")
    private int success;
    @SerializedName("message")
    private String message;

    public student getSstudent() {
        return sstudent;
    }

    public void setSstudent(student sstudent) {
        this.sstudent = sstudent;
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
}
