package dev.cruding.engine.gen;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class LabelMapper {

    private static LabelMapper instance = new LabelMapper();

    private static final HashMap<String, String> labels = new HashMap<>();

    static {
        labels.put("creer", "créer");
    }

    public static LabelMapper getInstance() {
        return instance;
    }

    private LabelMapper() {}

    public String lAction(String key) {
        if (labels.containsKey(key)) {
            return labels.get(key);
        }
        return key;
    }

    public String uAction(String key) {
        String label = key;
        if (labels.containsKey(key)) {
            label = labels.get(key);
        }
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(label)), " ");
    }
}
