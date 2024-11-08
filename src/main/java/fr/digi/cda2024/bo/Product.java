package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

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

    /** Ensemble d'animaleries possédant le produit */
    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petstores;

    {
        this.petstores = new HashSet<>();
    }

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
     * Appelle ajouterProduct : ajoute le produit dans la liste des produits
     * de l'animalerie et ajoute l'animalerie aux animaleries possédant
     * le produit.
     * @param petStore animalerie à ajouter
     */
    public void ajouterPetStore(PetStore petStore) {
        if (petStore != null) {
            petStore.ajouterProduct(this);
        }
    }

    /**
     * Appelle retirerProduct : retire le produit de la liste des produits
     * de l'animalerie et retire l'animalerie des animaleries du produit.
     * @param petStore animalerie à ajouter
     */
    public void retirerPetStore(PetStore petStore) {
        if (petStore != null) {
            petStore.retirerProduct(this);
        }
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * principales du produit.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("ID=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", type=").append(type);
        sb.append(", price=").append(price).append("€");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet Product
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
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
     * @return int, hash code de l'objet Product
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, label, type, price);
    }

    /** Getter
     * @return Id
     */
    public long getId() {
        return id;
    }

    /** Getter
     * @return code, code produit
     */
    public String getCode() {
        return code;
    }

    /** Setter
     * @param code nouveau code produit
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** Getter
     * @return label, libellé du produit
     */
    public String getLabel() {
        return label;
    }

    /** Setter
     * @param label nouveau libellé du produit
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /** Getter
     * @return type, type de produit
     */
    public ProdType getType() {
        return type;
    }

    /** Setter
     * @param type nouveau type du produit
     */
    public void setType(ProdType type) {
        this.type = type;
    }

    /** Getter
     * @return price, prix du produit
     */
    public double getPrice() {
        return price;
    }

    /** Setter
     * @param price nouveau prix du produit
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter
     * @return petstores, animaleries possédant le produit
     */
    public Set<PetStore> getPetstores() {
        return petstores;
    }
}
