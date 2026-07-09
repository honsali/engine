# Agent instructions

- This is a Maven/Java project.
- The authoritative project version is the top-level `<version>` in `pom.xml`.
- The project compiles with Java 25.
- Validate changes with `mvn test`.
- The application loads entities first, then discovers exactly one project bootstrap implementing `ProjectBootstrap` under `src/main/java/modules` and calls its `init()` method.
- The RH DSL bootstrap is `modules.rh.RhProject`; it declares modules and page variables in one place.
- Modules are explicit `new Module("ModuleName", "package.path")` instances; module subclasses are not used.
- Pages are added with `Module.addPage(new ViewComposerSubclass())`; `ViewComposer<T>` infers the page entity from its generic type.
- DSL code should use typed entity lookup (`entity(Employe.class)`) and typed page variables (`RhProject.pageConsulterEmploye`) instead of string references.
- `BaseComposer.super()` derives the element name from the class name; `ElementComposer` marks reusable `/element` composers.
- Frontend i18n TypeScript literals are escaped at emission time with `dev.cruding.engine.flow.TsLiteral`; `Context.addLabel` stores raw label keys/values rather than pre-quoted TypeScript fragments.
- Release commits use the current `pom.xml` version in the format `version <current-version>: <message>`; after a successful push, bump `pom.xml` to the next minor version (`MAJOR.(MINOR + 1).0`).
