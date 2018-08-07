package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sourav on 7/26/2017.
 */

public class ClassBasedBoard {


    @SerializedName("name")
    private String name;

    @SerializedName("active")
    private boolean active;

    @SerializedName("id")
    private int id;

    @SerializedName("board_id")
    private int board_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }
}
