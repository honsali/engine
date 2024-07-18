package dev.cruding.engine.gen.helper;

import java.util.Comparator;

public class AttributeSorter implements Comparator<Attribute> {

    public int compare(Attribute c1, Attribute c2) {
        if (c1 == null) {
            return c2 == null ? 0 : -1;
        }
        return c1.name.compareTo(c2.name);
    }
}
