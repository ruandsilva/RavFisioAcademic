package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarPastaActivity extends AppCompatActivity {

    EditText editTextPasta;
    Button buttonEditar, buttonRetornar;
    String idAtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pasta);

        editTextPasta = findViewById(R.id.pastaEdit2);
        buttonRetornar = findViewById(R.id.buttonVoltar);
        buttonEditar = findViewById(R.id.buttonRetornar);

        int idMain = getIntent().getIntExtra("MainIdPasta", 0);
        MyDB myDB = new MyDB(EditarPastaActivity.this);
        Cursor cursor = myDB.buscarPastaPorId(Integer.toString(idMain));
        cursor.moveToFirst();
        editTextPasta.setText(cursor.getString(1));
        idAtt = cursor.getString(0);
    }

    public void funcEditar(View view){

        if(editTextPasta.getText().toString().trim().equals("") || editTextPasta.getText().toString().trim().equals(null)) {

            Toast.makeText(getApplicationContext(),
                    "É preciso preencher todos os campos para Editar!",
                    Toast.LENGTH_LONG).show();

        }else {

            try {
                boolean result = MainActivity.myDB.updatePastaData(
                        idAtt,
                        editTextPasta.getText().toString().trim()
                );
                if (result) {
                    Intent intent = new Intent(EditarPastaActivity.this, MainActivity.class);
                    startActivity(intent);
                    EditarPastaActivity.this.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Erro ao Editar a Pasta",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void funcRetornar(View view){

        Intent intent = new Intent(EditarPastaActivity.this, MainActivity.class);
        startActivity(intent);
        EditarPastaActivity.this.finish();
    }
}
