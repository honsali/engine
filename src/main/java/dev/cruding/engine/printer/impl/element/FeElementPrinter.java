package dev.cruding.engine.printer.impl.element;

import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.printer.Printer;

public class FeElementPrinter extends Printer {


    public void print(Element element) {
        ViewFlow f = ViewFlow.create(element);

        f.flushViewImportBlock();
        f.L("");
        f.L("const ", element.name, " = (", f.joinProps(), f.joinPropsTypeAnnotation(), ") => {");
        f.flushInitBlock();
        f.flushScriptBlock();
        f.L____("//");
        f.L____("return ");
        f.flushUiBlock();
        f.L("};");
        f.L("");
        f.L("export default ", element.name, ";");

        printFile(f.toString(), EnginePaths.outputRoot + "/fe/src/" + element.path + "/" + element.name + ".tsx");
    }

}
