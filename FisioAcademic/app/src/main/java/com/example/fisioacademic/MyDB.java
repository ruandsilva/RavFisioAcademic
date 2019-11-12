package com.example.fisioacademic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {

    Context mContext;
    public static String DATABASE_NAME = "fisioacademic";
    public static int VERSION = 1;

    public MyDB (Context context){
        super(context,
                DATABASE_NAME,
                null,
                VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try {
            String sql = "create table biodata (id integer primary key AUTOINCREMENT NOT NULL, " +
                    "pasta text, assunto text, conteudo text);";
            db.execSQL(sql);

            String sql2 = "create table pastaData (idPasta integer primary key AUTOINCREMENT NOT NULL, " +
                    "nomePasta text);";
            db.execSQL(sql2);

            Toast.makeText(mContext, "Tabelas criadas com sucesso",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Error", "Erro na criação das Tabelas", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String pasta, String assunto, String conteudo){
        try {
            String sql = "insert into biodata (pasta,assunto,conteudo) values ('"+pasta+"','"+assunto+"','"+conteudo+"');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertPasta(String nomePasta){
        try {
            String sql = "insert into pastaData (nomePasta) values ('"+nomePasta+"');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor buscarPorId(String id){
        try {
            String sql = "select * from biodata where id="+id+";";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor buscarPastaPorId(String id){
        try {
            String sql = "select * from pastaData where idPasta="+id+";";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor buscarPastaPorNome(String pasta){
        try {
            String sql = "select * from pastaData where nomePasta='"+pasta+"';";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor listar(String idPasta){
        try {
            String sql = "select * from biodata where pasta='"+idPasta+"'";
            SQLiteDatabase db =  getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor listarPasta(){
        try {
            String sql = "select * from pastaData";
            SQLiteDatabase db =  getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateBiodata(String idUp, String assunto, String conteudo){
        try {
            String sql = "update biodata set " +
                    "assunto = '"+assunto+"', " +
                    "conteudo = '"+conteudo+"' where id = "+idUp+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePastaData(String idUp, String nomePasta){
        try {
            String sql = "update pastaData set " +
                    "nomePasta='"+nomePasta+"' where idPasta = "+idUp+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteBiodata(String idDel){
        try {
            String sql = "delete from biodata where id = "+idDel+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletePastaData(String idDel){
        try {
            String sql = "delete from pastaData where idPasta = "+idDel+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertPadrao(){
        try {
            String sql = "insert into pastaData (nomePasta) values ('Traumato - Ortopedia e Reumatologia');";
            String sql1 = "insert into pastaData (nomePasta) values ('Esportiva');";
            String sql2 = "insert into pastaData (nomePasta) values ('Respiratória');";

            String sql3 = "insert into biodata (pasta,assunto,conteudo) values ('1','Contratura','. é uma perda quase completa da mobilidade \n" +
                    ". um tipo de contração não reversível \n" +
                    ". leva a uma congruência articular\n" +
                    ". causada por atividades físicas exacerbada associada com imobilização prolongada');";

            String sql4 = "insert into biodata (pasta,assunto,conteudo) values ('1','Encurtamento','. é uma diminuição das fibras musculares\n" +
                    ". esta relacionada a proteção das fibras inibindo o alongamento ou a contração excêntrica \n" +
                    ". causada por atividades exacerbadas com alto índice de tensão ou reflexo de um processo doloroso');";

            String sql5 = "insert into biodata (pasta,assunto,conteudo) values ('1','Contusão','. lesão em uma região do corpo no qual a pele não perde continuidade   \n" +
                    ". causada por uma força externa de impacto direto ou uma compressão excessiva');";

            String sql6 = "insert into biodata (pasta,assunto,conteudo) values ('2','Dor Tardia','. acometido quando o corpo não está preparado para algum tipo de exercício \n" +
                    ". manifestação da dor aparece entre 8 a 10 horas e diminui até 72 horas após o exercício');";

            String sql7 = "insert into biodata (pasta,assunto,conteudo) values ('2','Fratura de Colles','. fratura na paste distal do rádio \n" +
                    ". causada queda com a mão espalmada \n" +
                    ". causa dor, edema, dificuldade para a realização de prono-supinação e diminuição da ADM');";

            String sql8 = "insert into biodata (pasta,assunto,conteudo) values ('2','Ruptura do Tendão do Bíceps (proximal)','. causada por atividade física vigorosa  ou repetitivas, seguidas por inflamação ou trauma \n" +
                    ". causa dor local, edema, hematoma, diminuição da ADM para flexão e supinação do antebraço ');";

            String sql9 = "insert into biodata (pasta,assunto,conteudo) values ('2','Ruptura do Tendão do Bíceps (Distal)','. causada por levantamento de peso ou empurrar objetos de peso elevado');";

            String sql10 = "insert into biodata (pasta,assunto,conteudo) values ('3','Asma','. Condição em que as vias aéreas de uma pessoa ficam inflamadas, estreitas e inchadas, além de produzirem muco extra, dificultando a respiração\n" +
                    ". tosse seca, chiado do peito, dificuldade de respirar, desconforto torácico e respiração rápida e curta \n" +
                    ". O tratamento medicamentoso é realizado junto com medidas educativas e de controle dos fatores que podem provocar a crise asmática.\n" +
                    ". prevenção: Mantenha o ambiente limpo, Evite acúmulo de sujeira ou poeira, Tome sol, Evite cheiros fortes, Tome a vacina da gripe, Não fume, Se agasalhe, principalmente na época de frio, Pratique atividades físicas regularmente, Tenha alimentação saudável, Beba bastante líquido (água) e Mantenha o peso ideal.');";

            String sql11 = "insert into biodata (pasta,assunto,conteudo) values ('3','DPOC','. é a obstrução da passagem do ar pelos pulmões provocada geralmente pela fumaça do cigarro ou de outros compostos nocivos.\n" +
                    ". causa tosse, pigarro, falta de ar e fadiga ');";

            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);
            db.execSQL(sql5);
            db.execSQL(sql6);
            db.execSQL(sql7);
            db.execSQL(sql8);
            db.execSQL(sql9);
            db.execSQL(sql10);
            db.execSQL(sql11);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
