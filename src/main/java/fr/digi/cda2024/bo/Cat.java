package fr.digi.cda2024.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cat extends Animal {
    /** ID de la puce du chat */
    @Column(name = "CHIP_ID")
    private String chipId;

    /** Constructeur vide */
    public Cat() {
    }

    /**
     * Constructeur
     * @param birth date de naissance
     * @param color couleur
     * @param chipId ID de la puce
     */
    public Cat(LocalDate birth, String color, String chipId) {
        super(birth, color);
        this.chipId = chipId;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * générales de l'instance de Cat.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cat{");
        sb.append("ID=").append(id);
        sb.append(", chipId='").append(chipId).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", birth=").append(birth);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet Cat
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(chipId, cat.chipId);
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int, hash code de l'objet Cat
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chipId);
    }

    /** Getter
     * @return chipId, ID de la puce du chat
     */
    public String getChipId() {
        return chipId;
    }

    /** Setter
     * @param chipId, nouvel ID de la puce du chat
     */
    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
}
