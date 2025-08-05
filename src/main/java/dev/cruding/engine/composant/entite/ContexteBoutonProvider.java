package dev.cruding.engine.composant.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class ContexteBoutonProvider extends Composant {

    private ActionDialogue actionDialogue;
    private String typeBouton;

    public ContexteBoutonProvider(Element element, String typeBouton, ActionDialogue actionDialogue) {
        super(element, actionDialogue);
        this.actionDialogue = actionDialogue;
        this.typeBouton = typeBouton;

    }

    public ContexteBoutonProvider action(Action action) {
        actionDialogue.action(action);
        return this;
    }

    public ContexteBoutonProvider nombreColonne(int nombreColonne) {
        actionDialogue.nombreColonne(nombreColonne);
        return this;
    }

    public ContexteBoutonProvider largeur(String largeur) {
        actionDialogue.largeur(largeur);
        return this;
    }

    public ContexteBoutonProvider initFormByEntite() {
        actionDialogue.initFormByEntite();
        return this;
    }

    public ContexteBoutonProvider typeBouton(String typeBouton) {
        this.typeBouton = typeBouton;
        return this;
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<ContexteBoutonProvider type=\"").append(typeBouton).append("\">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</ContexteBoutonProvider>");
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ ContexteBoutonProvider }", "waxant");
    }
}
