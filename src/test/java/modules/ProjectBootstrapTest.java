package modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.loader.EntityLoader;
import model.admin.Role;
import modules.admin.AdminModule;
import modules.rh.RhModule;

class ProjectBootstrapTest {

    @Test
    void composesProjectModulesInDeclarationOrder() {
        Context context = Context.init();
        EntityLoader.load(EnginePaths.modelPath.toString());

        ProjectBootstrap.init();
        context.initEntities();
        context.initPages();
        context.initActions();

        assertEquals(List.of("modules/admin", "modules/admin/account", "modules/rh", "modules/rh/employe", "modules/rh/departement"),
                context.getModuleList().stream().map(module -> module.path).toList());
        assertEquals(15, context.getPageList().size());
        assertEquals("role", context.getEntity(Role.class).dbName);
        assertEquals("seq_role", context.getEntity(Role.class).seqName);
        assertEquals("modules/admin/account", context.getPage(AdminModule.pageListerAccount).module.path);
        assertEquals("modules/rh/employe", context.getPage(RhModule.pageFiltrerEmploye).module.path);
        assertEquals("modules/rh/departement", context.getPage(RhModule.pageListerDepartement).module.path);
    }

    @Test
    void rhModuleDoesNotComposeAdministration() {
        Context context = Context.init();

        RhModule.init();

        assertEquals(List.of("modules/rh", "modules/rh/employe", "modules/rh/departement"),
                context.getModuleList().stream().map(module -> module.path).toList());
        assertEquals(11, context.getPageList().size());
    }
}
