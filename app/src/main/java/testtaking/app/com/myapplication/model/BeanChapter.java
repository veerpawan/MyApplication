package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 10/27/2016.
 */

public class BeanChapter {
    @SerializedName("id")
    private int id;

    @SerializedName("chapterName")
    private String chapterName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

}
