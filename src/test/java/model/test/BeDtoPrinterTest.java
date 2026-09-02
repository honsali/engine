package model.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.impl.entity.BeResponsePrinter;

class BeDtoPrinterTest {

    public static final class IdEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    @TempDir
    Path tempDir;

    @Test
    void marksGeneratedResponseIdentifiersForStringJsonSerialization() throws IOException {
        Context context = new Context(tempDir.toString());

        IdEntity entity = new IdEntity();
        context.addEntity(entity);
        context.initEntities();
        new BeResponsePrinter(context).print(entity);

        Path response = tempDir.resolve("be/src/main/java/app/domain/test/identity/IdEntityResponse.java");
        String generated = Files.readString(response);

        assertTrue(generated.contains("import app.core.reference.JsonId;"));
        assertTrue(generated.contains("@JsonId Long id,"));
        assertTrue(generated.contains("String code,"));
    }
}
