package model.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.ContextException;
import dev.cruding.engine.printer.impl.entity.BeResourcePrinter;

class BeResourcePrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesConfiguredResourceAuthority() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());
        context.setGeneratedResourceAuthority("ROLE_GESTIONNAIRE_RH");

        SecuredEntity entity = new SecuredEntity();
        entity.init();
        new BeResourcePrinter().print(entity);

        Path resource = tempDir.resolve("be/src/main/java/app/domain/test/securedEntity/SecuredEntityResource.java");
        String generated = Files.readString(resource);

        assertTrue(generated.contains("import org.springframework.security.access.prepost.PreAuthorize;"));
        assertTrue(generated.contains("@PreAuthorize(\"hasAuthority('ROLE_GESTIONNAIRE_RH')\")"));
    }

    @Test
    void rejectsUnsafeResourceAuthorities() {
        Context context = Context.getInstance();

        assertThrows(ContextException.class, () -> context.setGeneratedResourceAuthority(""));
        assertThrows(ContextException.class, () -> context.setGeneratedResourceAuthority("ROLE_MANAGER') or permitAll("));
    }

    static final class SecuredEntity extends Entity {
    }
}
