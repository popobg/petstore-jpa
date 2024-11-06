package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "animal")
public class Animal implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected long Id;

    /** Date de naissance de l'animal */
    @Column(name = "BIRTH")
    protected LocalDate birth;

    /** Couleur de l'animal */
    @Column(name = "COLOR")
    protected String color;

    /** Constructeur vide */
    public Animal() {
    }

    /**
     * Constructeur
     * @param birth date de naissance
     * @param color couleur
     */
    public Animal(LocalDate birth, String color) {
        this.birth = birth;
        this.color = color;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * principales de l'instance d'Animal.
     * @return String
     */
    @Override
    public String toString() {
        // formatage de la date de naissance au format jour/mois/année
        DateTimeFormatter formateurDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("Id=").append(Id);

        if (birth != null) {
            sb.append(", birth=").append(birth.format(formateurDate));
        }
        else {
            sb.append(", birth=unknown");
        }

        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Retourne 'true' si l'objet passé en argument est identique
     * à l'objet Address actuel, 'false' s'ils sont différents.
     * @param o objet à comparer à l'objet Animal
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return Objects.equals(birth, animal.birth) && Objects.equals(color, animal.color);
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(birth, color);
    }

    /** Getter
     * @return Id
     */
    public long getId() {
        return Id;
    }

    /** Getter
     * @return birth
     */
    public LocalDate getBirth() {
        return birth;
    }

    /** Setter
     * @param birth birth
     */
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    /** Getter
     * @return color
     */
    public String getColor() {
        return color;
    }

    /** Setter
     * @param color color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
