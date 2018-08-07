package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan on 11/18/2016.
 */

public class ExecutionAllBean {

    @SerializedName("TOPIC_NAME")
    private String topicname;

    @SerializedName("TOPIC_ID")
    private int topicid;

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
}
