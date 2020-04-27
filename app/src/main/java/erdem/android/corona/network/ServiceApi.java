package erdem.android.corona.network;

import erdem.android.corona.model.Api;
import erdem.android.corona.model.Country;
import erdem.android.corona.model.DataforWorld;
import erdem.android.corona.model.Dataforone;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ServiceApi {

    @GET("countries")
    Call<Api> getAllData();

    @GET("timeline")
    Call <DataforWorld> getWorld();

    @GET("countries/{request}")
    Call<Dataforone> getCountryData(@Path("request") String request);



}
