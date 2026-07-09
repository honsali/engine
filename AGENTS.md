# Agent instructions

- This is a Maven/Java project.
- The authoritative project version is the top-level `<version>` in `pom.xml`.
- The project compiles with Java 25.
- Validate changes with `mvn test`.
- The application loads entities first, then discovers exactly one project bootstrap implementing `ProjectBootstrap` under `src/main/java/modules` and calls its `init()` method.
- The RH DSL bootstrap is `modules.rh.RhElementComposer`; it declares modules and page variables in one place.
- Modules are explicit `new Module("ModuleName", "package.path")` instances; module subclasses are not used.
- DSL code should use typed entity lookup (`entity(Employe.class)`) and typed page variables (`pageConsulterEmploye`) instead of string references.
- `ElementComposer.super()` derives the element name from the class name; call `isElement()` for reusable `/element` composers.
- Release commits use the current `pom.xml` version in the format `version <current-version>: <message>`; after a successful push, bump `pom.xml` to the next minor version (`MAJOR.(MINOR + 1).0`).
