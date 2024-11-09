package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe POJO Address
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /** Numéro de la rue */
    @Column(name = "NUMBER")
    private String number;

    /** Nom de la rue */
    @Column(name = "STREET")
    private String street;

    /** Code postal */
    @Column(name = "ZIPCODE")
    private String zipCode;

    /** Nom de la ville */
    @Column(name = "CITY")
    private String city;

    /** Constructeur vide */
    public Address() {
    }

    /**
     * Constructeur
     * @param number numéro de la rue
     * @param street nom de la rue
     * @param zipCode code postal
     * @param city nom de la ville
     */
    public Address(String number, String street, String zipCode, String city) {
        this.number = number;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * générales de l'instance d'Address.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("ID=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet Address
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(number, address.number) && Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city);
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int, hash code de l'objet Address
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, street, zipCode, city);
    }

    /** Getter
     * @return Id
     */
    public long getId() {
        return id;
    }

    /** Getter
     * @return number, numéro de la rue
     */
    public String getNumber() {
        return number;
    }

    /** Setter
     * @param number nouveau numéro de rue
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /** Getter
     * @return street, nom de la rue
     */
    public String getStreet() {
        return street;
    }

    /** Setter
     * @param street nouveau nom de la rue
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /** Getter
     * @return zipCode, code postal de la ville
     */
    public String getZipCode() {
        return zipCode;
    }

    /** Setter
     * @param zipCode nouveau code postal de la ville
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /** Getter
     * @return city, nom de la ville
     */
    public String getCity() {
        return city;
    }

    /** Setter
     * @param city nouveau nom de la ville
     */
    public void setCity(String city) {
        this.city = city;
    }
}
