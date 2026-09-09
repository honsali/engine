package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import dev.cruding.engine.printer.impl.entity.BeBusinessPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entity.BeMapperPrinter;
import dev.cruding.engine.printer.impl.entity.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeRequestPrinter;

class BeFormRequestPrinterTest {

    @TempDir
    Path tempDir;

    private final Path originalOutputRoot = EnginePaths.outputRoot;

    @AfterEach
    void restoreOutputRoot() {
        EnginePaths.outputRoot = originalOutputRoot;
    }

    @Test
    void generatesCreateAndUpdateContractsFromTheirForms() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();

        ReferenceTarget referenceTarget = new ReferenceTarget();
        FormEntity entity = new FormEntity();
        context.addEntity(referenceTarget);
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module("ModuleFormEntity", "test/formEntity");
        module.addPage(new ViewCreerFormEntity());
        module.addPage(new ViewModifierFormEntity());
        context.initPages();
        context.initActions();

        new BeRequestPrinter().print(entity);
        new BeMapperPrinter().print(entity);
        new BeBusinessPrinter().print(entity);
        new BeRepositoryPrinter().print(entity);

        Path packagePath = tempDir.resolve("be/src/main/java/app/domain/test/formentity");
        String createRequest = Files.readString(packagePath.resolve("FormEntityCreateRequest.java"));
        String updateRequest = Files.readString(packagePath.resolve("FormEntityUpdateRequest.java"));
        String mapper = Files.readString(packagePath.resolve("FormEntityMapper.java"));
        String service = Files.readString(packagePath.resolve("FormEntityService.java"));
        String repository = Files.readString(packagePath.resolve("FormEntityRepository.java"));

        assertTrue(createRequest.contains("String code,"));
        assertTrue(createRequest.contains("@Size(max = 250) String libelle,"));
        assertTrue(createRequest.contains("@NotBlank @Size(min = 8, max = 100) String password,"));
        assertTrue(createRequest.contains("@Valid Reference referenceTarget)"));
        assertTrue(createRequest.indexOf("String libelle") < createRequest.indexOf("String code"));
        assertFalse(createRequest.contains("internalNote"));
        assertFalse(createRequest.contains("active"));
        assertFalse(createRequest.contains("@NotBlank @Size(max = 250) String libelle"));

        assertTrue(updateRequest.contains("@NotBlank @Size(max = 250) String libelle,"));
        assertTrue(updateRequest.contains("@NotNull Boolean active,"));
        assertTrue(updateRequest.contains("@NotNull @PositiveOrZero Long version)"));
        assertFalse(updateRequest.contains("String code"));
        assertFalse(updateRequest.contains("internalNote"));
        assertFalse(updateRequest.contains("referenceTarget"));

        assertTrue(mapper.contains("public static FormEntity toEntity(FormEntityCreateRequest request, ReferenceTarget referenceTarget)"));
        assertTrue(mapper.contains("request.code(),\n                request.libelle(),\n                null,\n                null,\n                referenceTarget);"));
        assertTrue(mapper.contains("public static void toEntity(FormEntity formEntity, FormEntityUpdateRequest request)"));
        assertTrue(mapper.contains("formEntity.getCode(),\n                request.libelle(),\n                formEntity.getInternalNote(),\n                request.active(),\n                formEntity.getReferenceTarget());"));

