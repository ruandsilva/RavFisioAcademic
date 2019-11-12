package com.example.fisioacademic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    static MyDB myDB;
    //ArrayList<String> arrayListPasta;
    ArrayList<Integer> arrayListId;
    ArrayList<String> arrayListAssunto;
    ListView listView;
    Button buttonAdicionar;
    ArrayAdapter<String> arrayAdapter;
    int idDaVez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDB = new MyDB(Main2Activity.this);
        //arrayListPasta = new ArrayList<>();
        arrayListId = new ArrayList<>();
        arrayListAssunto = new ArrayList<>();
        buttonAdicionar = findViewById(R.id.main2ButtonAdicionar);
        listView = findViewById(R.id.main2ListViewData);

        if(getIntent().getIntExtra("MainIdPasta", 0) > 0){
            idDaVez = getIntent().getIntExtra("MainIdPasta", 0);

        }else if(getIntent().getIntExtra("EditarIdPasta", 0) > 0){
            idDaVez = getIntent().getIntExtra("EditarIdPasta", 0);

        }else if(getIntent().getIntExtra("ViewIdPasta", 0) > 0){
            idDaVez = getIntent().getIntExtra("ViewIdPasta", 0);

        }else if(getIntent().getIntExtra("AddIdPasta", 0) > 0){
            idDaVez = getIntent().getIntExtra("AddIdPasta", 0);

        }else{
            idDaVez = 1;
        }

        Cursor cursor = myDB.listar(String.valueOf(idDaVez));
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                arrayListId.add(Integer.parseInt(cursor.getString(0)));
                //arrayListPasta.add(cursor.getString(1));
                arrayListAssunto.add(cursor.getString(2));
            }
        }
        arrayAdapter = new ArrayAdapter<>(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                arrayListAssunto);

        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
    }

    public void adicionarDados2(View view){
        Intent intent = new Intent(this, AdicionarActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void voltar(View view){

        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
        Main2Activity.this.finish();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int index = info.position;
        menu.setHeaderTitle("Opções");
        menu.add(index, v.getId(), 0, "Visualizar Assunto");
        menu.add(index, v.getId(), 0, "Editar Assunto");
        menu.add(index, v.getId(), 0, "Deletar Assunto");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Visualizar Assunto"){
            Intent intent = new Intent(Main2Activity.this, ViewActivity.class);
            intent.putExtra("MainId",arrayListId.get(item.getGroupId()));
            startActivity(intent);
        } else if(item.getTitle() == "Editar Assunto"){
            Intent intent = new Intent(Main2Activity.this, EditarActivity.class);
            intent.putExtra("MainId",arrayListId.get(item.getGroupId()));
            startActivity(intent);
        } else if(item.getTitle() == "Deletar Assunto"){
            myDB.deleteBiodata(Integer.toString(arrayListId.get(item.getGroupId())));
            Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
            startActivity(intent);
            Main2Activity.this.finish();
        }else{
            return false;
        }

        return super.onContextItemSelected(item);

    }
}
