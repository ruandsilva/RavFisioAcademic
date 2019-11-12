package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView textViewPasta, textViewAssunto, textViewConteudo;
    Button buttonVoltar;
    String idPasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textViewPasta = (TextView) findViewById(R.id.pastaBanco);
        textViewAssunto = (TextView) findViewById(R.id.assuntoBanco);
        textViewConteudo = (TextView) findViewById(R.id.conteudoBanco);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        int idMain = getIntent().getIntExtra("MainId",0);
        MyDB myDB = new MyDB(ViewActivity.this);
        Cursor cursor = myDB.buscarPorId(Integer.toString(idMain));
        cursor.moveToFirst();
        idPasta = cursor.getString(1);
        textViewAssunto.setText(cursor.getString(2));
        textViewConteudo.setText(cursor.getString(3));

        Cursor cursor2 = myDB.buscarPastaPorId(idPasta);
        cursor2.moveToFirst();
        textViewPasta.setText(cursor2.getString(1));
    }

    public void funcRetornar(View view){
        Intent intent = new Intent(ViewActivity.this, Main2Activity.class);
        intent.putExtra("ViewIdPasta", idPasta);
        startActivity(intent);
        ViewActivity.this.finish();
    }
}
