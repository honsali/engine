# Engine 3.0.0

Engine est un outil de génération de code full-stack piloté par un DSL Java.

Il permet de décrire un domaine, des pages, des composants et des actions, puis de produire le gros œuvre correspondant côté frontend et côté backend.

Sa valeur principale n'est pas de générer du CRUD à partir de templates figés. Elle réside dans son architecture en Flow : les éléments visuels et fonctionnels font apparaître les actions réellement nécessaires, et chaque action contribue transversalement aux différentes couches de l'application.

> Le CRUD est un vocabulaire d'actions fourni par Engine. Ce n'est pas la limite ni l'architecture du générateur.

## Proposition de valeur

Engine cherche à obtenir simultanément :

- un DSL Java court et lisible ;
- du code frontend et backend adapté au projet cible ;
- une génération déterministe et facile à comparer ;
- des actions fonctionnelles extensibles au-delà du CRUD ;
- une séparation claire entre le code proposé par le générateur et le code réellement intégré ;
- un plan initial qui conserve sa valeur lorsque l'application de production évolue.

Engine est volontairement lié aux conventions des applications qu'il aide à construire. Il ne cherche pas à devenir un générateur universel et indépendant de toute cible.

## Modèle mental

Le fonctionnement général est le suivant :

```text
DSL Java
  ├─ model/**       domaine, champs et relations
  └─ modules/**     modules, pages, composants et actions
          │
          ▼
Chargement et Context
Entities · Modules · Pages · Elements · Actions
          │
          ▼
Actions et Injections
          │
          ▼
Flows et Printers
       ┌──┴──┐
       ▼     ▼
 result/be  result/fe
       └──┬──┘
          ▼
Comparateur de code
          │
          ▼
Transfert explicite et sélectif vers l'application
```

Trois niveaux doivent rester distincts :

```text
DSL                    intention fonctionnelle et structurelle
Code généré            plan initial, proposition, gros œuvre
Code en production     plan intégré puis adapté au besoin réel
```

Le code exécuté en production reste la référence opérationnelle. Le code généré reste la référence du plan initial.

Le noyau technique de l'application cible n'a pas besoin d'être décrit dans le DSL. Dans le projet de démonstration actuel, l'authentification JWT et l'administration des comptes restent ainsi dans `crud-be` et `crud-fe`. Engine génère le domaine métier RH ; il ne produit pas un pseudo-modèle de sécurité incomplet ni des champs sensibles dans ses DTO.

## Le DSL Java

Les entités sont décrites sous `src/main/java/model`.

Exemple réel :

```java
public class Conge extends Entity {

    public final Field code = Text("code").isId();
    public final Field typeConge = Ref(TypeConge.class);
    public final Field dateDebutConge = Date("dateDebutConge");
    public final Field dateFinConge = Date("dateFinConge");
    public final Field commentaire = LongText("commentaire");
    public final Field employe = Father(Employe.class);
}
```

Le nom de collection REST est dérivé par défaut du nom d'entité avec un `s`. Une entité dont le pluriel est irrégulier peut le déclarer dans son constructeur :

```java
public class Cheval extends Entity {

    public Cheval() {
        apiCollectionName("chevaux");
    }
}
```

Les routes utilisent ce nom de collection. Une entité enfant est exposée dans le contexte de son parent, par exemple `/api/rh/employes/{idEmploye}/conges`, tandis que ses opérations unitaires utilisent `/api/rh/conges/{id}`. Les packages Java générés sont entièrement en minuscules ; les chemins et identifiants frontend conservent leur camelCase.

Les modules, pages et compositions visuelles sont décrits sous `src/main/java/modules`.

Un composant ne décrit pas seulement son apparence. Il fait également apparaître les opérations nécessaires à son fonctionnement :

```java
table(e,
        e.typeConge,
        e.dateDebutConge,
        e.dateFinConge,
        e.commentaire)
    .fillWith(listAll(e).byFatherId())
    .onRowClick(goToPage(e, RhProject.pageConsulterConge));
```

Ici, le tableau exprime notamment le besoin de lister les congés d'un employé et de naviguer vers la consultation d'un congé.

Le DSL porte donc l'intention. Les actions traduisent cette intention dans les couches techniques.

## L'architecture en Flow

Une action ne génère pas directement un fichier complet.

