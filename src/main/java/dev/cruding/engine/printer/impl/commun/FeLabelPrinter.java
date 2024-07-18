package dev.cruding.engine.printer.impl.commun;

import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeLabelPrinter extends Printer {

    private String[] actions = new String[] {"chercher", "lister", "ajouter", "creer", "retourListe", "supprimer", "modifier", "enregistrer", "filtrer"};

    public void print() {
        Flow f = new Flow();

        HashSet<String> liste = new HashSet<>();
        for (Entity e : entityList()) {
            liste.add(e.lname);
            for (Field fld : e.fieldList) {
                liste.add(fld.lname);
            }
        }
        f.__("export const labelMap = {");
        f.L____("_: ' ',");
        for (String s : liste) {
            f.L____(s, ": '", labelise(s), "',");
        }
        f.L("};");
        f.L("export const actionMap = {");
        f.L____("confirmer: 'Confirmer',");
        f.L____("annuler: 'Annuler',");
        f.L____("retour: 'Retour',");
        f.L____("initialiserRecherche: 'Initialiser',");

        for (Entity e : entityList()) {
            for (String s : actions) {
                f.L____("'", s, ".", e.lname, "': '", labelise(s), "',");
            }
        }
        f.L("};");
        f.L("export const titreMap = {");
        for (Page p : pageList()) {
            f.L____(p.uc, ": '", labelise(p.uc), "',");
        }
        HashMap<String, String> labelMap = Context.getInstance().getLabelMap();
        for (String key : labelMap.keySet()) {
            f.L____("'", key, "': '", labelMap.get(key), "',");
            f.L____("'onglet.", key, "': '", labelMap.get(key), "',");
        }
        f.L____("_: ' ',");
        f.L("};");
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/core/util/labelMap.ts");
    }

    private String labelise(String s) {
        String a = StringUtils.capitalize(s);
        String[] b = StringUtils.splitByCharacterTypeCamelCase(a);
        String c = StringUtils.join(b, " ");
        return c;
    }
}
