package dev.cruding.engine.gen;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class DbNameMapper {
    private final static DbNameMapper instance = new DbNameMapper();

    public static final DbNameMapper getInstance() {
        return instance;
    }

    private HashMap<String, String> legacyDbMap = new HashMap<>();

    private DbNameMapper() {}

    public String getTableName(String entityName) {
        return getLegacyDbName(entityName, "table", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(entityName), "_").toLowerCase());
    }

    public String getSequenceName(String entityName) {
        return getLegacyDbName(entityName, "sequence", "seq_" + getTableName(entityName));
    }

    public String getLegacyDbName(String entityName, String key, String defaultValue) {
        if (StringUtils.isBlank(entityName)) {
            throw new ContextException("Entity name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContextException("Key cannot be null or empty");
        }
        String value = legacyDbMap.get(entityName + "." + key);
        return value != null ? value : defaultValue;
    }

    public String getLegacyDbName(String entityName, String fieldName, String key, String defaultValue) {
        if (StringUtils.isBlank(entityName)) {
            throw new ContextException("Entity name cannot be null or empty");
        }
        if (StringUtils.isBlank(fieldName)) {
            throw new ContextException("Field name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContextException("Key cannot be null or empty");
        }
        String value = legacyDbMap.get(entityName + "." + fieldName + "." + key);
        return value != null ? value : defaultValue;
    }

    public void setLegacyDbMap(HashMap<String, String> legacyDbMap) {
        this.legacyDbMap = legacyDbMap != null ? legacyDbMap : new HashMap<>();
    }
}
