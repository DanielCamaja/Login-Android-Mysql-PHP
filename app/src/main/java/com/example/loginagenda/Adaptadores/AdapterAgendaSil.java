package com.example.loginagenda.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginagenda.DatosAgenda;
import com.example.loginagenda.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterAgendaSil extends RecyclerView.Adapter<AdapterAgendaSil.ViewHolder> {

    private Context mcontext;
    private List<DatosAgenda> mData;

    public AdapterAgendaSil(Context mcontext, List<DatosAgenda> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.lista, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatosAgenda players = mData.get(position);

        Picasso.get().load(mData.get(position)
                .getImage()).resize(60,60)
                .into(holder.imageView);

        holder.texthora.setText(players.getHora());
        holder.textname.setText(players.getNombre());
        holder.textdesc.setText(players.getAsunto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView texthora, textname,textdesc;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            texthora = itemView.findViewById(R.id.textohora);
            textname = itemView.findViewById(R.id.textonombre);
            textdesc = itemView.findViewById(R.id.textoinfo);
            imageView = itemView.findViewById(R.id.imagetipo);
        }
    }
}
