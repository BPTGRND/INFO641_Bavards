# INFO641_Bavards

ePapotage est une application de messagerie instantanée simplifiée qui permet aux utilisateurs de communiquer entre eux via un concierge central. Les utilisateurs peuvent envoyer des messages à travers le concierge, qui les redistribue ensuite à tous les autres utilisateurs connectés.

## Objectifs

L'objectif principal de ce projet est de maîtriser le mécanisme de communication par événements et de s'initier aux interfaces graphiques. Trois niveaux de fonctionnalités sont proposés : "à l'essentiel", "fonctionnelle" et "étendue".

## Fonctionnalités

### Version "À l'essentiel"

- Gestion d'événements : 
  - La classe `PapotageEvent` représente un message avec un sujet et un corps.
  - L'interface `PapotageListener` permet de recevoir des événements de type `PapotageEvent`.
  - La classe `Bavard` est à la fois créatrice et auditrice des événements `PapotageEvent`.
  - La classe `Concierge` agit en tant qu'intermédiaire, redistribuant les messages reçus à tous les utilisateurs connectés.
  - La classe `Batiment` gère les bavards et le concierge.

### Interfaces Graphiques

- Une interface permettant la création et la connexion des bavards.
- Une interface pour le concierge, affichant les messages reçus.
- Chaque bavard a une interface pour envoyer des messages et accéder aux messages reçus.

### Version "Fonctionnelle" (ajout)

- Gestion des utilisateurs connectés : envoi d'événements de connexion et de déconnexion aux autres utilisateurs.

## Technologies utilisées

- Java pour la création de classes.
- [Swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html) pour les interfaces graphiques.

## Installation

1. Clonez ce dépôt sur votre machine :
   ```
   git clone https://github.com/BPTGRND/INFO641_Bavards.git
   ```
2. Ouvrez le projet dans votre IDE Java préféré.
3. Compilez et exécutez l'application.

## Auteur

- [BPTGRND](https://github.com/BPTGRND)

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE.md](LICENSE) pour plus de détails.
