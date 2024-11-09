package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe POJO PetStore
 */
@Entity
@Table(name = "pet_store")
public class PetStore implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /** Nom de l'animalerie */
    @Column(name = "NAME")
    private String name;

    /** Nom du manager de l'animalerie */
    @Column(name = "MANAGER_NAME")
    private String managerName;

    /** Adresse de l'animalerie */
    // L'attribut cascade entraîne la modification dans la base de données
    // (toutes les modifs avec "ALL") des données de Address
    // en même temps que celles de PetStore.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADDRESS")
    private Address address;

    /** Ensemble des animaux présents dans l'animalerie */
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private Set<Animal> animals;

    /** Ensemble des produits vendus par l'animalerie */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STORE_PRODUCT",
    joinColumns = @JoinColumn(name = "ID_STORE", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID"))
    private Set<Product> products;

    // bloc d'initialisation : éviter de répéter ce code dans tous les constructeurs
    {
        this.animals = new HashSet<>();
        this.products = new HashSet<>();
    }

    /** Constructeur vide */
    public PetStore() {
    }

    /**
     * Constructeur
     * @param name nom de l'animalerie
     * @param managerName nom du manager de l'animalerie
     */
    public PetStore(String name, String managerName) {
        this(name, managerName, null);
    }

    /**
     * Constructeur
     * @param name nom de l'animalerie
     * @param managerName nom du manager de l'animalerie
     * @param address adresse de l'animalerie
     */
    public PetStore(String name, String managerName, Address address) {
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }

    /**
     * Appelle ajouterPetStore : cette fonction rattache l'animalerie à l'animal
     * et ajoute l'animal dans la liste des animaux de l'animalerie.
     * @param animal animal
     */
    public void ajouterAnimal(Animal animal) {
        if (animal != null) {
            animal.ajouterPetStore(this);
        }
    }

    /**
     * Appelle retirerPetStore : cette fonction retire l'animalerie de l'animal
     * et retire l'animal de la liste des animaux de l'animalerie.
     * @param animal animal
     */
    public void retirerAnimal(Animal animal) {
        if(animal != null) {
            animal.retirerPetStore(this);
        }
    }

    /**
     * Ajoute le produit dans la liste des produits de l'animalerie
     * et ajoute l'animalerie aux animaleries possédant le produit.
     * @param product produit à ajouter
     */
    public void ajouterProduct(Product product) {
        if (product != null) {
            this.products.add(product);
            product.getPetstores().add(this);
        }
    }

    /**
     * Retire le produit de la liste des produits de l'animalerie
     * et retire l'animalerie des animaleries du produit.
     * @param product produit à supprimer
     */
    public void retirerProduct(Product product) {
        if(product != null) {
            this.products.remove(product);
            product.getPetstores().remove(this);
        }
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * générales de l'instance de PetStore.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PetStore{");
        sb.append("ID=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", managerName='").append(managerName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet PetStore
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetStore petStore)) return false;
        return Objects.equals(name, petStore.name) && Objects.equals(managerName, petStore.managerName);
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int, hash code de l'objet PetStore
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, managerName);
    }

    /** Getter
     * @return id
     */
    public long getId() {
        return id;
    }

    /** Getter
     * @return name, nom de l'animalerie
     */
    public String getName() {
        return name;
    }

    /** Setter
     * @param name nouveau nom de l'animalerie
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Getter
     * @return managerName, nom du manager de l'animalerie
     */
    public String getManagerName() {
        return managerName;
    }

    /** Setter
     * @param managerName nouveau nom du manager de l'animalerie
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /** Getter
     * @return address, l'adresse de l'animalerie
     */
    public Address getAddress() {
        return address;
    }

    /** Setter
     * @param address nouvelle adresse de l'animalerie
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /** Getter
     * @return animals, l'ensemble des animaux de l'animalerie
     */
    public Set<Animal> getAnimals() {
        return animals;
    }

    /** Getter
     * @return products, l'ensemble des produits de l'animalerie
     */
    public Set<Product> getProducts() {
        return products;
    }
}
