package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.create.CreateAction;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.find.FindAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.listPaginated.ListPaginatedAction;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.action.update.UpdateAction;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Date;
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
    void rendersApiDatesWithTheFormattedReadOnlyComponent() {
        assertEquals("DateFormatee", new Date("date").ui(Element.DETAIL));
    }

    @Test
    void generatesStrictPageContractsAndCallbackSignatures() throws IOException {
        Context context = new Context(tempDir.toString());

        PageContractEntity entity = new PageContractEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModulePageContract", "test.pageContract");
        ViewFiltrerPageContractEntity view = new ViewFiltrerPageContractEntity(entity);
        Page page = module.addPage(view).icon("faFilter").isIndex();
        view.targetPage = page;
        page.init();

        ViewConsulterPageContractEntity detailView = new ViewConsulterPageContractEntity(entity);
        Page detailPage = module.addPage(detailView).pathById();
        detailPage.init();

        ViewModifierPageContractEntity nestedView = new ViewModifierPageContractEntity(entity);
        Page nestedPage = module.addPage(nestedView)
                .route("/test/page-contract/:idParent/child/modifier/:idPageContractEntity");
        nestedPage.init();

        Module viewOnlyModule = new Module(context, "ModuleViewOnly", "test.viewOnly");
        ViewGoToModulePageContractEntity goToModuleView = new ViewGoToModulePageContractEntity(entity);
        Page goToModulePage = viewOnlyModule.addPage(goToModuleView);
        goToModulePage.init();
        ViewEmitEventPageContractEntity emitEventView = new ViewEmitEventPageContractEntity(entity);
        Page emitEventPage = viewOnlyModule.addPage(emitEventView);
        emitEventPage.init();

        Module componentlessAclModule = new Module(context, "ModuleComponentlessAcl", "test.componentlessAcl");
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

        new FeCtrlPrinter(context).print(page);
        new FeMdlPrinter(context).print(page);
        new FeHookPrinter(context).print(page);
        new FeElementPrinter(context).print(view.element);
        new FeElementPrinter(context).print(formAction);
        new FePageListPrinter(context).print(module);
        new FeCtrlPrinter(context).print(goToModulePage);
        new FeCtrlPrinter(context).print(emitEventPage);
        new FeElementPrinter(context).print(goToModuleView.element);
        new FeElementPrinter(context).print(emitEventView.element);
        new FeAclPrinter(context).print(componentlessAclModule);

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
        assertFalse(mdl.contains("FormInstance"));
        assertTrue(mdl.contains("filtre: IRequetePageContractEntity;"));
        assertTrue(mdl.contains("request: IPageContractEntity;"));
        assertTrue(mdl.contains("pageCourante?: number;"));
        assertTrue(mdl.contains("pageContractEntity?: IPageContractEntity;"));
        assertFalse(mdl.contains(" | {}"));

        assertTrue(ctrl.contains("import { ActionOperation, action } from 'waxant';"));
        assertFalse(ctrl.contains("requete.form"));
        assertFalse(ctrl.contains("getFieldsValue"));
        assertFalse(ctrl.contains("validateFields"));
        assertTrue(ctrl.contains(": ActionOperation<ReqFiltrerPageContractEntity, ResFiltrerPageContractEntity> = async ("));
        assertTrue(ctrl.contains("ServicePageContractEntity.creer(requete.request)"));
        assertTrue(ctrl.contains("async (requete, resultat, thunkAPI) =>"));
        assertTrue(ctrl.contains("async (_requete, resultat, _thunkAPI) =>"));
        assertTrue(ctrl.contains("_resultat, _thunkAPI"));

        assertTrue(mdl.contains(".supprimerPageContractEntity.fulfilled, (state) =>"));
        assertFalse(mdl.contains(".pending, (state, action)"));
        assertFalse(mdl.contains(".rejected, (state, action)"));

        assertTrue(hook.contains("(req?: Partial<ReqFiltrerPageContractEntity>)"));
        assertTrue(hook.contains("const creerPageContractEntity = async (form: FormInstance<IPageContractEntity>) =>"));
        assertTrue(hook.contains("const request = util.removeNonSerialisable(await form.validateFields()) as IPageContractEntity;"));
        assertTrue(hook.contains("CtrlFiltrerPageContractEntity.creerPageContractEntity({ request, ...params })"));
        assertTrue(hook.contains("const filtrerPageContractEntity = async ({ form, ...req }: Partial<ReqFiltrerPageContractEntity> & { form: FormInstance<IRequetePageContractEntity> }) =>"));
        assertTrue(hook.contains("const filtre = util.removeNonSerialisable(form.getFieldsValue()) as IRequetePageContractEntity;"));
        assertTrue(hook.contains("CtrlFiltrerPageContractEntity.filtrerPageContractEntity({ ...req, filtre, ...params } as ReqFiltrerPageContractEntity)"));
        assertTrue(ctrl.contains("ServicePageContractEntity.filtrer(requete.filtre)"));
        assertTrue(generatedView.contains("Form.useForm<IPageContractEntity>()"));
        assertTrue(generatedView.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertTrue(generatedView.contains(
                "goToPage(PageFiltrerPageContractEntity, { idPageContractEntity: pageContractEntity.id });"));
        assertTrue(generatedView.contains("listeDonnee={listePagineePageContractEntity?.liste}"));
        assertTrue(generatedView.contains("pagination={listePagineePageContractEntity?.pagination}"));
        assertTrue(generatedFormAction.contains("({ form }: { form: FormInstance<IPageContractEntity> }) =>"));
        assertTrue(generatedFormAction.contains("creerPageContractEntity(form);"));
        assertTrue(generatedPageList.contains("toPath: () =>"));
        assertTrue(generatedPageList.contains("toPath: (args) =>"));
        assertTrue(generatedPageList.contains("${args.idPageContractEntity}"));
        assertTrue(generatedPageList.contains(
                "path: '/test/page-contract/:idParent/child/modifier/:idPageContractEntity'"));
        assertTrue(generatedPageList.contains(
                "toPath: (args) => `/test/page-contract/${args.idParent}/child/modifier/${args.idPageContractEntity}`"));

        assertFalse(goToModuleCtrl.contains("import "));
        assertFalse(emitEventCtrl.contains("import "));
        assertTrue(goToModuleElement.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertTrue(emitEventElement.contains("(pageContractEntity: IPageContractEntity) =>"));
        assertFalse(componentlessAcl.contains("import "));
    }

    @Test
    void keepsUpdateFindAndCustomActionFormsInHooks() throws IOException {
        Context context = new Context(tempDir.toString());
        PageContractEntity entity = new PageContractEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModulePageContract", "test.pageContract");
        ViewModifierPageContractEntity view = new ViewModifierPageContractEntity(entity);
        Page page = module.addPage(view).pathById();
        page.init();

        Element formAction = new Element("ActionModifierPageContractEntity", "/element").page(page).byForm();
        new UpdateAction(entity, formAction);
        new BasicAction(ActionType.UCA, "valider", entity, formAction).byForm().byId().byEntity();
        new FindAction(entity, formAction);
        new FilterAction(entity, formAction, false);
        new BasicAction(ActionType.UCA, "preparer", entity, formAction)
                .onSuccess(new BasicAction(ActionType.NOUI, "enregistrer", entity, formAction).byForm());
        context.initActions();

        new FeCtrlPrinter(context).print(page);
        new FeMdlPrinter(context).print(page);
        new FeHookPrinter(context).print(page);

        Path pageDirectory = tempDir.resolve("fe/src/modules/test/pageContract/pageContractEntity/modifier");
        String ctrl = Files.readString(pageDirectory.resolve("CtrlModifierPageContractEntity.ts"));
        String mdl = Files.readString(pageDirectory.resolve("MdlModifierPageContractEntity.ts"));
        String hook = Files.readString(pageDirectory.resolve("useModifierPageContractEntity.ts"));

        assertFalse(mdl.contains("FormInstance"));
        assertFalse(mdl.contains("from 'antd'"));
        assertTrue(mdl.contains("request: IPageContractEntity;"));
        assertTrue(mdl.contains("filtre: IRequetePageContractEntity;"));
        assertFalse(ctrl.contains("requete.form"));
        assertFalse(ctrl.contains("getFieldsValue"));
        assertFalse(ctrl.contains("validateFields"));
        assertFalse(ctrl.contains("removeNonSerialisable"));
        assertTrue(ctrl.contains("ServicePageContractEntity.maj(requete.request)"));
        assertTrue(ctrl.contains("ServicePageContractEntity.chercher(requete.request)"));
        assertTrue(ctrl.contains("{ ...pageContractEntity, ...requete.request }"));
        assertTrue(ctrl.contains("await enregistrerPageContractEntityImpl(requete, resultat, thunkAPI);"));
        assertTrue(ctrl.contains("ServicePageContractEntity.filtrer(requete.filtre)"));

        for (String name : new String[] { "maj", "valider", "chercher", "preparer", "enregistrer" }) {
            assertTrue(hook.contains("const " + name + "PageContractEntity = async ({ form, ...req }: Partial<ReqModifierPageContractEntity> & { form: FormInstance<IPageContractEntity> }) =>"), name);
            assertTrue(hook.contains("CtrlModifierPageContractEntity." + name + "PageContractEntity({ ...req, request, ...params } as ReqModifierPageContractEntity)"), name);
        }
        assertTrue(hook.contains("const request = util.removeNonSerialisable(await form.validateFields()) as IPageContractEntity;"));
        assertTrue(hook.contains("const filtre = util.removeNonSerialisable(form.getFieldsValue()) as IRequetePageContractEntity;"));
        assertFalse(hook.contains("{ ...req, form"));
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
            return block(
                    form(entity, entity.code),
                    table(entity, entity.code)
                            .fillWith(filter)
                            .onRowClick(goToPage(entity, targetPage)));
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

    public static final class ViewModifierPageContractEntity extends ViewComposer<PageContractEntity> {
        private final PageContractEntity entity;

        ViewModifierPageContractEntity(PageContractEntity entity) {
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
