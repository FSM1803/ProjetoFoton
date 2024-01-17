import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class ContatosDB extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "ContatosDB";
    private static final int DATABASE_VERSION = 1;

    // Tabela de contatos
    public static final String TABLE_CONTATOS = "contatos";
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

    // Criação da tabela
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CONTATOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT, " +
                    COLUMN_SOBRENOME + " TEXT, " +
                    COLUMN_NASCIMENTO + " TEXT, " +
                    COLUMN_TELEFONE + " TEXT, " +
                    COLUMN_CEP + " TEXT, " +
                    COLUMN_ESTADO + " TEXT, " +
                    COLUMN_CIDADE + " TEXT, " +
                    COLUMN_BAIRRO + " TEXT, " +
                    COLUMN_RUA + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ContatosDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATOS);
        onCreate(db);
    }
}