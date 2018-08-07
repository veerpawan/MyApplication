package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sourav on 7/7/2017.
 */

public class TestBean {


    @SerializedName("MESSAGE")
    private String message;


    @SerializedName("SUCCESS")
    private int success;

    @SerializedName("description")
    private String description;

    @SerializedName("test_test_id")
    private int test_test_id;

    @SerializedName("test_id")
    private int test_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTest_test_id() {
        return test_test_id;
    }

    public void setTest_test_id(int test_test_id) {
        this.test_test_id = test_test_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }
}
