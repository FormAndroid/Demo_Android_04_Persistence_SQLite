package be.bxl.formation.demo_04_persistence_sqlite.models;

// Modele POJO
//  -> Plain Old Java Object
//  -> Classe avec des champs, un constructeur, des getters et setters

import androidx.annotation.NonNull;

public class Product {

    //region Champs
    private long id;
    private @NonNull String name;
    private String category;
    private int quantity;
    private double price;
    //endregion

    //region Constructeur
    // - Utiliser lors de la création d'un produit par l'utilisateur (Sans id)
    public Product(@NonNull String name, String category, int quantity, double price) {
        this.id = 0;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    // - Utiliser lors de la recuperation en base de donnée (Avec l'id généré par SQLite)
    public Product(long id, @NonNull String name, String category, int quantity, double price) {
        this(name, category, quantity, price);
        this.id = id;
    }
    //endregion

    //region Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion

    //region Méthodes
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
    //endregion
}
