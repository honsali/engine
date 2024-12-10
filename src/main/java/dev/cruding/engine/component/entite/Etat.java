
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class Etat extends Component {

    public int colNumber = 2;
    public int largeur = 0;
    public String lname;
    public String uname;
    public Field field;
    public boolean inView = true;

    public Etat(Element element, Entity entity, Field... fieldList) {
        this(entity.lname, entity.uname, element, entity, null, fieldList);

    }

    public Etat(Element element, Field field, Entity entity, Field... fieldList) {
        this(field.lname, field.uname, element, entity, field, fieldList);
    }

    private Etat(String lname, String uname, Element element, Entity entity, Field field, Field... fieldList) {
        super(element, entity, fieldList);
        this.lname = lname;
        this.uname = uname;
        this.field = field;
    }

    public Etat nom(String uname) {
        this.uname = uname;
        this.lname = StringUtils.uncapitalize(uname);
        return this;
    }

    public Etat inView(boolean inView) {
        this.inView = inView;
        return this;
    }

    public String getTitle() {
        String key = "etat." + lname;
        addLabel("Uc" + element.page.uc + "." + key, "Etat " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Bloc, Champ, Etat }", "waxant");
    }

    public void addScript(ViewFlow flow) {
        flow.addSpecificSelector(entity.lname, "../Mdl" + element.page.uc);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (field != null) {
            indent(flow, level).append("<Etat modele={" + entity.lname + "." + field.lname + "}");
        } else {
            indent(flow, level).append("<Etat modele={" + entity.lname + "}");
        }
        if (colNumber != 2) {
            flow.addToUi(" nombreColonne={" + colNumber + "}");
        }
        flow.addToUi(">");
        for (Field c : fieldList) {
            indent(flow, level + 1).append("<Champ " + c.ui(ElementPrinter.DETAIL) + "=\"" + c.lname + "\"");
            if (c.libelle != null) {
                flow.addToUi(" libelle=\"" + c.libelle + "\"");
            }
            if (c.width > 0) {
                flow.addToUi(" width={" + c.width + "}");
            }
            if (c.invisibleSi != null) {
                flow.addToUi(" invisible={" + c.invisibleSi + "}");
            }
            if (c.videSi != null) {
                flow.addToUi(" invisible={" + c.videSi + "}");
            }
            if (c.seulDansLaLigne) {
                flow.addToUi(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                flow.addToUi(" surTouteLaLigne");
            }
            flow.addToUi(" />");
            if (c.videSi != null && colNumber == 2) {
                indent(flow, level + 1).append("<Champ vide invisible={!" + c.videSi + "} />");
            }
        }
        return false;

    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Etat>");
    }

    public Etat colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Etat largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Etat lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        return this;
    }


}
