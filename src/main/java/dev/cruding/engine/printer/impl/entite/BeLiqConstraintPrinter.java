package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.RefChamp;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeLiqConstraintPrinter extends Printer {

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

        f.L____("<changeSet id=\"", entite.key, "-2\" author=\"app_core\">");
        if (entite.haveFather) {
            f.L________("<addForeignKeyConstraint");
            f.L____________("baseColumnNames=\"", entite.father.dbName, "\"");
            f.L____________("baseTableName=\"", entite.dbName, "\"");
            f.L____________("constraintName=\"fk_", entite.dbName, "_", entite.father.dbName, "\"");
            f.L____________("referencedColumnNames=\"", entite.id_.getDbName(entite.uname), "\"");
            f.L____________("referencedTableName=\"", entite.father.dbTypeName, "\" />");
        }
        for (Champ fld : entite.fieldList) {
            if (fld.isRef) {
                RefChamp<?> ref = (RefChamp) fld;
                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.dbName, "\"");
                f.L____________("baseTableName=\"", entite.dbName, "\"");
                f.L____________("constraintName=\"fk_", entite.dbName, "_", ref.dbName, "\"");
                f.L____________("referencedColumnNames=\"", entite.id_.getDbName(entite.uname), "\"");
                f.L____________("referencedTableName=\"", ref.dbTypeName, "\" />");
            } else if (fld.isRefMany) {
                RefChamp<?> ref = (RefChamp) fld;
                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.jcDbName, "\"");
                f.L____________("baseTableName=\"", ref.jtDbName, "\"");
                f.L____________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L____________("referencedColumnNames=\"", entite.id_.getDbName(entite.uname), "\"");
                f.L____________("referencedTableName=\"", entite.dbName, "\" />");

                Entite fieldEntite = Context.getInstance().getEntite(ref.jtype);
                String ijtDbName = fieldEntite.dbName;

                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.ijcDbName, "\"");
                f.L____________("baseTableName=\"", ref.jtDbName, "\"");
                f.L____________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L____________("referencedColumnNames=\"", entite.id_.getDbName(entite.uname), "\"");
                f.L____________("referencedTableName=\"", ijtDbName, "\" />");

            }

        }
        f.L____("</changeSet>");
        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/changelog/" + entite.lname + "_constraints.xml");
    }

}
