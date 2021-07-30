package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //criar bd
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", 0, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT (3))");

            //inserir valores
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ful', 30)");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Sicl', 35)");

            //recuperar pessoas - filtros
            String consulta =
                    "SELECT nome, idade" +
                    "FROM pessoas " +
                    "WHERE nome = 'Ful' AND idade = 30";

            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null){
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                Log.i("RESULTADO - nome ", nome + " / idade: " + idade);

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.fillInStackTrace();
        }
    }
}