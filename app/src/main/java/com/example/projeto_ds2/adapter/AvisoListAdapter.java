package com.example.projeto_ds2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_ds2.model.aviso.Aviso;
import com.example.projeto_ds2.R;

import java.util.ArrayList;

public class AvisoListAdapter extends RecyclerView.Adapter<AvisoListAdapter.AvisoViewHolder> {


    private ArrayList<Aviso> avisos;
    private OnAvisoClickListener listener;

    public AvisoListAdapter(ArrayList<Aviso> avisos,
                           OnAvisoClickListener listener){
        this.avisos = avisos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AvisoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        View layout =
                layoutInflater.inflate(R.layout.view_aviso,
                        parent,
                        false);

        AvisoViewHolder userViewHolder =
                new AvisoViewHolder(layout);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvisoViewHolder holder,
                                 int position) {

        Aviso aviso = avisos.get(position);

        TextView textViewFirstName = holder.itemView.
                findViewById(R.id.edit_text_aviso);

        textViewFirstName.setText(aviso.getTexto());

    }

    @Override
    public int getItemCount() {
        return avisos.size();
    }

    public class AvisoViewHolder
            extends RecyclerView.ViewHolder{

        public AvisoViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Aviso aviso = avisos.get(position);
                    listener.onClick(aviso);

                }
            });

        }
    }

    public interface OnAvisoClickListener{
        void onClick(Aviso aviso);
    }

}
