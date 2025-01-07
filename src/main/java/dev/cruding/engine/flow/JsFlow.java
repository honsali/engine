package dev.cruding.engine.flow;

import java.util.HashMap;
import dev.cruding.engine.gen.helper.Imp;
import dev.cruding.engine.gen.helper.ImpProcessor;

public class JsFlow extends Flow {

    private HashMap<String, Imp> importJSSet = new HashMap<>();
    private final ImpProcessor impProcessor = new ImpProcessor();

    public void addJsImport(String comp, String path) {
        importJSSet.put(comp + path, new Imp(comp, path));
    }

    public void flushJsImportBloc() {
        __(impProcessor.convert(importJSSet));
    }

    public void addJsDeclaration(String lname, String type) {
        L____(lname, "?: ", type, ";");
    }

}
