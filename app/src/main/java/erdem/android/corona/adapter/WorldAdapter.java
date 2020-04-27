package erdem.android.corona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import erdem.android.corona.R;
import erdem.android.corona.model.DataforWorld;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.worldViewHolder> {

    private DataforWorld dataWorld;


public WorldAdapter(DataforWorld worlddata) {
        this.dataWorld = worlddata;
        }

@Override
public worldViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_country, parent, false);
        return new worldViewHolder(view);
        }

@Override
public void onBindViewHolder(worldViewHolder holder, int position) {

        holder.txtCounry.setText(R.string.world);
        holder.txtConfirmed.setText(String.valueOf(dataWorld.getWorldConfirmed()));
        holder.txtDeath.setText(String.valueOf(dataWorld.getWorldDeaths()));
        holder.txtRecovered.setText(String.valueOf(dataWorld.getWorldRecovered()));
        holder.txtActive.setText(String.valueOf(dataWorld.getWorldActive()));
        holder.txtTodayConfirmed.setText(String.valueOf(dataWorld.getWorldTodayConfirmed()));
        holder.txtTodayDeaths.setText(String.valueOf(dataWorld.getWorldTodayDeaths()));
        }

@Override
public int getItemCount() {
        return 1;
        }

class worldViewHolder extends RecyclerView.ViewHolder {
    TextView txtCounry,txtConfirmed, txtDeath,txtRecovered, txtActive,txtTodayConfirmed,txtTodayDeaths;

    worldViewHolder(View itemView) {
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