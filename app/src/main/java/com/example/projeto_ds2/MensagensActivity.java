package com.example.projeto_ds2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projeto_ds2.adapter.MensagemListAdapter;
import com.example.projeto_ds2.model.mensagem.Mensagem;
import com.example.projeto_ds2.model.mensagem.OrigemEnum;

import java.util.ArrayList;

public class MensagensActivity extends AppCompatActivity {
    MensagemListAdapter mensagemListAdapter;

   private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mensagens);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Button botao = findViewById(R.id.button_mensagem);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem novaMensagem = new Mensagem(OrigemEnum.Remetente, "teste");
                adicionarNovaMensagem(novaMensagem, recyclerView);
            }
        });

    }

    private void adicionarNovaMensagem(Mensagem mensagem, RecyclerView recyclerView){
        mensagens.add(mensagem);
        mensagemListAdapter = new MensagemListAdapter(mensagens);
        recyclerView.setAdapter(mensagemListAdapter);
    }

}