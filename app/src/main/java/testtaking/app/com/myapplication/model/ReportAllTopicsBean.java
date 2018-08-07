package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 11/19/2016.
 */

public class ReportAllTopicsBean {


    @SerializedName("TEST_TESTATTEMPTED")
    private int testattampted;

    @SerializedName("TEST_DESCRIPTION")
    private String testdescription;


    @SerializedName("TEST_ALLOWVIEWREPORT")
    private int testallowviewreport;

    @SerializedName("TEST_TESTID")
    private int test_testid;

    @SerializedName("TEST_MARKS")
    private int testmarks;

    @SerializedName("TEST_TIME")
    private String testtime;


    @SerializedName("END_TIME")
    private String endtimetime;

    @SerializedName("TEST_ID")
    private int testid;

    @SerializedName("TEST_ATTEMPTNO")
    private int testattemptno;

    @SerializedName("TEST_STUID")
    private int teststudid;

    @SerializedName("TEST_DURATION")
    private int testduration;

    public int getTestattampted() {
        return testattampted;
    }

    public void setTestattampted(int testattampted) {
        this.testattampted = testattampted;
    }

    public String getTestdescription() {
        return testdescription;
    }

    public void setTestdescription(String testdescription) {
        this.testdescription = testdescription;
    }

    public int getTestallowviewreport() {
        return testallowviewreport;
    }

    public void setTestallowviewreport(int testallowviewreport) {
        this.testallowviewreport = testallowviewreport;
    }

    public int getTest_testid() {
        return test_testid;
    }

    public void setTest_testid(int test_testid) {
        this.test_testid = test_testid;
    }

    public int getTestmarks() {
        return testmarks;
    }

    public void setTestmarks(int testmarks) {
        this.testmarks = testmarks;
    }

    public String getTesttime() {
        return testtime;
    }

    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }

    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public int getTestattemptno() {
        return testattemptno;
    }

    public void setTestattemptno(int testattemptno) {
        this.testattemptno = testattemptno;
    }

    public int getTeststudid() {
        return teststudid;
    }

    public void setTeststudid(int teststudid) {
        this.teststudid = teststudid;
    }

    public int getTestduration() {
        return testduration;
    }

    public void setTestduration(int testduration) {
        this.testduration = testduration;
    }

    public String getEndtimetime() {
        return endtimetime;
    }

    public void setEndtimetime(String endtimetime) {
        this.endtimetime = endtimetime;
    }


}
