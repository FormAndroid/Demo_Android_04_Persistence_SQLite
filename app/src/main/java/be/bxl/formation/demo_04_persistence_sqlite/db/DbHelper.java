package be.bxl.formation.demo_04_persistence_sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Appele le constructeur parent avec tout les infos necessaire
    public DbHelper(Context context) {
        super(context, DbInfo.DB_NAME, null, DbInfo.DB_VERSION);
    }

    // Méthode appelé automatiquement si l'app ne contient pas la base de donnée
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbInfo.Product.REQUEST_CREATE);
    }

    // Méthode appelé automatiquement si l'app  contient une version précédente de la base de donnée
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Pour la mise a jour de la DB, solution simple => On detruit et on recrée
        db.execSQL(DbInfo.Product.REQUEST_DELETE);
        onCreate(db);

        // Note : pour les données sensible -> Faite une mise a jour avec des ALTER TABLE ;)
    }
}
