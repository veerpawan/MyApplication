package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by PAWAN on 10/7/2017.
 */

public class StudentClassSubjects {


    @SerializedName("studentId")
    public studentId studentId;

   public studentId getStudentId() {
        return studentId;
    }

    public void setStudentId(studentId studentId) {
        this.studentId = studentId;
    }
}
