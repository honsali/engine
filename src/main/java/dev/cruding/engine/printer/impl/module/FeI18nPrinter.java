package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeI18nPrinter extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        if (!module.estParent) {
            f.__("import { enteteConfirmation, messageSuccess, titreConfirmation } from 'waxant';");
            f.L("import { Action", module.unameLast, " } from './Action", module.unameLast, "';");
            f.L("");
        }
        f.L("export const I18n", module.unameLast, " = {");
        if (!module.estParent) {
            List<Page> listePage = new ArrayList<>(Contexte.getInstance().getPageList(module));
            Collections.sort(listePage);
            for (Page page : listePage) {
                f.L____("Page", page.uc, ": '", LabelMapper.getInstance().getTitre(page), "',");
                f.L____("'Uc", page.uc, ".titre': '", LabelMapper.getInstance().getTitre(page), "',");

                for (Action action : Contexte.getInstance().actionPage(page)) {
                    if (!action.noUi() && !action.flow()) {
                        action.viewActionInjection.addI18n(f);
                    }
                }

            }

            HashMap<String, String> labelMap = Contexte.getInstance().getLabelMap(module.uname);
            if (labelMap != null) {
                List<String> keySet = new ArrayList<>(labelMap.keySet());
                Collections.sort(keySet);
                for (String key : keySet) {
                    f.L____("", key, ": '", labelMap.get(key), "',");
                }
            }
        } else {
            f.L____("Page", module.unameLast, ": '", LabelMapper.getInstance().uLabel(module.unameLast), "',");
        }
        f.L("};");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/I18n" + module.unameLast + ".ts");
    }

}
