package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeLiqConstraintPrinter extends Printer {

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

        f.L____("<changeSet id=\"", entity.key, "-2\" author=\"app_core\">");
        if (entity.haveFather) {
            f.L________("<addForeignKeyConstraint");
            f.L________________("baseColumnNames=\"", entity.father.dbName, "\"");
            f.L________________("baseTableName=\"", entity.dbName, "\"");
            f.L________________("constraintName=\"fk_", entity.dbName, "_", entity.father.dbName, "\"");
            f.L________________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
            f.L________________("referencedTableName=\"", entity.father.dbTypeName, "\"/>");
        }
        for (Field fld : entity.fieldList) {
            if (fld.isRef) {
                RefField<?> ref = (RefField) fld;
                f.L________("<addForeignKeyConstraint");
                f.L________________("baseColumnNames=\"", ref.dbName, "\"");
                f.L________________("baseTableName=\"", entity.dbName, "\"");
                f.L________________("constraintName=\"fk_", entity.dbName, "_", ref.dbName, "\"");
                f.L________________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L________________("referencedTableName=\"", ref.dbTypeName, "\"/>");
            } else if (fld.isRefMany) {
                RefField<?> ref = (RefField) fld;
                f.L________("<addForeignKeyConstraint");
                f.L________________("baseColumnNames=\"", ref.jcDbName, "\"");
                f.L________________("baseTableName=\"", ref.jtDbName, "\"");
                f.L________________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L________________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L________________("referencedTableName=\"", entity.dbName, "\"/>");

                Entity fieldEntity = Context.getInstance().getEntity(ref.jtype);
                String ijtDbName = fieldEntity.dbName;

                f.L________("<addForeignKeyConstraint");
                f.L________________("baseColumnNames=\"", ref.ijcDbName, "\"");
                f.L________________("baseTableName=\"", ref.jtDbName, "\"");
                f.L________________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L________________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L________________("referencedTableName=\"", ijtDbName, "\"/>");

            }

        }
        f.L____("</changeSet>");
        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/changelog/" + entity.lname + "_constraints.xml");
    }

}
