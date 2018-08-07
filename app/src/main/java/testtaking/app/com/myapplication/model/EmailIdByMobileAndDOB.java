package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by PAWAN on 8/3/2017.
 */

public class EmailIdByMobileAndDOB {

    @SerializedName("success")
    private int success;
    @SerializedName("message")
    private String message;
    @SerializedName("User")
    private List<User> user;

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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
