package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionChangerPageChercher;
import dev.cruding.engine.action.impl.ActionChangerPageLister;
import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.action.impl.ActionConsulterElement;
import dev.cruding.engine.action.impl.ActionEvent;
import dev.cruding.engine.action.impl.ActionGoToModule;
import dev.cruding.engine.action.impl.ActionGoToPage;
import dev.cruding.engine.action.impl.ActionInitCreation;
import dev.cruding.engine.action.impl.ActionInitModification;
import dev.cruding.engine.action.impl.ActionLister;
import dev.cruding.engine.action.impl.ActionListerEnPage;
import dev.cruding.engine.action.impl.ActionListerEnPageParIdPere;
import dev.cruding.engine.action.impl.ActionListerParChamp;
import dev.cruding.engine.action.impl.ActionListerParIdPere;
import dev.cruding.engine.action.impl.ActionListerParParam;
import dev.cruding.engine.action.impl.ActionRecupererDepuisMdl;
import dev.cruding.engine.action.impl.ActionRecupererEnSession;
import dev.cruding.engine.action.impl.ActionRecupererParChamp;
import dev.cruding.engine.action.impl.ActionRecupererParId;
import dev.cruding.engine.action.impl.ActionSpecifique;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.Cache;
import dev.cruding.engine.champ.impl.Code;
import dev.cruding.engine.champ.impl.ColonneAction;
import dev.cruding.engine.champ.impl.Custom;
import dev.cruding.engine.champ.impl.Liste;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.champ.impl.Rendu;
import dev.cruding.engine.champ.impl.Tag;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.ElementWrapper;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.component.bouton.Actionnable.ActionType;
import dev.cruding.engine.component.bouton.Bouton;
import dev.cruding.engine.component.bouton.ElementBoutonComposer;
import dev.cruding.engine.component.conteneur.Bloc;
import dev.cruding.engine.component.conteneur.BlocAction;
import dev.cruding.engine.component.conteneur.CadreBas;
import dev.cruding.engine.component.conteneur.CadreHaut;
import dev.cruding.engine.component.conteneur.Conteneur;
import dev.cruding.engine.component.conteneur.FilAriane;
import dev.cruding.engine.component.conteneur.MenuOnglet;
import dev.cruding.engine.component.conteneur.Panneau;
import dev.cruding.engine.component.conteneur.PanneauEtendable;
import dev.cruding.engine.component.conteneur.PanneauFiltre;
import dev.cruding.engine.component.conteneur.PlaqueEtat;
import dev.cruding.engine.component.conteneur.Section;
import dev.cruding.engine.component.conteneur.Separateur;
import dev.cruding.engine.component.conteneur.SiCondition;
import dev.cruding.engine.component.entite.Etat;
import dev.cruding.engine.component.entite.Filtre;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.component.entite.Tableau;
import dev.cruding.engine.entite.Entite;

public abstract class ElementComposer {

    public Element element;
    public Page page;

    public ElementComposer(String name, String path) {
        this.element = new Element(name, path);
    }

    public void setPage(Page page) {
        this.page = page;
        element.page(page);
    }

    public Component composantRacine() {
        return null;
    }

    public Element compose() {
        return element.composantRacine(composantRacine());
    }

    public ElementWrapper element(ElementComposer elementComposer) {
        elementComposer.setPage(page);
        Element subElement = elementComposer.compose();
        page.addElement(subElement);
        return new ElementWrapper(element, subElement);
    }

    public ElementWrapper element(Actionnable actionnable) {
        if (actionnable.action == null) {
            actionnable.action(new ActionSpecifique());
        }
        return element(new ElementBoutonComposer(actionnable));
    }

    public Bouton bouton(Actionnable actionnable) {
        return new Bouton(actionnable.element(element));
    }

    public Entite getEntite(String uname) {
        return Context.getInstance().getEntite(uname);
    }


    public Actionnable actionForte(Entite entite, String ltype) {
        return new Actionnable(ActionType.FORTE, ltype, entite, page);
    }

    public Actionnable actionNormale(Entite entite, String ltype) {
        return new Actionnable(ActionType.NORMALE, ltype, entite, page);
    }

    public Actionnable actionConfirmer(Entite entite, String ltype) {
        return new Actionnable(ActionType.CONFIRMER, ltype, entite, page);
    }

    public Actionnable actionUc(Entite entite, String ltype) {
        return new Actionnable(ActionType.UCA, ltype, entite, page);
    }

    public Actionnable actionUcSpec(Entite entite, String ltype) {
        return new Actionnable(ActionType.UCA, ltype, entite, page).action(new ActionSpecifique());
    }

    public Actionnable recupererParId(Entite entite) {
        return new Actionnable(ActionType.NOUI, "recupererParId", entite, element).action(new ActionRecupererParId());
    }

    public Actionnable recupererParChamp(Entite entite, Champ field) {
        return new Actionnable(ActionType.NOUI, "recupererParChamp", entite, element).action(new ActionRecupererParChamp(field));
    }


    public Actionnable initCreation(Entite entite, Champ... fieldList) {
        return new Actionnable(ActionType.NOUI, "initCreation", entite, element).action(new ActionInitCreation(fieldList));
    }

    public Actionnable initModification(Entite entite) {
        return new Actionnable(ActionType.NOUI, "initModification", entite, element).action(new ActionInitModification());
    }

    public Actionnable initModification(Entite entite, Champ... fieldList) {
        return new Actionnable(ActionType.NOUI, "initModification", entite, element).action(new ActionInitModification(fieldList));
    }

    public Actionnable listerEnPageParIdPere(Entite entite) {
        return new Actionnable(ActionType.NOUI, "listerEnPage", entite, element).action(new ActionListerEnPageParIdPere());
    }

