package dev.cruding.engine.action;

import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.injection.CtrlActionInjection;
import dev.cruding.engine.injection.MdlActionInjection;
import dev.cruding.engine.injection.RepoActionInjection;
import dev.cruding.engine.injection.ResourceActionInjection;
import dev.cruding.engine.injection.ServiceActionInjection;
import dev.cruding.engine.injection.ViewActionInjection;

public abstract class Action implements Comparable<Action> {

    public enum ActionType {
        NOUI, UCA, NORMALE, FORTE, FLOW, CONFIRMER
    }

    public CtrlActionInjection ctrlActionInjection;
    public MdlActionInjection mdlActionInjection;
    public RepoActionInjection repoActionInjection;
    public ResourceActionInjection resourceActionInjection;
    public ServiceActionInjection serviceActionInjection;
    public ViewActionInjection viewActionInjection;

    public ActionType type;
    public String lcoreName;
    public String ucoreName;
    public String lnameAvecEntite;
    public String unameAvecEntite;
    public String lnameSansEntite;
    public String unameSansEntite;
    public String icone = null;
    public boolean byId = false;
    public boolean parIdPere = false;
    public boolean parIdGrandPere = false;
    public boolean byForm = false;
    public boolean byEntite = false;
    public boolean byRow = false;
    public boolean byProp = false;
    public Champ byChamp = null;
    public boolean recharger = false;
    public boolean confirmer;
    public ArrayList<Action> siReussi = new ArrayList<>();
    public String actionKey = null;
    public Element element;
    public Page page;
    public Entite entite;
    public Page targetPage;
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
    public boolean isVide = false;
    public boolean hasReussi = false;
    public String lrest = "get";
    public String urest = "Get";


    public Action(ActionType type, String lcoreName, Entite entite, Element element) {
        this.type = type;
        this.entite = entite;
        this.orderBy = entite.uid;
        this.page = element.page;
        this.uc = page.uc;
        lcoreName(lcoreName);
        if (ucConfirmer()) {
            this.confirmer();
        }
        element(element);
        Contexte.getInstance().addAction(this);
    }

    public void init() {
        ctrlActionInjection = new CtrlActionInjection();
        mdlActionInjection = new MdlActionInjection();
        repoActionInjection = new RepoActionInjection();
        resourceActionInjection = new ResourceActionInjection();
        serviceActionInjection = new ServiceActionInjection();
        viewActionInjection = new ViewActionInjection();
        overrideActionInjection();
        ctrlActionInjection.action(this);
        mdlActionInjection.action(this);
        repoActionInjection.action(this);
        resourceActionInjection.action(this);
        serviceActionInjection.action(this);
        viewActionInjection.action(this);
    }

    public void overrideActionInjection() {}

    public Action lcoreName(String lcoreName) {
        this.lcoreName = lcoreName;
        this.ucoreName = StringUtils.capitalize(lcoreName);
        String n = this.lcoreName;
        if (this.entite != null) {
            n = n + this.entite.uname;
        } else {
            n = n + this.page.entiteUname;
        }
        this.lnameSansEntite(this.lcoreName);
        this.lnameAvecEntite(n);
        return this;
    }

    public Action lnameSansEntite(String lnameSansEntite) {
        this.lnameSansEntite = lnameSansEntite;
        this.unameSansEntite = StringUtils.capitalize(this.lnameSansEntite);
        return this;
    }

    public Action lnameAvecEntite(String lnameAvecEntite) {
        this.lnameAvecEntite = lnameAvecEntite;
        this.unameAvecEntite = StringUtils.capitalize(this.lnameAvecEntite);
        this.actionKey = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.lnameAvecEntite), "_").toUpperCase();
        return this;
    }

    public Action element(Element element) {
        this.element = element;
        for (Action a : siReussi) {
            a.element(element);
        }
        return this;
    }



    public Action byId() {
        this.byId = true;
        return this;
    }


    public Action parIdPere() {
        this.parIdPere = true;
        return this;
    }

    public Action inElement(boolean inElement) {
        this.inElement = inElement;
        return this;
    }

    public Action inElement() {
        this.inElement = true;
        return this;
    }

    public Action inViewOnly() {
        this.inViewOnly = true;
        return this;
    }

    public Action resultatInId() {
        this.resultatInId = true;
        return this;
    }

    public Action parIdGrandPere() {
        this.parIdGrandPere = true;
        return this;
    }

    public Action byForm() {
        this.byForm = true;
        return this;
    }


    public Action byEntite() {
        this.byEntite = true;
        return this;
    }


    public Action byRow() {
        this.byRow = true;
        return this;
    }

    public Action byProp() {
        this.byProp = true;
        return this;
    }

    public Action byChamp(Champ field) {
        this.byChamp = field;
        return this;
    }

    public Action siReussiRecharger() {
        this.recharger = true;
        return this;
    }

    public Action icone(String icone) {
        this.icone = icone;
        return this;
    }

    public Action targetPage(String targetPage) {
        this.targetPage = Contexte.getInstance().getPage(targetPage);
        return this;
    }


    public Action siReussi(Action... listeAction) {
        for (Action action : listeAction) {
            this.siReussi.add(action);
            action.type(ActionType.FLOW);
            if (action.element != null) {
                action.element(this.element);
            }
            hasReussi = true;
        }
        return this;
    }



    public Action confirmer() {
        this.confirmer = true;
        return this;
    }

    public Action uc(String uc) {
        this.uc = uc;
        return this;
    }

    public Action modele(String modele) {
        this.modele = modele;
        return this;
    }

    public Action child(Champ child) {
        this.child = child;
        return this;
    }


    public Action paginee(boolean paginee) {
        this.paginee = paginee ? "Paginee" : "";
        return this;
    }

    public Action orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Action sourceDonnee(String sourceDonnee) {
        this.sourceDonnee = sourceDonnee;
        return this;
    }

    public Action type(ActionType type) {
        this.type = type;
        return this;
    }

    public Action lrest(String lrest) {
        this.lrest = lrest;
        this.urest = StringUtils.capitalize(lrest);
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



    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Action other = (Action) obj;

        return lnameAvecEntite.equals(other.lnameAvecEntite);
    }


    public int hashCode() {
        return Objects.hash(lnameAvecEntite);
    }

    @Override
    public int compareTo(Action o) {
        return this.lnameAvecEntite.compareTo(o.lnameAvecEntite);
    }

}
