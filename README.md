# Engine — Personal CRUD Application Generator

`engine` is a personal code-generation tool built for a solo freelance development workflow. Its purpose is simple:

> Describe an application with the smallest readable Java DSL possible, then generate the repetitive database, backend, and frontend code.

The engine is not intended to be a generic, target-neutral generator product. It evolves with the applications it generates. For a new project, the engine can be copied from the latest useful version and adapted to that project's needs.

## Design goals

The project optimizes for:

- **More output from less input** — the DSL should contain intent and exceptions, not boilerplate.
- **Readable specifications** — entities, pages, components, and actions should remain easy to understand.
- **Fast delivery** — repeated full-stack patterns should be generated consistently.
- **Strong conventions** — names and defaults should eliminate unnecessary declarations.
- **Progressive customization** — common cases should be short, while uncommon cases retain low-level escape hatches.
- **Diff-friendly output** — generated files are reviewed with comparator tools before selected changes are applied to the application.
- **Project ownership** — the generator and its backend/frontend targets are developed together and may evolve together.

## One application, two generated runtimes

The generated backend and frontend belong to one application and normally evolve in the same delivery. Their HTTP contract remains explicit and testable, but the engine does not optimize for independently versioned frontend and backend products or hypothetical external consumers.

The ownership boundary is deliberate:

- generated backend candidates place business rules, authoritative validation, authorization, transactions, persistence, and integrity on the backend side; after review and selective transfer, runnable backend code is authoritative;
- generated frontend candidates remain limited to components, layouts, navigation, presentation, interaction state, and API orchestration;
- when one DSL field constraint feeds backend request validation and frontend feedback, backend emission remains authoritative; frontend emission is only an inline UX projection and must not add acceptance logic;
- frontend TypeScript types describe the transport contract without creating a separate browser-side business domain.

A generated frontend controller or Redux model may coordinate a request and its UI state, but it must not mirror backend use cases or decide business outcomes. The backend must remain correct when called without the generated frontend, and its Problem Details are the canonical errors presented by the UI.

## Intended workflow

The normal workflow is:

1. Copy the latest useful engine version for a new project.
2. Adapt the engine when the project introduces a genuinely new pattern.
3. Describe the domain under `src/main/java/model`.
4. Describe modules, pages, UI composition, and actions under `src/main/java/modules`.
5. Run `dev.cruding.engine.App` from the engine project root.
6. Review `result/be` and `result/fe` with comparator tools.
7. Transfer only the desired changes into the runnable backend and frontend applications.
8. Keep the adapted generator with the project so its generated architecture remains reproducible.

`result/` is disposable generated output. It is not the production source tree and should not contain manually maintained code.

## What the engine generates

From the same application specification, the engine can generate coordinated artifacts for the database, backend, and frontend.

### Backend

The backend target is based on Spring, JPA, and Liquibase. Generated artifacts include:

- domain entities;
- DTOs;
- mappers;
- repositories;
- specifications and filters;
- domain-owned reference-data catalogs;
- business services;
- REST resources;
- Liquibase tables, constraints, data headers, and master changelog entries.

Output is written under:

```text
result/be/
```

The backend overlay assumes that the host application provides `app.core.configuration.JsonId`, `app.core.pagination.PageResponse`, `app.core.pagination.PageableUtils`, `app.core.persistence.BaseSpecification`, `app.core.exception.ConflictException`, `app.core.referenceData.ReferenceDataCatalog`, `app.core.referenceData.ReferenceDataDefinition`, global API exception handling for service-level `NoSuchElementException` and `IllegalArgumentException`, and `liquibase/changelog/security_table.xml`. Generated paginated resources return `PageResponse` rather than exposing Spring Data's `Page` contract directly.

Generated DTO identifiers remain `Long` inside Java and receive the host-owned `@JsonId` annotation. The host serializes only these annotated values as JSON strings, matching generated frontend string identifiers without changing JPA or repository ID types.

`JavaFlow` follows the same import groups as VS Code's Java organizer: `java`, `javax`, `org`, `com`, then unmatched packages alphabetically. Generated record components are emitted explicitly one per line; formatter configuration should preserve intentional wrapping rather than requiring trailing comments in generated/runtime comparisons.

Generated REST resources use the entity model package as their API namespace. Entities under `model.rh` therefore receive the class-level prefix `/api/rh`, and generated frontend services append `/rh` to the host application's `API_URL`. Each resource also receives a class-level `@PreAuthorize` using the explicit authority supplied by the project bootstrap. The host must enable Spring method security and enforce the same namespace and authority in its HTTP security configuration so authorization remains stable as entities are added to a domain.

### Frontend

The frontend target is based on React, TypeScript, Redux Toolkit, and Waxant conventions. Generated artifacts include:

- frontend domain models;
- API services;
- pages and reusable elements;
- controllers;
- Redux models and reducers;
- hooks;
- module definitions and page lists;
- actions;
- ACL declarations;
- i18n labels.

