package dev.cruding.engine.injection;

import java.util.List;
import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class ActionBusinessInjection extends ActionWrapper {

    public void addBusinessDeclaration(JavaFlow f) {}

    public void addBusinessImport(JavaFlow f) {}

    public List<Field> businessRelationFields() {
        return List.of();
    }

    protected void addBusinessRelationDeclarations(JavaFlow f) {
        List<Field> relationFields = businessRelationFields();
        if (!relationFields.isEmpty()) {
            f.L("");
        }
        for (Field field : relationFields) {
            if (field.isFather) {
                f.L________(field.jtype, " ", field.lname, " = recuperer", field.jtype, "(id", field.uname, ");");
            } else {
                f.L________(field.jtype, " ", field.lname, " = recuperer", field.jtype, "(request.", field.lname, "());");
            }
        }
    }

    protected String mapperBusinessRelationArguments() {
        StringBuilder arguments = new StringBuilder();
        for (Field field : businessRelationFields()) {
            arguments.append(", ").append(field.lname);
        }
        return arguments.toString();
    }
}
