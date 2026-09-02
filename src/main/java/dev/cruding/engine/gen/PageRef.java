package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;

public record PageRef(String name) {

    public PageRef {
        if (StringUtils.isBlank(name)) {
            throw new ContextException("Page reference name cannot be null or empty");
        }
    }
}
