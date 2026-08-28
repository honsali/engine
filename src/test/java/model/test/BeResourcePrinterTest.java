package model.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.impl.entity.BeControllerPrinter;

class BeResourcePrinterTest {

    static final class SecuredEntity extends Entity {
    }

    @TempDir
    Path tempDir;

    @Test
    void generatesConfiguredResourceAuthority() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());

        SecuredEntity entity = new SecuredEntity();
        entity.init();
        new BeControllerPrinter().print(entity);

        Path resource = tempDir.resolve("be/src/main/java/app/domain/test/securedEntity/SecuredEntityResource.java");
        String generated = Files.readString(resource);

        assertTrue(generated.contains("import org.springframework.security.access.prepost.PreAuthorize;"));
        assertTrue(generated.contains("@PreAuthorize(\"hasAuthority('ROLE_GESTIONNAIRE_RH')\")"));
    }

}
