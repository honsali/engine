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
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.composant.bouton.Actionnable;
import dev.cruding.engine.composant.bouton.Actionnable.ActionType;
import dev.cruding.engine.composant.bouton.Bouton;
import dev.cruding.engine.composant.bouton.ElementBoutonComposer;
import dev.cruding.engine.composant.conteneur.Bloc;
import dev.cruding.engine.composant.conteneur.BlocAction;
import dev.cruding.engine.composant.conteneur.CadreBas;
import dev.cruding.engine.composant.conteneur.CadreHaut;
import dev.cruding.engine.composant.conteneur.Conteneur;
import dev.cruding.engine.composant.conteneur.FilAriane;
import dev.cruding.engine.composant.conteneur.MenuOnglet;
import dev.cruding.engine.composant.conteneur.Panneau;
import dev.cruding.engine.composant.conteneur.PanneauEtendable;
import dev.cruding.engine.composant.conteneur.PanneauFiltre;
import dev.cruding.engine.composant.conteneur.PlaqueEtat;
import dev.cruding.engine.composant.conteneur.Section;
import dev.cruding.engine.composant.conteneur.Separateur;
import dev.cruding.engine.composant.conteneur.SiCondition;
import dev.cruding.engine.composant.entite.Etat;
import dev.cruding.engine.composant.entite.Filtre;
import dev.cruding.engine.composant.entite.Formulaire;
import dev.cruding.engine.composant.entite.Tableau;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.ElementEntantQueComposant;
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

    public Composant composantRacine() {
        return null;
    }

    public Element compose() {
        return element.composantRacine(composantRacine());
    }

    public ElementEntantQueComposant element(ElementComposer elementComposer) {
        elementComposer.setPage(page);
        Element subElement = elementComposer.compose();
        page.addElement(subElement);
        return new ElementEntantQueComposant(element, subElement);
    }

    public ElementEntantQueComposant element(Actionnable actionnable) {
        if (actionnable.action == null) {
            actionnable.action(new ActionSpecifique());
        }
        return element(new ElementBoutonComposer(actionnable));
    }

    public Bouton bouton(Actionnable actionnable) {
        return new Bouton(actionnable.element(element));
    }

    public Entite getEntite(String uname) {
        return Contexte.getInstance().getEntite(uname);
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


    public Section section(Composant... ComposantList) {
        return new Section(element, ComposantList);
    }

    public Section section(Entite entite, Composant... ComposantList) {
        return new Section(element, entite, ComposantList);
    }

    public Bloc bloc(Composant... ComposantList) {
        return new Bloc(element, ComposantList);
    }

    public CadreBas cadreBas(Composant... ComposantList) {
        return new CadreBas(element, ComposantList);
    }

    public Conteneur cadreHaut(Composant... ComposantList) {
        return new CadreHaut(element, ComposantList);
    }

    public Composant plaqueEtat(Entite e) {
        return new PlaqueEtat(element, e);
    }

    public Panneau panneau(Composant... ComposantList) {
        return new Panneau(element, ComposantList);
    }

    public PanneauFiltre panneauFiltre(Entite entite, Composant... ComposantList) {
        return new PanneauFiltre(element, entite, ComposantList);
    }

    public PanneauEtendable panneauEtendable(Composant... ComposantList) {
        return new PanneauEtendable(element, ComposantList);
    }

    public MenuOnglet menuOnglet(Composant... ComposantList) {
        return new MenuOnglet(element, ComposantList);
    }

    public Etat etat(Entite e, Champ... fieldList) {
        return new Etat(e, element, fieldList);
    }

    public Etat etat(Champ f, Entite e, Champ... fieldList) {
        return new Etat(f, e, element, fieldList);
    }



    public Formulaire formulaire(Entite e, Champ... fieldList) {
        return new Formulaire(element, e, fieldList);
    }

    public Filtre filtre(Entite e, Champ... fieldList) {
        return new Filtre(element, e, fieldList);
    }


    public Composant separateur() {
        return new Separateur(element);
    }

    public Composant siVrai(String condition, Composant... ComposantList) {
        return new SiCondition(element, condition, "siVrai", false, ComposantList);
    }

    public Composant siVraiInLine(String condition, Composant... ComposantList) {
        return new SiCondition(element, condition, "siVrai", true, ComposantList);
    }

    public Composant siFaux(String condition, Composant... ComposantList) {
        return new SiCondition(element, condition, "siFaux", false, ComposantList);
    }

    public Composant siNonVide(String condition, Composant... ComposantList) {
        return new SiCondition(element, condition, "siNonVide", false, ComposantList);
    }

    public Composant separateur(int height) {
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



    public Composant blocAction(Composant... ComposantList) {
        return new BlocAction(element, ComposantList);
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

    public FilAriane filAriane(String uname, Composant... ComposantList) {
        return new FilAriane(element, uname, ComposantList);
    }

}
