package dev.cruding.engine.action;

import java.util.ArrayList;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Page;



public class ActionWrapper {

    private static final Champ[] listeChampVide = new Champ[] {};

    public Action action;

    public void action(Action action) {
        this.action = action;
    };

    public void setInElement(boolean inElement) {
        action.inElement(inElement);
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


    public String unameSansEntite() {
        return action.unameSansEntite;
    };

    public String icone() {
        return action.icone;
    };

    public boolean parId() {
        return action.parId;
    };

    public boolean parIdPere() {
        return action.parIdPere;
    };

    public boolean parForm() {
        return action.parForm;
    };

    public boolean parEntite() {
        return action.parEntite;
    };

    public String parProp() {
        return action.parProp;
    };

    public Champ[] parChamp() {
        return action.parChamp == null ? listeChampVide : action.parChamp;
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


    public boolean enTantQueListe() {
        return action.enTantQueListe;
    };

    public boolean hasReussiInViewOnly() {
        return action.hasReussiInViewOnly;
    };

    public boolean estActionReussi() {
        return action.estActionReussi;
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

    public String sourceDonnee() {
        return action.sourceDonnee;
    };

    public Champ resultatIn() {
        return action.resultatIn;
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

    public String lrest() {
        return action.lrest;
    }

    public String urest() {
        return action.urest;
    }

    public boolean flow() {
        return action.flow();
    }

    public boolean noUi() {
        return action.noUi();
    }

    public boolean ucDialogue() {
        return action.ucDialogue();
    }

    public boolean pagine() {
        return action.actionPagination != null || action.pagine;
    };

    public boolean attendreSiPret() {
        return action.attendreSiPret;
    }


    public boolean inInit() {
        return action.inInit;
    }


    public boolean filtrerAuDepart() {
        return action.filtrerAuDepart;
    }


    public boolean appelDecale() {
        return action.appelDecale;
    }
}