Elle sélectionne les injections utiles à son cas d'usage. Chaque injection sait contribuer à une couche précise, tandis que les printers assemblent les contributions dans des fichiers cohérents.

| Frontend | Backend |
|---|---|
| View | Controller |
| Controller | Service métier |
| Model Redux | Repository |
| Service HTTP | Request |
|  | Mapper |

Une action peut contribuer à toutes ces couches ou seulement à certaines d'entre elles. Les injections non nécessaires restent vides.

Les artefacts comme le Domain et la Response sont actuellement produits directement à partir de l'Entity et de ses Fields ; ils ne disposent pas d'une Action Injection dédiée en version 3.0.0.

Les Flows construisent le contenu Java, TypeScript, TSX ou XML. Les printers :

- parcourent les entités et les actions ;
- collectent les imports et les contributions ;
- assemblent les fichiers ;
- écrivent uniquement sous `result`.

Un printer ne doit pas contenir une liste fermée de cas d'usage métier. Il doit rester générique et demander aux actions ce qu'elles souhaitent injecter.

## Des actions au-delà du CRUD

`CreateAction`, `UpdateAction`, `DeleteAction`, `FilterAction` ou `GetByFieldAction` sont des actions déjà disponibles. Elles ne constituent pas une frontière.

Lorsqu'un projet introduit un nouveau cas d'usage réutilisable, il peut devenir une action du DSL :

- historiser ;
- imprimer ;
- envoyer ;
- exporter ;
- valider ;
- clôturer ;
- déclencher un traitement propre au métier.

Une nouvelle action :

1. porte une intention fonctionnelle identifiable ;
2. fournit uniquement les injections nécessaires ;
3. est exposée par le DSL ;
4. devient réutilisable dans les composants et les pages ;
5. ne nécessite aucun cas spécial dans les printers existants.

Exemple conceptuel :

```java
// Pseudo-code illustrant une extension possible du DSL.
actionBlock(
    button(historiser(e).byId()),
    button(imprimer(e).byId()),
    button(envoyer(e).byForm().confirm())
);
```

Le code produit reste adapté aux actions effectivement utilisées. Une action disponible dans Engine mais absente du DSL du projet ne doit pas ajouter de code inutile au résultat.

## Actions annexes et mutualisation

Une action principale peut dépendre d'une opération annexe.

Par exemple, imprimer et envoyer un employé peuvent toutes les deux nécessiter sa récupération par identifiant :

```text
Imprimer ───┐
            ├── RecupererParId(Employe)
Envoyer ────┘
```

`Imprimer` et `Envoyer` restent deux usages fonctionnels distincts. En revanche, la même méthode `recupererParId` ne doit être déclarée qu'une seule fois dans chaque portée où elle est partagée.

Il faut distinguer :

- les occurrences d'une action dans les pages et composants ;
- la capacité technique réutilisable produite par cette action ;
- la portée de la contribution générée.

Deux pages peuvent donc conserver deux appels ou deux états d'interface distincts tout en partageant une seule méthode de service frontend, une seule route backend ou une seule méthode de repository.

La clé de mutualisation doit être sémantique. Elle dépend au minimum :

- du type d'opération ;
- de l'entité ;
- des champs ou du parent utilisés pour la recherche ;
- des paramètres ;
- du contrat Request/Response ;
- de la portée de génération.

### État en version 3.0.0

`Context` conserve encore chaque occurrence d'`Action` avec une identité propre. Plusieurs printers évitent déjà les doubles déclarations grâce à `lnameWithoutEntity`, et les requests sont mutualisées par leur nom.

Cette déduplication tardive couvre des cas réels, comme plusieurs usages de `recupererParId`, mais le graphe explicite des actions annexes et leur identité sémantique restent une architecture à consolider.

## Un résultat adapté au projet

Engine génère du code adapté aux conventions et aux besoins de l'application cible :

- structure des packages ;
- routes ;
- contrats ;
- composants ;
- organisation MVC frontend ;
- organisation Controller/Service/Repository backend ;
- infrastructure attendue par le projet.

Ce choix est volontaire. Le résultat n'est pas un framework générique : c'est une proposition directement exploitable pour un projet donné.

