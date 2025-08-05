package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.chercher.ActionChercher;
import dev.cruding.engine.action.crud.ActionCreer;
import dev.cruding.engine.action.crud.ActionEnregistrer;
import dev.cruding.engine.action.crud.ActionModifierParDialogue;
import dev.cruding.engine.action.crud.ActionSupprimer;
import dev.cruding.engine.action.filtrer.ActionFiltrer;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.action.inViewOnly.ActionConsulterElement;
import dev.cruding.engine.action.inViewOnly.ActionEvent;
import dev.cruding.engine.action.inViewOnly.ActionGoToModule;
import dev.cruding.engine.action.inViewOnly.ActionGoToPage;
import dev.cruding.engine.action.inViewOnly.ActionInitialiserMdl;
import dev.cruding.engine.action.inViewOnly.ActionRecupererEnSession;
import dev.cruding.engine.action.inViewOnly.AppliquerFiltre;
import dev.cruding.engine.action.inViewOnly.InitialiserFiltre;
import dev.cruding.engine.action.init.ActionInitCreation;
import dev.cruding.engine.action.init.ActionInitModification;
import dev.cruding.engine.action.lister.ActionLister;
import dev.cruding.engine.action.listerEnPage.ActionListerEnPage;
import dev.cruding.engine.action.rechargerPage.ActionRechargerPageFiltrer;
import dev.cruding.engine.action.recuperer.ActionRecupererDepuisMdl;
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
import dev.cruding.engine.composant.conteneur.BlocInline;
import dev.cruding.engine.composant.conteneur.CadreBas;
import dev.cruding.engine.composant.conteneur.CadreHaut;
import dev.cruding.engine.composant.conteneur.Conteneur;
import dev.cruding.engine.composant.conteneur.Div;
import dev.cruding.engine.composant.conteneur.EnColonne;
import dev.cruding.engine.composant.conteneur.FilAriane;
import dev.cruding.engine.composant.conteneur.MenuOnglet;
import dev.cruding.engine.composant.conteneur.Panneau;
import dev.cruding.engine.composant.conteneur.PanneauEtendable;
import dev.cruding.engine.composant.conteneur.PanneauFiltre;
import dev.cruding.engine.composant.conteneur.PlaqueEtat;
import dev.cruding.engine.composant.conteneur.Section;
import dev.cruding.engine.composant.conteneur.Separateur;
import dev.cruding.engine.composant.conteneur.SiCondition;
import dev.cruding.engine.composant.conteneur.Space;
import dev.cruding.engine.composant.conteneur.Span;
import dev.cruding.engine.composant.entite.ActionDialogue;
import dev.cruding.engine.composant.entite.ContexteBoutonProvider;
import dev.cruding.engine.composant.entite.Etat;
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

    public Action actionNormaleInViewOnly(Entite entite, String ltype) {
        return new ActionVide(ActionType.NORMALE, ltype, entite, element).isVide(false);
    }

    public Action actionConfirmer(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.CONFIRMER, ltype, entite, element);
    }


    public Action actionUca(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.UCA, ltype, entite, element);
    }

    public Action actionNoui(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.NOUI, ltype, entite, element);
    }

    public Action actionDansDialogue(Entite entite, String ltype) {
        return new ActionSpecifique(ActionType.DIALOGUE, ltype, entite, element);
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

    public Action initialiserMdl(Entite entite) {
        return new ActionInitialiserMdl(entite, element);
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

    public Action listerToutEnPage(Entite entite) {
        return new ActionListerEnPage(entite, element);
    }

    public Action consulterElement(Entite entite) {
        return new ActionConsulterElement(entite, element).inViewOnly();
    }

    public Action modifierParDialogue(Entite entite) {
        return new ActionModifierParDialogue(entite, element).inViewOnly();
    }

    public Action recupererEnSession(Entite entite) {
        return new ActionRecupererEnSession(entite, element, null);
    }

    public Action recupererEnSession(String variable) {
        return new ActionRecupererEnSession(null, element, variable);
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

    public ActionFiltrer filtrer(Entite entite) {
        return new ActionFiltrer(entite, element, true);
    }

    public ActionFiltrer filtrerSansPagination(Entite entite) {
        return new ActionFiltrer(entite, element, false);
    }


    public Action rechargerPageFiltrer(Entite entite) {
        return new ActionRechargerPageFiltrer(entite, element);
    }

    public AppliquerFiltre appliquerFiltre(Entite entite, Action a) {
        return new AppliquerFiltre(entite, element, a);
    }


    public Action initialiserFiltre(Entite entite, Action action) {
        return new InitialiserFiltre(entite, element, action);
    }

    public Section section(Composant... listeComposant) {
        return new Section(element, listeComposant);
    }

    public Section section(Entite entite, Composant... listeComposant) {
        return new Section(element, entite, listeComposant);
    }

    public Bloc bloc(Composant... listeComposant) {
        return new Bloc(element, listeComposant);
    }

    public BlocInline blocInline(Composant... listeComposant) {
        return new BlocInline(element, listeComposant);
    }


    public EnColonne enColonne(Composant... listeComposant) {
        return new EnColonne(element, listeComposant);
    }

    public Div div(Composant... listeComposant) {
        return new Div(element, listeComposant);
    }

    public Span span(String texte) {
        return new Span(element, texte);
    }

    public Space space(Composant... listeComposant) {
        return new Space(element, listeComposant);
    }


    public CadreBas cadreBas(Composant... listeComposant) {
        return new CadreBas(element, listeComposant);
    }

    public Conteneur cadreHaut(Composant... listeComposant) {
        return new CadreHaut(element, listeComposant);
    }

    public Composant plaqueEtat(Entite e) {
        return new PlaqueEtat(element, e);
    }

    public Panneau panneau(Composant... listeComposant) {
        return new Panneau(element, listeComposant);
    }

    public Panneau panneau(Entite entite, Composant... listeComposant) {
        return new Panneau(element, entite, listeComposant);
    }


    public PanneauFiltre panneauFiltre(Entite entite, Composant... listeComposant) {
        return new PanneauFiltre(element, entite, false, listeComposant);
    }

    public PanneauFiltre panneauFiltre(Entite entite, boolean initialiserRecherche, Composant... listeComposant) {
        return new PanneauFiltre(element, entite, initialiserRecherche, listeComposant);
    }

    public PanneauEtendable panneauEtendable(Composant... listeComposant) {
        return new PanneauEtendable(element, listeComposant);
    }

    public MenuOnglet menuOnglet(Composant... listeComposant) {
        return new MenuOnglet(element, listeComposant);
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

    public Composant separateur() {
        return new Separateur(element);
    }

    public ContexteBoutonProvider actionDialogue(Entite e, String typeBouton, Champ... listeChamp) {
        return new ContexteBoutonProvider(element, typeBouton, new ActionDialogue(element, e, listeChamp));
    }

    public ActionDialogue actionDialogue(Entite e, Champ... listeChamp) {
        return new ActionDialogue(element, e, listeChamp);
    }

    public Composant siVrai(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "siVrai", false, listeComposant);
    }

    public Composant siVraiInLine(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "siVrai", true, listeComposant);
    }

    public Composant siFaux(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "siFaux", false, listeComposant);
    }

    public Composant siNonVide(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "nonVide", false, listeComposant);
    }

    public Composant siNonVideInLine(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "nonVide", true, listeComposant);
    }

    public Composant siVide(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "estVide", false, listeComposant);
    }

    public Composant siVideInLine(String condition, Composant... listeComposant) {
        return new SiCondition(element, condition, "estVide", true, listeComposant);
    }

    public Composant separateur(int height) {
        return new Separateur(element, height);
    }


    public Tableau tableau(Entite e, Champ... listeChamp) {
        return new Tableau(element, e, listeChamp);
    }

    public Composant blocAction(Composant... listeComposant) {
        return new BlocAction(element, listeComposant);
    }


    public Liste<?> liste(Champ f) {
        if (f instanceof Ref) {
            return new Liste<>((Ref<?>) f);
        }
        return null;
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

    public Champ colonneAction(Entite entite, Bouton bouton) {
        return new ColonneAction(entite, bouton);
    }


    public Champ colonneAction(Entite entite, Action action) {
        return new ColonneAction(entite, action);
    }

    public Champ colonneAction(Entite entite, String lname, ComposantRepresentantUnElement c) {
        return new ColonneAction(entite, lname + entite.uname, c);
    }

    public Champ colonneActionModifier(Entite entite, ComposantRepresentantUnElement c) {
        return new ColonneAction(entite, "modifier" + entite.uname, c);
    }

    public Champ colonneActionSupprimer(Entite entite, ComposantRepresentantUnElement c) {
        return new ColonneAction(entite, "supprimer" + entite.uname, c);
    }


    public Champ cache(Champ f) {
        return new Cache(f);
    }

    public Champ rendu(String lname) {
        return new Rendu(lname);
    }

    public FilAriane filAriane(String uname, Composant... listeComposant) {
        return new FilAriane(element, uname, listeComposant);
    }

}
