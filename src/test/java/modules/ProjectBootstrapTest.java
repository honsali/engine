package modules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.loader.EntityLoader;
import dev.cruding.engine.loader.LoaderUtils;
import model.admin.Role;
import modules.admin.AdminModule;
import modules.rh.RhModule;

class ProjectBootstrapTest {

    @TempDir
    Path tempDir;

    @Test
    void composesProjectModulesInDeclarationOrder() {
        Context context = new Context(tempDir.toString());
        EntityLoader.load(context, LoaderUtils.getModelPath().toString());

        ProjectBootstrap.init(context);
        context.initEntities();
        context.initPages();
        context.initActions();

        assertEquals(List.of("admin", "admin.account", "rh", "rh.employe", "rh.departement"),
                context.getModuleList().stream().map(module -> module.packge).toList());
        assertEquals(15, context.getPageList().size());
        assertEquals("app_role", context.getEntity(Role.class).dbName);
        assertEquals("admin.account", context.getPage(AdminModule.pageListerAccount).module.packge);
        assertEquals("rh.employe", context.getPage(RhModule.pageFiltrerEmploye).module.packge);
        assertEquals("rh.departement", context.getPage(RhModule.pageListerDepartement).module.packge);
    }

    @Test
    void rhModuleDoesNotComposeAdministration() {
        Context context = new Context(tempDir.toString());

        RhModule.init(context);

        assertEquals(List.of("rh", "rh.employe", "rh.departement"),
                context.getModuleList().stream().map(module -> module.packge).toList());
        assertEquals(11, context.getPageList().size());
    }
}
