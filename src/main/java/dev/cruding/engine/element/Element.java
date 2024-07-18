package dev.cruding.engine.element;

import java.util.Arrays;
import java.util.HashSet;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public abstract class Element extends Printer {

    public static final String DETAIL = "DETAIL";
    public static final String FORM = "FORM";
    public static final String TABLEAU = "TABLEAU";

    public String utype;
    public Entity entity;
    public String lname;

    public Element(String utype) {
        this.utype = utype;
        this.lname = utype;
    }

    public Element(String utype, Entity entity) {
        this.utype = utype;
        this.entity = entity;
        this.lname = utype + "." + entity.lname;
    }

    public abstract void print(Page page);

    public StringBuilder processListeField(Component component, String elementType) {

        HashSet<String> listeType = new HashSet<>();

        for (Field field : component.fieldList) {
            if (field != null) {
                listeType.add(field.ui(elementType));
            } else {
                System.out.println("Field is null");

            }
        }
        StringBuilder sb = new StringBuilder();
        if (listeType.size() > 0) {
            Object[] array = listeType.toArray();
            Arrays.sort(array);
            for (Object type : array) {
                sb.append(type).append(", ");
            }
        }
        return sb;
    }

}
