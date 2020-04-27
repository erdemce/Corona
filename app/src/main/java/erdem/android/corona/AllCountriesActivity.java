package erdem.android.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import erdem.android.corona.adapter.MyAdapter;
import erdem.android.corona.model.Api;
import erdem.android.corona.model.Country;
import erdem.android.corona.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllCountriesActivity extends AppCompatActivity {

    TextView texts;
    RecyclerView recyclerView;
    ServiceApi service;
    Retrofit retrofit;
    String baseURL="https://corona-api.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);
        texts=findViewById(R.id.textmain);
        recyclerView=findViewById(R.id.recycler_view_user_list);

    }

    @Override
    public void onResume(){
        super.onResume();
        fillAllCountries();
        recyclerView.invalidate();
    }

    private void fillAllCountries() {
        retrofit=new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(ServiceApi.class);

        Call<Api> call=service.getAllData();
        final String[] codes=getResources().getStringArray(R.array.countryCodes);
        final String[] names=getResources().getStringArray(R.array.countriesNew);
        call.enqueue(new Callback<Api>() {
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(AllCountriesActivity.this,
                            R.string.connectionproblem,Toast.LENGTH_LONG).show();
                }else{
                    List<Country> selectedCountry =response.body().getCountries();
                    MyAdapter adapter = new MyAdapter(selectedCountry,names,codes);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(AllCountriesActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {
                Toast.makeText(AllCountriesActivity.this,
                        R.string.connectionproblem,Toast.LENGTH_LONG).show();
            }
        });
    }

}
