package dev.cruding.engine.action.init.injection;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CtrlInitCreateInjection extends ActionCtrlInjection {

    private Field[] fieldList = new Field[0];

    public CtrlInitCreateInjection(Field[] fieldList) {
        this.fieldList = fieldList;
    };

    public void addCtrlImport(CtrlFlow f) {
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addCtrlImport(f);
            }
        }
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("resultat.", entity().lname, " = {");
        String result = Arrays.asList(fieldList).stream().filter(field -> field.init != null).map(field -> " " + field.lname + ": " + field.init).collect(Collectors.joining(","));
        f.__(result, StringUtils.isNotBlank(result) ? " " : "");
        f.__("};");
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addCtrlImplementation(f);
            }
        }
    }
}
