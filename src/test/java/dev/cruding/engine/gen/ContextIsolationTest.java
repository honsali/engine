package dev.cruding.engine.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

class ContextIsolationTest {

    @TempDir
    Path tempDir;

    @Test
    void keepsGenerationStateIndependentAcrossContexts() {
        DbNameMapper firstDbNames = new DbNameMapper();
        firstDbNames.setLegacyDbMap(Map.of("IsolatedEntity.table", "legacy_isolated_entity"));

        Context first = new Context(tempDir.resolve("first").toString(), firstDbNames);
        Context second = new Context(tempDir.resolve("second").toString());

        IsolatedEntity firstEntity = new IsolatedEntity();
        IsolatedEntity secondEntity = new IsolatedEntity();
        first.addEntity(firstEntity);
        second.addEntity(secondEntity);
        first.initEntities();
        second.initEntities();

        assertSame(firstEntity, first.getEntity(IsolatedEntity.class));
        assertSame(secondEntity, second.getEntity(IsolatedEntity.class));
        assertEquals("legacy_isolated_entity", firstEntity.dbName);
        assertEquals("isolated_entity", secondEntity.dbName);

        Module firstModule = new Module(first, "ModuleIsolation", "isolation");
        Module secondModule = new Module(second, "ModuleIsolation", "isolation");
        ViewListerIsolatedEntity firstView = new ViewListerIsolatedEntity();
        ViewListerIsolatedEntity secondView = new ViewListerIsolatedEntity();
        Page firstPage = firstModule.addPage(firstView);
        Page secondPage = secondModule.addPage(secondView);

        PageRef pageReference = new PageRef("PageListerIsolatedEntity");
        assertSame(firstPage, first.getPage(pageReference));
        assertSame(secondPage, second.getPage(pageReference));

        Action firstAction = new EmptyAction(ActionType.NORMAL, "charger", firstEntity, firstView.element);
        Action secondAction = new EmptyAction(ActionType.NORMAL, "charger", secondEntity, secondView.element);

        assertEquals(List.of(firstAction), first.actionEntity(firstEntity));
        assertEquals(List.of(secondAction), second.actionEntity(secondEntity));
        assertEquals(0, Action.ORDER_BY_NAME.compare(firstAction, secondAction));
        assertNotEquals(firstAction, secondAction);

        assertThrows(ContextException.class, () -> first.getPageList(secondModule));
        assertThrows(ContextException.class, () -> first.actionEntity(secondEntity));
        assertThrows(ContextException.class, () -> first.actionPage(secondPage));
        assertThrows(IllegalArgumentException.class,
                () -> new EmptyAction(ActionType.NORMAL, "invalide", secondEntity, firstView.element));
        assertThrows(IllegalArgumentException.class, () -> firstAction.targetPage(secondPage));
    }

    public static final class IsolatedEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ViewListerIsolatedEntity extends ViewComposer<IsolatedEntity> {
    }
}
