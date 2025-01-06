
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.gen.helper.Util;

public class Etat extends Component {

    public int colNumber = 2;
    public int largeur = 0;
    public String lname;
    public String uname;
    public Champ field;
    public boolean inView = true;

    public Etat(Element element, Entite entite, Champ... fieldList) {
        this(entite.lname, entite.uname, element, entite, null, fieldList);

    }

    public Etat(Element element, Champ field, Entite entite, Champ... fieldList) {
        this(field.lname, field.uname, element, entite, field, fieldList);
    }

    private Etat(String lname, String uname, Element element, Entite entite, Champ field, Champ... fieldList) {
        super(element, entite, fieldList);
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
        for (Champ c : fieldList) {

            if (c.videSi != null) {
                flow.addJsImport("{ ChampVide }", "waxant");
            }
        }

        StringBuilder fieldImportList = Util.processListeChamp(fieldList, ElementPrinter.DETAIL);

        flow.addJsImport("{ FormulaireConsultation }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        flow.addSpecificSelector(entite.lname, "../Mdl" + element.page.uc);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (field != null) {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "." + field.lname + "}");
        } else {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "}");
        }
        if (colNumber != 2) {
            flow.addToUi(" nombreColonne={" + colNumber + "}");
        }
        flow.addToUi(">");
        for (Champ c : fieldList) {
            indent(flow, level + 1).append("<" + c.ui(ElementPrinter.DETAIL) + " nom=\"" + c.lname + "\"");
            flow.addToUi(c.getExtension());
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
        indent(flow, level).append("</FormulaireConsultation>");
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
