package erdem.android.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
"data":[{"coordinates":{"latitude":33,"longitude":65},"name":"Afghanistan","code":"AF","population":29121286,
"updated_at":"2020-04-13T12:15:10.567Z","today":{"deaths":1,"confirmed":0},
"latest_data":{"deaths":19,"confirmed":607,"recovered":32,"critical":0,"calculated":{"death_rate":3.130148270181219,
"recovery_rate":5.2718286655683695,"recovered_vs_death_ratio":null,"cases_per_million_population":16}}}
 */

public class DataforWorld {
    @SerializedName("data")
    @Expose
    private List<Case> cases;

    public int getWorldConfirmed(){
        return cases.get(0).getConfirmed();

    }
    public int getWorldDeaths(){
        return cases.get(0).getDeaths();

    }
    public int getWorldRecovered(){
        return cases.get(0).getRecovered();

    }
    public int getWorldActive(){
        return cases.get(0).getActive();

    }

    public int getWorldTodayConfirmed(){
        return getWorldConfirmed()-cases.get(1).getConfirmed();
    }
    public int getWorldTodayDeaths(){
        return getWorldDeaths()-cases.get(1).getDeaths();
    }


}
