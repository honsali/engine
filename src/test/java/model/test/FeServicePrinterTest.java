package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.create.CreateAction;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.list.ListAction;
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

    @Test
    void generatesTypedAxiosCallsWithExplicitDataDestructuring() throws IOException {
        Context context = Context.getInstance();
        context.setBasePath(tempDir.toString());

        ServiceEntity entity = new ServiceEntity();
        entity.init();

        Module module = new Module("ModuleServiceStyle", "test.serviceStyle");
        ViewTestServiceEntity view = new ViewTestServiceEntity();
        module.addPage(view);

        new CreateAction(entity, view.element);
        new ListAction(entity, view.element);
        new UpdateAction(entity, view.element);
        new GetByFieldAction(entity, view.element).byField(entity.code);
        new FilterAction(entity, view.element, true);
        context.initActions();

        new FeServicePrinter().print(entity);

        Path service = tempDir.resolve("fe/src/modele/test/serviceEntity/ServiceServiceEntity.ts");
        String generated = Files.readString(service);

        assertTrue(generated.contains("const { data } = await axios.post<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.put<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.get<IServiceEntity[]>("));
        assertTrue(generated.contains("const { data } = await axios.get<IServiceEntity>("));
        assertTrue(generated.contains("const { data } = await axios.post<Page<IServiceEntity>>("));
        assertFalse(generated.contains("(await axios"));
        assertFalse(generated.contains(": Promise<"));
    }

    public static final class ServiceEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ViewTestServiceEntity extends ViewComposer<ServiceEntity> {
    }
}
