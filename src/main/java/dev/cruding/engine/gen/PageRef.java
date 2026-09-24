package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.loader.GeneratorException;

public record PageRef(String name) {

    public PageRef {
        if (StringUtils.isBlank(name)) {
            throw new GeneratorException("Page reference name cannot be null or empty");
        }
    }
}
