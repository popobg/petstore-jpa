package fr.digi.cda2024;

import fr.digi.cda2024.bo.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // CREATION D'INSTANCES
        // Création d'objets Address
        Address adresseToulouse = new Address("10 BIS", "rue du Vélo", "31300", "Toulouse");
        Address adressePoissy = new Address("35", "avenue de la bicyclette", "78300", "Poissy");
        Address adresseBordeaux = new Address("2", "impasse du Tricycle", "20200", "Bastia");

        // Création d'objets Animal
        Animal oursBrun = new Animal(LocalDate.of(2020, Month.APRIL, 02), "brun");
        Cat chatRoux = new Cat(LocalDate.of(2018, 11, 20), "roux", "CA1322");
        Cat chatBlanc = new Cat(LocalDate.of(2023, 7, 12), "blanc", "CR526");
        Cat chatBrun = new Cat(LocalDate.of(2021, 3, 07), "brun", "CA1322");
        Fish poissonRouge = new Fish(LocalDate.of(2024, 1, 15), "rouge", FishLivEnv.FRESH_WATER);
        Fish poissonClown = new Fish(LocalDate.of(2023, 12, 30), "orange et blanc", FishLivEnv.FRESH_WATER);
        Fish poissonLune = new Fish(LocalDate.of(2022, 6, 04), "argenté", FishLivEnv.SEA_WATER);

        // Création d'objets Product
        Product produitCroquettes = new Product("0102223459875", "Croquettes", ProdType.FOOD, 30.5);
        Product produitBrossePoils = new Product("8322223455555", "Brosse à poils", ProdType.CLEANING, 12.6);
        Product produitSouris = new Product("5555534598753", "Jouet souris", ProdType.ACCESSORY, 8.99);
        Product produitBaton = new Product("7878734598753", "Faux bâton", ProdType.ACCESSORY, 13.4);
        Product produitGranules = new Product("6412334598753", "Nourriture pour poisson", ProdType.FOOD, 14.3);
        Product produitBalayette = new Product("4621335489573", "Pelle et balayette", ProdType.CLEANING, 12);


        // Création d'objets PetStore
        // Noms de managers originaux qui plus est
        PetStore animalerieAnimalis = new PetStore("Animalis", "Georges Washington", adressePoissy);
        PetStore animalerieZooEtMoi = new PetStore("Zoo et moi", "Abraham Lincoln", adresseToulouse);
        PetStore animalerieAnimalProtect = new PetStore("Animal Protect", "James Monroe", adresseBordeaux);

        // MISE EN LIEN DES ENTITES : gestion des relations bidirectionnelle
        // Animaux et animalerie
        animalerieAnimalis.ajouterAnimal(oursBrun);
        animalerieZooEtMoi.ajouterAnimal(chatRoux);
        animalerieZooEtMoi.ajouterAnimal(chatBlanc);
        animalerieAnimalProtect.ajouterAnimal(chatBrun);
        animalerieAnimalis.ajouterAnimal(poissonRouge);
        animalerieZooEtMoi.ajouterAnimal(poissonClown);
        animalerieAnimalProtect.ajouterAnimal(poissonLune);

        // Animalerie et produits
        animalerieAnimalis.ajouterProduct(produitCroquettes);
        animalerieAnimalis.ajouterProduct(produitBaton);
        animalerieAnimalis.ajouterProduct(produitBrossePoils);
        animalerieAnimalis.ajouterProduct(produitSouris);
        animalerieAnimalProtect.ajouterProduct(produitGranules);
        animalerieAnimalProtect.ajouterProduct(produitCroquettes);
        animalerieZooEtMoi.ajouterProduct(produitBaton);
        animalerieZooEtMoi.ajouterProduct(produitBrossePoils);
        animalerieZooEtMoi.ajouterProduct(produitSouris);
        animalerieZooEtMoi.ajouterProduct(produitBalayette);

        // PERSISTANCE DES DONNEES
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        /*
         * La configuration en cascade des relations entres les entités
         * permet de sauvegarder toutes les instances des objets créés précédemment
         * en persistant l'instance de PetStore, que ces objets lui soient liés
         * directement ou indirectement.
         */
        em.persist(animalerieAnimalis);
        em.persist(animalerieZooEtMoi);
        em.persist(animalerieAnimalProtect);

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
        em.close();
        emf.close();
    }
}