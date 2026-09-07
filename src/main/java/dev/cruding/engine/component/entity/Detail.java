
package dev.cruding.engine.component.entity;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Util;

public class Detail extends Component {

    private static final int COL_NUMBER = 2;
    public int columnNumber = COL_NUMBER;
    public int width = 0;
    public Field field;

    public Detail(Entity entity, Element element, Field... fieldList) {
        super(element, entity, fieldList);
    }

    public Detail(Field field, Entity entity, Element element, Field... fieldList) {
        super(element, entity, fieldList);
        this.field = field;
    }

    public void addImport(ViewFlow flow) {
        for (Field c : fieldList) {
            if (c.emptyIf != null) {
                flow.addJsImport("{ FieldVide }", "waxant");
            }
        }

        StringBuilder fieldImportList = Util.processFieldList(fieldList, Element.DETAIL);

        flow.addJsImport("{ FormulaireConsultation }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        flow.addSelector(entity.lname);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (field != null) {
            indent(flow, level).append("<FormulaireConsultation modele={" + entity.lname + "." + field.lname + "}");
        } else {
            indent(flow, level).append("<FormulaireConsultation modele={" + entity.lname + "}");
        }
        if (columnNumber != COL_NUMBER) {
            flow.totalUi().__(" nombreColonne={" + columnNumber + "}");
        }
        flow.totalUi().__(">");
        for (Field c : fieldList) {
            indent(flow, level + 1).append("<" + c.ui(Element.DETAIL) + " nom=\"" + c.lname + "\"");
            if (c.label != null) {
                flow.totalUi().__(" libelle=\"" + c.label + "\"");
            }
            if (c.width > 0) {
                flow.totalUi().__(" width={" + c.width + "}");
            }
            flow.totalUi().__(c.getExtension());
            if (c.hiddenIf != null) {
                flow.totalUi().__(" invisible={" + c.hiddenIf + "}");
            }
            if (c.emptyIf != null) {
                flow.totalUi().__(" invisible={" + c.emptyIf + "}");
            }
            if (c.aloneInRow) {
                flow.totalUi().__(" seulDansLaLigne");
            }
            if (c.wholeRow) {
                flow.totalUi().__(" surTouteLaLigne");
            }
            flow.totalUi().__(" />");
            if (c.emptyIf != null && columnNumber == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.emptyIf + "} />");
            }
            Context.getInstance().addLabelForField(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</FormulaireConsultation>");
    }

    public Detail columnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        return this;
    }

    public Detail width(int width) {
        this.width = width;
        return this;
    }

}
