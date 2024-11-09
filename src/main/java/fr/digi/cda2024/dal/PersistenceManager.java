package fr.digi.cda2024.dal;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitaire permettant la création d'un objet
 * EntityManagerFactory qui pourra être partagé par toute l'application
 */
public final class PersistenceManager {

    /**
     * Attribut statique permettant l'utilisation d'un emf unique
     * qui peut-être appelé à plusieurs endroits du programme.
     */
    private static EntityManagerFactory emf;

    private PersistenceManager() {}

    /**
     * Retourne l'EntityManagerFactory unique ou le crée s'il est null.
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("petstore-jpa");
        }

        return emf;
    }

    /**
     * Ferme l'EntityManagerFactory créé.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }
}
