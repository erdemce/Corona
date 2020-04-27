package erdem.android.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Case {

    @SerializedName("deaths")
    @Expose
    private int deaths;

    @SerializedName("confirmed")
    @Expose
    private int confirmed;

    @SerializedName("recovered")
    @Expose
    private int recovered=0;


    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        int active=confirmed-deaths-recovered;
        return active;
    }
}
