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

public class adapterricercaStruct extends RecyclerView.Adapter<adapterricercaStruct.holder> implements Filterable {
    private ArrayList<StrutturaEsame> s;
    private ArrayList<StrutturaEsame> full;

    private OnItemClickListener listener;

    public adapterricercaStruct(ArrayList<StrutturaEsame> s) {
        this.s = s;
        this.full = new ArrayList<>(s);
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_struttura, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterricercaStruct.holder holder, int position) {
        StrutturaEsame st = s.get(position);

        holder.nomeStrut.setText(st.getNomeStruttura());
        holder.distanza.setText(st.getDistanza()+"Km");

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
            List<StrutturaEsame> filtrata = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrata.addAll(full);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (StrutturaEsame e : full) {
                    if (e.getNomeStruttura().toLowerCase().contains(pattern)) {
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
            s.addAll((List<StrutturaEsame>)results.values);
            notifyDataSetChanged();

        }
    };

    public static class holder extends RecyclerView.ViewHolder {
        TextView nomeStrut,distanza;


        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);
            nomeStrut = itemView.findViewById(R.id.cardNomeStruttura);
            distanza=itemView.findViewById(R.id.cardDistanza);
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
