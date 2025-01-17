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
        for (Champ champ : entite.listeChamp) {
            if (champ.isBasic || champ.isRef) {
                f.__(";", champ.dbName);
            }
        }

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/" + "be/src/main/resources/liquibase/data/" + entite.lname + ".csv");
    }

}
