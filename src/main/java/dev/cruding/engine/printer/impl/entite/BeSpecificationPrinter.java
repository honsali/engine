package dev.cruding.engine.printer.impl.entite;

import java.util.List;
import java.util.function.Predicate;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.filtrer.ActionFiltrer;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class BeSpecificationPrinter extends Printer {

    private static final Predicate<Champ> IS_BASIC_REF_OR_PERE = p -> !p.filtre && (p.isBasic || p.isRef || p.isPere);

    public void print(Entite entite) {
        boolean estFiltre = false;
        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            if (estFiltre = action instanceof ActionFiltrer) {
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

        List<Champ> notManyList = entite.listeChamp.stream().filter(IS_BASIC_REF_OR_PERE).toList();


        /* *********************************************************************** */

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("public class ", entite.uname, "Specification {");
        f.L("");
        f.L____("public static Specification<Employe> buildSpecification(", entite.uname, "Filtre condition) {");
        f.L________("return (Root<Employe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {");
        f.L____________("List<Predicate> predicates = new ArrayList<>();");


        for (Champ champ : notManyList) {
            champ.addSpecification(f);
        }
        f.L("");
        f.L____________("return criteriaBuilder.and(predicates.toArray(new Predicate[0]));");
        f.L________("};");
        f.L____("}");
        f.L("}");


        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + '/' + entite.uname + "Specification.java");
    }

}
