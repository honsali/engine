# Engine TODOs

This backlog is organized around the engine's real purpose: maximize solo delivery speed by expressing the minimum readable DSL and generating the maximum useful full-stack code.

## Guiding rules

- Optimize for the author's workflow, not for a generic generator market.
- Prefer convention and inference over repeated declarations.
- Keep the common case short and the exceptional case possible.
- Preserve the current Java DSL and its typed references.
- Keep generated output deterministic and easy to review with comparator tools.
- Avoid abstractions that do not reduce delivery or maintenance time.

---

## Established generated contracts

- [x] Backend DTO IDs remain Java `Long` and receive host-owned `@JsonId` for JSON string serialization.
- [x] Generated frontend IDs remain TypeScript `string`, including URL parameters.
- [x] Generated business resources and services share explicit domain namespaces such as `/api/rh` and a configured canonical authority.
- [x] Current Axios services use normal imports, response generics, `const { data }`, separate returns, and inferred async return types.
- [x] Java import groups match the VS Code organizer to avoid save-time comparator churn.
- [x] Focused tests cover the highest-leverage current DTO, resource, catalog, frontend-service, and page-contract rules.
- [x] Generated page requests require strict service identifiers, keep shared `form`/`pageCourante` values optional, and expose `Partial<Req*>` hook inputs completed by route parameters without frontend validation throws.
- [x] Generated page results use optional action-specific properties instead of `T | {}`, and paginated consumers remain null-safe.

# Priority 1 — Generate more with less DSL

## 1.1 Add a high-level recipe layer

1.1.1 [ ] Identify the page and action patterns repeated across projects.
1.1.2 [ ] Add a `standard CRUD` recipe that derives list, detail, create, update, and delete flows from an entity.
1.1.3 [ ] Add a `filterable CRUD` recipe with filter initialization, pagination, table population, and standard navigation.
1.1.4 [ ] Add a `reference data` recipe for code/label entities.
1.1.5 [ ] Add a `parent/child` recipe for entities using `Father` relationships.
1.1.6 [ ] Add a `master/detail` recipe for a parent detail page containing child tables and child actions.
1.1.7 [ ] Add a `dialog editing` recipe for create/update actions performed without a dedicated page.
1.1.8 [ ] Add a `tabbed detail` recipe for modules or pages composed from multiple related sections.

## 1.2 Infer standard structures

1.2.1  [ ] Derive default form fields from the entity when no explicit form field list is supplied.
1.2.2  [ ] Derive default table columns from entity metadata with concise include/exclude overrides.
1.2.3  [ ] Derive default detail fields and sections where possible.
1.2.4  [ ] Derive normal list, detail, create, and update routes from entity/module conventions.
1.2.5  [ ] Derive standard action success flows, such as create → detail, update → detail, and delete → list.
1.2.6  [ ] Derive standard module index pages when the module uses a known CRUD recipe.
1.2.7  [ ] Allow entity-level presentation metadata to define sensible defaults reused by forms, tables, filters, and details.
1.2.8  [ ] Derive correct parent/child routes that preserve both parent and child identifiers across list, detail, create, and update flows.

## 1.3 Keep progressive escape hatches

1.3.1  [ ] Ensure every recipe can be customized without rebuilding the whole page.
1.3.2  [ ] Allow individual generated pages or elements to be replaced by explicit `ViewComposer` or `ElementComposer` implementations.
1.3.3  [ ] Preserve direct component composition for project-specific layouts.
1.3.4  [ ] Preserve custom actions and target-specific injection overrides.
1.3.5  [ ] Document the intended customization path: convention → recipe → override → raw composer → custom printer.

## 1.4 Reduce project declaration boilerplate

1.4.1  [ ] Evaluate a typed page registry such as `pages.of(Employe.class).detail()` to reduce static page variables while preserving typed navigation.
1.4.2  [ ] Allow groups of fields to be declared once and reused across form, detail, table, and filter definitions.
1.4.3  [ ] Add reusable layout fragments for common personal, contact, administrative, and status sections.
1.4.4  [ ] Reduce the number of classes required for a completely standard CRUD entity.
1.4.5  [ ] Keep explicit classes available where their names improve understanding or reuse.

---

# Priority 2 — Comparator-friendly deterministic output

## 2.1 Clean snapshot generation

2.1.1  [ ] Add an explicit clean-output option that recreates `result/` before generation.
2.1.2  [ ] Make clean snapshot generation the documented default comparator workflow.
2.1.3  [ ] Ensure removing or renaming an entity, page, or module cannot leave obsolete files in a clean result.
2.1.4  [ ] Keep all manually maintained content outside `result/`.

## 2.2 Stable ordering

2.2.1  [ ] Sort entities by stable qualified identity before processing and rendering.
2.2.2  [ ] Sort modules by stable package/name identity.
2.2.3  [ ] Sort pages consistently for both generation and generated collections.
2.2.4  [ ] Sort actions by stable semantic identity rather than construction order where possible.
2.2.5  [ ] Sort labels before i18n emission.
2.2.6  [ ] Sort Liquibase table and constraint includes.
2.2.7  [ ] Review every `HashMap` and `HashSet` iteration that can affect generated content.

## 2.3 Stable formatting

2.3.1  [x] Keep import ordering deterministic across Java and TypeScript output and aligned with the IDE organizer.
2.3.2  [ ] Keep generated whitespace and line endings stable.
2.3.3  [ ] Avoid timestamps, random values, and run-dependent content in generated files.
2.3.4  [ ] Keep generated file paths and names based on stable conventions.
2.3.5  [ ] Add a concise generation summary listing created output paths and artifact counts.

