package erdem.android.corona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import erdem.android.corona.R;
import erdem.android.corona.model.Country;

public class MyAdapterforOne extends RecyclerView.Adapter<MyAdapterforOne.CountryViewHolder> {
    private Country data;
    private String name;

    public MyAdapterforOne(Country data,String strCountryname) {
        this.data = data;
        this.name=strCountryname;
    }
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_country, parent, false);
        return new CountryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.txtCounry.setText(name);
        holder.txtConfirmed.setText(String.valueOf(data.getTotalCase().getConfirmed()));
        holder.txtDeath.setText(String.valueOf(data.getTotalCase().getDeaths()));
        holder.txtRecovered.setText(String.valueOf(data.getTotalCase().getRecovered()));
        holder.txtActive.setText(String.valueOf(data.getTotalCase().getActive()));
        holder.txtTodayConfirmed.setText(String.valueOf(data.getTodayCase().getConfirmed()));
        holder.txtTodayDeaths.setText(String.valueOf(data.getTodayCase().getDeaths()));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView txtCounry,txtConfirmed, txtDeath,txtRecovered, txtActive,txtTodayConfirmed,txtTodayDeaths;

        CountryViewHolder(View itemView) {
            super(itemView);
            txtCounry =  itemView.findViewById(R.id.txt_Countryname);
            txtConfirmed =  itemView.findViewById(R.id.txt_confirmed);
            txtDeath =  itemView.findViewById(R.id.txt_deaths);
            txtRecovered= itemView.findViewById(R.id.txt_recovered);
            txtActive= itemView.findViewById(R.id.txt_active);
            txtTodayConfirmed=itemView.findViewById(R.id.today_confirmed);
            txtTodayDeaths=itemView.findViewById(R.id.today_deaths);
        }
    }
}