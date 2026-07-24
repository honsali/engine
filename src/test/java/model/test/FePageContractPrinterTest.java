package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.create.CreateAction;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.listPaginated.ListPaginatedAction;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.module.FeAclPrinter;
import dev.cruding.engine.printer.impl.module.FePageListPrinter;
import dev.cruding.engine.printer.impl.page.FeCtrlPrinter;
import dev.cruding.engine.printer.impl.page.FeHookPrinter;
import dev.cruding.engine.printer.impl.page.FeMdlPrinter;

class FePageContractPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesStrictPageContractsAndCallbackSignatures() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());

        PageContractEntity entity = new PageContractEntity();
        entity.init();

        Module module = new Module("ModulePageContract", "test.pageContract");
        ViewFiltrerPageContractEntity view = new ViewFiltrerPageContractEntity(entity);
        Page page = module.addPage(view).icon("faFilter").isIndex();
        view.targetPage = page;
        page.init();

        ViewConsulterPageContractEntity detailView = new ViewConsulterPageContractEntity(entity);
        Page detailPage = module.addPage(detailView).pathById();
        detailPage.init();

        Module viewOnlyModule = new Module("ModuleViewOnly", "test.viewOnly");
        ViewGoToModulePageContractEntity goToModuleView = new ViewGoToModulePageContractEntity(entity);
        Page goToModulePage = viewOnlyModule.addPage(goToModuleView);
        goToModulePage.init();
        ViewEmitEventPageContractEntity emitEventView = new ViewEmitEventPageContractEntity(entity);
        Page emitEventPage = viewOnlyModule.addPage(emitEventView);
        emitEventPage.init();

        Module componentlessAclModule = new Module("ModuleComponentlessAcl", "test.componentlessAcl");
        ViewComponentlessPageContractEntity componentlessView = new ViewComponentlessPageContractEntity();
        Page componentlessPage = componentlessAclModule.addPage(componentlessView);
        componentlessPage.init();
        new DeleteAction(entity, componentlessView.element);

        Element formAction = new Element("ActionCreerPageContractEntity", "/element").page(page).byForm();
        new CreateAction(entity, formAction);
        new DeleteAction(entity, view.element);
        new GetByFieldAction(entity, view.element).byField(entity.code);
        new ListPaginatedAction(entity, view.element);
        context.initActions();

        new FeCtrlPrinter().print(page);
        new FeMdlPrinter().print(page);
        new FeHookPrinter().print(page);
        new FeElementPrinter().print(view.element);
        new FeElementPrinter().print(formAction);
        new FePageListPrinter().print(module);
        new FeCtrlPrinter().print(goToModulePage);
        new FeCtrlPrinter().print(emitEventPage);
        new FeElementPrinter().print(goToModuleView.element);
        new FeElementPrinter().print(emitEventView.element);
        new FeAclPrinter().print(componentlessAclModule);

        Path pageDirectory = tempDir.resolve("fe/src/modules/test/pageContract/pageContractEntity/filtrer");
        String ctrl = Files.readString(pageDirectory.resolve("CtrlFiltrerPageContractEntity.ts"));
        String mdl = Files.readString(pageDirectory.resolve("MdlFiltrerPageContractEntity.ts"));
        String hook = Files.readString(pageDirectory.resolve("useFiltrerPageContractEntity.ts"));
        String generatedView = Files.readString(pageDirectory.resolve("ViewFiltrerPageContractEntity.tsx"));
        String generatedFormAction = Files.readString(pageDirectory.resolve("element/ActionCreerPageContractEntity.tsx"));
        String generatedPageList = Files.readString(tempDir.resolve("fe/src/modules/test/pageContract/ListePagePageContract.tsx"));
        String goToModuleCtrl = Files.readString(tempDir.resolve("fe/src/modules/test/viewOnly/pageContractEntity/goToModule/CtrlGoToModulePageContractEntity.ts"));
        String emitEventCtrl = Files.readString(tempDir.resolve("fe/src/modules/test/viewOnly/pageContractEntity/emitEvent/CtrlEmitEventPageContractEntity.ts"));
        String goToModuleElement = Files.readString(tempDir.resolve("fe/src/modules/test/viewOnly/pageContractEntity/goToModule/ViewGoToModulePageContractEntity.tsx"));
        String emitEventElement = Files.readString(tempDir.resolve("fe/src/modules/test/viewOnly/pageContractEntity/emitEvent/ViewEmitEventPageContractEntity.tsx"));
        String componentlessAcl = Files.readString(tempDir.resolve("fe/src/commun/securite/acl/aclComponentlessAcl.ts"));

        assertTrue(mdl.contains("code: string;"));
        assertTrue(mdl.contains("idPageContractEntity: string;"));
        assertTrue(mdl.contains("form?: FormInstance;"));
        assertTrue(mdl.contains("pageCourante?: number;"));
        assertTrue(mdl.contains("pageContractEntity?: IPageContractEntity;"));
        assertFalse(mdl.contains(" | {}"));

        assertTrue(ctrl.contains("import { ActionOperation, action, util } from 'waxant';"));
        assertTrue(ctrl.contains(": ActionOperation<ReqFiltrerPageContractEntity, ResFiltrerPageContractEntity> = async ("));
        assertTrue(ctrl.contains("async (requete, resultat, thunkAPI) =>"));
        assertTrue(ctrl.contains("async (_requete, resultat, _thunkAPI) =>"));
        assertTrue(ctrl.contains("_resultat, _thunkAPI"));

        assertTrue(mdl.contains(".supprimerPageContractEntity.fulfilled, (state) =>"));
        assertFalse(mdl.contains(".pending, (state, action)"));
        assertFalse(mdl.contains(".rejected, (state, action)"));

        assertTrue(hook.contains("(req?: Partial<ReqFiltrerPageContractEntity>)"));
        assertTrue(generatedView.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertTrue(generatedView.contains("listeDonnee={listePagineePageContractEntity?.liste}"));
        assertTrue(generatedView.contains("pagination={listePagineePageContractEntity?.pagination}"));
        assertTrue(generatedFormAction.contains("({ form }: { form: FormInstance }) =>"));
        assertTrue(generatedPageList.contains("toPath: () =>"));
        assertTrue(generatedPageList.contains("toPath: (args) =>"));
        assertTrue(generatedPageList.contains("${args.idPageContractEntity}"));

        assertFalse(goToModuleCtrl.contains("import "));
        assertFalse(emitEventCtrl.contains("import "));
        assertTrue(goToModuleElement.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertTrue(emitEventElement.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertFalse(componentlessAcl.contains("import "));
    }

    public static final class PageContractEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ViewFiltrerPageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;
        private Page targetPage;

        ViewFiltrerPageContractEntity(PageContractEntity entity) {
            this.entity = entity;
        }

        @Override
        public Component rootComponent() {
            FilterAction filter = filter(entity);
            return table(entity, entity.code)
                    .fillWith(filter)
                    .onRowClick(goToPage(entity, targetPage));
        }
    }

    public static final class ViewConsulterPageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;

        ViewConsulterPageContractEntity(PageContractEntity entity) {
            this.entity = entity;
        }

        @Override
        public Component rootComponent() {
            return table(entity, entity.code);
        }
    }

    public static final class ViewGoToModulePageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;

        ViewGoToModulePageContractEntity(PageContractEntity entity) {
            this.entity = entity;
        }

        @Override
        public Component rootComponent() {
            return table(entity, entity.code).onRowClick(goToModule(entity, "target"));
        }
    }

    public static final class ViewEmitEventPageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;

        ViewEmitEventPageContractEntity(PageContractEntity entity) {
            this.entity = entity;
        }

        @Override
        public Component rootComponent() {
            return table(entity, entity.code).onRowClick(emitEvent(entity, "rowSelected"));
        }
    }

    public static final class ViewComponentlessPageContractEntity extends ViewComposer<PageContractEntity> {
    }
}
