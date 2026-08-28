# Consignes de travail pour Engine

## Sources de vérité

- Lire `README.md` avant toute modification : il définit la finalité d'Engine, son architecture en Flow et le workflow d'intégration par comparaison.
- Lire `todos.md` pour connaître les chantiers ouverts. Ne pas réintroduire un objectif ou un contrat absent de ces deux documents sans demande explicite.
- Le code de production est la référence opérationnelle ; `result` représente le plan généré. Lorsqu'une comparaison avec `C:\dev\crud` est demandée, ne prendre comme modèle que les modules et fichiers explicitement désignés pour l'itération.

## Architecture à préserver

- Le DSL décrit l'intention fonctionnelle et structurelle. Le CRUD est un ensemble d'Actions disponibles, pas l'architecture ni la limite du générateur.
- Une `Action` représente un cas d'usage. Elle sélectionne uniquement les `Injection` nécessaires aux couches auxquelles elle contribue.
- Une `Injection` produit la contribution d'une Action à une couche. Un `Flow` construit le contenu et un printer assemble le fichier final.
- `Processor` orchestre les familles de printers. Il ne doit contenir ni logique CRUD transversale ni cas particulier propre à une Action métier.
- Lorsqu'un artefact dépend des Actions, son printer les parcourt et sollicite leurs Injections. Cela concerne notamment les controllers, services, repositories, requests, mappers et les couches MVC frontend.
- Un printer générique ne doit pas reconnaître une liste fermée d'Actions concrètes. Placer une nouvelle règle dans l'Action, l'Injection, le Flow ou le DSL qui en porte la responsabilité.
- Une Action disponible mais absente du DSL du projet ne doit produire aucun code inutile.
- Une Action peut dépendre d'Actions annexes. Lors des travaux de mutualisation, distinguer les occurrences fonctionnelles de la capacité technique partagée et dédupliquer celle-ci par identité sémantique et par portée, pas seulement par nom ou ordre de création.
- Les artefacts structurels directement dérivés d'une `Entity` et de ses `Field` peuvent rester pilotés par l'entité tant qu'ils ne contiennent pas de logique propre à un cas d'usage.

## Code généré et application cible

- Les printers écrivent uniquement sous `result/be` et `result/fe`. Le terme « injection » ne signifie jamais une modification automatique de l'application cible.
- Ne transférer du code vers l'application cible que si la tâche le demande. Le transfert peut être complet pour une première intégration ou sélectif lorsque la production a divergé.
- Ne placer aucun contenu manuel dans `result`. Avant tout nettoyage, préserver ou identifier `G0`, puis générer `G1` afin de permettre la comparaison avec la production `P`.
- Préserver la compatibilité avec les conventions du projet cible plutôt que de chercher une abstraction universelle ou indépendante de la stack.
- Maintenir une sortie adaptée au comparateur : chemins, noms, ordre, imports et formatage déterministes, fins de ligne LF et exactement une fin de ligne finale.

## Méthode de modification

- Inspecter `git status` et le diff avant et après le travail. Préserver toutes les modifications existantes sans rapport avec la tâche et ne pas commit, stage ou push sans demande explicite.
- Procéder par petites itérations centrées sur une responsabilité, une Action, un printer ou un ensemble précis de fichiers générés. Éviter les refactorings adjacents non demandés.
- Pour une règle transversale, identifier d'abord son propriétaire naturel : DSL pour l'exposition, Action pour le cas d'usage, Injection pour une couche, Flow pour le contenu ou printer pour l'assemblage générique.
- Ne pas ajouter de framework de vérification, de préconditions ou de compatibilité préventive. Ajouter uniquement les contrôles directement nécessaires au contrat demandé.
- Adapter la vérification au périmètre : compiler avec `mvn -DskipTests compile`, exécuter `dev.cruding.engine.App` depuis la racine lorsque la génération change, puis comparer uniquement les sorties concernées. Lancer des tests ciblés lorsqu'ils protègent le comportement modifié ; ne pas transformer une petite itération en campagne de tests générale.
- Ne jamais annoncer qu'un résultat est conforme sans distinguer la compilation d'Engine, l'exécution du générateur, la comparaison des fichiers produits et la compilation éventuelle de l'application cible.
