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

public class BeSpecificationPrinter extends Printer {

    private static final Predicate<Field> IS_BASIC_REF_OR_PERE = p -> (p.isBasic || p.isRef || p.isFather);

    public void print(Entity entity) {
        boolean estFiltre = false;
        for (Action action : Context.getInstance().actionEntity(entity)) {
            if (estFiltre = action instanceof FilterAction) {
                break;
            }
        }
        if (!estFiltre) {
            return;
        }
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */



        f.addJavaImport("java.util.ArrayList");
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.data.jpa.domain.Specification");
        f.addJavaImport("jakarta.persistence.criteria.CriteriaBuilder");
        f.addJavaImport("jakarta.persistence.criteria.CriteriaQuery");
        f.addJavaImport("jakarta.persistence.criteria.Predicate");
        f.addJavaImport("jakarta.persistence.criteria.Root");

        List<Field> notManyList = entity.fieldList.stream().filter(IS_BASIC_REF_OR_PERE).toList();


        /* *********************************************************************** */

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public class ", entity.uname, "Specification {");
        f.L("");
        f.L____("public static Specification<Employe> buildSpecification(", entity.uname, "Filtre condition) {");
        f.L________("return (Root<Employe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {");
        f.L____________("List<Predicate> predicates = new ArrayList<>();");


        for (Field champ : notManyList) {
            champ.addSpecification(f);
        }
        f.L("");
        f.L____________("return criteriaBuilder.and(predicates.toArray(new Predicate[0]));");
        f.L________("};");
        f.L____("}");
        f.L("}");


        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + '/' + entity.uname + "Specification.java");
    }

}
