# Développer et utiliser Engine

Le [README](README.md) présente l'intention du moteur, ses cas d'usage et le workflow d'intégration par comparaison. Ce guide rassemble les détails techniques de la cible actuelle. La version courante d'Engine est déclarée dans [pom.xml](pom.xml).

## Prérequis et exécution

Prérequis : Java 25 et Maven. Exécuter les commandes depuis la racine d'Engine.

Compiler le moteur :

```bash
mvn -DskipTests compile
```

Exécuter la suite de tests :

```bash
mvn test
```

Pour générer, lancer `dev.cruding.engine.App` depuis l'IDE avec la racine d'Engine comme répertoire de travail. Le `pom.xml` ne déclare pas de plugin d'exécution Maven ; aucune commande CLI autonome de génération n'est fournie comme contrat du projet.

La compilation d'Engine, ses tests, l'exécution du générateur, la comparaison de ses sorties et la compilation des applications cibles sont des vérifications distinctes. Adapter les vérifications aux couches modifiées et rapporter celles qui ont effectivement été exécutées.

Avant de régénérer ou de nettoyer `result`, préserver ou identifier l'ancien résultat `G0`. Le moteur écrase les fichiers qu'il produit et peut laisser des artefacts devenus obsolètes. La gestion des snapshots et de ces artefacts est suivie dans [todos.md](todos.md).

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
├── README.md                   intention et workflow d'intégration
├── DEVELOPMENT.md              exécution, DSL et détails techniques
└── todos.md                    travaux et évolutions envisagés
```

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

Une page utilise `pathById()` pour la route simple dérivée de son entité. Les parcours parent/enfant peuvent déclarer leur route explicitement ; chaque paramètre `:id...` est repris dans le `toPath` TypeScript généré :

```java
moduleEmploye.addPage(new ViewCreerConge())
    .route("/rh/employe/:idEmploye/creer");
moduleEmploye.addPage(new ViewConsulterConge())
    .route("/rh/employe/:idEmploye/conge/consulter/:idConge");
```

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

Pour `CreateAction` et `UpdateAction`, le contrat Request est dérivé des champs effectivement présents dans les `Form` associés à l'action, et non de tous les champs de l'`Entity`. Les champs répartis dans plusieurs formulaires sont réunis dans leur ordre de déclaration ; un champ déclaré `readOnly()` n'appartient pas au contrat d'écriture. Les validations déclarées sur les copies utilisées par le formulaire sont conservées. Un champ typé propre au formulaire peut donc enrichir la Request sans devenir automatiquement une propriété persistée.

Pour les champs qui appartiennent à l'`Entity`, les contrôles d'unicité, les références résolues et le mapper utilisent le même sous-ensemble. Lors d'une modification, les propriétés absentes du formulaire conservent leur valeur courante.

Une action construite sans aucun `Form`, notamment dans un usage programmatique sans interface, conserve par compatibilité le contrat complet de l'entité hors relation parent.

Dans le frontend généré, l'instance Ant Design reste dans les vues et les hooks. Le hook extrait les valeurs avant dispatch : `request` pour une commande ou une recherche, `filtre` pour un filtrage. Il valide les formulaires de commande, tandis que le filtrage conserve une simple lecture des valeurs. Les `Mdl*` et `Ctrl*` ne reçoivent que les données ; ils n'importent pas `FormInstance` et ne lisent pas le formulaire. Les injections d'actions portent cette adaptation, sans spécialiser les printers par cas d'usage.

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

Les artefacts comme le Domain et la Response sont actuellement produits directement à partir de l'Entity et de ses Fields ; ils ne disposent pas d'une Action Injection dédiée dans l'implémentation actuelle.

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

### État actuel

`Context` conserve encore chaque occurrence d'`Action` avec une identité propre. Plusieurs printers évitent déjà les doubles déclarations grâce à `lnameWithoutEntity`, et les requests sont mutualisées par leur nom.

Cette déduplication tardive couvre des cas réels, comme plusieurs usages de `recupererParId`, mais le graphe explicite des actions annexes et leur identité sémantique restent une architecture à consolider.

## Cycle de génération

`dev.cruding.engine.App` orchestre le cycle :

1. chargement des entités depuis `src/main/java/model` ;
2. chargement du `ProjectBootstrap` depuis `src/main/java/modules` ;
3. initialisation des entités ;
4. composition et initialisation des pages ;
5. découverte et initialisation des actions ;
6. exécution du `Processor` ;
7. écriture des résultats frontend et backend.

Chaque exécution crée son propre `Context`, puis le transmet explicitement aux loaders, au `ProjectBootstrap`, au `Processor` et aux printers. Les entités, modules, pages, actions, identifiants internes et mappings de noms SQL appartiennent ainsi à une seule génération. Un objet rattaché à un autre `Context` est refusé afin d'éviter qu'une exécution pollue la suivante.

Le bootstrap expose `init(Context context)` et construit ses modules avec `new Module(context, ...)`. Les références de pages partagées par le DSL utilisent des `PageRef` immuables ; elles sont résolues dans le `Context` courant au moment de composer les actions.

`Processor` orchestre les familles de printers. Les printers concernés par les actions les parcourent ensuite et demandent à leurs injections de contribuer au fichier visé.

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

## Socle attendu par les fichiers générés

La cible actuelle attend Java/Spring pour le backend et React/TypeScript/Waxant pour le frontend. Adapter la stack ou l'architecture implique de modifier les composants, Injections, Flows, printers ou leur orchestration selon la responsabilité concernée.

Le DSL couvre le domaine RH ainsi que la structure commune de `Account`, `Role` et des écrans d'administration. `Role` est une référence en lecture seule dont le `libelle` porte directement l'autorité préfixée par `ROLE_`.

Un mot de passe peut être déclaré comme champ de formulaire pour le contrat de création d'Account, sans devenir une propriété persistée de l'entité DSL ni apparaître dans les réponses générées. L'authentification JWT, l'encodage et la réinitialisation des mots de passe restent des adaptations du backend et du frontend exécutables.

Les fichiers backend Account générés ne sont pas transférés sur l'implémentation de sécurité de `crud-be`. Les contrats techniques de la cible sont détaillés dans les guides [backend](../crud-be/DEVELOPMENT.md) et [frontend](../crud-fe/DEVELOPMENT.md).

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
