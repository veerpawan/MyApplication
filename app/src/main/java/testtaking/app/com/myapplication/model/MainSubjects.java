package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MainSubjects {


    @SerializedName("SUCCESS")
    private int SUCCESS;


    @SerializedName("MESSAGE")
    private String MESSAGE;


    @SerializedName("User")
    private User user;

    @SerializedName("Subject")
    private List<Subject> subjects;


    public int getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(int SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
