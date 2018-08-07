package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANKU on 06-10-2017.
 */

public class LoginSuccess {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;


    @SerializedName("email_id")
    private String email_id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("studentClassSubjects")
    private List<StudentClassSubjects> studentClassSubjects;



    public List<StudentClassSubjects> getStudentClassSubjects() {
        return studentClassSubjects;
    }

    public void setStudentClassSubjects(List<StudentClassSubjects> studentClassSubjects) {
        this.studentClassSubjects = studentClassSubjects;
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

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
