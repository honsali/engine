# Engine

Engine sert à construire le gros œuvre d'applications CRUD-like : des applications où l'on consulte, recherche, crée et modifie des données, avec des parcours et des traitements métier qui peuvent aller au-delà du CRUD.

Son intention est de rendre une manière de construire des applications explicite, réutilisable et modifiable. On décrit le domaine et les usages dans un DSL Java ; le moteur produit une structure cohérente côté frontend et backend. Le développeur peut ensuite s'approprier cette structure et faire évoluer librement l'application.

Le résultat généré donne une forme au projet et conserve le plan de base à partir duquel comprendre ses transformations.

## Un moteur fait pour être modifié

Adapter Engine à la technologie, à l'architecture et aux conventions d'un projet fait partie de son usage normal. L'ambition est de pouvoir construire des applications CRUD-like avec des cibles techniques différentes en faisant évoluer le moteur.

Cette adaptabilité repose sur des responsabilités identifiables : composition des composants, Actions, Injections, Flows et printers. Selon le changement recherché, on modifie le rendu d'un composant, la contribution d'une action, l'assemblage d'une couche ou l'organisation des couches elles-mêmes.

La cible actuellement implémentée est Java/Spring côté backend et React/TypeScript/Waxant côté frontend. Le passage à une autre cible demande un travail sur ces responsabilités ; la prise en charge de toutes les technologies par simple configuration n'est pas une capacité actuelle. Chaque adaptation vise du code directement exploitable dans les conventions du projet choisi.

## Deux mécanismes structurent la génération

### La hiérarchie des composants frontend

Une page se compose de sections, blocs, onglets, formulaires, tableaux et boutons. Ces composants peuvent contenir d'autres composants ; leur parcours construit l'interface et rassemble les contributions nécessaires à son fonctionnement.

Cette hiérarchie exprime à la fois la forme de l'écran et les usages qu'il propose. Un tableau peut demander la liste des congés d'un employé, et un bouton déclencher une création ou une navigation. Modifier un composant réutilisé permet de faire évoluer une convention d'interface dans les écrans concernés.

### Les actions qui contribuent aux différentes couches

Une Action représente un cas d'usage. Ses Injections alimentent les Flows des couches auxquelles elle participe ; les printers assemblent ces contributions en fichiers. Une seule intention fonctionnelle peut ainsi produire des éléments cohérents dans l'interface, l'état frontend, les appels HTTP, les contrôleurs, les services et la persistance.

```text
DSL : domaine + hiérarchie de composants
                    │
                    ▼
             Actions nécessaires
                    │
               Injections
             ┌──────┴──────┐
             ▼             ▼
       Flows frontend   Flows backend
             │             │
          Printers      Printers
             │             │
         result/fe     result/be
```

Le CRUD fournit un premier vocabulaire d'Actions. Des usages comme imprimer, exporter ou historiser peuvent devenir de nouvelles Actions lorsqu'un besoin réel justifie leur construction. Les dépendances entre Actions et leur mutualisation sémantique restent des [chantiers ouverts](todos.md).

Les printers restent des assembleurs génériques vis-à-vis des cas d'usage : chaque Action porte ses contributions, et seules les Actions utilisées doivent produire du code fonctionnel. Les artefacts structurels, comme les entités et les réponses, peuvent être construits directement depuis le domaine. `Processor` orchestre les familles de printers.

## Une application, un socle commun et du gros œuvre

[crud-fe](../crud-fe/README.md) et [crud-be](../crud-be/README.md) constituent ensemble l'application RH de référence. Leurs contrats sont conçus autour des mêmes parcours utilisateur.

Chaque application cible associe :

- un core réutilisable, qui fournit les mécanismes communs de sa stack ;
- une partie applicative issue du DSL et du générateur ;
- les adaptations ajoutées au fil des besoins du projet.

Le core est destiné à être repris d'une application à l'autre partageant les mêmes conventions. Il apporte notamment les fondations UI côté frontend, ainsi que la persistance, les erreurs et la sécurité côté backend. Engine produit le code qui s'appuie sur ce socle.

