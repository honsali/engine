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
│   └── modules/                composition du projet, modules, pages et composants
│       ├── ProjectBootstrap.java   point d'assemblage unique du projet
│       ├── admin/AdminModule.java  pages du domaine administration
│       └── rh/RhModule.java        pages du domaine RH
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

    public final Field code = Text().isId();
    public final Field typeConge = Ref(TypeConge.class);
    public final Field dateDebutConge = Date();
    public final Field dateFinConge = Date();
    public final Field commentaire = LongText();
    public final Field employe = Father(Employe.class);
}
```

`Entity.init()` collecte les champs publics de type `Field` du modèle et de ses bases métier, notamment `ReferenceData.name`. Les champs techniques déclarés par `Entity` (`id_`, `father`, `setting`) sont exclus de cette collecte. L'identifiant technique par défaut est initialisé explicitement ; un `Setting` déclaré dans le DSL reste pris en compte.

Les fabriques de champs simples ne prennent pas de nom :

```java
public final Field code = Text().isId();
public final Field nom = Text().required().minLength(3).maxLength(150);
public final Field dateNaissance = Date().filtrable();
```

Le constructeur configure le type ; `Entity.init()` déduit ensuite le nom de l'attribut Java lorsqu'il n'a pas été fourni. Ainsi, `dateNaissance` donne `lname = "dateNaissance"`, `uname = "DateNaissance"` et `dbName = "date_naissance"`. Les champs hérités suivent la même règle. Les copies créées par les méthodes fluides fonctionnent avant cette attribution ; `onChange()` attend également le nom lorsqu'il manque.

Le nom explicite passe uniquement par la méthode existante `lname(...)` : `Field name = Text().lname("libelle")` pour un alias, ou `Text().lname("password")` pour un champ propre à un formulaire, qui n'est pas collecté par `Entity.init()`. Les constructeurs et fabriques prenant le nom en argument sont supprimés. Un alias scalaire explicite n'est pas remplacé par le nom Java. `Ref(Cible.class)` et `Father(Cible.class)` conservent leur convention de nommage par l'attribut Java.

L'option de rendu d'une liste statique se déclare séparément : `StaticList().type("radioVertical")`, ou `StaticList().type("radioVertical").lname("choix")` hors d'une entité. `type(...)` crée une copie, comme les autres personnalisations du champ.

`Setting` conserve la convention d'identifiant technique de la cible actuelle : `id`, de type Java `Long`, SQL `bigint` et TypeScript `string`, auto-généré côté backend. Il porte aussi le libellé de l'entité et ses accords grammaticaux. Une autre stratégie d'identifiant demande une adaptation cohérente du moteur et du core cible, pas l'activation d'une option du DSL existant.

Un choix explicite comme `Setting().vowel().label("Établissement")` est conservé à l'initialisation ; sinon, l'accord par défaut est déduit du nom de classe. `Setting.readOnly()` utilise le même indicateur que les autres champs : il rend le champ technique non modifiable dans un formulaire et l'exclut de sa Request d'écriture. Il ne rend pas toute l'entité non modifiable.

Une entité peut déclarer plusieurs `Ref`, y compris vers la même cible, mais au plus un `Father`. Deux déclarations de `Father`, même réparties dans la hiérarchie d'héritage, lèvent une `EntityInitializationException` qui indique l'entité et les deux champs concernés. Ces règles sont couvertes par `EntityInitializationTest`.

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
    .onRowClick(goToPage(e, RhModule.pageConsulterConge));
```

Ici, le tableau exprime notamment le besoin de lister les congés d'un employé et de naviguer vers la consultation d'un congé.

Pour les conteneurs configurés, les options peuvent précéder les enfants :

```java
Employe e = entity(Employe.class);
return primaryPanel()
    .title("employe")
    .width("500px")
    .content(
        detail(e, e.matricule, e.dateEntree)
    );
```

