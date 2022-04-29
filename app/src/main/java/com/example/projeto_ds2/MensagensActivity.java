package com.example.projeto_ds2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projeto_ds2.adapter.MensagemListAdapter;
import com.example.projeto_ds2.model.mensagem.Mensagem;
import com.example.projeto_ds2.model.mensagem.OrigemEnum;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MensagensActivity extends AppCompatActivity {

    MensagemListAdapter mensagemListAdapter;
    private ArrayList<Mensagem> mensagens = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mensagens);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        TextInputEditText campoMensagem = findViewById(R.id.edit_text_mensagem);
        campoMensagem.requestFocus();

        Button botaoEnvio = findViewById(R.id.button_mensagem);
        botaoEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem novaMensagem = new Mensagem(OrigemEnum.Remetente, campoMensagem.getText().toString(),
                        sdf.format(calendar.getTime()));
                adicionarNovaMensagem(novaMensagem, recyclerView);

                novaMensagem = new Mensagem(OrigemEnum.Destinatario, "Olá! :)\nAgradecemos sua " +
                                        "mensagem, mas infelizmente não estamos disponíveis no momento.",
                        sdf.format(calendar.getTime()));
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