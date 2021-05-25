package be.bxl.formation.demo_04_persistence_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import be.bxl.formation.demo_04_persistence_sqlite.db.dao.ProductDao;
import be.bxl.formation.demo_04_persistence_sqlite.models.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exemple d'utilisation de la base de donnée dans une activité
        //  - Initialisation du DAO pour travailler avec des produits
        ProductDao productDao = new ProductDao(getApplicationContext());

        //  - Création d'un produit
        Product p1 = new Product("Demo", "N/A", 2, 3.14);

        productDao.openWritable();
        long id = productDao.insert(p1);
        productDao.close();

        //  - Lecture de tout les produits en DB
        productDao.openReadable();
        List<Product> products = productDao.getAll();
        productDao.close();

        // - Lecture du produit avec l'id de valeur "1"
        productDao.openReadable();
        Product p2 = productDao.getById(1);
        productDao.close();
    }
}