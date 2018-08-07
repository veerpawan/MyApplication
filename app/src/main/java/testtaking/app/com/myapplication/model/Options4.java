package testtaking.app.com.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Options4 implements Parcelable {

    @SerializedName("OPTION_ID")
    private int OPTION_ID;

    @SerializedName("OPTION_ANSWER")
    private int OPTION_ANSWER;
    @SerializedName("OPTION")
    private String OPTION;

    protected Options4(Parcel in) {
        OPTION_ID = in.readInt();
        OPTION_ANSWER = in.readInt();
        OPTION = in.readString();
    }

    public static final Creator<Options4> CREATOR = new Creator<Options4>() {
        @Override
        public Options4 createFromParcel(Parcel in) {
            return new Options4(in);
        }

        @Override
        public Options4[] newArray(int size) {
            return new Options4[size];
        }
    };

    public int getOPTION_ID() {
        return OPTION_ID;
    }

    public void setOPTION_ID(int OPTION_ID) {
        this.OPTION_ID = OPTION_ID;
    }

    public int getOPTION_ANSWER() {
        return OPTION_ANSWER;
    }

    public void setOPTION_ANSWER(int OPTION_ANSWER) {
        this.OPTION_ANSWER = OPTION_ANSWER;
    }

    public String getOPTION() {
        return OPTION;
    }

    public void setOPTION(String OPTION) {
        this.OPTION = OPTION;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(OPTION_ID);
        parcel.writeInt(OPTION_ANSWER);
        parcel.writeString(OPTION);
    }
}
