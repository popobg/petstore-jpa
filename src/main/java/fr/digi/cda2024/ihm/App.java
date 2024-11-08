package fr.digi.cda2024.ihm;

import fr.digi.cda2024.bo.*;
import fr.digi.cda2024.services.CreationAnimalerie;
import fr.digi.cda2024.dal.PersistenceManager;
import jakarta.persistence.*;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // CREATION D'INSTANCES
        List<PetStore> animaleries = CreationAnimalerie.creerAnimaleries();

        // PERSISTANCE DES DONNEES
        // création d'un EntityMangerFactory à l'aide d'une classe utilitaire
        EntityManagerFactory emf = PersistenceManager.getEntityManagerFactory();

        // auto-close grâce au try : l'em sera fermé en sortant du bloc try
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            /*
             * La configuration en cascade des relations entres les entités
             * permet de sauvegarder toutes les instances des objets créés précédemment
             * en persistant l'instance de PetStore, que ces objets lui soient liés
             * directement ou indirectement.
             */
            for (PetStore animalerie: animaleries) {
                em.persist(animalerie);
            }

            // REQUETES A LA BASE DE DONNEES
            // Exemple de requête JPA
            Product produitID3 = em.find(Product.class, 3L);

            if (produitID3 != null) {
                System.out.println("Le produit d'ID 3 est le suivant :");
                System.out.println(produitID3);
                System.out.println();
            }

            // Requête JPQL pour trouver tous les animaux d'une animalerie donnée
            TypedQuery<Animal> queryAnimaux = em.createQuery("select a from PetStore p join p.animals as a where p.name = 'Zoo et moi'", Animal.class);
            List<Animal> animalsZooEtMoi = queryAnimaux.getResultList();

            if (animalsZooEtMoi != null) {
                System.out.println("Les animaux de 'Zoo et moi' sont :");
                for (Animal animal : animalsZooEtMoi) {
                    System.out.println(animal);
                }
            }

            transaction.commit();
        }
        PersistenceManager.closeEntityManagerFactory();
    }
}