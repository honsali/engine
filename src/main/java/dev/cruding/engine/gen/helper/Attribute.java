package dev.cruding.engine.gen.helper;

import java.util.Objects;

public class Attribute {
    public String name;
    public String type;

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Attribute other = (Attribute) obj;

        return name.equals(other.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

}
