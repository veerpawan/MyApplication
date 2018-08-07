package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Subject {


    @SerializedName("id")
    private int id;

    @SerializedName("subjectName")
    private String subjectName;


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