Output is written under:

```text
result/fe/
```

The generated trees are overlays for applications that already provide shared runtime infrastructure such as Spring Boot configuration, frontend layout, security, common components, and Waxant integration. These artifacts organize presentation and transport; business behavior and authoritative validation stay in the generated or host backend.

Generated Axios services use normal TypeScript imports rather than `import type`, type the response on the Axios call, destructure `data` into a local variable, and let TypeScript infer the async function return type. They do not duplicate the response type with an explicit `Promise<T>` annotation or return `(await axios...).data` inline. Paginated Redux consumers use null-safe access while their initial shared pagination state may be absent.

A generated page shares aggregate `Req*` and `Res*` interfaces across multiple actions. Strict service inputs such as route identifiers are required in `Req*`, while shared UI values such as `form` and `pageCourante` remain optional. Hooks accept `Partial<Req*>` because router parameters complete the dispatched request; controllers do not add frontend `throw` validation. Backend validation remains authoritative. Results use optional properties rather than `T | {}` unions, since each action populates only its own subset.

## Architectural overview

The engine behaves like a small compiler built around an internal Java DSL:

```text
Java application specification
  ├─ model/**       entities, fields, and relationships
  └─ modules/**     modules, pages, components, and actions
          │
          ▼
Discovery and project bootstrap
          │
          ▼
Shared generation context
 entities → modules/pages → elements/actions → labels
          │
          ▼
Fixed generation pipeline
          │
     ┌────┴────┐
     ▼         ▼
 result/be   result/fe
```

### Main subsystems

| Package | Responsibility |
|---|---|
| `dev.cruding.engine.loader` | Discovers entity classes and the project bootstrap. |
| `dev.cruding.engine.gen` | Holds the generation context, modules, pages, composers, orchestration, and naming helpers. |
| `dev.cruding.engine.entity` | Defines the entity DSL and field factory. |
| `dev.cruding.engine.field` | Defines scalar fields, references, parent relationships, and field presentation options. |
| `dev.cruding.engine.component` | Defines composable UI structures such as forms, tables, details, panels, and buttons. |
| `dev.cruding.engine.element` | Represents generated page elements and reusable UI elements. |
| `dev.cruding.engine.action` | Defines CRUD, navigation, filtering, initialization, and view-only actions. |
| `dev.cruding.engine.injection` | Defines how actions contribute to generated frontend and backend layers. |
| `dev.cruding.engine.flow` | Builds formatted Java, TypeScript, TSX, and XML content. |
| `dev.cruding.engine.printer` | Generates complete files and writes them to `result/`. |

## Generation lifecycle

`dev.cruding.engine.App` coordinates the lifecycle.

### 1. Discover entities

`EntityLoader` scans `src/main/java/model`, loads each `Entity` subclass, and registers it in `Context`.

### 2. Bootstrap the project

`ProjectBootstrapLoader` discovers exactly one concrete `ProjectBootstrap` under `src/main/java/modules` and calls its `init()` method.

The RH example uses:

```text
src/main/java/modules/rh/RhProject.java
```

The bootstrap creates modules, registers pages, selects index pages, and exposes page references used by navigation actions.

### 3. Initialize entities

Entity initialization resolves:

- declared fields;
- identifiers;
- references and parent relationships;
- database names;
- generated paths;
- labels and settings.

All entities are registered before references are resolved.

### 4. Compose pages

Each `ViewComposer<T>` builds a component tree. Nested `ElementComposer` classes define reusable page fragments such as forms, tables, filters, and detail sections.

Actions are created while these trees are composed and are registered in the shared context.

### 5. Initialize actions

Every action selects the contributors needed by the generated layers. A single action may coordinate:

- frontend view behaviour;
- frontend controller behaviour;
- Redux state;
- frontend API services;
- backend REST resources;
- backend business services;
- repository operations.

### 6. Generate artifacts

`Processor` invokes the concrete printers in the required order:

1. global backend files;
2. frontend pages and reusable elements;
3. frontend and backend entity files;
4. frontend module files.

Page and element generation currently occurs before module i18n generation because rendering also collects field and component labels.

## DSL examples

### Entity declaration

Entities extend `Entity` and declare fields using the inherited field factory:

```java
public class Employe extends Entity {
    public final Field matricule = Text("matricule").isId();
    public final Field nom = Text("nom").required();
    public final Field dateNaissance = Date("dateNaissance").filtrable();
    public final Field departement = Ref(Departement.class);
}
```

Relationships use typed entity classes:

```java
public final Field employe = Father(Employe.class);
public final Field departement = Ref(Departement.class);
```

`Text(...)` fields default to a maximum length of 250, which is emitted as backend DTO and filter validation. Field modifiers are copy-on-write, so a required domain field can be reused as an optional filter input with `field.required(false)` without weakening the entity declaration.

Entities can also register database ordering constraints in their constructor:

