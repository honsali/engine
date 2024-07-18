package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class BeLiqTablePrinter extends Printer {

    public void print(Entity entity) {
        Flow f = new Flow();
        /* *********************************************************************** */

        f.__("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        f.L("<databaseChangeLog");
        f.L____("xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"");
        f.L____("xmlns:ext=\"http://www.liquibase.org/xml/ns/dbchangelog-ext\"");
        f.L____("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        f.L____("xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd");
        f.L____________("            http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd\">");
        f.L("");
        f.L____("<changeSet id=\"", entity.key, "-0\" author=\"app_core\">");
        f.L________("<createSequence sequenceName=\"", entity.seqName, "\" startValue=\"1050\" incrementBy=\"50\"/>");
        f.L____("</changeSet>");

        f.L____("<changeSet id=\"", entity.key, "-1\" author=\"app_core\">");
        f.L________("<createTable tableName=\"", entity.dbName, "\">");
        f.L____________("<column name=\"", entity.id_.getDbName(entity.uname), "\" type=\"bigint\">");
        f.L________________("<constraints primaryKey=\"true\" nullable=\"false\"/>");
        f.L____________("</column>");
        for (Field fld : entity.fieldList) {
            if (fld.isBasic || fld.isRef || fld.isFather) {
                fld.addLiqDeclaration(f);
            }
        }
        f.L________("</createTable>");
        f.L____("</changeSet>");
        f.L(" ");
        /* 
        f.L____("<changeSet id=\"", entity.id, "-data\" author=\"app_core\" >");
        f.L________("<loadData");
        f.L____________("      file=\"liquibase/data/", entity.lname, ".csv\"");
        f.L____________("      separator=\";\"");
        f.L____________("      tableName=\"", entity.dbName, "\"");
        f.L____________("      usePreparedStatements=\"true\">");
        f.L____________("<column name=\"id\" type=\"numeric\"/>");
        for (Field fld : entity.fieldList) {
            if (fld.isBasic || fld.isRef) {
                f.L____________("<column name=\"", fld.dbName, "\" type=\"", fld.stype, "\"/>");
            }
        }
        f.L________("</loadData>");
        f.L____("</changeSet>");
        */
        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/changelog/" + entity.lname + "_table.xml");
    }

}