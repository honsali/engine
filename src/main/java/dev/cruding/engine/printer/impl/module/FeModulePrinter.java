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
        f.L("import ListePage", module.unameLast, ", { ", module.pageIndex, " } from './ListePage", module.unameLast, "';");
        f.L("import Reducer", module.unameLast, " from './Reducer", module.unameLast, "';");
        f.L("");
        f.L("const Module", module.unameLast, " = (): ModuleDefinition => {");
        f.L____("return {");
        f.L________("key: 'Module", module.unameLast, "',");
        f.L________("mapI18N: I18n", module.unameLast, ",");
        f.L________("listePage: ListePage", module.unameLast, ",");
        f.L________("reducer: Reducer", module.unameLast, ",");
        f.L________("index: ", module.pageIndex, ",");
        f.L____("};");
        f.L("};");
        f.L("export default Module", module.unameLast, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/Module" + module.unameLast + ".tsx");
    }
}
