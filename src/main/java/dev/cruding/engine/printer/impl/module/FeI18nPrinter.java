package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.TsLiteral;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeI18nPrinter extends Printer {

    public void print(Module module) {
        Flow f = new Flow();

        /* *********************************************************************** */
        if (!module.isParent) {
            f.__("import { enteteConfirmation, messageSuccess, titreConfirmation } from 'waxant';");
            f.L("import { Action", module.unameLast, " } from './Action", module.unameLast, "';");
            f.L("");
        }
        f.L("export const I18n", module.unameLast, " = {");
        if (!module.isParent) {
            ArrayList<Page> pageList = sortedPageList(module);
            for (Page page : pageList) {
                f.L____("Page", page.uc, ": ", TsLiteral.string(LabelMapper.getInstance().getTitle(page)), ",");
                f.L____(TsLiteral.objectKey("Uc" + page.uc + ".titre"), ": ", TsLiteral.string(LabelMapper.getInstance().getTitle(page)), ",");

                for (Action action : Context.getInstance().actionPage(page)) {
                    if (!action.noUi() && !action.flow()) {
                        action.viewActionInjection.addI18n(f);
                    }
                }

            }

            HashMap<String, String> labelMap = Context.getInstance().getLabelMap(module.uname);
            if (labelMap != null) {
                List<String> keySet = new ArrayList<>(labelMap.keySet());
                Collections.sort(keySet);
                for (String key : keySet) {
                    f.L____(TsLiteral.objectKey(key), ": ", TsLiteral.string(labelMap.get(key)), ",");
                }
            }
        } else {
            f.L____("Page", module.unameLast, ": ", TsLiteral.string(LabelMapper.getInstance().uLabel(module.unameLast)), ",");
        }
        f.L("};");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/I18n" + module.unameLast + ".ts");
    }

}