```java
public Conge() {
    dateOrder("ck_conge_date_order", dateDebutConge, dateFinConge);
}
```

This emits a Liquibase check that permits missing endpoints and otherwise requires the end value to be greater than or equal to the beginning. `ReferenceData` supplies a required `name` field mapped to `libelle` as its identifier; reference entities use the host application's shared reference-data runtime rather than generated per-entity backend resources or frontend services. The engine generates one domain-owned catalog from explicit reference-data entities and entities targeted by `Ref`, `RefMany`, or `Father` relationships. Catalog entries use each entity's identifier field as the display label and allow filtering by `id` plus its to-one reference IDs. All catalog entities must share one top-level model domain, and their case-insensitive reference names must be unique; generation fails otherwise.

### Project bootstrap

Modules and pages are declared in one project bootstrap:

```java
public class RhProject implements ProjectBootstrap {
    @Override
    public String generatedResourceAuthority() {
        return "ROLE_GESTIONNAIRE_RH";
    }

    @Override
    public void init() {
        Module moduleEmploye = new Module("ModuleEmploye", "rh.employe");

        pageFiltrerEmploye = moduleEmploye
            .addPage(new ViewFiltrerEmploye())
            .icon("faUser")
            .isIndex();

        pageConsulterEmploye = moduleEmploye
            .addPage(new ViewConsulterEmploye())
            .pathById();
    }
}
```

### Page composition

Pages use typed entity lookup and component composition:

```java
public class ViewConsulterEmploye extends ViewComposer<Employe> {
    public Component rootComponent() {
        Employe e = entity(Employe.class);

        return section(
            element(new EtatEmploye()),
            actionBlock(
                button(editAction(e, RhProject.pageModifierEmploye)),
                button(backToListAction(e, RhProject.pageFiltrerEmploye))
            )
        );
    }
}
```

## Important conventions

- Entity definitions belong under `src/main/java/model`.
- Project and page definitions belong under `src/main/java/modules`.
- Exactly one project bootstrap must implement `ProjectBootstrap` directly.
- The project bootstrap must return one canonical authority from `generatedResourceAuthority()` for generated business resources; the value must match `ROLE_[A-Z][A-Z0-9_]*`.
- Modules are explicit `new Module("ModuleName", "package.path")` instances.
- Module names start with `Module`.
- Pages are added with `Module.addPage(new ViewComposerSubclass())`.
- `ViewComposer<T>` supplies the page entity through its generic type.
- View class names follow `View<Action><Entity>` conventions.
- A normal frontend module explicitly marks one page with `.icon(...).isIndex()`.
- `ElementComposer` defines reusable generated `/element` components.
- Typed lookup such as `entity(Employe.class)` is preferred over string lookup.
- Duplicate entities, modules, and pages are rejected by `Context`.
- Generated TypeScript literals must be emitted through `TsLiteral` where escaping is required.
- Generated frontend validation is limited to non-authoritative inline feedback; business rules and acceptance validation belong in backend candidates and become authoritative only in the reviewed runnable backend.

## Extension philosophy

The intended customization hierarchy is:

```text
conventions
    ↓
high-level CRUD recipes
    ↓
recipe overrides
    ↓
component and action DSL
    ↓
custom action injection or printer
```

The common case should become increasingly concise, but the existing component/action layer should remain available for project-specific pages.

The engine does not need to stay identical between projects. Useful general improvements can be carried into the next engine version, while highly specific behaviour can remain in the project where it is needed.

## Project structure

```text
.
├── pom.xml
├── src/main/java/
│   ├── dev/cruding/engine/     generator and DSL implementation
│   ├── model/                  application domain specification
│   └── modules/                application/module/UI specification
├── result/
│   ├── be/                     generated backend overlay
│   └── fe/                     generated frontend overlay
└── todos.md                    prioritized improvement backlog
```

## Requirements and execution

- Java 25
- Maven

The authoritative project version is the top-level `<version>` in `pom.xml`. Release commits use `version <current-version>: <message>`; after a successful release push, `pom.xml` advances to the next minor version as `MAJOR.(MINOR + 1).0`.

Validate the project with:

```bash
mvn test
```

Focused tests currently protect reference-catalog determinism, generated resource authority, backend `@JsonId` emission, frontend Axios service conventions, and generated page request/result, hook, and pagination contracts. Add similarly bounded tests when a deterministic generator regression would otherwise be repeated across client projects.

Run `dev.cruding.engine.App` with the engine directory as the working directory. The application expects `src/main/java/model` and `src/main/java/modules` relative to that directory and writes generated files to `result/`.

## Current direction

The main development direction is not generic platform abstraction. It is improving the personal workflow by:

1. generating more from less DSL;
2. adding reusable high-level CRUD recipes;
3. keeping complete escape hatches for exceptional project needs;
4. producing deterministic, comparator-friendly output;
5. making generation phases easier to understand and modify.

See [`todos.md`](todos.md) for the prioritized backlog.
