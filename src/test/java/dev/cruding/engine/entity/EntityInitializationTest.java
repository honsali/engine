package dev.cruding.engine.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.gen.Context;

class EntityInitializationTest {

    @BeforeEach
    void initializeContext() {
        Context.init();
    }

    @Test
    void initializesTheDefaultSettingOutsideTheDslFields() {
        SimpleEntity entity = new SimpleEntity();
        Context.getInstance().addEntity(entity);
        Context.getInstance().initEntities();

        assertEquals(List.of(entity.code), entity.fieldList);
        assertSame(entity.id_, entity.setting);
        assertEquals("Long", entity.id_.jtype);
        assertEquals("string", entity.id_.jstype);
        assertEquals("bigint", entity.id_.stype);
        assertEquals(entity.uname, entity.id_.containingEntity);
        assertEquals(entity.dbName, entity.id_.containingEntityDbname);
        assertEquals("code", entity.lid);
    }

    @Test
    void bindsADeclaredSettingOnlyOnce() {
        ConfiguredEntity entity = new ConfiguredEntity();
        entity.configuration.label = "Configuration métier";
        entity.configuration.feminine = true;
        Context.getInstance().addEntity(entity);
        Context.getInstance().initEntities();

        assertEquals(List.of(entity.code), entity.fieldList);
        assertSame(entity.configuration, entity.id_);
        assertSame(entity.configuration, entity.setting);
        assertEquals(1, entity.configuration.bindingCount);
        assertEquals(entity.uname, entity.setting.containingEntity);
        assertEquals(entity.dbName, entity.setting.containingEntityDbname);
        assertEquals("Configuration métier", entity.setting.label);
        assertTrue(entity.setting.feminine);
    }

    @Test
    void keepsOwnAndInheritedDslFields() {
        ReferenceEntity entity = new ReferenceEntity();
        Context.getInstance().addEntity(entity);
        Context.getInstance().initEntities();

        assertEquals(2, entity.fieldList.size());
        assertTrue(entity.fieldList.containsAll(List.of(entity.description, entity.name)));
        assertEquals("libelle", entity.lid);
        assertEquals(entity.uname, entity.name.containingEntity);
    }

    @Test
    void bindsAFatherOnlyOnceAndAllowsSeveralReferencesToItsTarget() {
        SimpleEntity parent = new SimpleEntity();
        EntityWithRelations entity = new EntityWithRelations();
        Context.getInstance().addEntity(parent);
        Context.getInstance().addEntity(entity);
        Context.getInstance().initEntities();

        assertEquals(4, entity.fieldList.size());
        assertTrue(entity.fieldList.containsAll(List.of(entity.code, entity.parent, entity.origine, entity.destination)));
        assertSame(entity.parent, entity.father);
        assertEquals(1, entity.parent.bindingCount);
        assertTrue(entity.haveFather);
        assertEquals("parent", entity.lfather);
        assertEquals("Parent", entity.ufather);
        assertEquals("parent_id", entity.parent.jcDbName);
        assertSame(parent, entity.parent.referencedEntity);
        assertSame(parent, entity.origine.referencedEntity);
        assertSame(parent, entity.destination.referencedEntity);
    }

    @Test
    void rejectsTwoDeclaredFathers() {
        EntityWithTwoFathers entity = new EntityWithTwoFathers();
        Context.getInstance().addEntity(new SimpleEntity());
        Context.getInstance().addEntity(entity);

        EntityInitializationException error = assertThrows(
                EntityInitializationException.class, () -> Context.getInstance().initEntities());

        assertTrue(error.getMessage().contains(entity.uname));
        assertTrue(error.getMessage().contains("principal"));
        assertTrue(error.getMessage().contains("secondaire"));
    }

    @Test
    void rejectsAnAdditionalFatherAlongsideAnInheritedFather() {
        EntityWithInheritedFather entity = new EntityWithInheritedFather();
        Context.getInstance().addEntity(new SimpleEntity());
        Context.getInstance().addEntity(entity);

        EntityInitializationException error = assertThrows(
                EntityInitializationException.class, () -> Context.getInstance().initEntities());

        assertTrue(error.getMessage().contains(entity.uname));
        assertTrue(error.getMessage().contains("parent"));
        assertTrue(error.getMessage().contains("secondaire"));
    }

    public static class SimpleEntity extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class ConfiguredEntity extends SimpleEntity {
        public final CountingSetting configuration = new CountingSetting();
    }

    public static final class ReferenceEntity extends ReferenceData {
        public final Field description = Text("description");
    }

    public static class EntityWithRelations extends SimpleEntity {
        public final CountingFather parent = new CountingFather();
        public final Ref<SimpleEntity> origine = Ref(SimpleEntity.class);
        public final Ref<SimpleEntity> destination = Ref(SimpleEntity.class);
    }

    public static final class EntityWithTwoFathers extends SimpleEntity {
        public final Field principal = Father(SimpleEntity.class);
        public final Field secondaire = Father(SimpleEntity.class);
    }

    public static final class EntityWithInheritedFather extends EntityWithRelations {
        public final Field secondaire = Father(SimpleEntity.class);
    }

    public static final class CountingFather extends Father<SimpleEntity> {
        public int bindingCount;

        public CountingFather() {
            super(SimpleEntity.class);
        }

        @Override
        public Field containingEntity(Entity entity) {
            bindingCount++;
            return super.containingEntity(entity);
        }
    }

    public static final class CountingSetting extends Setting {
        public int bindingCount;

        @Override
        public Field containingEntity(Entity entity) {
            bindingCount++;
            return super.containingEntity(entity);
        }
    }
}
