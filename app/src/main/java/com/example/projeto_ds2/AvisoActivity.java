package com.example.projeto_ds2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projeto_ds2.model.aviso.Aviso;
import com.example.projeto_ds2.model.mensagem.Mensagem;

import java.util.ArrayList;

public class AvisoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_avisos);

        Button buttonAdicionar = findViewById(R.id.button_add);
        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvisoActivity.this, AdicionarAvisoActivity.class);
                startActivity(intent);
            }
        });
    }
}