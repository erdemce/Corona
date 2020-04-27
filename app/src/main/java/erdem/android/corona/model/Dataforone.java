package erdem.android.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Dataforone {

    @SerializedName("data")
    @Expose
    private Country data = null;

    // Getter Methods

    public Country getCountry() {
        return data;
    }

}
