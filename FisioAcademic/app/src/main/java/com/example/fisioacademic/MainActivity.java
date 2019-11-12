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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    static MyDB myDB;
    //ArrayList<String> arrayListPasta;
    ArrayList<Integer> arrayListId;
    ArrayList<String> arrayListPasta;
    ListView listView;
    Button buttonAdicionar;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDB(MainActivity.this);
        //arrayListPasta = new ArrayList<>();
        arrayListId = new ArrayList<>();
        arrayListPasta = new ArrayList<>();
        buttonAdicionar = findViewById(R.id.mainButtonAdicionar);
        listView = findViewById(R.id.mainListViewData);

        Cursor cursor = myDB.listarPasta();
        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                arrayListId.add(Integer.parseInt(cursor.getString(0)));
                //arrayListPasta.add(cursor.getString(1));
                arrayListPasta.add(cursor.getString(1));
            }
        }else{
            myDB.insertPadrao();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayListPasta);

        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
    }

    public void adicionarDados(View view){
        Intent intent = new Intent(this, AdicionarPastaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int index = info.position;
        menu.setHeaderTitle("Opções");
        menu.add(index, v.getId(), 0, "Assuntos");
        menu.add(index, v.getId(), 0, "Editar Pasta");
        menu.add(index, v.getId(), 0, "Deletar Pasta");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Assuntos"){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("MainIdPasta",arrayListId.get(item.getGroupId()));
            startActivity(intent);
        } else if(item.getTitle() == "Editar Pasta"){
            Intent intent = new Intent(MainActivity.this, EditarPastaActivity.class);
            intent.putExtra("MainIdPasta",arrayListId.get(item.getGroupId()));
            startActivity(intent);
        } else if(item.getTitle() == "Deletar Pasta"){
            myDB.deletePastaData(Integer.toString(arrayListId.get(item.getGroupId())));
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }else{
            return false;
        }

        return super.onContextItemSelected(item);

    }

}