Cette forme est disponible sur la famille `Container` (blocs, panneaux, sections et onglets). `content(...)` remplace la liste des enfants ; il ne les ajoute pas à ceux déjà présents. Sur `InColumn`, chaque appel à `column(Component)` accepte un seul composant et ajoute une colonne à la suite des précédentes : `.column(a).column(b)`. Pour regrouper plusieurs composants dans une même colonne, utiliser par exemple `.column(block(a, b))`. Un composant `null` est refusé. Les formes courtes comme `block(a, b)` et `inColumn(a, b)` restent disponibles et construisent le même arbre. Les formulaires et tableaux conservent leurs arguments `Field`, et les conditions leurs branches explicites dès la construction.

`Container<T>` conserve le type concret pendant le chaînage : chaque conteneur déclare son propre type, par exemple `ExtendedPanel extends Container<ExtendedPanel>`. Ainsi, `extendedPanel().title("employe").open().content(...)` reste un `ExtendedPanel`. Ce changement de typage reste limité aux conteneurs ; il ne se propage pas à `Component`, aux vues ni aux actions.

Les attributs de présentation et leurs méthodes fluentes appartiennent uniquement aux composants qui les rendent. `Container` ne stocke ni `title`, ni `width`, ni `margin`, ni `background` ; il conserve la composition et un helper protégé de rendu des titres. Par exemple, `Block` expose `width`, `margin` et `background`, `Section` expose `title` et `margin`, et `InlineBlock` n'expose aucune de ces options. Un appel non pris en charge échoue donc à la compilation. `filterPanel(e)` n'accepte plus le booléen historique sans effet ; `DialogAction.width(...)` transmet la largeur demandée.

Les options de présentation peuvent être déplacées avant `content(...)` sans changer l'arbre. En revanche, les appels construisant des actions ou des éléments s'exécutent immédiatement : déplacer un `actionBlock(button(...))` peut changer leur ordre d'enregistrement. Les compositions de référence conservent cet ordre, ainsi que l'appel à `element(filtre)` avant la lecture de `filtre.action`.

Les marqueurs `//` du DSL préservent les coupures de lignes et la lecture de la hiérarchie face au formateur automatique. Ils font partie de la convention d'écriture et ne doivent pas être supprimés comme des commentaires inutiles.

`Component.addContent()` porte le cycle commun du rendu : préparation du parent et des indicateurs de rendu, collecte des imports et du script, puis encadrement de l'expression racine. Il délègue le contenu à `addBody(ViewFlow, int)`, dont le comportement par défaut produit l'ouverture, parcourt les enfants et produit la fermeture.

`Condition` spécialise `addBody()` pour organiser ses branches et leurs délimiteurs. `InColumn` contient des nœuds internes `Column`, chacun portant un seul composant et sa largeur éventuelle ; leur rendu utilise le parcours commun. Les autres composants conservent leurs spécialisations `addImport`, `addScript`, `addOpenTag` et `addCloseTag`. L'indentation est calculée par `Component.indent(level)` sans tableau de profondeur fixe, avec les mêmes espaces qu'auparavant.

À la construction, les conteneurs ordinaires ignorent les enfants `null` et conservent l'ordre des autres composants. Une liste d'enfants `null` est traitée comme une liste vide. `TabMenu` applique ce traitement aux onglets explicites qu'il reçoit. `Condition` et `InColumn` refusent en revanche les enfants `null` avec une `IllegalArgumentException` explicite : les positions portent respectivement le sens des branches et celui des colonnes.

Sans configuration explicite, `inColumn(...)` utilise une répartition par défaut sur deux colonnes, soit `span={12}` par enfant. Les trois signatures de `column(...)` acceptent chacune un seul composant :

```java
inColumn().columnNumber(2).column(a).column(b);      // Répartition égale : 12/24 chacune
inColumn().column(16, a).column(8, b);               // Span propre à chaque colonne
inColumn().column("400px", a).column("auto", b);     // Flex propre à chaque colonne
```

