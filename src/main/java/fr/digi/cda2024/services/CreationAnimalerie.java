package fr.digi.cda2024.services;

import fr.digi.cda2024.bo.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CreationAnimalerie {

    // Empêche la création d'objets pour cette classe
    private CreationAnimalerie() {}

    /**
     * Création d'objets de chaque type et remplissage d'objets PetStore.
     * Retourne la liste des animaleries créées.
     * @return List<PetStore>, les animaleries créées
     */
    // Cette classe mériterait un sous-découpage supplémentaire mais cela permet
    // tout de même de ne pas tout avoir dans la méthode Main.
    public static List<PetStore> creerAnimaleries() {
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

        List<PetStore> animaleries = new ArrayList<>();
        Collections.addAll(animaleries, animalerieAnimalis, animalerieAnimalProtect, animalerieZooEtMoi);

        return animaleries;
    }
}
