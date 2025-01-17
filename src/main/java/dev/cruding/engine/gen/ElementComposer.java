package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.changerPage.ActionChangerPageChercher;
import dev.cruding.engine.action.changerPage.ActionChangerPageLister;
import dev.cruding.engine.action.chercher.ActionChercher;
import dev.cruding.engine.action.crud.ActionCreer;
import dev.cruding.engine.action.crud.ActionEnregistrer;
import dev.cruding.engine.action.crud.ActionSupprimer;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.action.impl.AppliquerFiltre;
import dev.cruding.engine.action.impl.InitialiserFiltre;
import dev.cruding.engine.action.inViewOnly.ActionConsulterElement;
import dev.cruding.engine.action.inViewOnly.ActionEvent;
import dev.cruding.engine.action.inViewOnly.ActionGoToModule;
import dev.cruding.engine.action.inViewOnly.ActionGoToPage;
import dev.cruding.engine.action.init.ActionInitCreation;
import dev.cruding.engine.action.init.ActionInitModification;
import dev.cruding.engine.action.lister.ActionLister;
import dev.cruding.engine.action.recuperer.ActionRecupererDepuisMdl;
import dev.cruding.engine.action.recuperer.ActionRecupererEnSession;
import dev.cruding.engine.action.recuperer.ActionRecupererParChamp;
import dev.cruding.engine.action.specifique.ActionSpecifique;
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
import dev.cruding.engine.element.ComposantRepresentantUnElement;
import dev.cruding.engine.element.Element;
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

    public Element creerElement() {
        element.setComposantRacine(composantRacine());
        page.addElement(element);
        return element;
    }

    public ComposantRepresentantUnElement element(ElementComposer elementComposerFils) {
        elementComposerFils.setPage(page);
        Element elementFils = elementComposerFils.creerElement();
        return new ComposantRepresentantUnElement(element, elementFils);
    }

    public ComposantRepresentantUnElement element(Action action) {
        return element(new ElementBoutonComposer(action));
    }

    public Bouton bouton(Action action) {
        return new Bouton(action.element(element));
    }

    public Entite getEntite(String uname) {
        return Contexte.getInstance().getEntite(uname);
    }

    public Action actionForte(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.FORTE, ltype, entite, element);
    }

    public Action actionNormale(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.NORMALE, ltype, entite, element);
    }

    public Action actionNormaleVide(Entite entite, String ltype) {
        return new ActionVide(ActionType.NORMALE, ltype, entite, element);
    }

    public Action actionConfirmer(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.CONFIRMER, ltype, entite, element);
    }

    public Action actionUcSpec(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.UCA, ltype, entite, element);
    }

    public Action recupererParId(Entite entite) {
        return new ActionRecupererParChamp(entite, element, "Id");
    }

    public Action actionCreer(Entite entite) {
        return new ActionCreer(entite, element);
    }

    public Action actionSupprimer(Entite entite) {
        return new ActionSupprimer(entite, element);
    }

    public Action actionEnregistrer(Entite entite) {
        return new ActionEnregistrer(entite, element);
    }

    public Action actionAjouter(Entite entite, String targePage) {
        return new ActionVide(ActionType.UCA, "ajouter", entite, element).targetPage(targePage).inViewOnly();
    }

    public Action actionModifier(Entite entite, String targePage) {
        return new ActionVide(ActionType.UCA, "modifier", entite, element).targetPage(targePage).inViewOnly();
    }

    public Action actionRetourListe(Entite entite, String targePage) {
        return new ActionVide(ActionType.UCA, "retourListe", entite, element).targetPage(targePage).inViewOnly();
    }

    public Action actionRetourConsulter(Entite entite, String targePage) {
        return new ActionVide(ActionType.UCA, "retourConsulter", entite, element).targetPage(targePage).inViewOnly();
    }

    public Action recupererParChamp(Entite entite, Champ field) {
        return new ActionRecupererParChamp(entite, element, field.uname);
    }

    public Action initCreation(Entite entite, Champ... listeChamp) {
        return new ActionInitCreation(entite, element, listeChamp);
    }

    public Action initModification(Entite entite) {
        return new ActionInitModification(entite, element);
    }

    public Action initModification(Entite entite, Champ... listeChamp) {
        return new ActionInitModification(entite, element, listeChamp);
    }

    public Action goToModule(Entite entite, String target) {
        return new ActionGoToModule(entite, element, target);
    }

    public Action goToPage(Entite entite, String target) {
        return new ActionGoToPage(entite, element, target);
    }

    public Action throwEvent(Entite entite, String target) {
        return new ActionEvent(entite, element, target);
    }

    public Action listerTout(Entite entite) {
        return new ActionLister(entite, element);
    }


    public Action consulterElement(Entite entite) {
        return new ActionConsulterElement(entite, element).inViewOnly();
    }

    public Action recupererEnSession(Entite entite, String variable) {
        return new ActionRecupererEnSession(entite, element, variable);
    }

    public Action recupererDepuisMdl(Entite entite, String mdlName) {
        return new ActionRecupererDepuisMdl(entite, element, mdlName);
    }

    public Action chercher(Entite entite) {
        return new ActionChercher(entite, element);
    }

    public Action appliquerFiltre(Entite entite) {
        return new AppliquerFiltre(entite, element);
    }

    public Action initialiserFiltre(Entite entite) {
        return new InitialiserFiltre(entite, element);
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

    public Etat etat(Entite e, Champ... listeChamp) {
        return new Etat(e, element, listeChamp);
    }

    public Etat etat(Champ f, Entite e, Champ... listeChamp) {
        return new Etat(f, e, element, listeChamp);
    }

    public Formulaire formulaire(Entite e, Champ... listeChamp) {
        return new Formulaire(element, e, listeChamp);
    }

    public Filtre filtre(Entite e, Champ... listeChamp) {
        return new Filtre(element, e, listeChamp);
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

    public Tableau tableauPagine(Entite e, Champ... listeChamp) {
        return new Tableau(element, e, new ActionChangerPageLister(e, element), listeChamp);
    }

    public Tableau tableauResultatPagine(Entite e, Champ... listeChamp) {
        return new Tableau(element, e, new ActionChangerPageChercher(e, element), listeChamp);
    }

    public Tableau tableau(Entite e, Champ... listeChamp) {
        return new Tableau(element, e, listeChamp);
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