`columnNumber(n)` doit être positif et définit uniquement la largeur des colonnes sans largeur explicite (`24 / n`, calculée au rendu). Il ne limite pas le nombre d'enfants et ne remplace ni les spans ni les valeurs flex déjà associés à des colonnes. Les trois formes peuvent cohabiter. Les méthodes globales `spans(...)` et `flex(...)` sont supprimées : il n'y a plus de listes parallèles de largeurs et de composants à synchroniser.

Un onglet porte explicitement son titre et sa clé, indépendamment du nom de son contenu :

```java
tabMenu(//
    tab("employe").content(//
        block().margin("20px").content(contenuEmploye)//
    ),//
    tab("conge").content(contenuConge)//
);
```

`tabMenu(Tab...)` accepte uniquement des onglets, sans envelopper implicitement les composants à partir de leur `name`. `Tab` et `Block` restent deux descendants distincts de `Container` ; le bloc est contenu dans l'onglet, il n'en hérite pas.

Dans `Section`, `statePanel()` et `actionBlock(...)` sont exclusifs : le second appel incompatible lève une `IllegalArgumentException`, quel que soit l'ordre des appels, sans modifier la configuration précédente.

Une `Condition` simple (`siVrai`, `siFaux` ou un prédicat `util`) attend exactement un enfant ; `siVraiFaux` attend exactement deux enfants, dans l'ordre vrai puis faux. Un autre nombre d'enfants lève une `IllegalArgumentException` dès la construction. Pour afficher plusieurs composants dans une même branche, les regrouper dans `block(...)`.

Le contrat des conditions reste une valeur simple (`pret`, `etat.succes`, `liste`), pas une expression composée avec des opérateurs. Elles peuvent être la racine d'un élément ou être imbriquées : les accolades JSX ne sont émises que dans un parent JSX ; une condition enfant d'une autre condition est parenthésée.

Les personnalisations de champs comme `required(...)`, `label(...)` et `width(...)` créent des copies : elles conservent la nature et le rendu du champ, sans modifier l'original ni les variantes déjà créées. Une spécialisation de rendu doit fournir un `initCopy()` adapté ; `makeCopy()` reprend les propriétés communes et, lorsqu'il est redéfini, les propriétés propres au sous-type. Le contrat est protégé par `FieldCopyTest` pour `Text`, `ArabicText`, `Hour`, `Hidden`, `TextArray`, `Tag` et `Setting`.

Les copies de `Ref` et de `Father` conservent également leur cible déjà résolue, ainsi que leurs noms de rôle et métadonnées SQL. La conversion d'un `Ref` en `RefList` et les copies suivantes suivent ce même contrat, couvert par `RefFieldCopyTest`. L'entité référencée reste partagée ; elle n'est pas dupliquée avec le champ.

Les méthodes `maxLength(int)` et `minLength(int)` prennent des entiers, par exemple `.minLength(3).maxLength(150)`. Les métadonnées correspondantes sont des `Integer` ; `null` signifie qu'aucune limite n'a été déclarée.

Pour `Text`, `ArabicText`, `Email`, `Tel` et `StaticList`, la longueur maximale vaut `250` par défaut et détermine aussi la taille SQL : `Text().maxLength(500)` produit une colonne `nvarchar(500)` et une validation `@Size(max = 500)` dans la Request. Ces variantes réutilisent le comportement de `Text` et conservent leurs rendus propres ; une `StaticList` obligatoire conserve sa validation `@NotNull`, distincte du `@NotBlank` d'un champ texte. Une copie d'un champ limitée à `100` dans un formulaire restreint la validation de ce cas d'usage sans réduire la colonne du modèle. `LongText().maxLength(1000)` conserve le type SQL `text` ; la limite concerne sa validation.

`Hour` produit un type Java `LocalTime` et un type SQL `time`. Les imports des entités et des réponses suivent le type temporel du champ, y compris pour un champ transitoire ; `Date` et `Year` conservent `LocalDate` et le type SQL `date`.

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

