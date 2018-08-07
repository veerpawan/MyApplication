package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class TestList {



    @SerializedName("TEST_CONSUMED_DURATION")
    private int testconsumedduration;

    @SerializedName("TEST_TESTATTEMPTED")
    private int testattampted;

    @SerializedName("END_TESTTIME")
    private String endtesttime;

    @SerializedName("TEST_DESCRIPTION")
    private String testdescription;

    @SerializedName("TEST_ALLOWVIEWREPORT")
    private int testallowreport;

    @SerializedName("TEST_TESTID")
    private int test_testid;

    @SerializedName("TEST_MARKS")
    private int testmarks;

    @SerializedName("TEST_TIME")
    private String testtime;

    @SerializedName("TEST_ID")
    private int testid;

    @SerializedName("TEST_ATTEMPTNO")
    private int testattemptednumber;

    @SerializedName("TEST_STUID")
    private int teststudentid;

    @SerializedName("TEST_DURATION")
    private int testduration;

    public int getTestconsumedduration() {
        return testconsumedduration;
    }

    public void setTestconsumedduration(int testconsumedduration) {
        this.testconsumedduration = testconsumedduration;
    }

    public int getTestattampted() {
        return testattampted;
    }

    public void setTestattampted(int testattampted) {
        this.testattampted = testattampted;
    }

    public String getEndtesttime() {
        return endtesttime;
    }

    public void setEndtesttime(String endtesttime) {
        this.endtesttime = endtesttime;
    }

    public String getTestdescription() {
        return testdescription;
    }

    public void setTestdescription(String testdescription) {
        this.testdescription = testdescription;
    }

    public int getTestallowreport() {
        return testallowreport;
    }

    public void setTestallowreport(int testallowreport) {
        this.testallowreport = testallowreport;
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

    public int getTestattemptednumber() {
        return testattemptednumber;
    }

    public void setTestattemptednumber(int testattemptednumber) {
        this.testattemptednumber = testattemptednumber;
    }

    public int getTeststudentid() {
        return teststudentid;
    }

    public void setTeststudentid(int teststudentid) {
        this.teststudentid = teststudentid;
    }

    public int getTestduration() {
        return testduration;
    }

    public void setTestduration(int testduration) {
        this.testduration = testduration;
    }
}
