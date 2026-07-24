package dev.cruding.engine.flow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.flow.helper.Attribute;
import dev.cruding.engine.flow.helper.AttributeSorter;
import dev.cruding.engine.flow.helper.Imp;
import dev.cruding.engine.flow.helper.ImpProcessorForJS;

public class MdlFlow extends Flow {

    private HashMap<String, Imp> importMdlSet = new HashMap<>();
    private HashSet<Attribute> mdlRequestAttributeSet = new HashSet<>();
    private HashSet<String> mdlRequiredRequestAttributeSet = new HashSet<>();
    private HashSet<Attribute> mdlResultAttributeSet = new HashSet<>();
    public HashSet<Attribute> mdlStateAttributeSet = new HashSet<>();
    public HashSet<Attribute> mdlSelectorAttributeSet = new HashSet<>();

    private final ImpProcessorForJS impProcessor = new ImpProcessorForJS();
    private final AttributeSorter attributeSorter = new AttributeSorter();


    public void addMdlImport(String comp, String path) {
        importMdlSet.put(comp + path, new Imp(comp, path));
    }

    public void addMdlRequestAttribute(String name, String type) {
        mdlRequestAttributeSet.add(new Attribute(name, type));
    }

    public void addMdlRequiredRequestAttribute(String name, String type) {
        mdlRequestAttributeSet.add(new Attribute(name, type));
        mdlRequiredRequestAttributeSet.add(name);
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

    public void flushMdlImportBlock() {
        addImportBlock(importMdlSet);
    }

    public void addImportBlock(HashMap<String, Imp> importSet) {
        __(impProcessor.convert(importSet));
    }

    public void flushMdlRequestAttributeBlock() {
        List<Attribute> mdlRequestAttributeList = new ArrayList<>(mdlRequestAttributeSet);
        Collections.sort(mdlRequestAttributeList, attributeSorter);

        for (Attribute att : mdlRequestAttributeList) {
            String separator = mdlRequiredRequestAttributeSet.contains(att.name) ? ": " : "?: ";
            L____(att.name, separator, att.type, ";");
        }
    }

    public void flushMdlResultAttributeBlock() {
        List<Attribute> mdlResultAttributeList = new ArrayList<>(mdlResultAttributeSet);
        Collections.sort(mdlResultAttributeList, attributeSorter);

        for (Attribute att : mdlResultAttributeList) {
            L____(att.name, "?: ", att.type, ";");
        }
    }

    public void flushMdlStateTypeBlock() {
        List<Attribute> mdlStateAttributeList = new ArrayList<>(mdlStateAttributeSet);
        Collections.sort(mdlStateAttributeList, attributeSorter);

        for (Attribute att : mdlStateAttributeList) {
            if (att.type.length() > 1 && !att.type.endsWith("[]") && att.type.charAt(0) == 'I' && Character.isUpperCase(att.type.charAt(1))) {
                L____(att.name, "?: ", att.type, ";");
            } else if ("createEtatInit()".equals(att.type)) {
                L____(att.name, ": EtatMdl;");
            } else {
                L____(att.name, "?: ", att.type, ";");
            }
        }
    }

    public void flushMdlStateAttributeBlock() {
        List<Attribute> mdlStateAttributeList = new ArrayList<>(mdlStateAttributeSet);
        Collections.sort(mdlStateAttributeList, attributeSorter);

        for (Attribute att : mdlStateAttributeList) {
            if ("createEtatInit()".equals(att.type)) {
                L____(att.name, ": ", att.type + ",");
            } else if (att.type.endsWith("[]")) {
                L____(att.name, ": ", "[] as ", att.type, ",");
            } else if (att.type.charAt(0) == 'I') {
                L____(att.name, ": ", "{} as ", att.type, ",");
            }
        }
    }
}
