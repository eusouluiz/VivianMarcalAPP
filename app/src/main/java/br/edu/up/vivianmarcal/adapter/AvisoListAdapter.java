package br.edu.up.vivianmarcal.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.up.vivianmarcal.model.aviso.Aviso;

import com.example.projeto_ds2.R;

import java.util.ArrayList;

public class AvisoListAdapter extends RecyclerView.Adapter<AvisoListAdapter.AvisoViewHolder> {


    private ArrayList<Aviso> avisos;
    private OnAvisoClickListener listener;

    public AvisoListAdapter(ArrayList<Aviso> avisos,
                            OnAvisoClickListener listener) {
        this.avisos = avisos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AvisoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        layout = layoutInflater.inflate(R.layout.view_aviso, parent, false);

        return new AvisoViewHolder(layout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AvisoViewHolder holder,
                                 int position) {

        if (Boolean.TRUE.equals(avisos.get(position).getAtivo())) {
            Aviso aviso = avisos.get(position);

            TextView textViewAviso = holder.itemView.
                    findViewById(R.id.text_aviso);

            textViewAviso.setText(aviso.getTexto());

            TextView timeTextViewAviso = holder.itemView.
                    findViewById(R.id.time_aviso);

            timeTextViewAviso.setText(aviso.getHora());
        } else {
            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }

    }

    @Override
    public int getItemCount() {
        return avisos.size();
    }

    public class AvisoViewHolder
            extends RecyclerView.ViewHolder {

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

    public interface OnAvisoClickListener {
        void onClick(Aviso aviso);
    }

}

