package dev.cruding.engine.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.field.impl.RefList;
import dev.cruding.engine.gen.Context;

class RefFieldCopyTest {

    private TargetEntity target;
    private SourceEntity source;

    @BeforeEach
    void initializeEntities() {
        Context context = Context.init();
        target = new TargetEntity();
        source = new SourceEntity();
        context.addEntity(source);
        context.addEntity(target);
        context.initEntities();
    }

    @Test
    void preservesResolvedTargetsAndRoleNamesOnReferenceAndFatherCopies() {
        for (RefField<?> original : List.of(source.parent, source.origine, source.destination)) {
            RefField<?> copy = (RefField<?>) original.required().label("Copie").width(120);

            assertNotSame(original, copy);
            assertEquals(original.getClass(), copy.getClass());
            assertReferenceMetadata(original, copy);
            assertTrue(copy.required);
            assertFalse(original.required);
            assertEquals(0, original.width);
            for (String element : List.of(Element.FORM, Element.DETAIL, Element.TABLE)) {
                assertEquals(original.ui(element), copy.ui(element));
            }
        }
    }

    @Test
    void preservesReferenceMetadataWhenAdaptingToAListAndCopyingIt() {
        RefList<TargetEntity> list = new RefList<>(source.origine);
        RefField<?> copy = (RefField<?>) list.required().label("Origine");

        assertReferenceMetadata(source.origine, list);
        assertReferenceMetadata(list, copy);
        assertEquals(RefList.class, copy.getClass());
        assertEquals("ChampListe", copy.ui(Element.FORM));
        assertEquals(" liste={listeOrigine}", copy.getExtension());
        assertFalse(list.required);
        assertTrue(copy.required);
    }

    @Test
    void resolvesCopiesAndListVariantsDeclaredBeforeInitialization() {
        Context context = Context.init();
        target = new TargetEntity();
        CopiesEntity entity = new CopiesEntity();
        context.addEntity(entity);
        context.addEntity(target);
        context.initEntities();

        for (Field field : List.of(entity.parent, entity.origine, entity.destination)) {
            RefField<?> reference = (RefField<?>) field;
            assertSame(target, reference.referencedEntity);
            assertEquals(entity.uname, reference.containingEntity);
            assertEquals(reference.lname + "_id", reference.jcDbName);
            assertTrue(reference.required);
        }
    }

    private void assertReferenceMetadata(RefField<?> original, RefField<?> copy) {
        assertSame(target, original.referencedEntity);
        assertSame(target, copy.referencedEntity);
        assertEquals(original.lname, copy.lname);
        assertEquals(original.uname, copy.uname);
        assertEquals(original.jtype, copy.jtype);
        assertEquals(original.jstype, copy.jstype);
        assertEquals(original.dbName, copy.dbName);
        assertEquals(original.jcDbName, copy.jcDbName);
        assertEquals(original.dbTypeName, copy.dbTypeName);
        assertEquals(original.containingEntity, copy.containingEntity);
        assertEquals(original.containingEntityDbname, copy.containingEntityDbname);
        assertEquals(original.isRef, copy.isRef);
        assertEquals(original.isFather, copy.isFather);
    }

    public static final class TargetEntity extends Entity {
        public final Field code = Text().isId();
    }

    public static final class SourceEntity extends Entity {
        public final Field code = Text().isId();
        public final Father<TargetEntity> parent = Father(TargetEntity.class);
        public final Ref<TargetEntity> origine = Ref(TargetEntity.class);
        public final Ref<TargetEntity> destination = Ref(TargetEntity.class);
    }

    public static final class CopiesEntity extends Entity {
        public final Field code = Text().isId();
        public final Field parent = Father(TargetEntity.class).required();
        public final Field origine = Ref(TargetEntity.class).required();
        public final Field destination = new RefList<>(Ref(TargetEntity.class)).required();
    }
}
