package dev.cruding.engine.flow;

import java.util.HashMap;
import dev.cruding.engine.flow.helper.Imp;
import dev.cruding.engine.flow.helper.ImpProcessorForJS;

public class CtrlFlow extends Flow {

    private HashMap<String, Imp> importCtrlSet = new HashMap<>();

    private final ImpProcessorForJS impProcessor = new ImpProcessorForJS();

    public void addCtrlImport(String comp, String path) {
        importCtrlSet.put(comp + path, new Imp(comp, path));
    }


    public void flushCtrlImportBlock() {
        addImportBlock(importCtrlSet);
    }


    public void addImportBlock(HashMap<String, Imp> importSet) {
        __(impProcessor.convert(importSet));
    }



}