Le code généré doit être compatible et compilable une fois intégré à l'infrastructure de la cible. La valeur durable d'Engine se trouve surtout dans le DSL, les actions, les injections, les Flows et la reproductibilité du plan.

## `result` est une proposition, jamais la production

Engine écrit dans :

```text
result/be
result/fe
```

Il n'écrit pas dans les applications finales et ne tente pas de fusionner automatiquement leur code.

Le terme « injection » désigne l'injection d'une contribution dans un artefact généré. Il ne désigne jamais une modification automatique du projet de production.

Le workflow d'intégration reste sous le contrôle du développeur.

### Première utilisation d'une entité

Lorsque l'entité apparaît pour la première fois, le développeur peut copier l'ensemble des fichiers générés vers le frontend et le backend cibles.

### Itérations suivantes

Lorsque le projet a commencé à évoluer manuellement, le développeur :

1. régénère le code ;
2. compare `result` avec l'application ;
3. sélectionne les fichiers, blocs ou lignes utiles ;
4. transfère explicitement ces changements ;
5. conserve les adaptations propres à la production.

Aucun code manuel ne doit être maintenu dans `result`. Le dossier est une sortie reproductible et jetable.

Le moteur écrase les fichiers qu'il produit, mais il ne supprime pas nécessairement un ancien artefact qu'aucun printer ne génère plus. Un fichier obsolète dans `result` doit donc être identifié lors de la comparaison ou lors de la préparation d'une nouvelle référence.

## Le code généré comme plan initial

Après sa mise en production, une application diverge naturellement de son code généré :

- règles métier supplémentaires ;
- intégrations externes ;
- sécurité ;
- optimisation ;
- cas particuliers ;
- refactoring ;
- adaptations demandées par les utilisateurs.

Cette divergence ne retire pas sa valeur au résultat généré. Elle lui donne une nouvelle fonction : celle de plan initial avant travaux.

```text
Application de production - Plan généré = Travail spécifique au projet
```

La comparaison permet de retrouver :

- ce qui appartenait au gros œuvre ;
- ce qui a été ajouté ensuite ;
- ce qui a été remplacé ;
- ce qui est spécifique à l'application ;
- ce qui mérite de remonter dans Engine comme capacité réutilisable.

Une amélioration générique doit être reportée dans le DSL, une Action, une Injection ou un Flow. Une adaptation strictement locale doit rester dans l'application.

Avec une version identifiée d'Engine et le DSL correspondant, le plan reste reproductible. La version 3.0.0 constitue une baseline de ce contrat.

## Refactoring après mise en production

Engine reste utile pour appliquer un changement transversal lorsque l'application est déjà en production.

Le raisonnement utilise trois états :

```text
G0 = ancien résultat généré
P  = code actuel de production
G1 = résultat produit après évolution d'Engine
```

La règle de transfert est simple :

```text
si P == G0
    le fichier peut être remplacé intégralement par G1
sinon
    la différence G0 → G1 doit être reportée manuellement dans P
```

### Exemple : ajouter Springdoc OpenAPI

Supposons que l'application possède 100 controllers et que 20 d'entre eux aient été modifiés depuis leur génération.

Le workflow peut être :

1. comparer l'ancien `result` avec la production ;
2. sélectionner les 80 controllers restés identiques ;
3. modifier Engine pour générer les annotations Springdoc OpenAPI ;
4. régénérer `G1` ;
5. revenir au comparateur sans rafraîchir la sélection ;
6. copier intégralement les 80 controllers sélectionnés ;
7. reporter manuellement les annotations dans les 20 controllers personnalisés.

Le générateur applique ainsi le refactoring à toute la partie non divergée. Le travail manuel est limité aux fichiers qui contiennent réellement des adaptations.

Cette méthode s'applique également à :

- la sécurité ;
- la journalisation ;
- la gestion des erreurs ;
- les conventions de mapping ;
- la pagination ;
- les changements de packages ;
- les imports communs ;
- les annotations techniques ;
- les évolutions de contrats partagés.

Engine agit alors comme producteur d'un changement cohérent à grande échelle, sans devenir un outil d'écriture automatique dans la production.

## Contrat de comparabilité

Le workflow repose sur la qualité des différences produites. Engine doit donc garantir autant que possible :