        String updateService = service.substring(service.indexOf("public FormEntityResponse maj("));
        assertFalse(updateService.contains("request.code()"));
        assertFalse(updateService.contains("request.referenceTarget()"));
        assertTrue(repository.contains("boolean existsByCode(String code);"));
        assertFalse(repository.contains("existsByCodeAndIdNot"));
    }

    @Test
    void importsSizeForAFieldWithOnlyMinimumLength() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();

        MinimumLengthEntity entity = new MinimumLengthEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module("ModuleMinimumLengthEntity", "test/minimumLengthEntity");
        module.addPage(new ViewCreerMinimumLengthEntity());
        context.initPages();
        context.initActions();

        new BeRequestPrinter().print(entity);

        Path requestPath = tempDir.resolve(
                "be/src/main/java/app/domain/test/minimumlengthentity/MinimumLengthEntityCreateRequest.java");
        String createRequest = Files.readString(requestPath);

        assertTrue(createRequest.contains("import jakarta.validation.constraints.Size;"));
        assertTrue(createRequest.contains("@NotBlank @Size(min = 3) String description)"));
    }

    @Test
    void generatesTextStorageFromTheModelAndValidationFromEachForm() throws IOException {
        EnginePaths.outputRoot = tempDir;
        Context context = Context.init();

        TextLengthEntity entity = new TextLengthEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module("ModuleTextLengthEntity", "test/textLengthEntity");
        module.addPage(new ViewCreerTextLengthEntity());
        module.addPage(new ViewModifierTextLengthEntity());
        context.initPages();
        context.initActions();

        new BeRequestPrinter().print(entity);
        new BeLiqTablePrinter().print(entity);

        Path packagePath = tempDir.resolve("be/src/main/java/app/domain/test/textlengthentity");
        String createRequest = Files.readString(packagePath.resolve("TextLengthEntityCreateRequest.java"));
        String updateRequest = Files.readString(packagePath.resolve("TextLengthEntityUpdateRequest.java"));
        String table = Files.readString(tempDir.resolve(
                "be/src/main/resources/liquibase/changelog/textLengthEntity_table.xml"));

        assertTrue(createRequest.contains("@NotBlank @Size(max = 250) String code,"));
        assertTrue(createRequest.contains("@NotBlank @Size(max = 100) String libelle,"));
        assertTrue(createRequest.contains("@Size(max = 1000) String description)"));
        assertTrue(updateRequest.contains("@NotBlank @Size(max = 500) String libelle,"));
        assertTrue(updateRequest.contains("@Size(max = 1000) String description,"));

        assertTrue(table.contains("<column name=\"code\" type=\"nvarchar(250)\">"));
        assertTrue(table.contains("<column name=\"code\" type=\"nvarchar(250)\" />"));
        assertTrue(table.contains("<column name=\"libelle\" type=\"nvarchar(500)\">"));
        assertTrue(table.contains("<column name=\"libelle\" type=\"nvarchar(500)\" />"));
        assertTrue(table.contains("<column name=\"description\" type=\"text\">"));
        assertTrue(table.contains("<column name=\"description\" type=\"text\" />"));
        assertFalse(table.contains("nvarchar(100)"));
    }

    public static final class ReferenceTarget extends Entity {
        public final Field code = Text("code").isId();
    }

    public static final class FormEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field libelle = Text("libelle").required();
        public final Field internalNote = Text("internalNote");
        public final Field active = Boolean("active").required();
        public final Field referenceTarget = Ref(ReferenceTarget.class);
    }

    public static final class MinimumLengthEntity extends Entity {
        public final Field description = LongText("description").required().minLength("3").isId();
    }

    public static final class TextLengthEntity extends Entity {
        public final Field code = Text("code").isId();
        public final Field libelle = Text("libelle").maxLength("500").required();
        public final Field description = LongText("description").maxLength("1000");
    }

    public static final class ViewCreerFormEntity extends ViewComposer<FormEntity> {

        @Override
        public Component rootComponent() {
            FormEntity entity = entity(FormEntity.class);
            return block(
                    form(entity, entity.libelle.required(false), entity.code,
                            entity.Text("password").required().minLength("8").maxLength("100")),
                    form(entity, entity.referenceTarget),
                    element(createAction(entity)).byForm());
        }
    }

    public static final class ViewModifierFormEntity extends ViewComposer<FormEntity> {

        @Override
        public Component rootComponent() {
            FormEntity entity = entity(FormEntity.class);
            return block(
                    form(entity, entity.code.readOnly(), entity.libelle, entity.active, hidden(entity.id_)),
                    element(updateAction(entity)).byForm());
        }
    }

    public static final class ViewCreerMinimumLengthEntity extends ViewComposer<MinimumLengthEntity> {

        @Override
        public Component rootComponent() {
            MinimumLengthEntity entity = entity(MinimumLengthEntity.class);
            return block(
                    form(entity, entity.description),
                    element(createAction(entity)).byForm());
        }
    }

    public static final class ViewCreerTextLengthEntity extends ViewComposer<TextLengthEntity> {

        @Override
        public Component rootComponent() {
            TextLengthEntity entity = entity(TextLengthEntity.class);
            return block(
                    form(entity, entity.code, entity.libelle.maxLength("100"), entity.description),
                    element(createAction(entity)).byForm());
        }
    }

    public static final class ViewModifierTextLengthEntity extends ViewComposer<TextLengthEntity> {

        @Override
        public Component rootComponent() {
            TextLengthEntity entity = entity(TextLengthEntity.class);
            return block(
                    form(entity, entity.code.readOnly(), entity.libelle, entity.description, hidden(entity.id_)),
                    element(updateAction(entity)).byForm());
        }
    }
}
