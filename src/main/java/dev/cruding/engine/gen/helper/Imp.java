package dev.cruding.engine.gen.helper;

import java.util.Objects;

public class Imp {
    private String comp;
    private String path;

    public Imp(String comp, String path) {
        this.comp = comp;
        this.path = path;
    }

    public String getComp() {
        return this.comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Imp other = (Imp) obj;

        return path.equals(other.path) && comp.equals(other.comp);
    }

    public int hashCode() {
        return Objects.hash(path);
    }

}
