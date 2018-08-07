package testtaking.app.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PAWAN on 12/20/2017.
 */

public class VenueList {

    @SerializedName("walk_in_venue_id")
    private int walk_in_venue_id;

    @SerializedName("walk_in_venue_name")
    private String walk_in_venue_name;

    @SerializedName("walk_in_venue_active")
    private int walk_in_venue_active;

    public int getWalk_in_venue_id() {
        return walk_in_venue_id;
    }

    public void setWalk_in_venue_id(int walk_in_venue_id) {
        this.walk_in_venue_id = walk_in_venue_id;
    }

    public String getWalk_in_venue_name() {
        return walk_in_venue_name;
    }

    public void setWalk_in_venue_name(String walk_in_venue_name) {
        this.walk_in_venue_name = walk_in_venue_name;
    }

    public int getWalk_in_venue_active() {
        return walk_in_venue_active;
    }

    public void setWalk_in_venue_active(int walk_in_venue_active) {
        this.walk_in_venue_active = walk_in_venue_active;
    }
}
