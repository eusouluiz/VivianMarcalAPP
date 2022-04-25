package com.example.projeto_ds2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto_ds2.adapter.MensagemListAdapter;
import com.example.projeto_ds2.model.aviso.Aviso;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarAvisoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aviso);

        TextInputEditText editTextAviso =
                findViewById(R.id.edit_text_aviso);

        if(getIntent().hasExtra("aviso")){
            Aviso aviso = (Aviso) getIntent()
                    .getSerializableExtra("aviso");

            editTextAviso.setText(aviso.getTexto());
        }

        Button saveButton = findViewById(R.id.button_posta);

        saveButton.setOnClickListener(v -> {

            String aviso_completo = editTextAviso.getText().toString();

            if(aviso_completo.isEmpty()){
                editTextAviso.setError("Favor inserir o aviso");
                return;
            }

            Aviso aviso = new Aviso(aviso_completo);

            Intent intent = new Intent();
            intent.putExtra("aviso",aviso);

            setResult(RESULT_OK,intent);
            onBackPressed();

            //finish();

        });
    }


}
