package com.example.projeto_ds2;

import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto_ds2.adapter.MensagemListAdapter;
import com.example.projeto_ds2.model.aviso.Aviso;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarAvisoActivity extends AppCompatActivity {
    AvisoActivity avisoListAdapter;
    private ArrayList<Aviso> avisos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aviso);

        TextInputEditText editTextAviso =
                findViewById(R.id.edit_text_aviso);
        String texto = editTextAviso.getText().toString();

        Button saveButton = findViewById(R.id.button_posta);
    }


}