- des chemins et noms de fichiers stables ;
- un ordre déterministe des entités, actions et déclarations ;
- des imports ordonnés ;
- un formatage stable ;
- des fins de ligne LF ;
- exactement une fin de ligne finale ;
- l'absence de timestamps ou de contenu aléatoire ;
- des changements localisés aux responsabilités concernées ;
- l'absence de reformatage inutile.

Une évolution ciblée d'une Action ou d'une Injection doit produire une différence ciblée. Le bruit de génération réduit directement la capacité du développeur à sélectionner les changements avec confiance.

## Ce qu'Engine génère

Selon le DSL utilisé, Engine peut produire notamment :

### Backend

- entités de domaine ;
- requests et responses ;
- mappers ;
- controllers ;
- services métier ;
- repositories ;
- filtres et specifications ;
- données de référence ;
- fichiers de structure de base de données.

### Frontend

- modèles TypeScript ;
- services HTTP ;
- views et composants ;
- controllers frontend ;
- modèles Redux ;
- hooks ;
- reducers ;
- pages ;
- actions, ACL et libellés.

Tous ces artefacts sont des candidats à intégrer. Le projet cible reste libre de les adapter.

## Cycle de génération

`dev.cruding.engine.App` orchestre le cycle :

1. chargement des entités depuis `src/main/java/model` ;
2. chargement du `ProjectBootstrap` depuis `src/main/java/modules` ;
3. initialisation des entités ;
4. composition et initialisation des pages ;
5. découverte et initialisation des actions ;
6. exécution du `Processor` ;
7. écriture des résultats frontend et backend.

`Processor` orchestre les familles de printers. Les printers concernés par les actions les parcourent ensuite et demandent à leurs injections de contribuer au fichier visé.

## Étendre Engine

Pour ajouter un nouveau cas d'usage transversal :

1. identifier l'intention fonctionnelle ;
2. créer ou spécialiser une `Action` ;
3. lui associer uniquement les injections utiles ;
4. exposer sa création dans le DSL ;
5. identifier les opérations annexes dont elle dépend ;
6. les composer avec les mécanismes disponibles et vérifier leur mutualisation dans chaque portée ;
7. régénérer ;
8. comparer le résultat ;
9. transférer explicitement le code souhaité.

Une modification répétée dans plusieurs fichiers générés doit être placée dans la responsabilité qui la possède :

- Action pour le cas d'usage ;
- Injection pour une contribution de couche ;
- Flow pour la construction du contenu ;
- Printer pour l'assemblage générique d'un type de fichier ;
- DSL pour rendre la capacité disponible au projet.

Un cas métier particulier ne doit pas être codé directement dans un printer générique.

## Structure du projet

```text
.
├── pom.xml
├── src/main/java/
│   ├── dev/cruding/engine/     moteur, DSL, actions, injections et printers
│   ├── model/                  description du domaine
│   └── modules/                modules, pages et composants
├── result/
│   ├── be/                     proposition backend
│   └── fe/                     proposition frontend
└── todos.md                    travaux et évolutions envisagés
```

## Prérequis et exécution

- Java 25 ;
- Maven ;
- Engine 3.0.0.

Compiler le moteur depuis sa racine :

```bash
mvn -DskipTests compile
```

La suite de tests se lance avec :

```bash
mvn test
```

Dans la baseline 3.0.0, `BeDtoPrinterTest` et `BeResourcePrinterTest` sont encore fondés sur d'anciens contrats de génération et doivent être réalignés. Cette commande n'est donc pas encore entièrement verte.

Pour générer, lancer `dev.cruding.engine.App` depuis l'IDE avec la racine d'Engine comme répertoire de travail. Le `pom.xml` ne déclare pas actuellement de plugin d'exécution Maven et ne fournit donc pas de commande CLI autonome garantie.

## Non-objectifs

Engine :

- n'injecte pas de code dans l'application finale ;
- ne fusionne pas automatiquement génération et production ;
- ne cherche pas à maintenir les deux arbres strictement identiques ;
- ne remplace pas la décision du développeur ;
- n'est pas limité au CRUD ;
- n'a pas vocation à être neutre vis-à-vis du projet cible ;
- ne perd pas son intérêt lorsque la production diverge.

L'objectif est de produire un plan full-stack cohérent, reproductible, comparable et suffisamment précis pour accélérer aussi bien la construction initiale que les évolutions futures.
