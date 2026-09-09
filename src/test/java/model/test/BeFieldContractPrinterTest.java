package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.entity.BeDomainPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entity.BeRequestPrinter;
import dev.cruding.engine.printer.impl.entity.BeResponsePrinter;

class BeFieldContractPrinterTest {

    @TempDir
    Path tempDir;

    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    @Test
    void generatesHoursAsLocalTimeAndSqlTime() throws IOException {
        HourEntity entity = new HourEntity();
        initialize(entity, new ViewCreerHourEntity());

        new BeDomainPrinter().print(entity);
        new BeResponsePrinter().print(entity);
        new BeRequestPrinter().print(entity);
        new BeLiqTablePrinter().print(entity);

        Path packagePath = tempDir.resolve("be/src/main/java/app/domain/test/hourentity");
        for (String file : List.of("HourEntity.java", "HourEntityResponse.java", "HourEntityCreateRequest.java")) {
            String generated = Files.readString(packagePath.resolve(file));
            assertTrue(generated.contains("import java.time.LocalTime;"), file);
            assertTrue(generated.contains("LocalTime heure"), file);
            assertFalse(generated.contains("LocalDate"), file);
        }
        String table = Files.readString(tempDir.resolve(
                "be/src/main/resources/liquibase/changelog/hourEntity_table.xml"));
        assertTrue(table.contains("<column name=\"heure\" type=\"time\">"));
        assertTrue(table.contains("<column name=\"heure\" type=\"time\" />"));
    }

    @Test
    void importsTemporalTypesEvenForTransientFields() throws IOException {
        TransientHourEntity entity = new TransientHourEntity();
        initialize(entity);
        new BeDomainPrinter().print(entity);

        String domain = Files.readString(tempDir.resolve(
                "be/src/main/java/app/domain/test/transienthourentity/TransientHourEntity.java"));
        assertTrue(domain.contains("import java.time.LocalTime;"));
        assertTrue(domain.contains("@Transient\n    private LocalTime heure;"));
    }

    @Test
    void alignsTextVariantStorageWithModelLengthsAndKeepsFormCopiesIndependent() throws IOException {
        TextVariantsEntity entity = new TextVariantsEntity();
        initialize(entity, new ViewCreerTextVariantsEntity());

        new BeRequestPrinter().print(entity);
        new BeLiqTablePrinter().print(entity);

        String request = Files.readString(tempDir.resolve(
                "be/src/main/java/app/domain/test/textvariantsentity/TextVariantsEntityCreateRequest.java"));
        String table = Files.readString(tempDir.resolve(
                "be/src/main/resources/liquibase/changelog/textVariantsEntity_table.xml"));
        for (String name : List.of("arabe", "email", "tel", "choix")) {
            assertTrue(request.contains("@Size(max = 100) String " + name), name);
            assertTrue(table.contains("<column name=\"" + name + "\" type=\"nvarchar(500)\">"), name);
            assertTrue(table.contains("<column name=\"" + name + "\" type=\"nvarchar(500)\" />"), name);
        }
        assertTrue(request.contains("@NotBlank @Size(max = 100) String arabe"));
        assertTrue(request.contains("@NotBlank @Size(max = 100) String email"));
        assertTrue(request.contains("@NotBlank @Size(max = 100) String tel"));
        assertTrue(request.contains("@NotNull @Size(max = 100) String choix"));
        assertFalse(table.contains("nvarchar(100)"));
    }

    @Test
    void excludesAReadOnlySettingFromTheFormWriteContract() throws IOException {
        ReadOnlyEntity entity = new ReadOnlyEntity();
        initialize(entity, new ViewCreerReadOnlyEntity());
        new BeRequestPrinter().print(entity);

        String request = Files.readString(tempDir.resolve(
                "be/src/main/java/app/domain/test/readonlyentity/ReadOnlyEntityCreateRequest.java"));
        assertTrue(request.contains("String code)"));
        assertFalse(request.contains("Long id"));
    }

    private void initialize(Entity entity, ViewComposer<?>... views) {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();
        context.addEntity(entity);
        context.initEntities();
        Module module = new Module("ModuleFields", "test/fields");
        for (ViewComposer<?> view : views) {
            module.addPage(view);
        }
        context.initPages();
        context.initActions();
    }

    public static final class HourEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field heure = Hour("heure").required();
    }

    public static final class TransientHourEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field heure = Hour("heure").tranzient();
    }

    public static final class TextVariantsEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field arabe = ArabicText("arabe").maxLength("500").required();
        public final Field email = Email("email").maxLength("500").required();
        public final Field tel = Tel("tel").maxLength("500").required();
        public final Field choix = StaticList("choix", "radioVertical").maxLength("500").required();
    }

    public static final class ReadOnlyEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field configuration = Setting().readOnly().label("Structure");
    }

    public static final class ViewCreerHourEntity extends ViewComposer<HourEntity> {

        public Component rootComponent() {
            HourEntity e = entity(HourEntity.class);
            return block(form(e, e.code, e.heure), element(createAction(e)).byForm());
        }
    }

    public static final class ViewCreerTextVariantsEntity extends ViewComposer<TextVariantsEntity> {

        public Component rootComponent() {
            TextVariantsEntity e = entity(TextVariantsEntity.class);
            return block(form(e, e.code, e.arabe.maxLength("100"), e.email.maxLength("100"),
                    e.tel.maxLength("100"), e.choix.maxLength("100")), element(createAction(e)).byForm());
        }
    }

    public static final class ViewCreerReadOnlyEntity extends ViewComposer<ReadOnlyEntity> {

        public Component rootComponent() {
            ReadOnlyEntity e = entity(ReadOnlyEntity.class);
            return block(form(e, e.code, e.id_), element(createAction(e)).byForm());
        }
    }
}
