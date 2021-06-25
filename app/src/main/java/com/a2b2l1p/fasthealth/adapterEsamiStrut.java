package com.a2b2l1p.fasthealth;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class adapterEsamiStrut extends RecyclerView.Adapter<adapterEsamiStrut.holder> implements Filterable {
    private ArrayList<Strutture> s;
    private ArrayList<Strutture> full;

    private OnItemClickListener listener;

    public adapterEsamiStrut(ArrayList<Strutture> s) {
        this.s = s;
        this.full = new ArrayList<>(s);
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sceltastut, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterEsamiStrut.holder holder, int position) {
        Strutture st = s.get(position);

        holder.nomeStrut.setText(st.getNome());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        holder.pD.setText("Prima data utile: "+""+format.format(st.getC().getTime()));
        holder.c.setText(st.getCosto()+"€");
    }

    @Override
    public int getItemCount() {
        return s.size();
    }


    //gestione ricerca
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Strutture> filtrata = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrata.addAll(full);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Strutture e : full) {
                    if (e.getNome().toLowerCase().contains(pattern)) {
                        filtrata.add(e);
                    }
                }
            }
            FilterResults r = new FilterResults();
            r.values = filtrata;
            return r;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            s.clear();
            s.addAll((List<Strutture>)results.values);
            notifyDataSetChanged();

        }
    };

    public static class holder extends RecyclerView.ViewHolder {
        TextView nomeStrut,pD,c;
        Chip p;
        //Calendar d=Calendar.getInstance();
        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);
            nomeStrut = itemView.findViewById(R.id.cardSNome);
            pD=itemView.findViewById(R.id.cardSPD);
            c=itemView.findViewById(R.id.cardSCosto);
            p=itemView.findViewById(R.id.cPPSrenota);

            p.setOnClickListener(v -> {
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
