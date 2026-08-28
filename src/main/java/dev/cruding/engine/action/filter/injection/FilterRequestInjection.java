package dev.cruding.engine.action.filter.injection;

import java.util.List;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRequestInjection;

public class FilterRequestInjection extends ActionRequestInjection {

    @Override
    public String name() {
        return entity().uname + "Filtre";
    }

    @Override
    public String content() {
        JavaFlow f = new JavaFlow();
        List<Field> fields = entity().fieldList.stream().filter(field -> field.isBasic || field.isRef || field.isFather).toList();

        for (Field field : fields) {
            field.addFilterImport(f);
        }

        f.__("package app.domain.", entity().pkg, ".", entity().lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public record ", name(), "(");
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).addFilterJavaDeclaration(f);
            if (i < fields.size() - 1) {
                f.__(",");
            }
        }
        f.__(") {");
        f.L("}");

        return f.toString();
    }
}
