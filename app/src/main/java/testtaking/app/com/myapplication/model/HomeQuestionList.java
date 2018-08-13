package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by window on 7/7/2018.
 */

public class HomeQuestionList {

    @SerializedName("question_text")
    private String question_text;

    @SerializedName("post_created_on")
    private String post_created_on;

    @SerializedName("commentSize")
    private int commentSize;

    @SerializedName("post_id")
    private int post_id;

    @SerializedName("post_type")
    private String post_type;

    @SerializedName("post_created_username")
    private String post_created_username;

    @SerializedName("post_image_name")
    private String post_image_name;


    @SerializedName("attemptSize")
    private int attemptSize;

    @SerializedName("upvoteSize")
    private int upvoteSize;


    public int getAttemptSize() {
        return attemptSize;
    }

    public void setAttemptSize(int attemptSize) {
        this.attemptSize = attemptSize;
    }

    public int getUpvoteSize() {
        return upvoteSize;
    }

    public void setUpvoteSize(int upvoteSize) {
        this.upvoteSize = upvoteSize;
    }

    @SerializedName("options")
    private HomeQuestionOptions homeQuestionOptions;

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getPost_created_on() {
        return post_created_on;
    }

    public void setPost_created_on(String post_created_on) {
        this.post_created_on = post_created_on;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_created_username() {
        return post_created_username;
    }

    public void setPost_created_username(String post_created_username) {
        this.post_created_username = post_created_username;
    }

    public String getPost_image_name() {
        return post_image_name;
    }

    public void setPost_image_name(String post_image_name) {
        this.post_image_name = post_image_name;
    }

    public HomeQuestionOptions getHomeQuestionOptions() {
        return homeQuestionOptions;
    }

    public void setHomeQuestionOptions(HomeQuestionOptions homeQuestionOptions) {
        this.homeQuestionOptions = homeQuestionOptions;
    }
}
