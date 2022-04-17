package com.example.projeto_ds2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_ds2.R;
import com.example.projeto_ds2.model.mensagem.Mensagem;

import java.util.ArrayList;

public class MensagemListAdapter extends RecyclerView.Adapter<MensagemListAdapter.MensagemViewHolder> {

    private ArrayList<Mensagem> mensagens;

    public MensagemListAdapter(ArrayList<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    @NonNull
    @Override
    public MensagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View layout = layoutInflater.inflate(R.layout.view_mensagem_remetente, parent, false);

        return new MensagemViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MensagemViewHolder holder, int position) {
        Mensagem mensagem = mensagens.get(position);

        TextView texto = holder.itemView.findViewById(R.id.text_mensagem_remetente);

        texto.setText(mensagem.getTexto());
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    public class MensagemViewHolder extends RecyclerView.ViewHolder{

        public MensagemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
