package fr.digi.cda2024.bo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe POJO Fish
 */
@Entity
@Table(name = "fish")
public class Fish extends Animal {
    /** environnement de vie du poisson */
    @Enumerated(EnumType.STRING)
    @Column(name = "LIVING_ENV")
    private FishLivEnv livingEnv;

    /** Constructeur vide */
    public Fish() {
    }

    /**
     * Constructeur
     * @param birth date de naissance
     * @param color couleur
     * @param livingEnv environnement de vie
     */
    public Fish(LocalDate birth, String color, FishLivEnv livingEnv) {
        super(birth, color);
        this.livingEnv = livingEnv;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations
     * générales de l'instance de Fish.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fish{");
        sb.append("ID=").append(id);
        sb.append(", livingEnv=").append(livingEnv);
        sb.append(", birth=").append(birth);
        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Teste l'égalité entre l'objet actuel et celui passé en argument.
     * @param o objet à comparer à l'objet Fish
     * @return boolean, 'true' si les objets sont identiques,
     *          'false' s'ils sont différents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fish fish)) return false;
        if (!super.equals(o)) return false;
        return livingEnv == fish.livingEnv;
    }

    /**
     * Retourne le "hash code" de l'objet, généré à partir de ses propriétés
     * principales.
     * @return int, hash code de l'objet Fish
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), livingEnv);
    }

    /** Getter
     * @return livingEnv, environnement de vie du poisson
     */
    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    /** Setter
     * @param livingEnv, nouvel environnement de vie du poisson
     */
    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }
}