La démonstration couvre les départements, la recherche paginée d'employés, leurs congés et une base d'administration des comptes. Le traitement des mots de passe et les adaptations de sécurité restent dans l'application exécutable.

## Le code généré donne une forme au projet

Le gros œuvre installe des repères : organisation des fichiers, découpage des couches, parcours de pages et conventions répétées. Un développeur qui connaît cette forme retrouve plus facilement où intervenir lorsqu'il découvre un nouveau module ou une autre application construite de la même manière.

Après intégration, l'application reçoit ses règles métier, ses traitements spécifiques et ses adaptations. Ces différences font partie de son évolution normale. Le code exécuté devient la référence opérationnelle ; le résultat généré conserve la référence du plan de base.

Comparer les deux revient à comparer le plan actuel d'une maison à son plan d'origine : les ajouts, remplacements et transformations deviennent visibles. Le diff sert donc autant à comprendre le travail propre au projet qu'à intégrer une évolution du moteur.

## Quand utiliser cette approche

- Pour démarrer une démonstration, un POC ou une première application avec un socle CRUD cohérent.
- Pour ajouter un module qui suit les conventions déjà retenues par l'équipe.
- Pour transformer un besoin récurrent en composant ou en Action réutilisable.
- Pour adapter la génération à une nouvelle stack ou à une autre organisation des couches.
- Pour reporter une évolution transversale dans une application qui a déjà été personnalisée.

Un besoin isolé peut être développé directement dans l'application. Il remonte dans Engine lorsqu'il représente une capacité ou une convention que l'on souhaite reproduire.

## Comment travailler avec Engine

### À t = 0 : reprendre le gros œuvre

On prépare le core cible, on décrit les besoins dans le DSL et on examine les fichiers produits dans `result/be` et `result/fe`. Lors de la première intégration d'un module, les fichiers compatibles avec ce socle peuvent être repris tels quels dans l'application.

Les adaptations propres à la cible sont réalisées dans l'application. Aucun contenu manuel n'est maintenu dans `result`, afin de conserver une référence reproductible.

### Ensuite : choisir dans le diff

Le développeur fait évoluer l'application et régénère lorsqu'il souhaite bénéficier d'un changement du DSL ou du moteur. Dans son outil de comparaison, il sélectionne les fichiers, blocs ou lignes qu'il veut reprendre.

Engine écrit dans `result`. Le transfert vers l'application reste explicite et sous le contrôle du développeur ; les personnalisations locales sont conservées.

### Conserver le bon plan de référence

Pour distinguer les adaptations de l'application des évolutions d'Engine, on conserve trois états :

| État | Rôle |
|---|---|
| `G0` | Ancien résultat généré, référence avant modification |
| `P` | Application actuelle, avec ses adaptations |
| `G1` | Nouveau résultat généré |

Si un fichier de `P` est identique à `G0`, il peut être remplacé par `G1`. S'il a été personnalisé, le développeur reporte les changements utiles de `G0 → G1` dans `P`.

Par exemple, pour faire évoluer une convention dans cent contrôleurs dont vingt ont été adaptés, les quatre-vingts restés identiques peuvent être repris intégralement. Les vingt autres sont traités sélectivement dans le comparateur.

Ce workflow demande des sorties stables : chemins, noms, ordre, imports, formatage, fins de ligne LF et un unique saut final. Il demande aussi de conserver ou de pouvoir reproduire `G0` avant une nouvelle génération. La gestion automatisée des snapshots, la traçabilité des versions et le déterminisme complet restent à consolider ; la conservation de la référence fait aujourd'hui partie du travail d'intégration.

## Pour poursuivre

- [Guide de développement](DEVELOPMENT.md) : installation, exécution, DSL, contrats de génération et points d'extension.
- [Feuille de route](todos.md) : capacités à consolider et intentions encore à implémenter.
- [Application frontend](../crud-fe/README.md) et [application backend](../crud-be/README.md) : usages et arbitrages de la cible actuelle.
