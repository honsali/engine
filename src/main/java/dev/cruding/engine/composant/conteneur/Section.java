package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;

public class Section extends Composant {

    public String pageRetour = null;
    public boolean plaqueEtat = false;
    public BlocAction blocAction = null;

    public Section(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public Section(Element element, Entite entite, Composant... ComposantList) {
        super(element, entite, ComposantList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Section}", "waxant");
        if (pageRetour != null) {
            Page pr = Contexte.getInstance().getPage(pageRetour);
            flow.addJsImport("{ " + pr.name + " }", pr.module.listePage(element.path, inElement));
        }
        if (plaqueEtat) {
            flow.addJsImport("{PlaqueEtat}", "waxant");
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Section");
        if (pageRetour != null) {
            if (blocAction != null) {
                indent(flow, level + 1);
            }
            flow.addToUi(" pageRetour={").append(pageRetour).append("}");
            Contexte.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + ".retour" + pageRetour, "Retour");

        }
        if (plaqueEtat) {
            flow.addToUi(" blocAction={<PlaqueEtat entite={").append(entite.lname).append("} />}");
        }
        if (blocAction != null) {
            indent(flow, level + 1).append(" blocAction={");
            blocAction.addContent(this, flow, level + 2);
            indent(flow, level + 1).append("}");
            indent(flow, level).append(">");
        } else {
            flow.addToUi(">");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Section>");
    }

    public Section plaqueEtat() {
        this.plaqueEtat = true;
        return this;
    }

    public Section blocAction(Composant... ComposantList) {
        this.blocAction = new BlocAction(element, ComposantList);
        return this;
    }

    public Section pageRetour(String pageRetour) {
        this.pageRetour = pageRetour;
        return this;
    }

    public Section label(String label) {
        return this;
    }

}
