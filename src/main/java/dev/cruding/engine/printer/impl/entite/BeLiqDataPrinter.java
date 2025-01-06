package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class BeLiqDataPrinter extends Printer {

    public void print(Entite entite) {
        Flow f = new Flow();
        /* *********************************************************************** */

        f.__("id");
        for (Champ fld : entite.fieldList) {
            if (fld.isBasic || fld.isRef) {
                f.__(";", fld.dbName);
            }
        }

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/" + "be/src/main/resources/liquibase/data/" + entite.lname + ".csv");
    }

}