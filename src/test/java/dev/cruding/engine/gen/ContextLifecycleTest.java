package dev.cruding.engine.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.inViewOnly.EmptyAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

class ContextLifecycleTest {

    @TempDir
    Path tempDir;

    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    @Test
    void startsTheNextGenerationWithFreshState() {
        EnginePaths.outputRoot = tempDir;
        Context first = Context.init();
        assertSame(first, Context.getInstance());

        LifecycleEntity firstEntity = new LifecycleEntity();
        first.addEntity(firstEntity);
        first.initEntities();
        assertEquals("lifecycle_entity", firstEntity.dbName);

        Module firstModule = new Module("ModuleLifecycle", "lifecycle");
        ViewListerLifecycleEntity firstView = new ViewListerLifecycleEntity();
        Page firstPage = firstModule.addPage(firstView);
        PageRef pageReference = new PageRef("PageListerLifecycleEntity");
        assertSame(firstPage, first.getPage(pageReference));

        Action firstAction = new EmptyAction(ActionType.NORMAL, "charger", firstEntity, firstView.element) {
            @Override
            public int hashCode() {
                assertNotNull(id, "Action id must be assigned before insertion into the set");
                return super.hashCode();
            }
        };
        assertSame(firstAction, firstAction.targetPage(pageReference));
        assertSame(firstPage, firstAction.targetPage);
        first.addLabel("ModuleLifecycle", "titre", "Première génération");
        assertEquals(List.of(firstAction), first.actionEntity(firstEntity));
        assertEquals("0", firstAction.id);

        Action secondAction = new EmptyAction(ActionType.NORMAL, "charger", firstEntity, firstView.element);
        assertEquals("1", secondAction.id);
        assertEquals(List.of(firstAction, secondAction), first.actionEntity(firstEntity));

        Context current = Context.init();
        assertSame(current, Context.getInstance());
        assertNotSame(first, current);
        assertEquals(tempDir, EnginePaths.outputRoot);
        assertTrue(current.getEntityList().isEmpty());
        assertTrue(current.getModuleList().isEmpty());
        assertTrue(current.getPageList().isEmpty());
        assertNull(current.getLabelMap("ModuleLifecycle"));
        assertTrue(current.actionEntity(firstEntity).isEmpty());

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
        assertSame(currentAction, currentAction.targetPage(pageReference));
        assertSame(currentPage, currentAction.targetPage);
        assertEquals("0", currentAction.id);
        assertEquals(List.of(currentAction), current.actionEntity(currentEntity));
    }

    public static final class LifecycleEntity extends Entity {
        public final Field code = Text().isId();
    }

    public static final class ViewListerLifecycleEntity extends ViewComposer<LifecycleEntity> {
    }
}
