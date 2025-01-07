package dev.cruding.engine.flow;

import java.util.HashMap;
import dev.cruding.engine.gen.helper.Imp;
import dev.cruding.engine.gen.helper.ImpProcessor;

public class CtrlFlow extends Flow {

    private HashMap<String, Imp> importCtrlSet = new HashMap<>();

    private final ImpProcessor impProcessor = new ImpProcessor();

    public void addCtrlImport(String comp, String path) {
        importCtrlSet.put(comp + path, new Imp(comp, path));
    }


    public void flushCtrlImportBloc() {
        addImportBloc(importCtrlSet);
    }


    public void addImportBloc(HashMap<String, Imp> importSet) {
        __(impProcessor.convert(importSet));
    }



}
