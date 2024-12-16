package dev.cruding.engine.printer.impl.commun;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class BeLiqMasterPrinter extends Printer {

    public void print() {

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

        f.L____("<property name=\"now\" value=\"current_timestamp\" dbms=\"postgresql\"/>");
        f.L____("<property name=\"floatType\" value=\"float4\" dbms=\"postgresql\"/>");
        f.L____("<property name=\"clobType\" value=\"longvarchar\" dbms=\"postgresql\"/>");
        f.L____("<property name=\"blobType\" value=\"bytea\" dbms=\"postgresql\"/>");
        f.L____("<property name=\"uuidType\" value=\"uuid\" dbms=\"postgresql\"/>");
        f.L____("<property name=\"datetimeType\" value=\"datetime\" dbms=\"postgresql\"/>");
        f.L("");
        f.L____("<include file=\"liquibase/changelog/_initial_schema.xml\" relativeToChangelogFile=\"false\"/>");
        for (Entity e : entityList()) {
            f.L("  <include file=\"liquibase/changelog/", e.lname, "_table.xml\" relativeToChangelogFile=\"false\"/>");
        }
        for (Entity e : entityList()) {
            f.L("  <include file=\"liquibase/changelog/", e.lname, "_constraints.xml\" relativeToChangelogFile=\"false\"/>");
        }
        f.L("</databaseChangeLog>");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/resources/liquibase/master.xml");
    }

}