package dev.cruding.engine.flow;

import java.util.HashSet;
import java.util.TreeSet;

public class JavaFlow extends Flow {

    private HashSet<String> importJavaSet = new HashSet<>();

    public void addJavaImport(String clazz) {
        importJavaSet.add(clazz);
    }

    public void flushJavaImportBloc() {
        TreeSet<String> ts = new TreeSet<String>(importJavaSet);
        for (String s : ts) {
            if (s.startsWith("java")) {
                L("import ", s, ";");
            }
        }
        for (String s : ts) {
            if (s.startsWith("org")) {
                L("import ", s, ";");
            }
        }
        for (String s : ts) {
            if (s.startsWith("com")) {
                L("import ", s, ";");
            }
        }
        for (String s : ts) {
            if (!s.startsWith("java") && !s.startsWith("org") && !s.startsWith("com")) {
                L("import ", s, ";");
            }
        }
    }

}
