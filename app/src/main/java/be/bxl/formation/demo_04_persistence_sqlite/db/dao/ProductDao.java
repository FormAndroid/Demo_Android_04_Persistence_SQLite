package be.bxl.formation.demo_04_persistence_sqlite.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.bxl.formation.demo_04_persistence_sqlite.db.DbHelper;
import be.bxl.formation.demo_04_persistence_sqlite.db.DbInfo;
import be.bxl.formation.demo_04_persistence_sqlite.models.Product;

// Couche d'abstraction entre le modele Java et la base de donnée SQLite
//  - Défini les méthodes d'interaction avec la DB (Connexion & CRUD)
public class ProductDao {

    //region Champs
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    //endregion

    //region Constructeur
    public ProductDao(Context context) {
        this.context = context;
    }
    //endregion


    //region Méthode de connexion
    public ProductDao openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public ProductDao openReadable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }
    //endregion


    //region Méthode CRUD
    private Product cursorToProduct(Cursor c) {
        // Permet de convertir les données du curseur en "Product" sur base du nom des colonnes.
        long id = c.getLong(c.getColumnIndex(DbInfo.Product.COLUMN_ID));
        String name = c.getString(c.getColumnIndex(DbInfo.Product.COLUMN_NAME));
        String category = c.getString(c.getColumnIndex(DbInfo.Product.COLUMN_CATEGORY));
        int quantity = c.getInt(c.getColumnIndex(DbInfo.Product.COLUMN_QUANTITY));
        double price = c.getDouble(c.getColumnIndex(DbInfo.Product.COLUMN_PRICE));

        return new Product(id, name, category, quantity, price);
    }

    private ContentValues createCV(Product product) {
        // Permet de convertir un "Product" en "ContentValues" pour utiliser les données lors d'un insert ou update
        ContentValues cv = new ContentValues();
        cv.put(DbInfo.Product.COLUMN_NAME, product.getName());
        cv.put(DbInfo.Product.COLUMN_CATEGORY, product.getCategory());
        cv.put(DbInfo.Product.COLUMN_QUANTITY, product.getQuantity());
        cv.put(DbInfo.Product.COLUMN_PRICE, product.getPrice());

        return cv;
    }

    // Create
    public long insert(Product product) {
        ContentValues cv = createCV(product);
        return db.insert(DbInfo.Product.TABLE_NAME, null, cv);
    }

    // Read
    public Product getById(long id) {
        Cursor cursor = db.query( DbInfo.Product.TABLE_NAME,
                null, // -> null : Toute les colonnes
                DbInfo.Product.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) },
                null,
                null,
                null);

        // S'il n'y a pas de résultat
        if(cursor.getCount() == 0) {
            return null;
        }

        // Place le cursor sur l'element trouvé
        cursor.moveToFirst();

        // Renvoie le produit extrait du curseur
        return cursorToProduct(cursor);
    }

    public List<Product> getAll() {
        Cursor cursor = db.query(DbInfo.Product.TABLE_NAME, null, null, null, null, null, null);

        List<Product> products = new ArrayList<>();

        if(cursor.getCount() == 0) {
            return products;
        }

        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            Product p = cursorToProduct(cursor);
            products.add(p);

            cursor.moveToNext();
        }

        return products;
    }

    // Update
    public boolean update(long id, Product product) {
        ContentValues cv = createCV(product);

        int nbRow = db.update(DbInfo.Product.TABLE_NAME, cv,
                DbInfo.Product.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }

    // Delete
    public boolean delete(long id) {
        // -> "DELETE FROM product WHERE _id = " + id;

        int nbRow = db.delete( DbInfo.Product.TABLE_NAME,
                DbInfo.Product.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }
    //endregion
}
