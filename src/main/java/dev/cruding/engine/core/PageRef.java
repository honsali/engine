package dev.cruding.engine.core;

import org.apache.commons.lang3.StringUtils;

public record PageRef(String name) {

    public PageRef {
        if (StringUtils.isBlank(name)) {
            throw new GeneratorException("Page reference name cannot be null or empty");
        }
    }
}
