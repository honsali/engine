package model.catalog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.entity.ReferenceData;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.impl.common.BeReferenceDataCatalogPrinter;

public class BeReferenceDataCatalogPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesDeterministicCatalogFromReferenceRelationships() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());
        context.addEntity(new Leave());
        context.addEntity(new Status());
        context.addEntity(new Employee());
        context.addEntity(new Department());
        context.initEntities();

        new BeReferenceDataCatalogPrinter().print();

        Path catalog = tempDir.resolve("be/src/main/java/app/domain/catalog/referenceData/CatalogReferenceDataCatalog.java");
        String generated = Files.readString(catalog);

        assertTrue(generated.contains("public class CatalogReferenceDataCatalog implements ReferenceDataCatalog"));
        assertTrue(generated.contains("\"department\", new ReferenceDataDefinition(\"Department\", \"name\", Set.of(\"id\"))"));
        assertTrue(generated.contains("\"employee\", new ReferenceDataDefinition(\"Employee\", \"code\", Set.of(\"id\", \"department.id\", \"status.id\"))"));
        assertTrue(generated.contains("\"status\", new ReferenceDataDefinition(\"Status\", \"libelle\", Set.of(\"id\"))"));
        assertTrue(generated.indexOf("\"department\"") < generated.indexOf("\"employee\""));
        assertTrue(generated.indexOf("\"employee\"") < generated.indexOf("\"status\""));
        assertFalse(generated.contains("\"leave\", new ReferenceDataDefinition"));
    }

    public static final class Department extends Entity {
        public final Field name = Text("name").isId();
    }

    public static final class Employee extends Entity {
        public final Field code = Text("code").isId();
        public final Field status = Ref(Status.class);
        public final Field department = Ref(Department.class);
    }

    public static final class Leave extends Entity {
        public final Field code = Text("code").isId();
        public final Field employee = Father(Employee.class);
    }

    public static final class Status extends ReferenceData {
    }
}
