package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 10/25/2016.
 */

public class StudentSubject {

    @SerializedName("id")
    private int id;

    @SerializedName("subjectName")
    private String subjectName;



    @SerializedName("active")
    private Boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


}
