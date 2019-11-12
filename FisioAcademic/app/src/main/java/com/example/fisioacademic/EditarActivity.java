package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarActivity extends AppCompatActivity {

    EditText editTextAssunto, editTextConteudo;
    Button buttonEditar, buttonRetornar;
    String idAtt, idPasta;
    TextView spinnerPastaEdit;
    /*ArrayAdapter<String> spinnerAdapter;
    ArrayList<Integer> arrayListIdPasta;
    ArrayList<String> arrayListPasta;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        spinnerPastaEdit = findViewById(R.id.pastaEdit);
        editTextAssunto = findViewById(R.id.assuntoEdit);
        editTextConteudo = findViewById(R.id.conteudoEdit);
        buttonRetornar = findViewById(R.id.buttonVoltar);
        buttonEditar = findViewById(R.id.buttonRetornar);

        int idMain = getIntent().getIntExtra("MainId", 0);
        MyDB myDB = new MyDB(EditarActivity.this);
        Cursor cursor = myDB.buscarPorId(Integer.toString(idMain));
        cursor.moveToFirst();
        idAtt = cursor.getString(0);
        idPasta = cursor.getString(1);
        editTextAssunto.setText(cursor.getString(2));
        editTextConteudo.setText(cursor.getString(3));

        Cursor cursor2 = myDB.buscarPastaPorId(idPasta);
        cursor2.moveToFirst();
        spinnerPastaEdit.setText(cursor2.getString(1));

        /*Cursor cursor2 = myDB.listarPasta();
        if (cursor2.getCount() > 0){
            while(cursor2.moveToNext()){
                arrayListIdPasta.add(Integer.parseInt(cursor2.getString(0)));
                //arrayListPasta.add(cursor.getString(1));
                arrayListPasta.add(cursor2.getString(1));
            }
        }
        spinnerAdapter = new ArrayAdapter<>(EditarActivity.this,
                android.R.layout.simple_list_item_1,
                arrayListPasta);

        spinnerPastaEdit.setAdapter(spinnerAdapter);

        Cursor cursor3 = MainActivity.myDB.buscarPastaPorId(idPasta);
        cursor.moveToFirst();
        String nomePasta = cursor.getString(1);
        spinnerPastaEdit.setSelection(spinnerAdapter.getPosition(nomePasta));*/

    }

    public void funcEditar(View view){

        if(editTextAssunto.getText().toString().trim().equals("") || editTextAssunto.getText().toString().trim().equals(null) ||
                editTextConteudo.getText().toString().trim().equals("") || editTextConteudo.getText().toString().trim().equals(null)) {

            Toast.makeText(getApplicationContext(),
                    "É preciso preench",
                    Toast.LENGTH_LONG).show();

        }else {
            try {
                boolean result = MainActivity.myDB.updateBiodata(
                        idAtt,
                        editTextAssunto.getText().toString().trim(),
                        editTextConteudo.getText().toString().trim()
                );
                if (result) {
                    Intent intent = new Intent(EditarActivity.this, Main2Activity.class);
                    intent.putExtra("EditarIdPasta", idPasta);
                    startActivity(intent);
                    EditarActivity.this.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Erro ao Editar Assunto",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void funcRetornar(View view){

        Intent intent = new Intent(EditarActivity.this, Main2Activity.class);
        intent.putExtra("EditarIdPasta", idPasta);
        startActivity(intent);
        EditarActivity.this.finish();
    }
}
