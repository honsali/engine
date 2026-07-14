package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class BeLiqDataPrinter extends Printer {

    public void print(Entity entity) {
        Flow f = new Flow();
        /* *********************************************************************** */

        f.__("id");
        for (Field field : entity.fieldList) {
            if (field.isBasic || field.isRef || field.isFather) {
                f.__(";", field.dbName);
            }
        }

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/" + "be/src/main/resources/liquibase/data/" + entity.lname + ".csv");
    }

}
