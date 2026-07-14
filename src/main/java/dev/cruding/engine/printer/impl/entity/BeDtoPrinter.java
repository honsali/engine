package dev.cruding.engine.printer.impl.entity;

import java.util.List;
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
            if (field.isFather) {
                f.addJavaImport("com.fasterxml.jackson.annotation.JsonProperty");
            }
            if (field.required) {
                if (field.required && field.isText) {
                    f.addJavaImport("jakarta.validation.constraints.NotBlank");
                } else if (field.required) {
                    f.addJavaImport("jakarta.validation.constraints.NotNull");
                }
            }
            if (!entity.isReferenceData() && field.maxLength != null) {
                f.addJavaImport("jakarta.validation.constraints.Size");
            }
        }

        /* *********************************************************************** */

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public record ", entity.uname, "Dto(");
        f.L________("Long id,");
        f.L________("Long id", entity.uname, ",");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? "" : ",";
            if (field.isFather) {
                f.L________("@JsonProperty(access = JsonProperty.Access.READ_ONLY) ", field.jtype, "Dto ", field.lname, end);

            } else if (field.isRef) {
                f.L________(field.jtype, "Dto ", field.lname, end);

            } else {
                f.L________("");
                if (field.required && field.isText) {
                    f.__("@NotBlank ");
                } else if (field.required) {
                    f.__("@NotNull ");
                } else {
                    f.__("");
                }
                if (!entity.isReferenceData() && field.maxLength != null) {
                    f.__("@Size(max = ", field.maxLength, ") ");
                }
                f.__(field.jtype, " ", field.lname, end);
            }
        }
        f.__(") {");
        f.L("}");
        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Dto.java");
    }

}