1. initialisation du singleton par `Context.init()` ;
2. appel à `EntityLoader.load(EnginePaths.modelPath.toString())` pour charger les entités depuis `src/main/java/model` ;
3. appel direct à `modules.ProjectBootstrap.init()` pour assembler les modules ;
4. initialisation des entités ;
5. composition et initialisation des pages ;
6. découverte et initialisation des actions ;
7. exécution du `Processor` ;
8. écriture des résultats frontend et backend.

Les chemins sont définis dans [EnginePaths](src/main/java/dev/cruding/engine/EnginePaths.java), à côté d'`App` : `sourceRoot` désigne `src/main/java`, `modelPath` est dérivé par `sourceRoot.resolve("model")`, et `outputRoot` désigne `result` par défaut. Les chemins par défaut sont absolus et normalisés depuis le répertoire de travail. `sourceRoot` et `modelPath` sont des constantes ; `outputRoot` peut être affecté avant une génération. Les printers le lisent directement et `LoaderUtils` utilise `sourceRoot` pour résoudre les noms de classes, sans posséder de configuration de chemins.

Engine est utilisé manuellement pour une génération à la fois. `Context.init()` remplace l'instance courante par un contexte neuf, puis `Context.getInstance()` donne accès à ce singleton. Les registres d'entités, modules, pages, libellés et actions ainsi que le compteur d'actions repartent ensemble d'un état vide. Les chemins restent indépendants de cette réinitialisation. Le constructeur est privé ; il n'y a plus de contexte à fournir aux loaders, modules, actions ou printers.

Le constructeur d'`Action` appelle une seule fois `Context.getInstance().addAction(this)`. Cette méthode attribue `action.id` avant l'insertion dans le `LinkedHashSet` et gère elle-même le compteur. `equals` et `hashCode` restent fondés sur l'id ; cet id ne doit donc plus être modifié après l'enregistrement. Le champ est public pour permettre cette affectation directe par le contexte, sans méthode séparée de réservation d'identifiant.

Une initialisation marque le début d'une nouvelle génération, pas un changement de contexte en cours de traitement. Les objets de la génération précédente ne doivent pas être réutilisés. Le moteur ne prend pas en charge des générations concurrentes dans une même JVM. Les tests de génération qui n'appellent pas `App` commencent leur scénario par `Context.init()` et s'exécutent séquentiellement, comme le précise `src/test/resources/junit-platform.properties`. Les tests de printers affectent `EnginePaths.outputRoot` à leur dossier temporaire et restaurent sa valeur après chaque scénario.

Le projet possède un seul bootstrap concret, placé par convention dans `src/main/java/modules/ProjectBootstrap.java`. `App` le référence directement : il n'y a ni interface de bootstrap ni recherche de son implémentation dans les sources. Sa méthode statique `init()` appelle `AdminModule.init()`, puis `RhModule.init()`. L'ajout ou le retrait d'un module se fait explicitement à cet endroit.

Chaque module construit ses propres `Module` avec `new Module(...)` et y déclare ses pages. `RhModule` ne compose pas l'administration.

Le nommage SQL est conventionnel : tables et colonnes en snake_case, suffixe `_id` pour les références et colonne technique `id`. Engine ne maintient plus de table de correspondance vers un schéma existant. `Role` produit donc une table `role` ; une adaptation comme `app_role` reste un changement manuel dans `crud-be`, à préserver lors de l'intégration par comparaison.

Les références de pages partagées par le DSL utilisent des `PageRef` immuables déclarées dans leur module ; elles sont résolues dans le `Context` courant au moment de composer les actions.

`Action.targetPage(PageRef)` effectue directement cette résolution, par exemple `.targetPage(RhModule.pageConsulterEmploye)`. Les helpers `addAction`, `editAction`, `backToListAction` et `backToDetailAction` lui transmettent la référence ; les composants et injections utilisent ensuite la `Page` résolue.

`goToPage(Entity, PageRef)` transmet également la référence à `GoToPageAction`, qui réutilise `targetPage(PageRef)`. `Section.backPage(PageRef)` résout sa page de retour selon la même convention. Ces points d'entrée du DSL n'acceptent plus directement de `Page` ; les objets internes conservent la définition résolue pour produire les imports et la navigation.

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
