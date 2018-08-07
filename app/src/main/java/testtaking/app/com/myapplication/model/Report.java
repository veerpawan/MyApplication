package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("SECTION_QUESTION_ID")
    private int SECTION_QUESTION_ID;

    @SerializedName("SUBQUESTION_ID")
    private int SUBQUESTION_ID;

    @SerializedName("CORRECT")
    private int CORRECT;

    @SerializedName("SELECTED_OPTIONS")
    private String SELECTED_OPTIONS;

    @SerializedName("ATTEMPT")
    private int ATTEMPT;


    @SerializedName("CORRECT_ANSWER")
    private int CORRECT_ANSWER;

    @SerializedName("SEQUENCE")
    private int SEQUENCE;

    @SerializedName("QUESTION_ID")
    private int QUESTION_ID;




    @SerializedName("TOPICWISE")
    private String TOPICWISE;

    @SerializedName("STUDENT_ID")
    private int STUDENT_ID;


    @SerializedName("SECTIONWISE")
    private String SECTIONWISE;


    public int getSECTION_QUESTION_ID() {
        return SECTION_QUESTION_ID;
    }

    public void setSECTION_QUESTION_ID(int SECTION_QUESTION_ID) {
        this.SECTION_QUESTION_ID = SECTION_QUESTION_ID;
    }

    public int getSUBQUESTION_ID() {
        return SUBQUESTION_ID;
    }

    public void setSUBQUESTION_ID(int SUBQUESTION_ID) {
        this.SUBQUESTION_ID = SUBQUESTION_ID;
    }

    public int getCORRECT() {
        return CORRECT;
    }

    public void setCORRECT(int CORRECT) {
        this.CORRECT = CORRECT;
    }

    public String getSELECTED_OPTIONS() {
        return SELECTED_OPTIONS;
    }

    public void setSELECTED_OPTIONS(String SELECTED_OPTIONS) {
        this.SELECTED_OPTIONS = SELECTED_OPTIONS;
    }

    public int getATTEMPT() {
        return ATTEMPT;
    }

    public void setATTEMPT(int ATTEMPT) {
        this.ATTEMPT = ATTEMPT;
    }

    public int getCORRECT_ANSWER() {
        return CORRECT_ANSWER;
    }

    public void setCORRECT_ANSWER(int CORRECT_ANSWER) {
        this.CORRECT_ANSWER = CORRECT_ANSWER;
    }

    public int getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(int SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    public int getQUESTION_ID() {
        return QUESTION_ID;
    }

    public void setQUESTION_ID(int QUESTION_ID) {
        this.QUESTION_ID = QUESTION_ID;
    }


    public String getTOPICWISE() {
        return TOPICWISE;
    }

    public void setTOPICWISE(String TOPICWISE) {
        this.TOPICWISE = TOPICWISE;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getSECTIONWISE() {
        return SECTIONWISE;
    }

    public void setSECTIONWISE(String SECTIONWISE) {
        this.SECTIONWISE = SECTIONWISE;
    }
}
