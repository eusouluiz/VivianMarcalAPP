package br.edu.up.vivianmarcal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto_ds2.R;

import br.edu.up.vivianmarcal.model.aviso.Aviso;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarAvisoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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

            Aviso aviso = new Aviso(aviso_completo, sdf.format(calendar.getTime()));

            Intent intent = new Intent();
            intent.putExtra("aviso",aviso);

            setResult(RESULT_OK,intent);
            onBackPressed();

            //finish();

        });
        Button cancelButton = findViewById(R.id.button_cancel);

        cancelButton.setOnClickListener(v -> {

            setResult(RESULT_CANCELED);
            onBackPressed();
            //finish();

        });


    }


}
