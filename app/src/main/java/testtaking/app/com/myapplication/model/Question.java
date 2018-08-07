package testtaking.app.com.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Question implements Parcelable {


    @SerializedName("OPTIONS1")
    private Options1 options1;

    @SerializedName("OPTIONS2")
    private Options2 options2;

    @SerializedName("OPTIONS3")
    private Options3 options3;

    @SerializedName("OPTIONS4")
    private Options4 options4;


    @SerializedName("QUESTION")
    private String questionlist;

    @SerializedName("TYPE")
    private int type;

    @SerializedName("OPTION_COUNT")
    private int OPTION_COUNT;

    @SerializedName("ID")
    private int ID;

    @SerializedName("SECTION_QUESTION_ID")
    private int SECTION_QUESTION_ID;

    @SerializedName("ANSWER")
    private int ANSWER;


    @SerializedName("NAME")
    private String NAME;


    @SerializedName("O_ID")
    private int O_ID;


    private int optiontype;

    public int getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(int optiontype) {
        this.optiontype = optiontype;
    }

    public static Creator<Question> getCREATOR() {
        return CREATOR;
    }

    protected Question(Parcel in) {

        this.options1 = in.readParcelable(Options1.class.getClassLoader());
        this.options2 = in.readParcelable(Options2.class.getClassLoader());
        this.options3 = in.readParcelable(Options3.class.getClassLoader());
        this.options4 = in.readParcelable(Options4.class.getClassLoader());
        questionlist = in.readString();
        type = in.readInt();
        OPTION_COUNT = in.readInt();
        ID = in.readInt();
        SECTION_QUESTION_ID = in.readInt();
        ANSWER = in.readInt();
        NAME = in.readString();
        O_ID = in.readInt();
        status = in.readInt();
        optiontype = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getO_ID() {
        return O_ID;
    }

    public void setO_ID(int o_ID) {
        O_ID = o_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSECTION_QUESTION_ID() {
        return SECTION_QUESTION_ID;
    }

    public void setSECTION_QUESTION_ID(int SECTION_QUESTION_ID) {
        this.SECTION_QUESTION_ID = SECTION_QUESTION_ID;
    }

    public int getANSWER() {
        return ANSWER;
    }

    public void setANSWER(int ANSWER) {
        this.ANSWER = ANSWER;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getOPTION_COUNT() {
        return OPTION_COUNT;
    }

    public void setOPTION_COUNT(int OPTION_COUNT) {
        this.OPTION_COUNT = OPTION_COUNT;
    }

    public Options1 getOptions1() {
        return options1;
    }

    public void setOptions1(Options1 options1) {
        this.options1 = options1;
    }

    public Options2 getOptions2() {
        return options2;
    }

    public void setOptions2(Options2 options2) {
        this.options2 = options2;
    }

    public Options3 getOptions3() {
        return options3;
    }

    public void setOptions3(Options3 options3) {
        this.options3 = options3;
    }

    public Options4 getOptions4() {
        return options4;
    }

    public void setOptions4(Options4 options4) {
        this.options4 = options4;
    }

    public String getQuestionlist() {
        return questionlist;
    }

    public void setQuestionlist(String questionlist) {
        this.questionlist = questionlist;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeParcelable(options1, i);
        parcel.writeParcelable(options2, i);
        parcel.writeParcelable(options3, i);
        parcel.writeParcelable(options4, i);
        parcel.writeString(questionlist);
        parcel.writeInt(type);
        parcel.writeInt(OPTION_COUNT);
        parcel.writeInt(ID);
        parcel.writeInt(SECTION_QUESTION_ID);
        parcel.writeInt(ANSWER);
        parcel.writeString(NAME);
        parcel.writeInt(O_ID);
        parcel.writeInt(status);
        parcel.writeInt(optiontype);

    }
}
