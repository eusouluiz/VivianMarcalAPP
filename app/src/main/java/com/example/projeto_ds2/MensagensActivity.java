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
import com.google.android.material.textfield.TextInputEditText;

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

        TextInputEditText campoMensagem = findViewById(R.id.edit_text_mensagem);

        Button botaoEnvio = findViewById(R.id.button_mensagem);
        botaoEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem novaMensagem = new Mensagem(OrigemEnum.Remetente, campoMensagem.getText().toString());
                adicionarNovaMensagem(novaMensagem, recyclerView);

                novaMensagem = new Mensagem(OrigemEnum.Destinatario, "Olá! :)\nAgradecemos sua " +
                                        "mensagem, mas infelizmente não estamos disponíveis no momento.");
                adicionarNovaMensagem(novaMensagem, recyclerView);

                campoMensagem.setText("");
            }
        });

    }

    private void adicionarNovaMensagem(Mensagem mensagem, RecyclerView recyclerView){
        mensagens.add(mensagem);
        mensagemListAdapter = new MensagemListAdapter(mensagens);
        recyclerView.setAdapter(mensagemListAdapter);
    }

}