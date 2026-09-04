package model.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.ViewComposer;
import dev.cruding.engine.printer.impl.entity.BeBusinessPrinter;
import dev.cruding.engine.printer.impl.entity.BeMapperPrinter;
import dev.cruding.engine.printer.impl.entity.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeRequestPrinter;

class BeFormRequestPrinterTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesCreateAndUpdateContractsFromTheirForms() throws IOException {
        Context context = new Context(tempDir.toString());

        ReferenceTarget referenceTarget = new ReferenceTarget();
        FormEntity entity = new FormEntity();
        context.addEntity(referenceTarget);
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModuleFormEntity", "test.formEntity");
        module.addPage(new ViewCreerFormEntity());
        module.addPage(new ViewModifierFormEntity());
        context.initPages();
        context.initActions();

        new BeRequestPrinter(context).print(entity);
        new BeMapperPrinter(context).print(entity);
        new BeBusinessPrinter(context).print(entity);
        new BeRepositoryPrinter(context).print(entity);

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
        Context context = new Context(tempDir.toString());

        MinimumLengthEntity entity = new MinimumLengthEntity();
        context.addEntity(entity);
        context.initEntities();

        Module module = new Module(context, "ModuleMinimumLengthEntity", "test.minimumLengthEntity");
        module.addPage(new ViewCreerMinimumLengthEntity());
        context.initPages();
        context.initActions();

        new BeRequestPrinter(context).print(entity);

        Path requestPath = tempDir.resolve(
                "be/src/main/java/app/domain/test/minimumlengthentity/MinimumLengthEntityCreateRequest.java");
        String createRequest = Files.readString(requestPath);

        assertTrue(createRequest.contains("import jakarta.validation.constraints.Size;"));
        assertTrue(createRequest.contains("@NotBlank @Size(min = 3) String description)"));
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
}
