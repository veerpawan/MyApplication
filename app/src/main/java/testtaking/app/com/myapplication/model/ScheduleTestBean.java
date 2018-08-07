package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sourav on 7/10/2017.
 */

public class ScheduleTestBean {
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @SerializedName("SUCCESS")
    private int SUCCESS;


    @SerializedName("Test")
    private Test Test;

    @SerializedName("test_id")
    private int test_id;
    @SerializedName("test_test_id")
    private int test_test_id;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getTest_test_id() {
        return test_test_id;
    }

    public void setTest_test_id(int test_test_id) {
        this.test_test_id = test_test_id;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public int getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(int SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public Test getTest() {
        return Test;
    }

    public void setTest(Test test) {
        Test = test;
    }
}
