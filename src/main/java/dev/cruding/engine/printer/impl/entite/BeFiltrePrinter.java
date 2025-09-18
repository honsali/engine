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

public class BeFiltrePrinter extends Printer {

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

        List<Champ> notManyList = entite.listeChamp.stream().filter(IS_BASIC_REF_OR_PERE).toList();

        for (Champ champ : notManyList) {
            champ.addFiltreImport(f);
        }


        /* *********************************************************************** */

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("public class ", entite.uname, "Filtre {");
        f.L("");
        for (Champ champ : notManyList) {
            champ.addFiltreJavaDeclaration(f);
        }
        f.L("");

        for (Champ champ : notManyList) {
            champ.addFiltreGetterSetter(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + '/' + entite.uname + "Filtre.java");
    }

}
