package erdem.android.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Api {

    @SerializedName("data")
    @Expose
    private List<Country> data = null;

    // Getter Methods

    public List<Country> getCountries() {
        return data;
    }
}
