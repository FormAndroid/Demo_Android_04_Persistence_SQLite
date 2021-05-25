package be.bxl.formation.demo_04_persistence_sqlite.db;

public class DbInfo {

    public static final String DB_NAME = "my_db";
    public static final int DB_VERSION = 1;

    public static class Product {
        // Nom de la table
        public static final String TABLE_NAME = "product";

        // Nom des colonnes
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";

        // Requetes (DDL)
        public static final String REQUEST_CREATE =
                "CREATE TABLE " + Product.TABLE_NAME + " ( "
                    + Product.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Product.COLUMN_NAME + " TEXT NOT NULL, "
                    + Product.COLUMN_CATEGORY + " TEXT, "
                    + Product.COLUMN_QUANTITY + " INT NOT NULL, "
                    + Product.COLUMN_PRICE + " NUMERIC NOT NULL "
                    + ");" ;

        public static final String REQUEST_DELETE = "DROP TABLE " + Product.TABLE_NAME + ";" ;
    }

}
