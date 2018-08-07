package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by pawan on 10/27/2016.
 */

public class BeanTopic {
    @SerializedName("id")
    private int id;

    @SerializedName("topicName")
    private String topicName;

    @SerializedName("chapter")
    private BeanChapter beanChapter;

    @SerializedName("active")
    private Boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public BeanChapter getBeanChapter() {
        return beanChapter;
    }

    public void setBeanChapter(BeanChapter beanChapter) {
        this.beanChapter = beanChapter;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
