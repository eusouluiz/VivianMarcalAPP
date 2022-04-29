package com.example.projeto_ds2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projeto_ds2.adapter.AvisoListAdapter;
import com.example.projeto_ds2.model.aviso.Aviso;
import com.example.projeto_ds2.model.mensagem.Mensagem;

import java.util.ArrayList;

public class AvisoActivity extends AppCompatActivity {

    private ArrayList<Aviso> avisos = new ArrayList<>();
    AvisoListAdapter avisoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);

        Button buttonAdicionar = findViewById(R.id.button_add);

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisterActivity(null);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_avisos);

        avisoListAdapter =
                new AvisoListAdapter(avisos,
                        new AvisoListAdapter.OnAvisoClickListener() {
                            @Override
                            public void onClick(Aviso aviso) {

                                callRegisterActivity(aviso);

                            }
                        });
        recyclerView.setAdapter(avisoListAdapter);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void callRegisterActivity(Aviso aviso){

        Intent intent = new Intent(
                AvisoActivity.this,
                AdicionarAvisoActivity.class);

        if(aviso != null){
            intent.putExtra("aviso",aviso);
            avisos.remove(aviso);
        }

        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){

            Aviso aviso = (Aviso) data.getSerializableExtra("aviso");
            avisos.add(aviso);
            avisoListAdapter.notifyDataSetChanged();
        }

        if(requestCode == 100 && resultCode == RESULT_CANCELED){

            avisoListAdapter.notifyDataSetChanged();
        }

    }
}