package com.example.myapplication;

public class contatos {

    public static final String DATABASE_NAME = "ContatosDB";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Contatos";

    // Tabela de contatos
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_SOBRENOME = "sobrenome";
    public static final String COLUMN_NASCIMENTO = "nascimento";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_CEP = "cep";
    public static final String COLUMN_ESTADO = "estado";
    public static final String COLUMN_CIDADE = "cidade";
    public static final String COLUMN_BAIRRO = "bairro";
    public static final String COLUMN_RUA = "rua";
    public static final String COLUMN_NUMERO = "numero";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NOME + " TEXT," +
                    COLUMN_SOBRENOME + " TEXT," +
                    COLUMN_NASCIMENTO + " TEXT," +
                    COLUMN_TELEFONE + " TEXT," +
                    COLUMN_CEP + " TEXT," +
                    COLUMN_ESTADO + " TEXT," +
                    COLUMN_CIDADE + " TEXT," +
                    COLUMN_BAIRRO + " TEXT," +
                    COLUMN_RUA + " TEXT," +
                    COLUMN_NUMERO + " TEXT)"
                    +");";


    public static void setId(long aLong) {
    }
}
