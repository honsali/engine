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
import dev.cruding.engine.printer.impl.entity.BeControllerPrinter;

class BeResourcePrinterTest {

    public static final class SecuredEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    @TempDir
    Path tempDir;

    @Test
    void generatesConventionBasedController() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());

        SecuredEntity entity = new SecuredEntity();
        entity.init();
        new BeControllerPrinter().print(entity);

        Path controller = tempDir.resolve("be/src/main/java/app/domain/test/securedentity/SecuredEntityController.java");
        String generated = Files.readString(controller);

        assertTrue(generated.contains("@RestController"));
        assertTrue(generated.contains("@RequestMapping(\"/api/test\")"));
        assertTrue(generated.contains("public class SecuredEntityController"));
    }

}
