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

public class adapterStrutEsame extends RecyclerView.Adapter<adapterStrutEsame.holder> implements Filterable {
    private ArrayList<Esame> e;
    private ArrayList<Esame> full;

    private OnItemClickListener listener;

    public adapterStrutEsame(ArrayList<Esame> s) {
        this.e = s;
        this.full = new ArrayList<>(s);
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_esami, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterStrutEsame.holder holder, int position) {
        Esame st = e.get(position);
        holder.nomeEsame.setText(st.getNome());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        holder.pD.setText("Prima data utile: "+format.format(st.getData().getTime()));
        holder.c.setText(st.getCosto()+"€");

    }

    @Override
    public int getItemCount() {
        return e.size();
    }


    //gestione ricerca
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Esame> filtrata = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrata.addAll(full);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Esame e : full) {
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
            e.clear();
            e.addAll((List<Esame>)results.values);
            notifyDataSetChanged();

        }
    };

    public static class holder extends RecyclerView.ViewHolder {
        TextView nomeEsame,pD,c;
        Chip p;
        //Calendar d=Calendar.getInstance();
        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);
            nomeEsame = itemView.findViewById(R.id.cardNomeEsame);
            pD=itemView.findViewById(R.id.cardPD);
            c=itemView.findViewById(R.id.cardCosto);
            p=itemView.findViewById(R.id.cPPrenota);

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
