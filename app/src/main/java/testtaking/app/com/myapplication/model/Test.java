package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Test {


    @SerializedName("ID")
    private int ID;

    @SerializedName("STUDENT_ID")
    private int STUDENT_ID;

    @SerializedName("TEST_ID")
    private int TEST_ID;

    @SerializedName("TOTAL_TIME_TAKEN")
    private int TOTAL_TIME_TAKEN;


    @SerializedName("SCHEDULED_START_TIME_BEGIN")
    private String SCHEDULED_START_TIME_BEGIN;


    @SerializedName("attemptNumber")
    private int attemptNumber;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public int getTEST_ID() {
        return TEST_ID;
    }

    public void setTEST_ID(int TEST_ID) {
        this.TEST_ID = TEST_ID;
    }

    public int getTOTAL_TIME_TAKEN() {
        return TOTAL_TIME_TAKEN;
    }

    public void setTOTAL_TIME_TAKEN(int TOTAL_TIME_TAKEN) {
        this.TOTAL_TIME_TAKEN = TOTAL_TIME_TAKEN;
    }

    public String getSCHEDULED_START_TIME_BEGIN() {
        return SCHEDULED_START_TIME_BEGIN;
    }

    public void setSCHEDULED_START_TIME_BEGIN(String SCHEDULED_START_TIME_BEGIN) {
        this.SCHEDULED_START_TIME_BEGIN = SCHEDULED_START_TIME_BEGIN;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }
}
