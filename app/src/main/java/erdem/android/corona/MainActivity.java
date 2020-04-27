package erdem.android.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import erdem.android.corona.adapter.MyAdapterforOne;
import erdem.android.corona.adapter.WorldAdapter;
import erdem.android.corona.model.Country;
import erdem.android.corona.model.DataforWorld;
import erdem.android.corona.model.Dataforone;
import erdem.android.corona.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    TextView textMain, text2;
    Button btn_showAll;
    RecyclerView rv_selection, rv_world;
    Spinner staticSpinner;
    Retrofit retrofitW, retrofitS;
    String baseURL = "https://corona-api.com";
    ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMain = findViewById(R.id.textmain);
        text2 = findViewById(R.id.text2);
        staticSpinner = findViewById(R.id.staticSpinner);
        rv_selection = findViewById(R.id.recycler_view_Auswahl);
        rv_world = findViewById(R.id.recycler_view_World);
        btn_showAll = findViewById(R.id.btn_showall);
        btn_showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        erdem.android.corona.AllCountriesActivity.class);
                startActivity(intent);
            }
        });
        fillworld();
        fillSelected(Locale.getDefault().getCountry(), Locale.getDefault().getDisplayCountry());
    }

    @Override
    public void onResume() {
        super.onResume();
        fillSpinner();
        fillworld();
        fillSelected(Locale.getDefault().getCountry(), Locale.getDefault().getDisplayCountry());
        rv_world.invalidate();
        rv_selection.invalidate();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void fillworld() {
        retrofitW = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofitW.create(ServiceApi.class);

        Call<DataforWorld> call = service.getWorld();

        call.enqueue(new Callback<DataforWorld>() {
            @Override
            public void onResponse(Call<DataforWorld> call, Response<DataforWorld> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            R.string.connectionproblem, Toast.LENGTH_LONG).show();
                } else {
                    DataforWorld dataWorld = response.body();
                    WorldAdapter adapter = new WorldAdapter(dataWorld);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MainActivity.this);
                    rv_world.setLayoutManager(layoutManager);
                    rv_world.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataforWorld> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        R.string.connectionproblem, Toast.LENGTH_LONG).show();
                textMain.setVisibility(View.VISIBLE);
            }
        });
    }


    private void fillSpinner() {
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.countriesNew, android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] codes = getResources().getStringArray(R.array.countryCodes);
                fillSelected(codes[position], staticSpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void fillSelected(String code, final String strCountryname) {
        if (!code.equals("XX")) {
            retrofitS = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofitS.create(ServiceApi.class);
            Call<Dataforone> call = service.getCountryData(code);
            call.enqueue(new Callback<Dataforone>() {
                @Override
                public void onResponse(Call<Dataforone> call, Response<Dataforone> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this,
                                R.string.connectionproblem, Toast.LENGTH_LONG).show();
                    } else {
                        Country selectedCountry = response.body().getCountry();
                        MyAdapterforOne adapter = new MyAdapterforOne(selectedCountry, strCountryname);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MainActivity.this);
                        rv_selection.setLayoutManager(layoutManager);
                        rv_selection.setAdapter(adapter);
                    }
                }
                @Override
                public void onFailure(Call<Dataforone> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            R.string.connectionproblem, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
