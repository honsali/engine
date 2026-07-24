package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.listPaginated.ListPaginatedAction;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.page.FeHookPrinter;
import dev.cruding.engine.printer.impl.page.FeMdlPrinter;

class FePageContractPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesRequiredTransportIdsAndNullSafeViewInputs() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());

        PageContractEntity entity = new PageContractEntity();
        entity.init();

        Module module = new Module("ModulePageContract", "test.pageContract");
        ViewFiltrerPageContractEntity view = new ViewFiltrerPageContractEntity(entity);
        Page page = module.addPage(view);
        page.init();

        new DeleteAction(entity, view.element);
        new GetByFieldAction(entity, view.element).byField(entity.code);
        new ListPaginatedAction(entity, view.element);
        context.initActions();

        new FeMdlPrinter().print(page);
        new FeHookPrinter().print(page);
        new FeElementPrinter().print(view.element);

        Path pageDirectory = tempDir.resolve("fe/src/modules/test/pageContract/pageContractEntity/filtrer");
        String mdl = Files.readString(pageDirectory.resolve("MdlFiltrerPageContractEntity.ts"));
        String hook = Files.readString(pageDirectory.resolve("useFiltrerPageContractEntity.ts"));
        String generatedView = Files.readString(pageDirectory.resolve("ViewFiltrerPageContractEntity.tsx"));

        assertTrue(mdl.contains("code: string;"));
        assertTrue(mdl.contains("idPageContractEntity: string;"));
        assertTrue(mdl.contains("form?: FormInstance;"));
        assertTrue(mdl.contains("pageCourante?: number;"));
        assertTrue(mdl.contains("pageContractEntity?: IPageContractEntity;"));
        assertFalse(mdl.contains(" | {}"));

        assertTrue(hook.contains("(req?: Partial<ReqFiltrerPageContractEntity>)"));
        assertTrue(generatedView.contains("listeDonnee={listePagineePageContractEntity?.liste}"));
        assertTrue(generatedView.contains("pagination={listePagineePageContractEntity?.pagination}"));
    }

    public static final class PageContractEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ViewFiltrerPageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;

        ViewFiltrerPageContractEntity(PageContractEntity entity) {
            this.entity = entity;
        }

        @Override
        public Component rootComponent() {
            FilterAction filter = filter(entity);
            return table(entity, entity.code).fillWith(filter);
        }
    }
}
