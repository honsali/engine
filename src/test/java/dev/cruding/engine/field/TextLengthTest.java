package dev.cruding.engine.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.impl.ArabicText;
import dev.cruding.engine.field.impl.Email;
import dev.cruding.engine.field.impl.LongText;
import dev.cruding.engine.field.impl.StaticList;
import dev.cruding.engine.field.impl.Tel;
import dev.cruding.engine.field.impl.Text;

class TextLengthTest {

    @Test
    void defaultsTo250ForValidationAndStorage() {
        for (Field field : boundedTexts()) {
            assertEquals(250, field.maxLength, field.getClass().getSimpleName());
            assertNull(field.minLength);
            assertEquals("nvarchar(250)", field.stype);
        }
    }

    @Test
    void keepsLengthAndStorageAlignedOnIndependentCopies() {
        for (Field original : boundedTexts()) {
            Field model = original.required().maxLength(500).minLength(3);
            Field form = model.maxLength(100).label("Libellé court");

            assertNotSame(original, model);
            assertNotSame(model, form);
            assertEquals(250, original.maxLength, original.getClass().getSimpleName());
            assertNull(original.minLength);
            assertEquals("nvarchar(250)", original.stype);
            assertEquals(500, model.maxLength);
            assertEquals(3, model.minLength);
            assertEquals("nvarchar(500)", model.stype);
            assertEquals(100, form.maxLength);
            assertEquals("nvarchar(100)", form.stype);
            assertEquals(3, form.minLength);
            assertEquals(original.getClass(), form.getClass());
            assertEquals(original.isText, form.isText);
            assertEquals(original.getExtension(), form.getExtension());
            for (String element : List.of(Element.FORM, Element.DETAIL, Element.TABLE)) {
                assertEquals(original.ui(element), form.ui(element));
            }
        }
    }

    @Test
    void keepsStaticListRenderingOptionsOnIndependentUnnamedCopies() {
        StaticList original = new StaticList();
        StaticList radio = original.type("radioVertical");
        StaticList check = radio.type("checkVertical");
        Field copy = radio.required().maxLength(100).lname("choix");

        assertNotSame(original, radio);
        assertNotSame(radio, check);
        assertEquals("ChampListe", original.ui(Element.FORM));
        assertEquals("ChampListeRadio", radio.ui(Element.FORM));
        assertEquals("ChampListeCheck", check.ui(Element.FORM));
        assertEquals("ChampListeRadio", copy.ui(Element.FORM));
        assertEquals(" liste={listeChoix} direction=\"vertical\"", copy.getExtension());
        assertNull(radio.lname);
    }

    @Test
    void keepsLongTextStorageWhenAddingAMaximumLength() {
        Field original = new LongText().lname("description");
        Field limited = original.maxLength(1000).required();

        assertNotSame(original, limited);
        assertNull(original.maxLength);
        assertEquals("text", original.stype);
        assertEquals(1000, limited.maxLength);
        assertEquals("text", limited.stype);
    }

    private List<Field> boundedTexts() {
        return List.of(new Text().lname("libelle"), new ArabicText().lname("libelle"), new Email().lname("libelle"),
                new Tel().lname("libelle"), new StaticList().lname("libelle"),
                new StaticList().type("radioVertical").lname("libelle"),
                new StaticList().type("checkVertical").lname("libelle"));
    }
}
