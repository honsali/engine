package dev.cruding.engine.flow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.gen.helper.Attribute;
import dev.cruding.engine.gen.helper.AttributeSorter;
import dev.cruding.engine.gen.helper.Imp;
import dev.cruding.engine.gen.helper.ImpProcessor;

public class MdlFlow extends Flow {

    private HashMap<String, Imp> importMdlSet = new HashMap<>();
    private HashSet<Attribute> mdlRequestAttributeSet = new HashSet<>();
    private HashSet<Attribute> mdlResultAttributeSet = new HashSet<>();
    public HashSet<Attribute> mdlStateAttributeSet = new HashSet<>();
    public HashSet<Attribute> mdlSelectorAttributeSet = new HashSet<>();

    private final ImpProcessor impProcessor = new ImpProcessor();
    private final AttributeSorter attributeSorter = new AttributeSorter();


    public void addMdlImport(String comp, String path) {
        importMdlSet.put(comp + path, new Imp(comp, path));
    }

    public void addMdlRequestAttribute(String name, String type) {
        mdlRequestAttributeSet.add(new Attribute(name, type));
    }

    public void addMdlResultAttribute(String name, String type) {
        mdlResultAttributeSet.add(new Attribute(name, type));
    }

    public void addMdlStateAttribute(String name, String type) {
        mdlStateAttributeSet.add(new Attribute(name, type));
    }

    public void addMdlSelectorAttribute(String lname, String uname) {
        mdlSelectorAttributeSet.add(new Attribute(uname, lname));
    }

    public void flushMdlImportBloc() {
        addImportBloc(importMdlSet);
    }

    public void addImportBloc(HashMap<String, Imp> importSet) {
        __(impProcessor.convert(importSet));
    }

    public void flushMdlRequestAttributeBloc() {
        List<Attribute> mdlRequestAttributeList = new ArrayList<>(mdlRequestAttributeSet);
        Collections.sort(mdlRequestAttributeList, attributeSorter);

        for (Attribute att : mdlRequestAttributeList) {
            L____(att.name, "?: ", att.type, ";");
        }
    }

    public void flushMdlResultAttributeBloc() {
        List<Attribute> mdlResultAttributeList = new ArrayList<>(mdlResultAttributeSet);
        Collections.sort(mdlResultAttributeList, attributeSorter);

        for (Attribute att : mdlResultAttributeList) {
            L____(att.name, "?: ", att.type, ";");
        }
    }

    public void flushMdlStateAttributeBloc() {
        List<Attribute> mdlStateAttributeList = new ArrayList<>(mdlStateAttributeSet);
        Collections.sort(mdlStateAttributeList, attributeSorter);

        for (Attribute att : mdlStateAttributeList) {
            if (att.type.endsWith("[]")) {
                L____(att.name, ": ", "[] as ", att.type, ",");
            } else if ("string".equals(att.type)) {
                L____(att.name, ": ", "null,");

            } else if ("number".equals(att.type)) {
                L____(att.name, ": ", "0,");

            } else if (att.type.endsWith("()")) {
                L____(att.name, ": ", att.type + ",");

            } else {
                L____(att.name, ": ", "{} as ", att.type, ",");
            }
        }
    }
}
