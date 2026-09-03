package model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.delete.DeleteAction;
import dev.cruding.engine.action.get.GetByFieldAction;
import dev.cruding.engine.action.update.UpdateAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.entity.BeBusinessPrinter;

class BeBusinessPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesOnePrivateEntityResolverSharedByBusinessActions() throws IOException {
        Context context = new Context(tempDir.toString());

        LookupEntity entity = new LookupEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModuleLookupEntity", "test.lookupEntity");
        ViewConsulterLookupEntity view = new ViewConsulterLookupEntity();
        module.addPage(view);

        new GetByFieldAction(entity, view.element).byField(entity.id_);
        new UpdateAction(entity, view.element);
        new DeleteAction(entity, view.element);
        context.initActions();

        new BeBusinessPrinter(context).print(entity);

        Path service = tempDir.resolve("be/src/main/java/app/domain/test/lookupentity/LookupEntityService.java");
        String generated = Files.readString(service);

        assertTrue(generated.contains("return LookupEntityMapper.toResponse(recupererLookupEntity(id));"));
        assertTrue(generated.contains("LookupEntity lookupEntity = recupererLookupEntity(id);"));
        assertEquals(1, occurrences(generated, "private LookupEntity recupererLookupEntity(Long id)"));
        assertEquals(1, occurrences(generated, "lookupEntityRepository.findById(id)"));
        assertTrue(generated.contains(
                "return lookupEntityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(\"LookupEntity\", id));"));
        assertFalse(generated.contains("LookupEntity lookupEntity = lookupEntityRepository.findById(id)"));
        assertFalse(generated.contains("lookupEntityRepository.findById(id)\n"));
    }

    @Test
    void keepsReferenceResolverLookupOnOneLine() throws IOException {
        Context context = new Context(tempDir.toString());

        ReferenceTarget referenceTarget = new ReferenceTarget();
        EntityWithReference entity = new EntityWithReference();
        context.addEntity(referenceTarget);
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModuleEntityWithReference", "test.entityWithReference");
        ViewModifierEntityWithReference view = new ViewModifierEntityWithReference();
        module.addPage(view);

        new UpdateAction(entity, view.element);
        context.initActions();

        new BeBusinessPrinter(context).print(entity);

        Path service = tempDir.resolve(
                "be/src/main/java/app/domain/test/entitywithreference/EntityWithReferenceService.java");
        String generated = Files.readString(service);

        assertTrue(generated.contains(
                "return referenceTargetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(\"ReferenceTarget\", id));"));
        assertFalse(generated.contains("referenceTargetRepository\n"));
    }

    private int occurrences(String value, String searched) {
        return value.split(java.util.regex.Pattern.quote(searched), -1).length - 1;
    }

    public static final class LookupEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ReferenceTarget extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class EntityWithReference extends Entity {
        public final Field code = Text("code").isId();
        public final Field referenceTarget = Ref(ReferenceTarget.class);
    }

    public static final class ViewConsulterLookupEntity extends ViewComposer<LookupEntity> {
    }

    public static final class ViewModifierEntityWithReference extends ViewComposer<EntityWithReference> {
    }
}
