package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long Id;

    /** Code produit */
    // La taille d'un code produit est généralement de 13 caractères
    @Column(name = "CODE", length = 13)
    private String code;

    /** Nom du produit */
    @Column(name = "LABEL")
    private String label;

    /** Type de produits (selon l'énuméré ProdType) */
    // J'ai choisi de stocker les valeurs de l'énumérable
    // sous forme de String (plus facile à lire + pas d'incidence si
    // on change l'ordre des valeurs de la classe enum).
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ProdType type;

    /** Prix du produit */
    @Column(name = "PRICE")
    private double price;

    /** Constructeur vide */
    public Product() {
    }

    /**
     * Constructeur
     * @param code code produit
     * @param label libellé du produit
     * @param type type de produit
     * @param price prix du produit
     */
    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * principales du produit.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("Id=").append(Id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", type=").append(type);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Retourne 'true' si l'objet passé en argument est identique
     * à l'objet Address actuel, 'false' s'ils sont différents.
     * @param o objet à comparer à l'objet Product
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Double.compare(price, product.price) == 0 && Objects.equals(code, product.code) && Objects.equals(label, product.label) && type == product.type;
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, label, type, price);
    }

    /** Getter
     * @return Id
     */
    public long getId() {
        return Id;
    }

    /** Getter
     * @return code
     */
    public String getCode() {
        return code;
    }

    /** Setter
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** Getter
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /** Setter
     * @param label label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /** Getter
     * @return type
     */
    public ProdType getType() {
        return type;
    }

    /** Setter
     * @param type type
     */
    public void setType(ProdType type) {
        this.type = type;
    }

    /** Getter
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /** Setter
     * @param price price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