    public Actionnable goToModule(Entite entite, String target) {
        return new Actionnable(ActionType.NOUI, "goToModule" + target, entite, element).action(new ActionGoToModule(target)).inViewOnly();
    }

    public Actionnable goToPage(Entite entite, String target) {
        return new Actionnable(ActionType.NOUI, "goToPage" + target, entite, element).action(new ActionGoToPage(target)).inViewOnly();
    }

    public Actionnable throwEvent(Entite entite, String target) {
        return new Actionnable(ActionType.NOUI, target, entite, element).action(new ActionEvent(target)).inViewOnly();
    }

    public Actionnable listerTout(Entite entite) {
        return new Actionnable(ActionType.NOUI, "lister", entite, element).action(new ActionLister());
    }

    public Actionnable listerParIdPere(Entite entite) {
        return new Actionnable(ActionType.NOUI, "listerParIdPere", entite, element).action(new ActionListerParIdPere());
    }

    public Actionnable consulterElement(Entite entite) {
        return new Actionnable(ActionType.NOUI, "consulter", entite, element).action(new ActionConsulterElement()).inViewOnly();
    }

    public Action listerParChamp(Champ f) {
        return new ActionListerParChamp(f);
    }

    public Action listerParParam(String parName) {
        return new ActionListerParParam(parName);
    }

    public Actionnable recupererEnSession(Entite entite, String variable) {
        return new Actionnable(ActionType.NOUI, "recupererEnSession", entite, element).action(new ActionRecupererEnSession(variable));
    }

    public Actionnable recupererDepuisMdl(Entite entite, String mdlName) {
        return new Actionnable(ActionType.NOUI, "recupererEnSession", entite, element).action(new ActionRecupererDepuisMdl(mdlName));
    }

    public Action listerEnPage() {
        return new ActionListerEnPage();
    }

    public Actionnable chercher() {
        return new Actionnable(ActionType.NOUI, "chercher", null, element).action(new ActionChercher());
    }


    public Section section(Component... componentList) {
        return new Section(element, componentList);
    }

    public Section section(Entite entite, Component... componentList) {
        return new Section(element, entite, componentList);
    }

    public Bloc bloc(Component... componentList) {
        return new Bloc(element, componentList);
    }

    public CadreBas cadreBas(Component... componentList) {
        return new CadreBas(element, componentList);
    }

    public Conteneur cadreHaut(Component... componentList) {
        return new CadreHaut(element, componentList);
    }

    public Component plaqueEtat(Entite e) {
        return new PlaqueEtat(element, e);
    }

    public Panneau panneau(Component... componentList) {
        return new Panneau(element, componentList);
    }

    public PanneauFiltre panneauFiltre(Entite entite, Component... componentList) {
        return new PanneauFiltre(element, entite, componentList);
    }

    public PanneauEtendable panneauEtendable(Component... componentList) {
        return new PanneauEtendable(element, componentList);
    }

    public MenuOnglet menuOnglet(Component... componentList) {
        return new MenuOnglet(element, componentList);
    }

    public Etat etat(Entite e, Champ... fieldList) {
        return new Etat(element, e, fieldList);
    }

    public Etat etat(Champ f, Entite e, Champ... fieldList) {
        return new Etat(element, f, e, fieldList);
    }



    public Formulaire formulaire(Entite e, Champ... fieldList) {
        return new Formulaire(element, e, fieldList);
    }

    public Filtre filtre(Entite e, Champ... fieldList) {
        return new Filtre(element, e, fieldList);
    }


    public Component separateur() {
        return new Separateur(element);
    }

    public Component siVrai(String condition, Component... componentList) {
        return new SiCondition(element, condition, "siVrai", false, componentList);
    }

    public Component siVraiInLine(String condition, Component... componentList) {
        return new SiCondition(element, condition, "siVrai", true, componentList);
    }

    public Component siFaux(String condition, Component... componentList) {
        return new SiCondition(element, condition, "siFaux", false, componentList);
    }

    public Component siNonVide(String condition, Component... componentList) {
        return new SiCondition(element, condition, "siNonVide", false, componentList);
    }

    public Component separateur(int height) {
        return new Separateur(element, height);
    }


    public Tableau tableauPagine(Entite e, Champ... fieldList) {
        return new Tableau(element, e, new ActionChangerPageLister(), fieldList);
    }


    public Tableau tableauResultatPagine(Entite e, Champ... fieldList) {
        return new Tableau(element, e, new ActionChangerPageChercher(), fieldList);
    }

    /*
     * public Tableau resultatPagine(Entite e, Champ... fieldList) { return new Tableau(element, e, new
     * ActionChangerPageChercher(), fieldList); }
     */
    public Tableau tableau(Entite e, Champ... fieldList) {
        return new Tableau(element, e, fieldList);
    }



    public Component blocAction(Component... componentList) {
        return new BlocAction(element, componentList);
    }

    public Champ liste(Champ f) {
        return new Liste((Ref<?>) (f));
    }

    public Champ tag(Champ f) {
        return new Tag(f);
    }

    public Champ code(Champ f) {
        return new Code(f);
    }

    public Champ custom(Champ f) {
        return new Custom(f);
    }

    public Custom custom(String lname) {
        return new Custom(lname);
    }

    public Champ action(Bouton b) {
        return new ColonneAction(b);
    }

    public Champ cache(Champ f) {
        return new Cache(f);
    }


    public Champ rendu(String lname) {
        return new Rendu(lname);
    }

    public FilAriane filAriane(String uname, Component... componentList) {
        return new FilAriane(element, uname, componentList);
    }

}
