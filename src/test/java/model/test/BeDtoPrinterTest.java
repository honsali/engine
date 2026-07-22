package model.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.impl.entity.BeDtoPrinter;

class BeDtoPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void marksGeneratedDtoIdentifiersForStringJsonSerialization() throws IOException {
        Context.getInstance().setBasePath(tempDir.toString());

        IdEntity entity = new IdEntity();
        entity.init();
        new BeDtoPrinter().print(entity);

        Path dto = tempDir.resolve("be/src/main/java/app/domain/test/idEntity/IdEntityDto.java");
        String generated = Files.readString(dto);

        assertTrue(generated.contains("import app.core.configuration.JsonId;"));
        assertTrue(generated.contains("@JsonId Long id,"));
        assertTrue(generated.contains("@JsonId Long idIdEntity,"));
    }

    static final class IdEntity extends Entity {
    }
}
