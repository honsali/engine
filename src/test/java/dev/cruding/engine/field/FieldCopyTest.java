package dev.cruding.engine.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.impl.ArabicText;
import dev.cruding.engine.field.impl.Hidden;
import dev.cruding.engine.field.impl.Hour;
import dev.cruding.engine.field.impl.Tag;
import dev.cruding.engine.field.impl.Text;
import dev.cruding.engine.field.impl.TextArray;

class FieldCopyTest {

    @Test
    void preservesTextWhenCustomizingACopy() {
        assertPreservesField(new Text("nom"));
    }

    @Test
    void preservesArabicTextWhenCustomizingACopy() {
        assertPreservesField(new ArabicText("nomArabe"));
    }

    @Test
    void preservesHourWhenCustomizingACopy() {
        assertPreservesField(new Hour("heure"));
    }

    @Test
    void preservesHiddenWhenCustomizingACopy() {
        assertPreservesField(new Hidden(new Text("id")));
    }

    @Test
    void preservesTextArrayWhenCustomizingACopy() {
        assertPreservesField(new TextArray("motsCles"));
    }

    @Test
    void preservesTagWhenCustomizingACopy() {
        assertPreservesField(new Tag(new Text("statut")));
    }

    private void assertPreservesField(Field original) {
        Field labelled = original.label("Libellé");
        Field required = labelled.required();
        Field sized = required.width(120);
        Field optional = required.required(false);

        for (Field copy : List.of(labelled, required, sized, optional)) {
            assertNotSame(original, copy);
            assertEquals(original.getClass(), copy.getClass());
            assertEquals(original.lname, copy.lname);
            assertEquals(original.uname, copy.uname);
            assertEquals(original.dbName, copy.dbName);
            assertEquals(original.jtype, copy.jtype);
            assertEquals(original.jstype, copy.jstype);
            assertEquals(original.stype, copy.stype);
            assertEquals(original.isBasic, copy.isBasic);
            assertEquals(original.isText, copy.isText);
            assertEquals(original.isDate, copy.isDate);
            assertEquals(original.maxLength, copy.maxLength);
            assertEquals("Libellé", copy.label);
            for (String element : List.of(Element.FORM, Element.DETAIL, Element.TABLE)) {
                assertEquals(original.ui(element), copy.ui(element), element);
            }
        }

        assertNotSame(labelled, required);
        assertNotSame(required, sized);
        assertNotSame(required, optional);
        assertNull(original.label);
        assertFalse(original.required);
        assertEquals(0, original.width);
        assertFalse(labelled.required);
        assertEquals(0, labelled.width);
        assertTrue(required.required);
        assertEquals(0, required.width);
        assertTrue(sized.required);
        assertEquals(120, sized.width);
        assertFalse(optional.required);
        assertEquals(0, optional.width);
    }
}
