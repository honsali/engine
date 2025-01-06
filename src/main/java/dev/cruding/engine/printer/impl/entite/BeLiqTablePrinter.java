package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class BeLiqTablePrinter extends Printer {

    public void print(Entite entite) {
        Flow f = new Flow();
        /* *********************************************************************** */

        f.__("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        f.L("<databaseChangeLog");
        f.L____("xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"");
        f.L____("xmlns:ext=\"http://www.liquibase.org/xml/ns/dbchangelog-ext\"");
        f.L____("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        f.L____("xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd");
        f.L________________________("http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd\">");
        f.L("");
        f.L____("<changeSet id=\"", entite.key, "-0\" author=\"app_core\">");
        f.L________("<createSequence sequenceName=\"", entite.seqName, "\" startValue=\"1050\" incrementBy=\"50\" />");
        f.L____("</changeSet>");

        f.L____("<changeSet id=\"", entite.key, "-1\" author=\"app_core\">");
        f.L________("<createTable tableName=\"", entite.dbName, "\">");
        f.L____________("<column name=\"", entite.id_.getDbName(entite.uname), "\" type=\"bigint\">");
        f.L________________("<constraints primaryKey=\"true\" nullable=\"false\" />");
        f.L____________("</column>");
        for (Champ fld : entite.fieldList) {
            if (fld.isBasic || fld.isRef || fld.isFather) {
                fld.addLiqDeclaration(f);
            }
        }
        f.L________("</createTable>");
        f.L____("</changeSet>");
        f.L("");

        f.L____("<changeSet id=\"", entite.key, "-data\" author=\"app_core\">");
        f.L________("<loadData");
        f.L____________("file=\"liquibase/data/", entite.lname, ".csv\"");
        f.L____________("separator=\";\"");
        f.L____________("tableName=\"", entite.dbName, "\"");
        f.L____________("usePreparedStatements=\"true\">");
        f.L____________("<column name=\"id\" type=\"numeric\" />");

        for (Champ fld : entite.fieldList) {
            if (fld.isBasic) {
                f.L____________("<column name=\"", fld.dbName, "\" type=\"", fld.stype, "\" />");
            } else if (fld.isRef) {
                f.L____________("<column name=\"", fld.dbName, "\" type=\"numeric\" />");
            }
        }
        f.L________("</loadData>");
        f.L____("</changeSet>");

        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/changelog/" + entite.lname + "_table.xml");
    }

}
