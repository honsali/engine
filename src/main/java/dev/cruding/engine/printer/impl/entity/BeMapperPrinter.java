package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeMapperPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Field> fieldList = entity.fieldList;
        f.addJavaImport("java.util.NoSuchElementException");
        f.addJavaImport("org.springframework.stereotype.Component");

        /* *********************************************************************** */

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@Component");
        f.L("public class ", entity.uname, "Mapper {");
        f.L("");

        f.L____("private final ", entity.uname, "Repository ", entity.lname, "Repository;");
        f.L("");
        f.L____("public ", entity.uname, "Mapper(", entity.uname, "Repository ", entity.lname, "Repository) {");
        f.L________("this.", entity.lname, "Repository = ", entity.lname, "Repository;");
        f.L____("}");
        f.L("");


        if (!entity.isReferenceData()) {
            f.L____("public ", entity.uname, "Dto toDto(", entity.uname, " entity) {");
            f.L________("return entity == null ? null");
            f.L________________(": new ", entity.uname, "Dto(");
            f.L________________________("entity.getId(),");
            f.L________________________("entity.getId(),");
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                String end = i == fieldList.size() - 1 ? "" : ",";
                if (field.isRef || field.isFather) {
                    f.L________________________(field.lname, "Mapper.toDtoAsRef(entity.get", field.uname, "())", end);
                } else {
                    f.L________________________("entity.get", field.uname, "()", end);

                }
            }
            f.__(");");
            f.L____("}");
            f.L("");
        }
        f.L____("public ", entity.uname, "Dto toDtoAsRef(", entity.uname, " entity) {");
        f.L________("return entity == null ? null");
        f.L________________(": new ", entity.uname, "Dto(");
        f.L________________________("entity.getId(),");
        f.L________________________("entity.getId(),");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? "" : ",";
            if (field.isId) {
                f.L________________________("entity.get", field.uname, "()", end);
            } else {
                f.L________________________("null", end);
            }
        }
        f.__(");");
        f.L____("}");

        f.L("");

        if (!entity.isReferenceData()) {
            f.L____("public ", entity.uname, " toEntity(", entity.uname, "Dto dto) {");
            f.L________("if (dto == null) {");
            f.L____________("return null;");
            f.L________("}");
            f.L("");
            f.L________(entity.uname, " entity = new ", entity.uname, "();");
            f.L________("copyToEntity(dto, entity);");
            f.L________("return entity;");
            f.L____("}");
            f.L("");
        }


        f.L____("public ", entity.uname, " toEntityAsRef(", entity.uname, "Dto dto) {");
        f.L________("if (dto == null || dto.id() == null) {");
        f.L____________("return null;");
        f.L________("}");
        f.L________("return ", entity.lname, "Repository.findById(dto.id()).orElseThrow(() -> new NoSuchElementException(\"", entity.uname, " not found\"));");
        f.L____("}");

        if (!entity.isReferenceData()) {
            f.L("");
            f.L____("public void copyToEntity(", entity.uname, "Dto dto, ", entity.uname, " entity) {");
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);

                if (field.isRef || field.isFather) {
                    f.L________("entity.set", field.uname, "(", field.lname, "Mapper.toEntityAsRef(dto.", field.lname, "()));");
                } else {
                    f.L________("entity.set", field.uname, "(dto.", field.lname, "());");

                }
            }
            f.L____("}");
        }
        f.L("}");
        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Mapper.java");
    }

}
