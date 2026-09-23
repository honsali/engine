package model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.action.get.GetFromModelAction;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.loader.EntityLoader;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.page.FeHookPrinter;
import modules.ProjectBootstrap;

class FeActionHookPrinterTest {
    @TempDir Path tempDir;
    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @BeforeEach
    void generateFrontend() {
        EnginePaths.outputRoot = tempDir;
        Context.init();
        EntityLoader.load(EnginePaths.modelPath.toString());
        ProjectBootstrap.init();
        Context.getInstance().initEntities();
        Context.getInstance().initPages();
        Context.getInstance().initActions();
        for (Page page : Context.getInstance().getPageList()) {
            for (Element element : page.elementList) {
                if (!element.fake) new FeElementPrinter().print(element);
            }
            new FeHookPrinter().print(page);
        }
    }

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    private String read(String file) throws IOException {
        return Files.readString(tempDir.resolve("fe/src/modules/" + file));
    }

    @Test
    void consultationInitializesInsideItsHookAndSubscribesOnlyToTheEntity() throws IOException {
        String hook = read("rh/departement/consulter/useConsulterDepartement.ts");
        String retrieval = hook.substring(hook.indexOf("export const useRecuperer"), hook.indexOf("export const useSupprimer"));
        assertTrue(retrieval.contains("useSelector(selectDepartement)"));
        assertTrue(retrieval.contains("recupererDepartementParId({ idDepartement } as ReqConsulterDepartement)"));
        assertTrue(retrieval.contains("}, [dispatch, idDepartement]);"));
        assertFalse(retrieval.contains("etatRecuperer"));
        assertFalse(retrieval.contains("resetEtat"));
        assertFalse(retrieval.contains("useCallback"));
        String deletion = hook.substring(hook.indexOf("export const useSupprimer"));
        assertTrue(deletion.contains("useSelector(selectEtatSupprimerDepartement)"));
        assertFalse(deletion.contains("useSelector(selectDepartement)"));
        assertFalse(deletion.contains("useEffect"));

        String detail = read("rh/departement/consulter/element/EtatDepartement.tsx");
        assertTrue(detail.contains("const { departement } = useRecupererDepartementParId();"));
        assertFalse(detail.contains("useEffect"));
        String view = read("rh/departement/consulter/ViewConsulterDepartement.tsx");
        assertTrue(view.contains("<ActionSupprimerDepartement />"));
        assertFalse(view.contains("useSupprimerDepartement"));

        // A second reader of the shared model must not initialize it again.
        Page page = Context.getInstance().getPageList().stream()
                .filter(p -> p.uc.equals("ConsulterDepartement")).findFirst().orElseThrow();
        ViewFlow reader = new ViewFlow(new Element("AutreLecture", "/element").page(page));
        reader.addSelector("departement");
        reader.addSelector("etatRecupererDepartementParId");
        reader.flushViewImportBlock();
        reader.flushInitBlock();
        assertTrue(reader.toString().contains("const departement = useSelector(selectDepartement);"));
        assertTrue(reader.toString().contains("useSelector(selectEtatRecupererDepartementParId)"));
        assertFalse(reader.toString().contains("useRecuperer"));
    }

    @Test
    void initializationFromAnotherModelKeepsItsReadinessDependency() throws IOException {
        Page page = Context.getInstance().getPageList().stream()
                .filter(p -> p.uc.equals("ListerDepartement")).findFirst().orElseThrow();
        Element element = new Element("EtatEnSession", "/element").page(page).byProp("pret: boolean");
        page.elementList.add(element);
        new GetFromModelAction(Context.getInstance().getEntity("Departement"), element, "session", "departementEnSession")
                .waitUntilReady();
        Context.getInstance().initActions();
        new FeHookPrinter().print(page);
        new FeElementPrinter().print(element);
        String hook = read("rh/departement/lister/useListerDepartement.ts");
        assertTrue(hook.contains("export const useRecupererEnSessionDepartement = (pret: boolean)"));
        assertTrue(hook.contains("if (!pret) return;"));
        assertTrue(hook.contains("}, [dispatch, pret]);"));
        assertTrue(hook.contains("useSelector(selectDepartementEnSession)"));
        String component = read("rh/departement/lister/element/EtatEnSession.tsx");
        assertTrue(component.contains("useRecupererEnSessionDepartement(pret)"));
        assertFalse(component.contains("useEffect"));
    }

    @Test
    void listsAndFormsInitializeWithRouteDependenciesButKeepCommandsSeparate() throws IOException {
        String list = read("rh/departement/lister/useListerDepartement.ts");
        assertTrue(list.contains("}, [dispatch]);"));
        assertTrue(list.contains("useSelector(selectListeDepartement)"));
        assertFalse(list.contains("selectEtatLister"));
        assertFalse(list.contains("useCallback"));

        String childList = read("rh/employe/consulter/useConsulterEmploye.ts");
        assertTrue(childList.contains("export const useListerCongeParIdEmploye"));
        assertTrue(childList.contains("listerCongeParIdEmploye({ idEmploye } as ReqConsulterEmploye)"));
        assertTrue(childList.contains("}, [dispatch, idEmploye]);"));

        String modifier = read("rh/employe/conge/modifier/useModifierConge.ts");
        String initialization = modifier.substring(modifier.indexOf("export const useInit"), modifier.indexOf("export const useMaj"));
        assertTrue(initialization.contains("}, [dispatch, idConge]);"));
        assertFalse(initialization.contains("idEmploye"));
        assertTrue(initialization.contains("selectEtatInitModificationConge"));
        assertFalse(initialization.contains("selectEtatMajConge"));
        String command = modifier.substring(modifier.indexOf("export const useMaj"));
        assertTrue(command.contains("useCallback(async"));
        assertFalse(command.contains("useEffect"));
        assertFalse(command.contains("selectConge"));
    }

    @Test
    void filterInitializesAutomaticallyAndStillSupportsTheResetButton() throws IOException {
        String hook = read("rh/employe/filtrer/useFiltrerEmploye.ts");
        assertTrue(hook.contains("export const useInitialiserFiltrerEmploye"));
        assertTrue(hook.contains("dispatch(CtrlFiltrerEmploye.initialiserFiltrerEmploye({} as ReqFiltrerEmploye));"));
        assertTrue(hook.contains("const initialiserFiltrerEmploye = useCallback("));
        assertFalse(hook.contains("selectEtat"));
        String filter = read("rh/employe/filtrer/element/FiltreEmploye.tsx");
        assertTrue(filter.contains("useInitialiserFiltrerEmploye();"));
        assertTrue(filter.contains("form.resetFields();"));
        assertTrue(filter.contains("initialiserFiltrerEmploye();"));
        assertFalse(filter.contains("useEffect"));
        assertFalse(filter.contains("listePagineeEmploye"));
        String table = read("rh/employe/filtrer/element/TableauEmploye.tsx");
        assertTrue(table.contains("useChangerPageFiltrerEmploye();"));
        assertFalse(table.contains("useInitialiserFiltrerEmploye"));
    }
}
