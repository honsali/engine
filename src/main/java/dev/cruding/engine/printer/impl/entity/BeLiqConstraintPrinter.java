package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeLiqConstraintPrinter extends Printer {

    public void print(Entity entity) {
        if (entity.isReferenceData()) {
            return;
        }
        boolean isEmpty = true;
        Flow f = new Flow();
        Field identifierField = entity.fieldList.stream().filter(field -> field.isId).findFirst().orElse(null);

        /* *********************************************************************** */

        f.__("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        f.L("<databaseChangeLog");
        f.L____("xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"");
        f.L____("xmlns:ext=\"http://www.liquibase.org/xml/ns/dbchangelog-ext\"");
        f.L____("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        f.L____("xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd");
        f.L________________________("http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd\">");
        f.L("");

        f.L____("<changeSet id=\"", entity.key, "-2\" author=\"app_core\">");
        if (entity.haveFather) {
            f.L________("<addForeignKeyConstraint");
            f.L____________("baseColumnNames=\"", entity.father.dbName, "\"");
            f.L____________("baseTableName=\"", entity.dbName, "\"");
            f.L____________("constraintName=\"fk_", entity.dbName, "_", entity.father.dbName, "\"");
            f.L____________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
            f.L____________("referencedTableName=\"", entity.father.dbTypeName, "\" />");
            isEmpty = false;
        }
        for (Field field : entity.fieldList) {
            if (field.isRef) {
                RefField<?> ref = (RefField<?>) field;
                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.dbName, "\"");
                f.L____________("baseTableName=\"", entity.dbName, "\"");
                f.L____________("constraintName=\"fk_", entity.dbName, "_", ref.dbName, "\"");
                f.L____________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L____________("referencedTableName=\"", ref.dbTypeName, "\" />");
                isEmpty = false;
            } else if (field.isRefMany) {
                RefField<?> ref = (RefField<?>) field;
                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.jcDbName, "\"");
                f.L____________("baseTableName=\"", ref.jtDbName, "\"");
                f.L____________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L____________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L____________("referencedTableName=\"", entity.dbName, "\" />");


                Entity fieldEntity = Context.getInstance().getEntity(ref.jtype);
                String ijtDbName = fieldEntity.dbName;

                f.L________("<addForeignKeyConstraint");
                f.L____________("baseColumnNames=\"", ref.ijcDbName, "\"");
                f.L____________("baseTableName=\"", ref.jtDbName, "\"");
                f.L____________("constraintName=\"fk_", ref.jtDbName, "_", ref.jtDbName, "\"");
                f.L____________("referencedColumnNames=\"", entity.id_.getDbName(entity.uname), "\"");
                f.L____________("referencedTableName=\"", ijtDbName, "\" />");
                isEmpty = false;

            }

        }
        f.L____("</changeSet>");
        f.L("");

        f.L____("<changeSet id=\"", entity.key, "-3\" author=\"app_core\">");
        if (entity.haveFather) {
            String fatherIndexName = "ix_" + entity.dbName + "_" + entity.father.dbName.replaceFirst("_id$", "");
            if (identifierField != null) {
                fatherIndexName += "_" + identifierField.dbName;
            }
            f.L________("<createIndex indexName=\"", fatherIndexName, "\" tableName=\"", entity.dbName, "\">");
            f.L____________("<column name=\"", entity.father.dbName, "\" />");
            if (identifierField != null) {
                f.L____________("<column name=\"", identifierField.dbName, "\" />");
            }
            f.L________("</createIndex>");
        }
        for (Field field : entity.fieldList) {
            if (field.isRef) {
                RefField<?> ref = (RefField<?>) field;
                f.L________("<createIndex indexName=\"ix_", entity.dbName, "_", ref.dbName, "\" tableName=\"", entity.dbName, "\">");
                f.L____________("<column name=\"", ref.dbName, "\" />");
                f.L________("</createIndex>");
            }
        }
        for (Entity.DateOrderConstraint constraint : entity.dateOrderConstraints) {
            f.L________("<sql>ALTER TABLE ", entity.dbName, " ADD CONSTRAINT ", constraint.name(), " CHECK (", constraint.end().dbName, " IS NULL OR ", constraint.begin().dbName, " IS NULL OR ", constraint.end().dbName, " &gt;= ", constraint.begin().dbName, ")</sql>");
        }
        f.L____("</changeSet>");
        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        if (isEmpty) {
            return;
        }
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/changelog/" + entity.lname + "_constraints.xml");
    }

}
