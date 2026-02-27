package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import java.util.Optional;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeDtoPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Field> fieldList = entity.fieldList;
        for (Field field : fieldList) {
            field.addDtoImport(f);
            if (field.required) {
                f.addJavaImport("jakarta.validation.constraints.NotNull");
            }
        }

        /* *********************************************************************** */
        Optional<Field> idField = entity.fieldList.stream().filter(p -> p.isId).findFirst();

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public record ", entity.uname, "Dto(//");
        f.L________("Long id, //");
        f.L________("Long id", entity.uname, ", //");
        if (idField.isPresent() && !idField.get().lname.equals("libelle")) {
            f.L________("String libelle, //");
        }
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? " //" : ", //";
            if (field.isRef || field.isFather) {
                f.L________(field.jtype, "Dto ", field.lname, end);

            } else {
                if (field.required) {
                    f.L________("@NotNull");
                }
                f.L________(field.jtype, " ", field.lname, end);
            }
        }
        f.L(") {");
        f.L("");

        if (!entity.isReferenceData()) {
            f.L____("public static ", entity.uname, "Dto toDto(", entity.uname, " entity) {");
            f.L________("return entity == null ? null");
            f.L________________(": new ", entity.uname, "Dto(//");
            f.L________________________("entity.getId(), //");
            f.L________________________("entity.getId(), //");
            if (idField.isPresent() && !idField.get().lname.equals("libelle")) {
                f.L________________________("entity.getDisplayString(), //");
            }
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                String end = i == fieldList.size() - 1 ? " //" : ", //";
                if (field.isRef || field.isFather) {
                    f.L________________________(field.jtype, "Dto.toDtoAsRef(entity.get", field.uname, "())", end);
                } else {
                    f.L________________________("entity.get", field.uname, "()", end);

                }
            }
            f.L________________(");");
            f.L____("}");
            f.L("");
        }
        f.L____("public static ", entity.uname, "Dto toDtoAsRef(", entity.uname, " entity) {");
        f.L________("return entity == null ? null");
        f.L________________(": new ", entity.uname, "Dto(//");
        f.L________________________("entity.getId(), //");
        f.L________________________("entity.getId(), //");
        if (idField.isPresent() && !idField.get().lname.equals("libelle")) {
            f.L________________________("entity.getDisplayString(), //");
        }
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? " //" : ", //";
            if (field.isId) {
                f.L________________________("entity.get", field.uname, "()", end);
            } else {
                f.L________________________("null", end);
            }
        }
        f.L________________(");");
        f.L____("}");

        f.L("");

        if (!entity.isReferenceData()) {
            f.L____("public static ", entity.uname, " toEntity(", entity.uname, "Dto dto) {");
            f.L________("if (dto == null) {");
            f.L____________("return null;");
            f.L________("}");
            f.L("");
            f.L________(entity.uname, " entity = new ", entity.uname, "();");
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);

                if (field.isRef || field.isFather) {
                    f.L________("entity.set", field.uname, "(", field.uname, "Dto.toEntityAsRef(dto.", field.lname, "()));");
                } else {
                    f.L________("entity.set", field.uname, "(dto.", field.lname, "());");

                }
            }
            f.L________("return entity;");
            f.L____("}");
            f.L("");
            f.L____("public static ", entity.uname, " toEntity(", entity.uname, "Dto dto, Long id) {");
            f.L________(entity.uname, " entity = toEntity(dto);");
            f.L________("if (entity != null) {");
            f.L____________("entity.setId(id);");
            f.L________("}");
            f.L________("return entity;");
            f.L____("}");
            f.L("");
        }
        f.L____("public static ", entity.uname, " toEntityAsRef(", entity.uname, "Dto dto) {");
        f.L________("if (dto == null || dto.id() == null) {");
        f.L____________("return null;");
        f.L________("}");
        f.L("");
        f.L________(entity.uname, " entity = new ", entity.uname, "();");
        f.L________("entity.setId(dto.id());");
        f.L________("return entity;");
        f.L____("}");

        f.L("}");
        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Dto.java");
    }

}
