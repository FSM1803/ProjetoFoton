package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, contatos.DATABASE_NAME, null, contatos.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(contatos.CREATE_TABLE);

        Log.d("DBHelper", "Banco de dados ContatosDB criado com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + contatos.TABLE_NAME);
        onCreate(db);
    }

    public long insertContato(String nome, String sobrenome, String telefone, String nascimento, String cep, String estado, String cidade, String bairro, String rua, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(contatos.COLUMN_NOME, nome);
        contentValues.put(contatos.COLUMN_SOBRENOME, sobrenome);
        contentValues.put(contatos.COLUMN_TELEFONE, telefone);
        contentValues.put(contatos.COLUMN_NASCIMENTO, nascimento);
        contentValues.put(contatos.COLUMN_CEP, cep);
        contentValues.put(contatos.COLUMN_ESTADO, estado);
        contentValues.put(contatos.COLUMN_CIDADE, cidade);
        contentValues.put(contatos.COLUMN_BAIRRO, bairro);
        contentValues.put(contatos.COLUMN_RUA, rua);
        contentValues.put(contatos.COLUMN_NUMERO, numero);

        long id = db.insert(contatos.TABLE_NAME, null, contentValues);



        return id;
    }



    public ArrayList<ModelContact> getAllData() {
        ArrayList<ModelContact> arrayList = new ArrayList<>();


    String selectQuery = "SELECT * FROM " + contatos.TABLE_NAME;

    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact = new ModelContact(
                        // only id is integer type

                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(contatos.COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_NOME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_SOBRENOME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_TELEFONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_NASCIMENTO)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_CEP)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_ESTADO)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_CIDADE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_BAIRRO)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(contatos.COLUMN_RUA))
                );

                arrayList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }



    }
