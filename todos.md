# Feuille de route Engine

Cette feuille de route ne conserve que les évolutions encore utiles à l'architecture décrite dans le README. Chaque point doit améliorer le DSL, les Actions, les Injections, les Flows ou la comparaison du code généré avec le projet cible.

## 1. [ ] Modéliser les dépendances entre Actions

Permettre à une Action fonctionnelle de déclarer les Actions annexes dont elle dépend. Une action `Print`, `Send` ou `Historique` doit par exemple pouvoir demander un `GetById` sans connaître les printers ni dupliquer elle-même son implémentation technique.

## 2. [ ] Définir l'identité sémantique et la portée des Actions

Identifier une capacité à partir de son opération, de son entité, de ses champs de recherche ou de son parent, de ses paramètres, de ses contrats Request/Response et de sa portée de génération. Cette identité doit permettre de mutualiser une même capacité technique tout en conservant les occurrences visuelles ou les états frontend propres à chaque page.

## 3. [ ] Garder les printers génériques

Faire des printers des assembleurs de fichiers qui parcourent les Actions et sollicitent leurs Injections. Une règle propre à une Action concrète ne doit pas être codée directement dans un printer générique ; elle doit appartenir à l'Action, à une Injection ou à un Flow.

## 4. [ ] Ajouter une phase de finalisation avant la génération

Résoudre les dépendances, les identités, les portées, les routes, les références et les métadonnées avant l'exécution des printers. Cette phase doit stabiliser le modèle de génération sans introduire un framework général de vérifications ou de préconditions.

## 5. [ ] Rendre la génération entièrement déterministe

Garantir un ordre stable pour les entités, modules, pages, Actions, imports et déclarations, ainsi qu'un formatage stable sans contenu dépendant de l'exécution. Une même version d'Engine et un même DSL doivent produire le même résultat afin que les différences restent ciblées et fiables.

## 6. [ ] Gérer les snapshots et les artefacts obsolètes

Proposer un nettoyage explicite de `result` qui supprime les fichiers qu'aucun printer ne génère plus, tout en préservant l'ancien résultat `G0` avant de produire `G1`. Le workflow attendu est : conserver `G0`, nettoyer la sortie, générer `G1`, puis comparer `G0`, la production `P` et `G1`.

## 7. [ ] Tracer la lignée du générateur et les adaptations locales

Conserver la version ou le commit d'Engine utilisé pour chaque projet, un journal court des évolutions locales et la distinction entre amélioration réutilisable et adaptation propre au projet. Cette traçabilité doit permettre de reproduire le plan généré et de décider ce qui mérite de remonter dans une version suivante d'Engine.

## 8. [ ] Construire des recettes DSL composables

Fournir des recettes concises pour les cas fréquents comme le CRUD, les données de référence, les relations parent/enfant, les dialogues et les vues maître/détail, mais uniquement comme compositions d'Actions et de Flows. Chaque recette doit rester personnalisable par surcharge d'Injection, composition explicite ou Action spécialisée.

## 9. [ ] Documenter et versionner les contrats des projets cibles

Décrire les infrastructures backend et frontend attendues par le code généré, notamment les packages, routes, paramètres parent/enfant, contrats Request/Response, pagination, persistance, sécurité et composants partagés. Ces contrats servent de cible de génération sans imposer que la production reste strictement identique à `result`.

## 10. [ ] Valider l'architecture avec une Action non CRUD de référence

Choisir une Action réelle comme `Print`, `Send` ou `Historique` et la faire contribuer de bout en bout aux couches nécessaires du frontend et du backend. Cette référence doit vérifier par compilation, exécution et comparaison que les dépendances annexes sont mutualisées et que l'ajout d'un nouveau cas d'usage ne nécessite pas de spécialiser les printers.
