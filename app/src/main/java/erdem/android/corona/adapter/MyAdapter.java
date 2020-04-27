package erdem.android.corona.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import erdem.android.corona.model.Country;
import erdem.android.corona.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CountryViewHolder> {
    private List<Country> dataList;
    String[]names, codes;


    public MyAdapter(List<Country> countries,String[]names,String[]codes) {
        this.dataList = countries;
        this.codes=codes;
        this.names=names;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.txtCounry.setText(dataList.get(position).nameTranslate(names,codes));
        holder.txtConfirmed.setText(String.valueOf(dataList.get(position).getTotalCase().getConfirmed()));
        holder.txtDeath.setText(String.valueOf(dataList.get(position).getTotalCase().getDeaths()));
        holder.txtRecovered.setText(String.valueOf(dataList.get(position).getTotalCase().getRecovered()));
        holder.txtActive.setText(String.valueOf(dataList.get(position).getTotalCase().getActive()));
        holder.txtTodayConfirmed.setText(String.valueOf(dataList.get(position).getTodayCase().getConfirmed()));
        holder.txtTodayDeaths.setText(String.valueOf(dataList.get(position).getTodayCase().getDeaths()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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