package dev.cruding.engine.action;

import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.rechargerPage.ActionRechargerPageFiltrer;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.injection.BusinessActionInjection;
import dev.cruding.engine.injection.CtrlActionInjection;
import dev.cruding.engine.injection.MdlActionInjection;
import dev.cruding.engine.injection.RepoActionInjection;
import dev.cruding.engine.injection.ResourceActionInjection;
import dev.cruding.engine.injection.ServiceActionInjection;
import dev.cruding.engine.injection.ViewActionInjection;

public abstract class Action implements Comparable<Action> {

    public enum ActionType {
        NOUI, UCA, NORMALE, FORTE, FLOW, CONFIRMER, DIALOGUE
    }

    private static int ordre = 0;
    public CtrlActionInjection ctrlActionInjection;
    public MdlActionInjection mdlActionInjection;
    public RepoActionInjection repoActionInjection;
    public ResourceActionInjection resourceActionInjection;
    public BusinessActionInjection businessActionInjection;
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
    public boolean parId = false;
    public boolean parIdPere = false;
    public boolean parForm = false;
    public boolean parEntite = false;
    public boolean byRow = false;
    public String parProp = null;
    public Champ[] parChamp = null;
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
    public boolean sansSecurite = false;
    public boolean inElement = false;
    public String sourceDonnee;
    public Champ resultatIn = null;
    public String orderBy;
    public String mvcPath = ".";
    public String modele;
    public boolean isVide = false;
    public boolean hasReussi = false;
    public boolean hasReussiInViewOnly = false;
    public String lrest = "get";
    public String urest = "Get";
    public Action actionPagination = null;
    public boolean pagine = false;
    public boolean estActionReussi = false;
    public boolean enTantQueListe = false;
    public boolean attendreSiPret = false;
    private String id;
    public String nomVariable;
    public boolean inInit;
    public boolean filtrerAuDepart;
    public boolean appelDecale;


    public Action(ActionType type, String lcoreName, Entite entite, Element element) {
        this.id = "" + ordre++;
        this.type = type;
        this.entite = entite;
        if (this.entite != null) {
            this.orderBy = entite.uid;
        }
        this.page = element.page;
        this.uc = page.uc;
        lcoreName(lcoreName);
        if (ucConfirmer() || ucDialogue()) {
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
        businessActionInjection = new BusinessActionInjection();
        serviceActionInjection = new ServiceActionInjection();
        viewActionInjection = new ViewActionInjection();
        overrideActionInjection();
        ctrlActionInjection.action(this);
        mdlActionInjection.action(this);
        repoActionInjection.action(this);
        resourceActionInjection.action(this);
        businessActionInjection.action(this);
        serviceActionInjection.action(this);
        viewActionInjection.action(this);
    }

    public void overrideActionInjection() {}

    public Action lcoreName(String lcoreName) {
        this.lcoreName = lcoreName;
        this.ucoreName = StringUtils.capitalize(lcoreName);
        String n = this.lcoreName;
        if (this.entite != null && !n.endsWith(this.entite.uname)) {
            n = n + this.entite.uname;
        } else if (!n.endsWith(this.page.entiteUname)) {
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
            // if (a.element == null) {
            a.element(element);
            // }
        }
        return this;
    }

    public Action parId() {
        this.parId = true;
        return this;
    }

    public Action parIdPere() {
        this.parIdPere = true;
        if (actionPagination != null) {
            actionPagination.parIdPere();
        }
        return this;
    }

    public Action inElement(boolean inElement) {
        this.inElement = inElement;
        for (Action a : siReussi) {
            a.inElement(inElement);
        }
        if (actionPagination != null) {
            actionPagination.inElement(inElement);
        }
        return this;
    }

    public Action inElement() {
        this.inElement(true);
        return this;
    }

    public Action inViewOnly() {
        this.inViewOnly = true;
        return this;
    }

    public Action inViewOnly(boolean inViewOnly) {
        this.inViewOnly = inViewOnly;
        return this;
    }

    public Action sansSecurite() {
        this.sansSecurite = true;
        return this;
    }

    public Action resultatIn(Champ resultatIn) {
        this.resultatIn = resultatIn;
        return this;
    }

    public Action parForm(boolean parForm) {
        this.parForm = parForm;
        return this;
    }

    public Action parForm() {
        this.parForm = true;
        return this;
    }

    public Action parEntite() {
        this.parEntite = true;
        return this;
    }

    public Action byRow() {
        this.byRow = true;
        return this;
    }

    public Action parProp(String parProp) {
        this.parProp = parProp;
        return this;
    }

    public Action parChamp(Champ... field) {
        this.parChamp = field;
        if (actionPagination != null) {
            actionPagination.parChamp(field);
        }
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
            action.estActionReussi = true;
            // action.type(ActionType.FLOW);
            if (action.element == null) {
                action.element(this.element);
            }
            hasReussiInViewOnly = hasReussiInViewOnly || action.inViewOnly;
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

    public boolean ucDialogue() {
        return type == ActionType.DIALOGUE;
    };

    public boolean flow() {
        return type == ActionType.FLOW;
    };

    public Action isVide(boolean isVide) {
        this.isVide = isVide;
        return this;
    };

    public Action enTantQueListe() {
        enTantQueListe = true;
        return this;
    };

    public boolean nfc() {
        return normale() || forte() || ucConfirmer();
    };

    public Action actionPagination(Action action) {
        this.actionPagination = action;
        return this;
    }

    public Action actionRecharger() {
        return new ActionRechargerPageFiltrer(entite, element);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Action other = (Action) obj;

        return lnameAvecEntite.equals(other.lnameAvecEntite);
    }

    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Action o) {
        return this.lnameAvecEntite.compareTo(o.lnameAvecEntite);
    }

    public Action attendreSiPret() {
        attendreSiPret = true;
        return this;
    };


    public Action inInit() {
        inInit = true;
        return this;
    };
}
