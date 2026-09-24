package model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.create.CreateAction;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.find.FindAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.list.ListAction;
import dev.cruding.engine.action.listPaginated.ListPaginatedAction;
import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.action.update.UpdateAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.entity.FeServicePrinter;

class FeServicePrinterTest {

    @TempDir
    Path tempDir;

    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    @Test
    void generatesTypedAxiosCallsWithExplicitDataDestructuring() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();

        ServiceEntity entity = new ServiceEntity();
        context.addEntity(entity);
        context.initEntities();

        assertEquals("test.serviceentity", entity.javaPackage);
        assertEquals("test/serviceentity", entity.javaPath);
        assertEquals("test/serviceEntity", entity.path);

        Module module = new Module("ModuleServiceStyle", "test/serviceStyle");
        ViewTestServiceEntity view = new ViewTestServiceEntity();
        module.addPage(view);

        new CreateAction(entity, view.element);
        new ListAction(entity, view.element);
        new UpdateAction(entity, view.element);
        new GetByFieldAction(entity, view.element).byField(entity.code);
        new FilterAction(entity, view.element, true);
        new DeleteAction(entity, view.element);
        new FindAction(entity, view.element);
        new ListPaginatedAction(entity, view.element);
        new BasicAction(ActionType.NOUI, "exporter", entity, view.element);
        context.initActions();

        new FeServicePrinter().print(entity);

        Path service = tempDir.resolve("fe/src/modele/test/serviceEntity/ServiceServiceEntity.ts");
        String generated = Files.readString(service);

        assertTrue(generated.contains("const { data } = await axios.post<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.put<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.get<IServiceEntity[]>("));
        assertTrue(generated.contains("const { data } = await axios.get<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.post<PageResponse<IServiceEntity>>("));
        assertTrue(generated.contains("liste: data.items,"));
        assertTrue(generated.contains("`${API_URL}/test/serviceEntity`"));
        assertTrue(generated.contains("`${API_URL}/test/serviceEntity/${serviceEntity.id}`"));
        List<String> calls = generated.lines().filter(line -> line.contains("await axios.")).toList();
        assertEquals(9, calls.size());
        for (String call : calls) {
            assertTrue(call.contains("`${API_URL}/test/serviceEntity"), call);
        }
        assertFalse(generated.contains("(await axios"));
        assertFalse(generated.contains("data.content"));
        assertFalse(generated.contains(": Promise<"));
    }

    @Test
    void generatesParentScopedUrlsWithAnApiSeparatorAndSingularEntityNames() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();
        ServiceEntity parent = new ServiceEntity();
        ServiceChild child = new ServiceChild();
        context.addEntity(parent);
        context.addEntity(child);
        context.initEntities();

        Module module = new Module("ModuleServiceChild", "test/serviceChild");
        ViewTestServiceChild view = new ViewTestServiceChild();
        module.addPage(view);
        new CreateAction(child, view.element).byFatherId();
        new ListAction(child, view.element).byFatherId();
        new ListPaginatedAction(child, view.element).byFatherId();
        new BasicAction(ActionType.NOUI, "exporter", child, view.element).byFatherId();
        context.initActions();

        new FeServicePrinter().print(child);

        String generated = Files.readString(tempDir.resolve(
                "fe/src/modele/test/serviceChild/ServiceServiceChild.ts"));
        List<String> calls = generated.lines().filter(line -> line.contains("await axios.")).toList();
        assertEquals(4, calls.size());
        for (String call : calls) {
            assertTrue(call.contains("`${API_URL}/test/serviceEntity/${idParent}/serviceChild"), call);
        }
    }

    public static final class ServiceEntity extends Entity {
        public final Field code = Text().isId();
    }

    public static final class ServiceChild extends Entity {
        public final Field code = Text().isId();
        public final Field parent = Father(ServiceEntity.class);
    }

    public static final class ViewTestServiceEntity extends ViewComposer<ServiceEntity> {
    }

    public static final class ViewTestServiceChild extends ViewComposer<ServiceChild> {
    }
}
