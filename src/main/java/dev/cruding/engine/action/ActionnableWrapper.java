package dev.cruding.engine.action;

import java.util.ArrayList;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.bouton.Actionnable;
import dev.cruding.engine.composant.bouton.Actionnable.ActionType;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Page;



public class ActionnableWrapper {


    public Actionnable actionnable;

    public void setInElement(boolean inElement) {
        actionnable.inElement(inElement);
    }

    public void setPaginee(boolean paginee) {
        actionnable.paginee(paginee);
    }

    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
    };

    public ActionType type() {
        return actionnable.type;
    };

    public String lcoreName() {
        return actionnable.lcoreName;
    };

    public String ucoreName() {
        return actionnable.ucoreName;
    };

    public String lname() {
        return actionnable.lname;
    };

    public String uname() {
        return actionnable.uname;
    };

    public String icone() {
        return actionnable.icone;
    };

    public boolean byId() {
        return actionnable.byId;
    };

    public boolean byPereId() {
        return actionnable.byPereId;
    };

    public boolean byGrandPereId() {
        return actionnable.byGrandPereId;
    };

    public boolean byForm() {
        return actionnable.byForm;
    };

    public boolean byEntite() {
        return actionnable.byEntite;
    };

    public boolean byProp() {
        return actionnable.byProp;
    };

    public Champ byChamp() {
        return actionnable.byChamp;
    };

    public boolean recharger() {
        return actionnable.recharger;
    };

    public boolean confirmer() {
        return actionnable.confirmer;
    };

    public ArrayList<Actionnable> siReussi() {
        return actionnable.siReussi;
    };

    public String actionKey() {
        return actionnable.actionKey;
    };

    public Element element() {
        return actionnable.element;
    };

    public Page page() {
        return actionnable.page;
    };

    public Action action() {
        return actionnable.action;
    };

    public Entite entite() {
        return actionnable.entite;
    };

    public Page targetPage() {
        return actionnable.targetPage;
    };

    public String uc() {
        return actionnable.uc;
    };

    public boolean inViewOnly() {
        return actionnable.inViewOnly;
    };

    public Champ child() {
        return actionnable.child;
    };

    public String sourceDonnee() {
        return actionnable.sourceDonnee;
    };

    public boolean resultatInId() {
        return actionnable.resultatInId;
    };


    public String paginee() {
        return actionnable.paginee;
    };

    public String orderBy() {
        return actionnable.orderBy;
    };


    public String mvcPath() {
        return actionnable.element.path.endsWith("element") ? ".." : ".";
    };

    public boolean inElement() {
        return actionnable.inElement;
    }

    public boolean noUi() {
        return actionnable.type == ActionType.NOUI;
    };

}
