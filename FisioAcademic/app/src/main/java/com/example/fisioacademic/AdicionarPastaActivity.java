package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarPastaActivity extends AppCompatActivity {

    EditText editTextPasta;
    Button buttonSalvar, buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pasta);

        editTextPasta = findViewById(R.id.pastaSave2);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonVoltar = findViewById(R.id.buttonVoltar);
    }

    public void funcSalvar(View view){

        if(editTextPasta.getText().toString().trim().equals("") || editTextPasta.getText().toString().trim().equals(null)) {

            Toast.makeText(getApplicationContext(),
                    "É preciso preencher todos os campos para Editar!",
                    Toast.LENGTH_LONG).show();

        }else {

            try {
                boolean result = MainActivity.myDB.insertPasta(
                        editTextPasta.getText().toString().trim());

                if (result) {
                    Intent intent = new Intent(AdicionarPastaActivity.this, MainActivity.class);
                    startActivity(intent);
                    AdicionarPastaActivity.this.finish();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Erro ao Adicionar a Pasta",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void funcRetornar (View view){
        Intent intent = new Intent(AdicionarPastaActivity.this, MainActivity.class);
        startActivity(intent);
        AdicionarPastaActivity.this.finish();
    }
}
