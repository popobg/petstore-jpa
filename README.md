# Mon application d'animalerie (*petstore*)
**Pauline Bouyssou**

Application gestionnaire d'animalerie développé en Java avec l'ORM Hibernate permettant de mapper des entités en base de données relationnelle.

## Sommaire
1. [Contexte](#contexte)
2. [Pré-requis](#pré-requis)\
    2.1. [Dépendances](#dépendances)\
    2.2. [Installation](#installation)\
    2.3. [Configuration](#configuration)

## Contexte
Ce projet concerne le développement d'une application JAVA de gestion d'animalerie permettant de **mapper les entités de l'animalerie vers une base de données** à l'aide de Java Persistence API (JPA) et de l'ORM Hibernate.\
Après mapping des entités à partir du code, toutes les opérations de CRUD doivent pouvoir être exécutées dans le programme pour interagir avec cette base de données.

*L'application ne présente pas d'interface graphique/console pour le moment.*

### Modélisation de la base de données
La modélisation de la base de données suivante nous a été apportée par le client.\
Elle décrit la structure et les relations qui doivent exister entre les données dans la base de données.

![Modélisation des entités de la base de données](images_README/petstore_database.png)

### Base de données choisie
La base de données choisie ici est MariaDB, mais vous pouvez utiliser la base de données relationnelle de votre choix en configurant le fichier persistence.xml (cf [Configuration](#configuration)).

Le serveur utilisé tourne sur notre machine locale (utilisation de l'IP localhost (127.0.0.1)).\
Il vous faudra

### Mapping Objet-Relation (O/R)
Dans notre application, le mapping entités-tables est réalisé à l'aide de l'API proposée par JPA, qui apporte une abstraction supplémentaire par rapport à JDBC.\
L'approche choisie est une approche code-first : l'exécution de notre programme à l'aide de JPA permet la création des tables en base de données, à partir des classes objets entités.

Quelques insertions sont ensuite réalisées, et quelques requêtes JPA et JPQL sont faites pour récupérer des enregistrements de la base de données, et les afficher dans la console.

## Pré-requis

### Dépendances
- **JDK Java >= 17.0**
- **Gestionnaire de projet [Maven](https://maven.apache.org/)**
- **Système de Gestion de Base de Données Relationnelles (SGBDR) de votre choix** avec création d'une base de données locale
- **Librairie java associée à votre SGBDR** (par exemple [mariadb-java-client](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client) pour MariaDB)
- **Librairie de l'ORM [Hibernate](https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core)**

Pour les deux librairies, les dépendances transitives sont listées sur la page de la librairie sur Maven Repository (liens précédents). Elles sont déjà listées dans les dépendances du fichier pom.xml du projet.\
Le gestionnaire de projet Maven les installera automatiquement si vous lui passez ces deux dépendances.

### Installation
Il vous faut faire tourner un **serveur local** sur votre machine pour héberger une SGBDR et pouvoir utiliser ce programme.\
Il vous faudra ensuite créer une base de données vide qui sera liée à l'application grâce aux configurations que nous verrons dans la partie suivante. Dans notre application, la base de données est appelée "petstore".

### Configuration
#### Fichier persistence.xml
Ce fichier contient la configuration de base pour le mapping avec JPA.\
Il fournit entre autre les informations de connexion à la base de données.
Les configurations de JPA se font dans un fichier persistence.xml que vous devez inclure et compléter dans votre projet.