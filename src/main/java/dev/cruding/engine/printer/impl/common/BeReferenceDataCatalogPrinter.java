package dev.cruding.engine.printer.impl.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeReferenceDataCatalogPrinter extends Printer {

    public void print() {
        List<Entity> catalogEntities = catalogEntities();
        if (catalogEntities.isEmpty()) {
            return;
        }

        String domain = catalogDomain(catalogEntities);
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

        f.__("package app.domain.", domain, ".referenceData;");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@Component");
        f.L("public class ", className, " implements ReferenceDataCatalog {");
        f.L("");
        f.L____("private static final Map<String, ReferenceDataDefinition> REFERENCES = Map.", useEntries ? "ofEntries(" : "of(");
        for (int index = 0; index < catalogEntities.size(); index++) {
            Entity entity = catalogEntities.get(index);
            String entry = catalogEntry(entity, useEntries);
            f.L____________(entry, index + 1 < catalogEntities.size() ? "," : ");");
        }
        f.L("");
        f.L____("@Override");
        f.L____("public Optional<ReferenceDataDefinition> find(String referenceName) {");
        f.L________("return Optional.ofNullable(REFERENCES.get(referenceName.toLowerCase(Locale.ROOT)));");
        f.L____("}");
        f.L("}");

        printFile(f.toString(), getBasePath() + "/be/src/main/java/app/domain/" + domain + "/referenceData/" + className + ".java");
    }

    private List<Entity> catalogEntities() {
        List<Entity> entities = new ArrayList<>(entityList());
        Map<String, Entity> catalogEntitiesByReferenceName = new TreeMap<>();

        entities.stream().filter(Entity::isReferenceData).forEach(entity -> addCatalogEntity(catalogEntitiesByReferenceName, entity));
        for (Entity entity : entities) {
            for (Field field : entity.fieldList) {
                if (field.isRef || field.isRefMany || field.isFather) {
                    addCatalogEntity(catalogEntitiesByReferenceName, Context.getInstance().getEntity(field.jtype));
                }
            }
        }

        return List.copyOf(catalogEntitiesByReferenceName.values());
    }

    private void addCatalogEntity(Map<String, Entity> catalogEntitiesByReferenceName, Entity entity) {
        String referenceName = referenceName(entity);
        Entity existing = catalogEntitiesByReferenceName.putIfAbsent(referenceName, entity);
        if (existing != null && existing != entity) {
            throw new IllegalStateException("Duplicate case-insensitive reference-data catalog name: " + referenceName);
        }
    }

    private String catalogDomain(List<Entity> catalogEntities) {
        TreeSet<String> domains = catalogEntities.stream().map(this::topLevelDomain).collect(Collectors.toCollection(TreeSet::new));
        if (domains.size() != 1) {
            throw new IllegalStateException("Reference-data catalog entities must share one top-level model package: " + domains);
        }
        return domains.first();
    }

    private String topLevelDomain(Entity entity) {
        String domain = StringUtils.substringBefore(entity.pkg, ".");
        if (StringUtils.isBlank(domain)) {
            throw new IllegalStateException("Reference-data catalog entity must belong to a model domain package: " + entity.uname);
        }
        return domain;
    }

    private String catalogEntry(Entity entity, boolean useEntries) {
        String definition = "new ReferenceDataDefinition(\"" + entity.uname + "\", \"" + entity.lid + "\", Set.of(" + quotedAllowedFilters(entity) + "))";
        String entry = "\"" + referenceName(entity) + "\", " + definition;
        return useEntries ? "Map.entry(" + entry + ")" : entry;
    }

    private String quotedAllowedFilters(Entity entity) {
        List<String> filters = new ArrayList<>();
        filters.add("id");
        entity.fieldList.stream()
                .filter(field -> field.isRef || field.isFather)
                .map(field -> field.lname + ".id")
                .sorted()
                .forEach(filters::add);
        return filters.stream().distinct().map(filter -> "\"" + filter + "\"").collect(Collectors.joining(", "));
    }

    private String referenceName(Entity entity) {
        return entity.uname.toLowerCase(Locale.ROOT);
    }
}
