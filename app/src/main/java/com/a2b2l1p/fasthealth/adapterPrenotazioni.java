package com.a2b2l1p.fasthealth;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class adapterPrenotazioni extends RecyclerView.Adapter<adapterPrenotazioni.holder> implements Filterable {
    private ArrayList<Prenotazione> prenotazioni;
    private ArrayList<Prenotazione> full;
    private OnItemClickListener listener;

    public adapterPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
        this.full = new ArrayList<>(prenotazioni);
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardprenotazioni, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterPrenotazioni.holder holder, int position) {
        Prenotazione p = prenotazioni.get(position);
        holder.nomeEsame.setText(p.getNomeEsame());
        holder.struttura.setText(p.getNomeStruttura());
        holder.dataOra.setText(p.getOra());
        holder.pagamento.setText(p.isPagato() ? "Pagato" : "Da pagare");
    }

    @Override
    public int getItemCount() {
        return prenotazioni.size();
    }


    //gestione ricerca
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Prenotazione> filtrata = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrata.addAll(full);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Prenotazione p : full) {
                    if (p.getNomeEsame().toLowerCase().contains(pattern)) {
                        filtrata.add(p);
                    }
                }
            }
            FilterResults r = new FilterResults();
            r.values = filtrata;
            return r;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            prenotazioni.clear();
            prenotazioni.addAll((List<Prenotazione>)results.values);
            notifyDataSetChanged();

        }
    };

    public static class holder extends RecyclerView.ViewHolder {
        TextView nomeEsame, dataOra, struttura;
        Chip pagamento;

        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);
            nomeEsame = itemView.findViewById(R.id.cPNE);
            dataOra = itemView.findViewById(R.id.cPD);
            struttura = itemView.findViewById(R.id.cPS);
            pagamento = itemView.findViewById(R.id.cPPaga);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



}
