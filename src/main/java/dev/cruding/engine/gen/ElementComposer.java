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
import dev.cruding.engine.action.impl.ActionRecupererParField;
import dev.cruding.engine.action.impl.ActionRecupererParId;
import dev.cruding.engine.action.impl.ActionSpecifique;
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
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Cache;
import dev.cruding.engine.field.impl.Code;
import dev.cruding.engine.field.impl.Custom;
import dev.cruding.engine.field.impl.Liste;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.Rendu;
import dev.cruding.engine.field.impl.Tag;

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

    public Component bouton(Actionnable actionnable) {
        return new Bouton(actionnable.element(element));
    }

    public Entity getEntity(String uname) {
        return Context.getInstance().getEntity(uname);
    }


    public Actionnable actionForte(Entity entity, String ltype) {
        return new Actionnable(ActionType.FORTE, ltype, entity, page);
    }

    public Actionnable actionNormale(Entity entity, String ltype) {
        return new Actionnable(ActionType.NORMALE, ltype, entity, page);
    }

    public Actionnable actionConfirmer(Entity entity, String ltype) {
        return new Actionnable(ActionType.CONFIRMER, ltype, entity, page);
    }

    public Actionnable actionUc(Entity entity, String ltype) {
        return new Actionnable(ActionType.UCA, ltype, entity, page);
    }

    public Actionnable actionUcSpec(Entity entity, String ltype) {
        return new Actionnable(ActionType.UCA, ltype, entity, page).action(new ActionSpecifique());
    }

    public Actionnable recupererParId(Entity entity) {
        return new Actionnable(ActionType.NOUI, "recupererParId", entity, element).action(new ActionRecupererParId());
    }

    public Actionnable recupererParField(Entity entity, Field field) {
        return new Actionnable(ActionType.NOUI, "recupererParField", entity, element).action(new ActionRecupererParField(field));
    }


    public Actionnable initCreation(Entity entity, Field... fieldList) {
        return new Actionnable(ActionType.NOUI, "initCreation", entity, element).action(new ActionInitCreation(fieldList));
    }

    public Actionnable initModification(Entity entity) {
        return new Actionnable(ActionType.NOUI, "initModification", entity, element).action(new ActionInitModification());
    }

    public Actionnable initModification(Entity entity, Field... fieldList) {
        return new Actionnable(ActionType.NOUI, "initModification", entity, element).action(new ActionInitModification(fieldList));
    }

    public Actionnable listerEnPageParIdPere(Entity entity) {
        return new Actionnable(ActionType.NOUI, "listerEnPage", entity, element).action(new ActionListerEnPageParIdPere());
    }

    public Actionnable goToModule(Entity entity, String target) {
        return new Actionnable(ActionType.NOUI, "goToModule" + target, entity, element).action(new ActionGoToModule(target)).inViewOnly();
    }

    public Actionnable goToPage(Entity entity, String target) {
        return new Actionnable(ActionType.NOUI, "goToPage" + target, entity, element).action(new ActionGoToPage(target)).inViewOnly();
    }

    public Actionnable throwEvent(Entity entity, String target) {
        return new Actionnable(ActionType.NOUI, target, entity, element).action(new ActionEvent(target)).inViewOnly();
    }

    public Actionnable listerTout(Entity entity) {
        return new Actionnable(ActionType.NOUI, "lister", entity, element).action(new ActionLister());
    }

    public Actionnable listerParIdPere(Entity entity) {
        return new Actionnable(ActionType.NOUI, "listerParIdPere", entity, element).action(new ActionListerParIdPere());
    }

    public Actionnable consulterElement(Entity entity) {
        return new Actionnable(ActionType.NOUI, "consulter", entity, element).action(new ActionConsulterElement()).inViewOnly();
    }

    public Action listerParChamp(Field f) {
        return new ActionListerParChamp(f);
    }

    public Action listerParParam(String parName) {
        return new ActionListerParParam(parName);
    }

    public Actionnable recupererEnSession(Entity entity, String variable) {
        return new Actionnable(ActionType.NOUI, "recupererEnSession", entity, element).action(new ActionRecupererEnSession(variable));
    }

    public Actionnable recupererDepuisMdl(Entity entity, String mdlName) {
        return new Actionnable(ActionType.NOUI, "recupererEnSession", entity, element).action(new ActionRecupererDepuisMdl(mdlName));
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

    public Section section(Entity entity, Component... componentList) {
        return new Section(element, entity, componentList);
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

    public Component plaqueEtat(Entity e) {
        return new PlaqueEtat(element, e);
    }

    public Panneau panneau(Component... componentList) {
        return new Panneau(element, componentList);
    }

    public PanneauFiltre panneauFiltre(Entity entity, Component... componentList) {
        return new PanneauFiltre(element, entity, componentList);
    }

    public PanneauEtendable panneauEtendable(Component... componentList) {
        return new PanneauEtendable(element, componentList);
    }

    public MenuOnglet menuOnglet(Component... componentList) {
        return new MenuOnglet(element, componentList);
    }

    public Etat etat(Entity e, Field... fieldList) {
        return new Etat(element, e, fieldList);
    }

    public Etat etat(Field f, Entity e, Field... fieldList) {
        return new Etat(element, f, e, fieldList);
    }



    public Formulaire formulaire(Entity e, Field... fieldList) {
        return new Formulaire(element, e, fieldList);
    }

    public Filtre filtre(Entity e, Field... fieldList) {
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


    public Tableau tableauPagine(Entity e, Field... fieldList) {
        return new Tableau(element, e, new ActionChangerPageLister(), fieldList);
    }


    public Tableau tableauResultatPagine(Entity e, Field... fieldList) {
        return new Tableau(element, e, new ActionChangerPageChercher(), fieldList);
    }

    /*
     * public Tableau resultatPagine(Entity e, Field... fieldList) { return new Tableau(element, e, new
     * ActionChangerPageChercher(), fieldList); }
     */
    public Tableau tableau(Entity e, Field... fieldList) {
        return new Tableau(element, e, fieldList);
    }



    public Component blocAction(Component... componentList) {
        return new BlocAction(element, componentList);
    }

    public Field liste(Field f) {
        return new Liste((Ref<?>) (f));
    }

    public Field tag(Field f) {
        return new Tag(f);
    }

    public Field code(Field f) {
        return new Code(f);
    }

    public Field custom(Field f) {
        return new Custom(f);
    }

    public Field custom(String lname) {
        return new Custom(lname);
    }

    public Field cache(Field f) {
        return new Cache(f);
    }


    public Field rendu(String lname) {
        return new Rendu(lname);
    }

    public FilAriane filAriane(String uname, Component... componentList) {
        return new FilAriane(element, uname, componentList);
    }

}
