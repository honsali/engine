package dev.cruding.engine.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.FieldFactory;
import dev.cruding.engine.field.impl.ArabicText;
import dev.cruding.engine.field.impl.Custom;
import dev.cruding.engine.field.impl.Hidden;
import dev.cruding.engine.field.impl.Hour;
import dev.cruding.engine.field.impl.Render;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.field.impl.Tag;
import dev.cruding.engine.field.impl.Text;
import dev.cruding.engine.field.impl.TextArray;

class FieldCopyTest {

    @Test
    void copiesAllSimpleFieldsBeforeTheyAreNamed() {
        FieldFactory factory = new FieldFactory();
        for (Field field : List.of(factory.Text(), factory.ArabicText(), factory.Year(),
                factory.Double(), factory.Int(), factory.Date(), factory.Tel(), factory.Email(),
                factory.Hour(), factory.File(), factory.StaticList(), factory.TextArray(),
                factory.Boolean(), factory.LongText(), new Hidden(factory.Text()),
                new Custom(factory.Text()), new Render(factory.Text()), new Tag(factory.Text()))) {
            assertNull(field.lname);
            assertNull(field.uname);
            assertNull(field.dbName);
            assertPreservesField(field);
        }
    }

    @Test
    void preservesTextWhenCustomizingACopy() {
        assertPreservesField(new Text().lname("nom"));
    }

    @Test
    void preservesArabicTextWhenCustomizingACopy() {
        assertPreservesField(new ArabicText().lname("nomArabe"));
    }

    @Test
    void preservesHourWhenCustomizingACopy() {
        assertPreservesField(new Hour().lname("heure"));
    }

    @Test
    void preservesHiddenWhenCustomizingACopy() {
        assertPreservesField(new Hidden(new Text().lname("id")));
    }

    @Test
    void preservesTextArrayWhenCustomizingACopy() {
        assertPreservesField(new TextArray().lname("motsCles"));
    }

    @Test
    void preservesTagWhenCustomizingACopy() {
        assertPreservesField(new Tag(new Text().lname("statut")));
    }

    @Test
    void preservesSettingIdentityAndGrammaticalOptionsOnCopies() {
        assertPreservesField(new Setting());

        Setting original = new Setting();
        Setting feminine = original.feminine();
        Setting vowel = original.vowel();
        Setting labelled = (Setting) feminine.label("Structure");

        for (Setting setting : List.of(original, feminine, vowel, labelled)) {
            assertEquals("id", setting.lname);
            assertEquals("Long", setting.jtype);
            assertEquals("string", setting.jstype);
            assertEquals("bigint", setting.stype);
        }
        assertNotSame(original, feminine);
        assertNotSame(original, vowel);
        assertNotSame(feminine, labelled);
        assertEquals("ce", original.that());
        assertEquals("cette", feminine.that());
        assertEquals("cet", vowel.that());
        assertEquals("cette", labelled.that());
        assertEquals("Structure", labelled.label);
        assertNull(original.label);
        assertNull(feminine.label);
    }

    @Test
    void sharesTheSameReadOnlyFlagBetweenSettingAndFieldOnCopies() {
        Setting original = new Setting();
        Setting readOnly = original.readOnly();
        Field labelled = readOnly.label("Établissement").required();

        assertFalse(original.readOnly);
        assertFalse(((Field) original).readOnly);
        assertTrue(readOnly.readOnly);
        assertTrue(((Field) readOnly).readOnly);
        assertTrue(labelled.readOnly);
        assertTrue(((Setting) labelled).readOnly);
    }

    @Test
    void keepsAutomaticGrammaticalDefaults() {
        assertEquals("ce", new Setting().init("Departement").that());
        assertEquals("cet", new Setting().init("Employe").that());
        assertEquals("cette", new Setting().feminine().init("Adresse").that());
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
