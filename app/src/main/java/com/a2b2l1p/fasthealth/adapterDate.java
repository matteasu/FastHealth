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

public class adapterDate extends RecyclerView.Adapter<adapterDate.holder> {
    private ArrayList<Calendar> date;

    private OnItemClickListener listener;

    public adapterDate(ArrayList<Calendar> date) {
        this.date = date;

    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_modifica, parent, false);
        holder vH = new holder(v, listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(adapterDate.holder holder, int position) {
        Calendar c = date.get(position);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        holder.nuovaData.setText(format.format(c.getTime()));
        holder.nuovoOrario.setText((int)Math.floor(Math.random()*(20-8+1)+8)+":"+(int)Math.floor(Math.random()*(59-10+1)+10));
    }

    @Override
    public int getItemCount() {
        return date.size();
    }


    //gestione ricerca


    public static class holder extends RecyclerView.ViewHolder {
        TextView nuovaData,nuovoOrario,noD;
        Chip conferma;


        public holder(View itemView, OnItemClickListener listener) {
            super(itemView);

            nuovoOrario=itemView.findViewById(R.id.cModifcaNO);
            nuovaData=itemView.findViewById(R.id.cModificaND);
            conferma=itemView.findViewById(R.id.cardModifcaCONF);
            conferma.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position,nuovoOrario.getText().toString());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position,String nuovoOrario);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



}
