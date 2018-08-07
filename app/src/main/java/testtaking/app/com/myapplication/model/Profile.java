package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ANKU on 06-10-2017.
 */

public class Profile {

    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    @SerializedName("USER_ID")
    private int USER_ID;

    @SerializedName("CLASS_ID")
    private String CLASS_ID;

    @SerializedName("BOARD_ID")
    private int BOARD_ID;

    @SerializedName("EMAIL_ID")
    private int EMAIL_ID;

    @SerializedName("FIRST_NAME")
    private int FIRST_NAME;

    @SerializedName("LAST_NAME")
    private int LAST_NAME;

    @SerializedName("CLASS_NAME")
    private String CLASS_NAME;

    @SerializedName("BOARD_NAME")
    private String BOARD_NAME;

    @SerializedName("REGISTRATION_TYPE")
    private String REGISTRATION_TYPE;

    @SerializedName("SECTION")
    private String SECTION;

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

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(String CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }

    public int getBOARD_ID() {
        return BOARD_ID;
    }

    public void setBOARD_ID(int BOARD_ID) {
        this.BOARD_ID = BOARD_ID;
    }

    public int getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(int EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public int getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(int FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public int getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(int LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public String getBOARD_NAME() {
        return BOARD_NAME;
    }

    public void setBOARD_NAME(String BOARD_NAME) {
        this.BOARD_NAME = BOARD_NAME;
    }

    public String getREGISTRATION_TYPE() {
        return REGISTRATION_TYPE;
    }

    public void setREGISTRATION_TYPE(String REGISTRATION_TYPE) {
        this.REGISTRATION_TYPE = REGISTRATION_TYPE;
    }

    public String getSECTION() {
        return SECTION;
    }

    public void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }
}
