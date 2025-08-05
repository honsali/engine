package dev.cruding.engine.composant.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class ActionDialogue extends Composant {

    public String largeur;
    public Formulaire formulaire;
    public Action action;
    public boolean initFormByEntite;

    public ActionDialogue(Element element, Entite entite, Champ... listeChamp) {
        super(element, entite);
        formulaire = new Formulaire(element, entite, listeChamp);
        listeComposant = new Composant[] { formulaire };

    }

    public ActionDialogue action(Action action) {
        this.action = action;
        return this;
    }

    public ActionDialogue nombreColonne(int nombreColonne) {
        this.formulaire.nombreColonne = nombreColonne;
        return this;
    }

    public ActionDialogue largeur(String largeur) {
        this.largeur = largeur;
        return this;
    }

    public ActionDialogue initFormByEntite() {
        this.initFormByEntite = true;
        return this;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ ActionUcDialogue }", "waxant");
    }

    public void addScript(ViewFlow flow) {
        if (initFormByEntite) {
            flow.totalScript().L____("const siInit = () => {");
            flow.totalScript().L________("form.setFieldsValue(", action.entite.lname, ");");
            flow.totalScript().L____("};");
            flow.totalScript().L("");
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        flow.addSelector(action.lnameAvecEntite);
        String nomAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;

        indent(flow, level).append("<ActionUcDialogue");
        indent(flow, level + 1).append("nom={").append(nomAction).append("} //");
        if (action.icone != null) {
            indent(flow, level + 1).append("icone={<FontAwesomeIcon icon={").append(action.icone).append("} />}");
        }
        indent(flow, level + 1).append("action={").append(action.lnameAvecEntite).append("}");
        indent(flow, level + 1).append("form={form}");
        indent(flow, level + 1).append("etat={etat").append(action.unameAvecEntite).append("}");
        if (initFormByEntite) {
            indent(flow, level + 1).append("siInit={siInit}");
        }
        if (largeur != null) {
            indent(flow, level + 1).append("largeur=\"500px\"");
        }
        indent(flow, level).append(">");
        if (action.icone != null) {
            flow.useFontAwesome(action.icone);
        }
        flow.addSelector("etat" + action.unameAvecEntite);
        String s = Util.getRelativePath(action.element.path, action.page.module.path, false);
        flow.addJsImport("{ Action" + action.page.module.unameLast + " }", s + "/Action" + action.page.module.unameLast);
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</ActionUcDialogue>");

    }
}
