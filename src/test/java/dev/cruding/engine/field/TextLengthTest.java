package dev.cruding.engine.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import dev.cruding.engine.field.impl.LongText;
import dev.cruding.engine.field.impl.Text;

class TextLengthTest {

    @Test
    void defaultsTo250ForValidationAndStorage() {
        Field field = new Text("libelle");

        assertEquals("250", field.maxLength);
        assertEquals("nvarchar(250)", field.stype);
    }

    @Test
    void keepsLengthAndStorageAlignedOnIndependentCopies() {
        Field original = new Text("libelle");
        Field model = original.required().maxLength("500").minLength("3");
        Field form = model.maxLength("100").label("Libellé court");

        assertNotSame(original, model);
        assertNotSame(model, form);
        assertEquals("250", original.maxLength);
        assertEquals("nvarchar(250)", original.stype);
        assertEquals("500", model.maxLength);
        assertEquals("nvarchar(500)", model.stype);
        assertEquals("100", form.maxLength);
        assertEquals("nvarchar(100)", form.stype);
        assertEquals("3", form.minLength);
    }

    @Test
    void keepsLongTextStorageWhenAddingAMaximumLength() {
        Field original = new LongText("description");
        Field limited = original.maxLength("1000").required();

        assertNotSame(original, limited);
        assertNull(original.maxLength);
        assertEquals("text", original.stype);
        assertEquals("1000", limited.maxLength);
        assertEquals("text", limited.stype);
    }
}