---

# Priority 3 — Clarify the generation lifecycle

## 3.1 Make phases explicit

3.1.1  [ ] Represent or document the lifecycle as: discover → bootstrap → initialize entities → compose pages → initialize actions → finalize metadata → generate.
3.1.2  [ ] Add a dedicated finalization phase before any printer runs.
3.1.3  [ ] Move label collection out of view rendering and into composition/finalization where practical.
3.1.4  [ ] Resolve all references, routes, action names, ACL keys, and labels before generation starts.
3.1.5  [ ] Prevent printers from adding semantic information to `Context` after finalization.

## 3.2 Improve pre-generation diagnostics

3.2.1  [ ] Validate module index requirements before writing output.
3.2.2  [ ] Validate duplicate routes and generated file paths.
3.2.3  [ ] Validate unresolved entity/page references.
3.2.4  [ ] Validate incompatible action option combinations.
3.2.5  [ ] Validate that required host-runtime assumptions are represented by the current project.
3.2.6  [ ] Report multiple configuration problems together where this saves correction cycles.

## 3.3 Keep the runtime simple

3.3.1  [ ] Keep the one-shot execution model unless repeated in-process generation becomes useful.
3.3.2  [ ] Add `Context.reset()` only if IDE reruns, watch mode, or multiple runs in one JVM require it.
3.3.3  [ ] Do not introduce concurrency into model construction without a concrete delivery benefit.

---

# Priority 4 — Organize reusable and project-specific evolution

## 4.1 Make copied-engine lineage clear

4.1.1  [ ] Keep the engine version in `pom.xml` meaningful for copied project versions.
4.1.2  [ ] Record the source engine version or commit when beginning a new project copy.
4.1.3  [ ] Maintain a short project-specific generator change log.
4.1.4  [ ] Mark improvements that should be carried into the next base version.
4.1.5  [ ] Mark customizations that should remain local to one project.

## 4.2 Separate concerns by convention, not necessarily artifacts

4.2.1  [ ] Establish a recognizable package for reusable high-level recipes.
4.2.2  [ ] Establish a recognizable package for project-specific actions, components, injections, and printers.
4.2.3  [ ] Keep application declarations under `model` and `modules` distinct from generator implementation.
4.2.4  [ ] Avoid splitting the project into multiple Maven modules unless the single-project structure becomes a measurable burden.

## 4.3 Document new patterns as they appear

4.3.1  [ ] Add a concise recipe example when introducing a reusable generation pattern.
4.3.2  [ ] Document the intended DSL form before expanding a recipe with many options.
4.3.3  [ ] Prefer a small number of composable concepts over one method for every isolated case.
4.3.4  [ ] Periodically remove project-specific options that accidentally entered the reusable DSL but were never reused.

---

# Priority 5 — Improve DSL readability and semantic precision

## 5.1 Conventions and identities

5.1.1  [ ] Keep class-name conventions as defaults, but add explicit route or permission overrides when a real project needs them.
5.1.2  [ ] Define stable semantic identities for cases where renaming a Java class must not rename a public route or ACL key.
5.1.3  [ ] Support multiple relationships between the same entity types without ambiguous lookup.
5.1.4  [ ] Generalize ownership/cardinality semantics only when new project models require more than `Ref`, `RefMany`, and `Father`.

## 5.2 Replace fragile target-language strings selectively

5.2.1  [ ] Identify frequently reused string expressions such as `hiddenIf`, `requiredIf`, `readOnlyIf`, and bindings.
5.2.2  [ ] Introduce small typed helpers for common conditions without removing raw-expression escape hatches.
5.2.3  [ ] Prefer typed field and page references wherever they reduce mistakes without increasing verbosity.
5.2.4  [ ] Keep direct target-language expressions available for exceptional cases.

## 5.3 Keep the DSL discoverable

5.3.1  [ ] Organize high-level recipes so they are easy to find through IDE autocomplete.
5.3.2  [ ] Preserve a single convenient composer facade even if implementation helpers are internally grouped.
5.3.3  [ ] Use consistent vocabulary for list/filter/detail/create/update concepts.
5.3.4  [ ] Keep method chains short enough to remain readable.
5.3.5  [ ] Add focused comments only where conventions are not self-explanatory.

---

# Priority 6 — Generated application contract

6.1   [x] Document the backend infrastructure expected by generated files, including `@JsonId`, pagination, persistence helpers, exceptions, reference data, and security assumptions.
6.2   [ ] Document the frontend Waxant and `commun` APIs expected by generated files.
6.3   [ ] Document which root application files are manually maintained rather than generated.
6.4   [ ] Record the expected database and Liquibase conventions.
6.5   [ ] Keep generator changes synchronized with corresponding backend/frontend runtime changes inside each project.
6.6   [ ] Add a lightweight project profile only when different projects need configurable package names, route roots, locale, or database behaviour.
6.7   [ ] Generate stronger create, update, response, and filter contracts when one optional-field interface no longer gives sufficient safety.
6.8   [ ] Keep generated route-parameter requirements synchronized with each page path and parent/child ownership.

---

# Warning

The following are intentionally not goals:

- A generic plugin marketplace or runtime printer discovery system.
- A target-neutral generator supporting unrelated technology stacks.
- Independent deployment and compatibility management for many engine modules.
- Multi-project concurrent generation in one JVM.
- Direct generation into production source trees.
- Complex generated/manual source merging.
- A full template engine replacement for the current Java emitters.
- A complete immutable compiler IR solely for architectural purity.

---

# Conclusion

The success criterion is not the number of abstractions added. It is less application DSL, clearer intent, faster delivery, and cleaner comparator output.
