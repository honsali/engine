package dev.cruding.engine.flow;

import java.util.HashSet;
import java.util.TreeSet;

public class JavaFlow extends Flow {

    private HashSet<String> importJavaSet = new HashSet<>();

    public void addJavaImport(String clazz) {
        importJavaSet.add(clazz);
    }

    public void flushJavaImportBloc() {
        if (importJavaSet.isEmpty()) {
            return;
        }

        TreeSet<String> ts = new TreeSet<>(importJavaSet);
        String[] packagePrefixes = {"java", "org", "com"};

        for (String prefix : packagePrefixes) {
            for (String s : ts) {
                if (s.startsWith(prefix)) {
                    L("import ", s, ";");
                }
            }
        }

        for (String s : ts) {
            boolean isStandardPackage = false;
            for (String prefix : packagePrefixes) {
                if (s.startsWith(prefix)) {
                    isStandardPackage = true;
                    break;
                }
            }
            if (!isStandardPackage) {
                L("import ", s, ";");
            }
        }
    }

}
