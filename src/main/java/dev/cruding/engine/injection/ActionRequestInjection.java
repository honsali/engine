package dev.cruding.engine.injection;

import java.util.List;
import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class ActionRequestInjection extends ActionWrapper {

    public String name() {
        return "";
    }

    public String content() {
        return "";
    }

    protected String recordContent(List<Field> fields) {
        return recordContent(fields, false);
    }

    protected String recordContent(List<Field> fields, boolean withVersion) {
        JavaFlow f = new JavaFlow();

        for (Field field : fields) {
            addRequestImport(f, field);
        }
        if (withVersion) {
            f.addJavaImport("jakarta.validation.constraints.NotNull");
            f.addJavaImport("jakarta.validation.constraints.PositiveOrZero");
        }

        f.__("package app.domain.", entity().javaPackage(), ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public record ", name(), "(");
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            boolean lastField = i == fields.size() - 1 && !withVersion;
            f.L________(validationPrefix(field), requestType(field), " ", field.lname, lastField ? ") {" : ",");
        }
        if (withVersion) {
            f.L________("@NotNull @PositiveOrZero Long version) {");
        }
        f.L("}");

        return f.toString();
    }

    private void addRequestImport(JavaFlow f, Field field) {
        if (field.required && field.isText) {
            f.addJavaImport("jakarta.validation.constraints.NotBlank");
        } else if (field.required) {
            f.addJavaImport("jakarta.validation.constraints.NotNull");
        }
        if (field.isRef || field.isFather) {
            f.addJavaImport("app.core.reference.Reference");
            f.addJavaImport("jakarta.validation.Valid");
        }
        if (field.maxLength != null) {
            f.addJavaImport("jakarta.validation.constraints.Size");
        }
        if ("LocalDate".equals(field.jtype)) {
            f.addJavaImport("java.time.LocalDate");
        } else if ("LocalTime".equals(field.jtype)) {
            f.addJavaImport("java.time.LocalTime");
        } else if ("LocalDateTime".equals(field.jtype)) {
            f.addJavaImport("java.time.LocalDateTime");
        }
    }

    private String validationPrefix(Field field) {
        StringBuilder prefix = new StringBuilder();
        if (field.required && field.isText) {
            prefix.append("@NotBlank ");
        } else if (field.required) {
            prefix.append("@NotNull ");
        }
        if (field.isRef || field.isFather) {
            prefix.append("@Valid ");
        }
        if (field.minLength != null || field.maxLength != null) {
            prefix.append("@Size(");
            if (field.minLength != null) {
                prefix.append("min = ").append(field.minLength);
            }
            if (field.minLength != null && field.maxLength != null) {
                prefix.append(", ");
            }
            if (field.maxLength != null) {
                prefix.append("max = ").append(field.maxLength);
            }
            prefix.append(") ");
        }
        return prefix.toString();
    }

    private String requestType(Field field) {
        return field.isRef || field.isFather ? "Reference" : field.jtype;
    }
}
