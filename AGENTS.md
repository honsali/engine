# Agent instructions

- This is a Maven/Java project.
- The authoritative project version is the top-level `<version>` in `pom.xml`.
- The project compiles with Java 25.
- Validate changes with `mvn test`.
- Release commits use the current `pom.xml` version in the format `version <current-version>: <message>`; after a successful push, bump `pom.xml` to the next minor version (`MAJOR.(MINOR + 1).0`).
