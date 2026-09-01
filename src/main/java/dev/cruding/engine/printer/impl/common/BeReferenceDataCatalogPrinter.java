package dev.cruding.engine.printer.impl.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeReferenceDataCatalogPrinter extends Printer {

    public void print() {
        for (Map.Entry<String, List<Entity>> catalog : catalogEntitiesByDomain().entrySet()) {
            printCatalog(catalog.getKey(), catalog.getValue());
        }
    }

    private void printCatalog(String domain, List<Entity> catalogEntities) {
        String className = StringUtils.capitalize(domain) + "ReferenceDataCatalog";
        boolean useEntries = catalogEntities.size() > 10;

        JavaFlow f = new JavaFlow();
        f.addJavaImport("java.util.Locale");
        f.addJavaImport("java.util.Map");
        f.addJavaImport("java.util.Optional");
        f.addJavaImport("java.util.Set");
        f.addJavaImport("org.springframework.stereotype.Component");
        f.addJavaImport("app.core.referenceData.ReferenceDataCatalog");
        f.addJavaImport("app.core.referenceData.ReferenceDataDefinition");

        f.__("package app.domain.", domain, ".referencedata;");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@Component");
        f.L("public class ", className, " implements ReferenceDataCatalog {");
        f.L("");
        f.L____("private static final Map<String, ReferenceDataDefinition> REFERENCES = Map.", useEntries ? "ofEntries(" : "of(");
        for (int index = 0; index < catalogEntities.size(); index++) {
            Entity entity = catalogEntities.get(index);
            addCatalogEntry(f, entity, useEntries, index + 1 == catalogEntities.size());
        }
        f.L("");
        f.L____("@Override");
        f.L____("public Optional<ReferenceDataDefinition> find(String referenceName) {");
        f.L________("return Optional.ofNullable(REFERENCES.get(referenceName.toLowerCase(Locale.ROOT)));");
        f.L____("}");
        f.L("}");

        printFile(f.toString(), getBasePath() + "/be/src/main/java/app/domain/" + domain + "/referencedata/" + className + ".java");
    }

    private Map<String, List<Entity>> catalogEntitiesByDomain() {
        List<Entity> entities = new ArrayList<>(entityList());
        Map<String, Map<String, Entity>> entitiesByDomainAndReferenceName = new TreeMap<>();

        entities.stream().filter(Entity::isReferenceData).forEach(entity -> addCatalogEntity(entitiesByDomainAndReferenceName, entity));
        for (Entity entity : entities) {
            for (Field field : entity.fieldList) {
                if (field.isRef || field.isFather) {
                    addCatalogEntity(entitiesByDomainAndReferenceName, Context.getInstance().getEntity(field.jtype));
                }
            }
        }

        Map<String, List<Entity>> catalogs = new TreeMap<>();
        entitiesByDomainAndReferenceName.forEach((domain, byReferenceName) -> catalogs.put(domain, List.copyOf(byReferenceName.values())));
        return catalogs;
    }

    private void addCatalogEntity(Map<String, Map<String, Entity>> catalogs, Entity entity) {
        String domain = topLevelDomain(entity);
        String referenceName = referenceName(entity);
        Map<String, Entity> catalog = catalogs.computeIfAbsent(domain, ignored -> new TreeMap<>());
        Entity existing = catalog.putIfAbsent(referenceName, entity);
        if (existing != null && existing != entity) {
            throw new IllegalStateException("Duplicate case-insensitive reference-data catalog name in domain '" + domain + "': " + referenceName);
        }
    }

    private String topLevelDomain(Entity entity) {
        String domain = StringUtils.substringBefore(entity.pkg, ".");
        if (StringUtils.isBlank(domain)) {
            throw new IllegalStateException("Reference-data catalog entity must belong to a model domain package: " + entity.uname);
        }
        return domain;
    }

    private void addCatalogEntry(JavaFlow f, Entity entity, boolean useEntries, boolean last) {
        int definitionIndentation;
        int argumentIndentation;
        if (useEntries) {
            f.L____________("Map.entry(");
            f.L________________("\"", referenceName(entity), "\",");
            definitionIndentation = 16;
            argumentIndentation = 24;
        } else {
            f.L____________("\"", referenceName(entity), "\",");
            definitionIndentation = 12;
            argumentIndentation = 20;
        }

        f.newLineWithIndent(definitionIndentation, "new ReferenceDataDefinition(");
        f.newLineWithIndent(argumentIndentation, "\"" + entity.uname + "\",");
        f.newLineWithIndent(argumentIndentation, "\"" + entity.lid + "\",");
        String suffix = useEntries
                ? (last ? "))));" : "))),")
                : (last ? ")));" : ")),");
        f.newLineWithIndent(argumentIndentation, "Set.of(" + quotedAllowedFilters(entity) + suffix);
    }

    private String quotedAllowedFilters(Entity entity) {
        List<String> filters = new ArrayList<>();
        filters.add("id");
        entity.fieldList.stream().filter(field -> field.isRef || field.isFather).map(field -> field.lname + ".id").sorted().forEach(filters::add);
        return filters.stream().distinct().map(filter -> "\"" + filter + "\"").collect(Collectors.joining(", "));
    }

    private String referenceName(Entity entity) {
        return entity.uname.toLowerCase(Locale.ROOT);
    }
}
