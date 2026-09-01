package dev.cruding.engine.flow;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class JavaFlow extends Flow {

    private static final int MAX_LINE_LENGTH = 120;

    private HashSet<String> importJavaSet = new HashSet<>();

    public void addMethodDeclaration(int indentation, String declaration, List<String> parameters) {
        String singleLine = declaration + String.join(", ", parameters) + ") {";
        if (parameters.isEmpty() || indentation + singleLine.length() <= MAX_LINE_LENGTH) {
            newLineWithIndent(indentation, singleLine);
            return;
        }

        newLineWithIndent(indentation, declaration);
        for (int index = 0; index < parameters.size(); index++) {
            String suffix = index + 1 < parameters.size() ? "," : ") {";
            newLineWithIndent(indentation + 8, parameters.get(index), suffix);
        }
    }

    public void addJavaImport(String clazz) {
        importJavaSet.add(clazz);
    }

    public void flushJavaImportBlock() {
        if (importJavaSet.isEmpty()) {
            return;
        }

        TreeSet<String> ts = new TreeSet<>(importJavaSet);
        String[] packagePrefixes = {"java", "javax", "jakarta", "org", "com", "app"};

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
