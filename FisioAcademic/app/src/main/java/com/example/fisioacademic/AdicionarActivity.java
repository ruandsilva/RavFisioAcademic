package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class AdicionarActivity extends AppCompatActivity {

    EditText editTextConteudo, editTextAssunto;
    Spinner spinnerPastaSave;
    Button buttonSalvar, buttonVoltar;
    ArrayAdapter<String> spinnerAdapter;
    ArrayList<Integer> arrayListIdPasta;
    ArrayList<String> arrayListPasta;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        arrayListIdPasta = new ArrayList<>();
        arrayListPasta = new ArrayList<>();

        spinnerPastaSave = findViewById(R.id.pastaSave);
        editTextConteudo = findViewById(R.id.conteudoSave);
        editTextAssunto = findViewById(R.id.assuntoSave);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        Cursor cursor = Main2Activity.myDB.listarPasta();
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                arrayListIdPasta.add(Integer.parseInt(cursor.getString(0)));
                //arrayListPasta.add(cursor.getString(1));
                arrayListPasta.add(cursor.getString(1));
            }
        }
        spinnerAdapter = new ArrayAdapter<>(AdicionarActivity.this,
                android.R.layout.simple_list_item_1,
                arrayListPasta);

        spinnerPastaSave.setAdapter(spinnerAdapter);

    }

    public void funcSalvar(View view){

        if(editTextAssunto.getText().toString().trim().equals("") || editTextAssunto.getText().toString().trim().equals(null) ||
                editTextConteudo.getText().toString().trim().equals("") || editTextConteudo.getText().toString().trim().equals(null)) {

            Toast.makeText(getApplicationContext(),
                    "É preciso preencher todos os campos para Editar!",
                    Toast.LENGTH_LONG).show();

        }else {

            try {

                Cursor cursor = MainActivity.myDB.buscarPastaPorNome(spinnerPastaSave.getSelectedItem().toString());
                cursor.moveToFirst();
                id = cursor.getString(0);

                boolean result = MainActivity.myDB.insertData(
                        id,
                        editTextAssunto.getText().toString().trim(),
                        editTextConteudo.getText().toString().trim());
                if (result) {
                    Intent intent = new Intent(AdicionarActivity.this, Main2Activity.class);
                    intent.putExtra("AddIdPasta", id);
                    startActivity(intent);
                    AdicionarActivity.this.finish();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Erro ao Gravar Assunto",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void funcRetornar (View view){
        Intent intent = new Intent(AdicionarActivity.this, Main2Activity.class);
        intent.putExtra("AddIdPasta", id);
        startActivity(intent);
        AdicionarActivity.this.finish();
    }
}
