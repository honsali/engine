package dev.cruding.engine.component.bouton;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.gen.Page;



public class Actionnable {
    public enum ActionType {
        NOUI, UCA, NORMALE, FORTE, FLOW, CONFIRMER
    }



    public ActionType type;
    public String lcoreName;
    public String ucoreName;
    public String lname;
    public String uname;
    public String icone = null;
    public boolean byId = false;
    public boolean byFatherId = false;
    public boolean byGrandFatherId = false;
    public boolean byForm = false;
    public boolean byEntite = false;
    public boolean byRow = false;
    public boolean byProp = false;
    public Champ byChamp = null;
    public boolean recharger = false;
    public boolean confirmer;
    public ArrayList<Actionnable> siReussi = new ArrayList<>();
    public boolean hasReussi = false;
    public String actionKey = null;
    public Element element;
    public Page page;
    public Action action;
    public Entite entite;
    public Page targetPage;
    public String actionType;
    public String uc;
    public boolean inViewOnly = false;
    public boolean inElement = false;
    public Champ child;
    public String sourceDonnee;
    public boolean resultatInId = false;
    public String paginee = "";
    public String orderBy;
    public String mvcPath = ".";
    public String modele;
    public boolean isVide = true;

    public Actionnable(ActionType type, String lcoreName, Entite entite, Page page) {
        this.type = type;
        this.entite = entite;
        this.orderBy = entite.uid;
        this.page = page;
        this.uc = page.uc;
        lcoreName(lcoreName);
        this.action = new ActionVide();
        action.actionnable(this);
        Context.getInstance().addAction(this);
        if (ucConfirmer()) {
            this.confirmer();
        }
    }

    public Actionnable(ActionType type, String lcoreName, Entite entite, Element element) {
        this(type, lcoreName, entite, element.page);
        element(element);

    }

    public Actionnable lcoreName(String lcoreName) {
        this.lcoreName = lcoreName;
        this.ucoreName = StringUtils.capitalize(lcoreName);
        String n = this.lcoreName;
        if (this.entite != null) {
            n = n + this.entite.uname;
        } else {
            n = n + this.page.entiteUname;
        }
        this.lname(n);
        return this;
    }

    public Actionnable lname(String lname) {

        this.lname = lname;
        this.uname = StringUtils.capitalize(this.lname);
        this.actionKey = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.lname), "_").toUpperCase();

        return this;
    }

    public Actionnable element(Element element) {
        this.element = element;
        for (Actionnable a : siReussi) {
            a.element(element);
        }
        return this;
    }


    public Actionnable action(Action action) {
        this.action = action;
        this.isVide = false;
        action.actionnable(this);
        return this;
    }



    public Actionnable byId() {
        this.byId = true;
        return this;
    }


    public Actionnable byFatherId() {
        this.byFatherId = true;
        return this;
    }

    public Actionnable inElement(boolean inElement) {
        this.inElement = inElement;
        return this;
    }

    public Actionnable inElement() {
        this.inElement = true;
        return this;
    }

    public Actionnable inViewOnly() {
        this.inViewOnly = true;
        return this;
    }

    public Actionnable resultatInId() {
        this.resultatInId = true;
        return this;
    }

    public Actionnable byGrandFatherId() {
        this.byGrandFatherId = true;
        return this;
    }

    public Actionnable byForm() {
        this.byForm = true;
        return this;
    }


    public Actionnable byEntite() {
        this.byEntite = true;
        return this;
    }


    public Actionnable byRow() {
        this.byRow = true;
        return this;
    }

    public Actionnable byProp() {
        this.byProp = true;
        return this;
    }

    public Actionnable byChamp(Champ field) {
        this.byChamp = field;
        return this;
    }

    public Actionnable siReussiRecharger() {
        this.recharger = true;
        return this;
    }

    public Actionnable icone(String icone) {
        this.icone = icone;
        return this;
    }

    public Actionnable targetPage(String targetPage) {
        this.targetPage = Context.getInstance().getPage(targetPage);
        return this;
    }


    public Actionnable siReussi(Actionnable... listeActionnable) {
        for (Actionnable actionnable : listeActionnable) {
            this.siReussi.add(actionnable);
            actionnable.type(ActionType.FLOW);
            if (actionnable.element != null) {
                actionnable.element(this.element);
            }
            hasReussi = true;
        }
        return this;
    }



    public Actionnable confirmer() {
        this.confirmer = true;
        return this;
    }

    public Actionnable uc(String uc) {
        this.uc = uc;
        return this;
    }

    public Actionnable modele(String modele) {
        this.modele = modele;
        return this;
    }

    public Actionnable child(Champ child) {
        this.child = child;
        return this;
    }


    public Actionnable paginee(boolean paginee) {
        this.paginee = paginee ? "Paginee" : "";
        return this;
    }

    public Actionnable orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Actionnable sourceDonnee(String sourceDonnee) {
        this.sourceDonnee = sourceDonnee;
        return this;
    }

    public Actionnable type(ActionType type) {
        this.type = type;
        return this;
    }

    public boolean noUi() {
        return type == ActionType.NOUI;
    };

    public boolean uca() {
        return type == ActionType.UCA;
    };

    public boolean normale() {
        return type == ActionType.NORMALE;
    };

    public boolean forte() {
        return type == ActionType.FORTE;
    };

    public boolean ucConfirmer() {
        return type == ActionType.CONFIRMER;
    };


    public boolean flow() {
        return type == ActionType.FLOW;
    };

    public boolean nfc() {
        return normale() || forte() || ucConfirmer();
    };



}
