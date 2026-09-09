package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.entity.BeControllerPrinter;

class BeResourcePrinterTest {

    public static final class SecuredEntity extends Entity {
        public final Field code = Text().isId();
    }

    @TempDir
    Path tempDir;

    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    @Test
    void generatesConventionBasedController() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();

        SecuredEntity entity = new SecuredEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module("ModuleSecuredEntity", "test/securedEntity");
        ViewFiltrerSecuredEntity view = new ViewFiltrerSecuredEntity();
        module.addPage(view);
        new FilterAction(entity, view.element, true);
        context.initActions();

        new BeControllerPrinter().print(entity);

        Path controller = tempDir.resolve("be/src/main/java/app/domain/test/securedentity/SecuredEntityController.java");
        String generated = Files.readString(controller);

        assertTrue(generated.contains("@RestController"));
        assertTrue(generated.contains("@RequestMapping(\"/api/test\")"));
        assertTrue(generated.contains("public class SecuredEntityController"));
        assertTrue(generated.contains(
                "public PageResponse<SecuredEntityResponse> filtrer(@Valid @RequestBody(required = false) SecuredEntityFiltre filtre, Pageable pageable) {"));
        assertFalse(generated.contains("filtrer(\n"));
    }

    public static final class ViewFiltrerSecuredEntity extends ViewComposer<SecuredEntity> {
    }
}
