package dev.cruding.engine.action;

import java.util.ArrayList;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Page;



public class ActionWrapper {


    public Action action;

    public void action(Action action) {
        this.action = action;
    };

    public void setInElement(boolean inElement) {
        action.inElement(inElement);
    }

    public void setPaginee(boolean paginee) {
        action.paginee(paginee);
    }


    public ActionType type() {
        return action.type;
    };

    public String lcoreName() {
        return action.lcoreName;
    };

    public boolean byRow() {
        return action.byRow;
    }

    public String ucoreName() {
        return action.ucoreName;
    };

    public String lnameAvecEntite() {
        return action.lnameAvecEntite;
    };

    public String lnameSansEntite() {
        return action.lnameSansEntite;
    };


    public String unameAvecEntite() {
        return action.unameAvecEntite;
    };

    public String icone() {
        return action.icone;
    };

    public boolean byId() {
        return action.byId;
    };

    public boolean parIdPere() {
        return action.parIdPere;
    };

    public boolean parIdGrandPere() {
        return action.parIdGrandPere;
    };

    public boolean byForm() {
        return action.byForm;
    };

    public boolean byEntite() {
        return action.byEntite;
    };

    public boolean byProp() {
        return action.byProp;
    };

    public Champ byChamp() {
        return action.byChamp;
    };

    public boolean recharger() {
        return action.recharger;
    };

    public boolean confirmer() {
        return action.confirmer;
    };

    public ArrayList<Action> siReussi() {
        return action.siReussi;
    };


    public boolean hasReussi() {
        return action.hasReussi;
    };


    public String actionKey() {
        return action.actionKey;
    };

    public Element element() {
        return action.element;
    };

    public Page page() {
        return action.page;
    };

    public Entite entite() {
        return action.entite;
    };

    public Page targetPage() {
        return action.targetPage;
    };

    public String uc() {
        return action.uc;
    };

    public boolean inViewOnly() {
        return action.inViewOnly;
    };

    public Champ child() {
        return action.child;
    };

    public String sourceDonnee() {
        return action.sourceDonnee;
    };

    public boolean resultatInId() {
        return action.resultatInId;
    };


    public String paginee() {
        return action.paginee;
    };

    public String orderBy() {
        return action.orderBy;
    };


    public String mvcPath() {
        return action.element.path.endsWith("element") ? ".." : ".";
    };

    public boolean inElement() {
        return action.inElement;
    }

    public boolean noUi() {
        return action.type == ActionType.NOUI;
    };

    public String lrest() {
        return action.lrest;
    }

    public String urest() {
        return action.urest;
    }

}
