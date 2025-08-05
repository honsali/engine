package dev.cruding.engine.printer.impl.commun;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class FeHasRightPrinter extends Printer {

    public void print() {
        Flow f = new Flow();

        f.__("import { useAppSelector } from 'core/config/store.config';");
        f.L("");
        f.L("const ALL = [");
        for (Entite e : entiteList()) {
            f.L("    'creer.", e.lname, "', //");
            f.L("    'modifier.", e.lname, "',");
            f.L("    'enregistrer.", e.lname, "',");
            f.L("    'lister.", e.lname, "',");
            f.L("    'retourListe.", e.lname, "',");
            f.L("    'consulter.", e.lname, "',");
            f.L("    'ajouter.", e.lname, "',");
            f.L("    'chercher.", e.lname, "',");
            f.L("    'filtrer.", e.lname, "',");
            f.L("    'supprimer.", e.lname, "',");
        }
        f.L("    'retour',");
        f.L("];");
        f.L("const ROLE_ADMIN = [];");
        f.L("const rightMap = {");
        f.L("    ALL,");
        f.L("    ROLE_ADMIN,");
        f.L("};");
        f.L("");
        f.L("export default function useHasRight(action): boolean {");
        f.L("    const user = useAppSelector((state) => state.storeAuth.user);");
        f.L("    return rightMap['ALL'].includes(action) || rightMap[user.role].includes(action);");
        f.L("}");

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/core/util/useHasRight.ts");
    }

}
