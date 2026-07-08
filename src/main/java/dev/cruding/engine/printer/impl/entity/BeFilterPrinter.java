package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import java.util.function.Predicate;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.filter.FilterAction;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeFilterPrinter extends Printer {

    private static final Predicate<Field> IS_BASIC_REF_OR_FATHER = p -> (p.isBasic || p.isRef || p.isFather);

    public void print(Entity entity) {
        boolean filtred = false;
        for (Action action : Context.getInstance().actionEntity(entity)) {
            if (filtred = action instanceof FilterAction) {
                break;
            }
        }
        if (!filtred) {
            return;
        }
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */

        List<Field> notManyList = entity.fieldList.stream().filter(IS_BASIC_REF_OR_FATHER).toList();

        for (Field field : notManyList) {
            field.addFilterImport(f);
        }


        /* *********************************************************************** */

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public record ", entity.uname, "Filtre(");
        for (int i = 0; i < notManyList.size(); i++) {
            notManyList.get(i).addFilterJavaDeclaration(f);
            if (i < notManyList.size() - 1) {
                f.__(",");
            }

        }
        f.__(") {");

        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + '/' + entity.uname + "Filtre.java");
    }

}
