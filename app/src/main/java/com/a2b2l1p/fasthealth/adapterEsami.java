package com.a2b2l1p.fasthealth;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class adapterEsami extends RecyclerView.Adapter<adapterEsami.holder> implements Filterable {
    private ArrayList<EsameStruttura> esami;
    private ArrayList<EsameStruttura> full;
    private OnItemClickListener listener;

    public adapterEsami(ArrayList<EsameStruttura> esami) {
        this.esami = esami;
        this.full = new ArrayList<>(esami);
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_esame, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterEsami.holder holder, int position) {
        EsameStruttura e = esami.get(position);
        holder.nomeEsame.setText(e.getNomeEsame());
    }

    @Override
    public int getItemCount() {
        return esami.size();
    }


    //gestione ricerca
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EsameStruttura> filtrata = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrata.addAll(full);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (EsameStruttura e : full) {
                    if (e.getNomeEsame().toLowerCase().contains(pattern)) {
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
            esami.clear();
            esami.addAll((List<EsameStruttura>)results.values);
            notifyDataSetChanged();

        }
    };

    public static class holder extends RecyclerView.ViewHolder {
        TextView nomeEsame;


        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);
            nomeEsame = itemView.findViewById(R.id.cardSNomeEsame);

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
