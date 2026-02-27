package dev.cruding.engine.flow;

import java.util.HashMap;
import dev.cruding.engine.flow.helper.Imp;
import dev.cruding.engine.flow.helper.ImpProcessorForJS;

public class JsFlow extends Flow {

    private HashMap<String, Imp> importJSSet = new HashMap<>();
    private final ImpProcessorForJS impProcessor = new ImpProcessorForJS();

    public void addJsImport(String comp, String path) {
        importJSSet.put(comp + path, new Imp(comp, path));
    }

    public void flushJsImportBlock() {
        __(impProcessor.convert(importJSSet));
    }

    public void addJsDeclaration(String lname, String type) {
        L____(lname, "?: ", type, ";");
    }

}
