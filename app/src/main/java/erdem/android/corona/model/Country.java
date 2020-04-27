package erdem.android.corona.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
"data":[{"coordinates":{"latitude":33,"longitude":65},"name":"Afghanistan","code":"AF","population":29121286,"updated_at":"2020-04-13T12:15:10.567Z","today":{"deaths":1,"confirmed":0},"latest_data":{"deaths":19,"confirmed":607,"recovered":32,"critical":0,"calculated":{"death_rate":3.130148270181219,
"recovery_rate":5.2718286655683695,"recovered_vs_death_ratio":null,"cases_per_million_population":16}}}
 */
public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("today")
    @Expose
    private Case today;

    @SerializedName("latest_data")
    @Expose
    private Case latest_data;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public Case getTodayCase() {
        return today;
    }

    public Case getTotalCase() {
        return latest_data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String nameTranslate(String[] names, String[] codes){

        for (int i = 0; i < codes.length; i++) {
            if(code.equals(codes[i])){
                name=names[i];
                break;
            }

        }
        return name;
    }
}

