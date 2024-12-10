package dev.cruding.engine.gen.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class ImpProcessor {

    public final ImpSorter importSorter = new ImpSorter();

    public String convert(HashMap<String, Imp> importSet) {
        Map<String, Imp> map = new HashMap<>();

        for (Imp imp : importSet.values()) {
            String path = imp.getPath();
            String comp = imp.getComp();

            if (map.containsKey(path)) {
                Imp mergedImp = map.get(path);
                mergedImp.setComp(mergeNames(mergedImp.getComp(), comp));
            } else {
                map.put(path, imp);
            }
        }
        List<Imp> mergedList = new ArrayList<>(map.values());
        Collections.sort(mergedList, importSorter);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Imp imp : mergedList) {
            sb.append(i == 0 ? "" : "\n").append("import ").append(imp.getComp()).append(" from '").append(imp.getPath()).append("';");
            i++;
        }
        return sb.toString();
    }

    private String mergeNames(String name1, String name2) {
        String out1 = substringBefore(name1, "{");
        String out2 = substringBefore(name2, "{");
        String in1 = substringBetween(name1, "{", "}");
        String in2 = substringBetween(name2, "{", "}");

        String mergedInsideBrackets = mergeAndSort(StringUtils.joinWith(",", in1, in2));

        String mergedOutsideBrackets = mergeAndSort(StringUtils.joinWith(",", out1, out2));

        if (!StringUtils.isEmpty(mergedOutsideBrackets) && !StringUtils.isEmpty(mergedInsideBrackets)) {
            return mergedOutsideBrackets + ",{ " + mergedInsideBrackets + " }";
        } else if (StringUtils.isEmpty(mergedOutsideBrackets)) {
            return "{ " + mergedInsideBrackets + " }";
        } else if (StringUtils.isEmpty(mergedInsideBrackets)) {
            return mergedOutsideBrackets;
        } else {
            return "";
        }
    }

    private String mergeAndSort(String str) {
        String[] parts = StringUtils.split(StringUtils.deleteWhitespace(str), ",");
        Set<String> uniqueParts = new HashSet<>(Arrays.asList(parts));

        List<String> mergedList = new ArrayList<>(uniqueParts);

        Comparator<String> customComparator = (s1, s2) -> {
            boolean isS1Capitalized = Character.isUpperCase(s1.charAt(0));
            boolean isS2Capitalized = Character.isUpperCase(s2.charAt(0));

            if (isS1Capitalized && !isS2Capitalized) {
                return -1;
            } else if (!isS1Capitalized && isS2Capitalized) {
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        };

        Collections.sort(mergedList, customComparator);

        return StringUtils.join(mergedList, ", ");
    }

    private String substringBefore(String s, String sep) {
        String sub = StringUtils.substringBefore(s, sep);
        if (sub == null) {
            return null;
        }
        return sub.trim();
    }

    private String substringBetween(String s, String start, String end) {
        String sub = StringUtils.substringBetween(s, start, end);
        if (sub == null) {
            return null;
        }
        return sub.trim();
    }
}
