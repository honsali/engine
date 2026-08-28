package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class Father<T extends Entity> extends RefField<T> {

    public Father(Class<T> t) {
        super(t, true);
    }

    public void addJsDeclaration(JsFlow f) {
        addJsDeclaration(f, lname, "IReference");
    }

    public void addFilterImport(JavaFlow f) {
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("app.core.reference.Reference");
    }

    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L________("@Valid Reference " + lname);
    }

    public void addSpecification(JavaFlow f) {
        f.L____________("addEqual(predicates, builder, root.get(\"" + lname + "\").get(\"id\"), filtre." + lname + "() == null ? null : filtre." + lname + "().id());");
    }



    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"false\" />");
        f.L____________("</column>");
    }

    protected Father<T> initCopy() {
        return new Father<T>(type);
    }
}
