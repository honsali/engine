package dev.cruding.engine.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.inViewOnly.EmptyAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

class ContextLifecycleTest {

    @TempDir
    Path tempDir;

    @Test
    void startsTheNextGenerationWithFreshState() {
        Context first = Context.init(tempDir.resolve("first").toString());
        assertSame(first, Context.getInstance());
        first.getDbNameMapper().setLegacyDbMap(Map.of("LifecycleEntity.table", "legacy_entity"));

        LifecycleEntity firstEntity = new LifecycleEntity();
        first.addEntity(firstEntity);
        first.initEntities();
        assertEquals("legacy_entity", firstEntity.dbName);

        Module firstModule = new Module("ModuleLifecycle", "lifecycle");
        ViewListerLifecycleEntity firstView = new ViewListerLifecycleEntity();
        Page firstPage = firstModule.addPage(firstView);
        PageRef pageReference = new PageRef("PageListerLifecycleEntity");
        assertSame(firstPage, first.getPage(pageReference));

        Action firstAction = new EmptyAction(ActionType.NORMAL, "charger", firstEntity, firstView.element);
        first.addLabel("ModuleLifecycle", "titre", "Première génération");
        assertEquals(List.of(firstAction), first.actionEntity(firstEntity));
        assertEquals("1", first.nextActionId());

        Context current = Context.init(tempDir.resolve("second").toString());
        assertSame(current, Context.getInstance());
        assertNotSame(first, current);
        assertEquals(tempDir.resolve("second").toString(), current.getBasePath());
        assertTrue(current.getEntityList().isEmpty());
        assertTrue(current.getModuleList().isEmpty());
        assertTrue(current.getPageList().isEmpty());
        assertNull(current.getLabelMap("ModuleLifecycle"));
        assertTrue(current.actionEntity(firstEntity).isEmpty());
        assertEquals("0", current.nextActionId());

        LifecycleEntity currentEntity = new LifecycleEntity();
        current.addEntity(currentEntity);
        current.initEntities();
        assertSame(currentEntity, current.getEntity(LifecycleEntity.class));
        assertEquals("lifecycle_entity", currentEntity.dbName);

        Module currentModule = new Module("ModuleLifecycle", "lifecycle");
        ViewListerLifecycleEntity currentView = new ViewListerLifecycleEntity();
        Page currentPage = currentModule.addPage(currentView);
        assertSame(currentPage, current.getPage(pageReference));

        Action currentAction = new EmptyAction(ActionType.NORMAL, "charger", currentEntity, currentView.element);
        assertEquals(List.of(currentAction), current.actionEntity(currentEntity));
    }

    public static final class LifecycleEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ViewListerLifecycleEntity extends ViewComposer<LifecycleEntity> {
    }
}
