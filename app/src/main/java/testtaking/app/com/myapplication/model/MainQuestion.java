package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainQuestion {

    @SerializedName("QUESTION")
    private List<Question> question;

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
}
