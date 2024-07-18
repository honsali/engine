package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeI18nPrinter extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        f.__("export const I18n", module.unameLast, " = {");
        List<Page> listePage = new ArrayList<>(Context.getInstance().getPageList(module));
        Collections.sort(listePage);
        for (Page page : listePage) {
            f.L____("Page", page.uc, ": '", getTitre(page), "',");
            f.L____("'Uc", page.uc, ".titre': '", getTitre(page), "',");
            List<Action> listeAction = Context.getInstance().actionPage(page);
            for (Action action : listeAction) {
                action.addI18n(f, page);
            }
            for (Component action : page.listeActionInView) {
                action.addI18n(f, page);
            }
        }
        f.L("};");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/I18n" + module.unameLast + ".ts");
    }

    private String getTitre(Page page) {
        Entity entity = Context.getInstance().getEntity(page.entityUname);

        return (page.actionUname.equals("Lister") ? "Liste" : page.actionUname) + " " + (entity == null ? "" : entity.setting.libelle);
    }
}
