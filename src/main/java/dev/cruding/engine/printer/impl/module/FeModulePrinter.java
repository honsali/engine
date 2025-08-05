package dev.cruding.engine.printer.impl.module;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.printer.Printer;

public class FeModulePrinter extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        f.__("import { ModuleDefinition } from 'waxant';");
        f.L("import { I18n", module.unameLast, " } from './I18n", module.unameLast, "';");
        if (module.estParent) {
            f.L("import ListePage", module.unameLast, ", { Page", module.unameLast, " } from './ListePage", module.unameLast, "';");
        } else {
            f.L("import ListePage", module.unameLast, ", { ", getPageIndex(module), " } from './ListePage", module.unameLast, "';");
        }
        f.L("import Reducer", module.unameLast, " from './Reducer", module.unameLast, "';");
        f.L("");
        if (module.estParent) {
            f.L("const Module", module.unameLast, " = (listeSousModule: ModuleDefinition[]): ModuleDefinition => {");
        } else {
            f.L("const Module", module.unameLast, " = (): ModuleDefinition => {");
        }
        f.L____("return {");
        f.L________("key: 'Module", module.unameLast, "',");
        f.L________("mapI18n: I18n", module.unameLast, ",");
        f.L________("listePage: ListePage", module.unameLast, ",");
        if (module.estParent) {
            f.L________("listeSousModule,");
        }
        f.L________("reducer: Reducer", module.unameLast, ",");
        if (module.estParent || module.estMenuOnglet) {
            f.L________("index: Page", module.unameLast, ",");
        } else {
            f.L________("index: ", module.pageIndex, ",");
        }
        f.L____("};");
        f.L("};");
        f.L("export default Module", module.unameLast, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/Module" + module.unameLast + ".tsx");
    }

    private String getPageIndex(Module module) {
        if (module.pageIndex == null) {
            return "Page" + module.unameLast;
        }
        return module.pageIndex;
    }
}
