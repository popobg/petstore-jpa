package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "animal")
public class Animal implements Serializable {
    /** Id, valeur numérique unique,
     * clé primaire auto-incrémentée en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected long id;

    /** Date de naissance de l'animal */
    @Column(name = "BIRTH")
    protected LocalDate birth;

    /** Couleur de l'animal */
    @Column(name = "COLOR")
    protected String color;

    @ManyToOne
    @JoinColumn(name = "ID_PET_STORE")
    private PetStore petStore;

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
     * Constructeur
     * @param petStore animalerie où se trouve l'animal
     * @param color couleur
     * @param birth date de naissance
     */
    public Animal(PetStore petStore, String color, LocalDate birth) {
        this.petStore = petStore;
        this.color = color;
        this.birth = birth;
    }

    /**
     * Rattache l'animalerie à l'animal et ajoute l'animal
     * dans la liste des animaux de l'animalerie.
     * @param petStore animalerie
     */
    public void ajouterPetStore(PetStore petStore) {
        if (petStore != null) {
            this.petStore = petStore;
            petStore.getAnimals().add(this);
        }
    }

    /**
     * Retire l'animalerie de l'animal et retire l'animal
     * de la liste des animaux de l'animalerie.
     * @param petStore animalerie
     */
    public void retirerPetStore(PetStore petStore) {
        if (petStore != null) {
            petStore.getAnimals().remove(this);
            this.petStore = null;
        }
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
        sb.append("ID=").append(id);

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
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet Animal
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
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
     * @return int, hash code de l'objet Animal
     */
    @Override
    public int hashCode() {
        return Objects.hash(birth, color);
    }

    /** Getter
     * @return Id
     */
    public long getId() {
        return id;
    }

    /** Getter
     * @return birth, date de naissance de l'animal
     */
    public LocalDate getBirth() {
        return birth;
    }

    /** Setter
     * @param birth nouvelle date de naissance de l'animal
     */
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    /** Getter
     * @return color, la couleur de l'animal
     */
    public String getColor() {
        return color;
    }

    /** Setter
     * @param color nouvelle couleur de l'animal
     */
    public void setColor(String color) {
        this.color = color;
    }

    /** Getter
     * @return petStore, l'animalerie dans laquelle se trouve l'animal
     */
    public PetStore getPetStore() {
        return petStore;
    }
}
