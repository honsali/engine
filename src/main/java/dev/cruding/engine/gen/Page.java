package dev.cruding.engine.gen;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.action.impl.ActionChangerPageChercher;
import dev.cruding.engine.action.impl.ActionChangerPageLister;
import dev.cruding.engine.action.impl.ActionChangerPageListerParIdPere;
import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.action.impl.ActionConsulterDansDialogue;
import dev.cruding.engine.action.impl.ActionConsulterElement;
import dev.cruding.engine.action.impl.ActionEvent;
import dev.cruding.engine.action.impl.ActionGoToModule;
import dev.cruding.engine.action.impl.ActionLister;
import dev.cruding.engine.action.impl.ActionListerEnPage;
import dev.cruding.engine.action.impl.ActionListerEnPageParIdPere;
import dev.cruding.engine.action.impl.ActionListerParChamp;
import dev.cruding.engine.action.impl.ActionListerParIdPere;
import dev.cruding.engine.action.impl.ActionListerParParam;
import dev.cruding.engine.action.impl.ActionRecupererDepuisMdl;
import dev.cruding.engine.action.impl.ActionRecupererEnSession;
import dev.cruding.engine.action.impl.ActionRecupererParId;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Bouton;
import dev.cruding.engine.component.bouton.BoutonActionDialogue;
import dev.cruding.engine.component.bouton.BoutonActionLocale;
import dev.cruding.engine.component.bouton.BoutonActionSpecifique;
import dev.cruding.engine.component.bouton.BoutonActionWorkflow;
import dev.cruding.engine.component.bouton.BoutonCreer;
import dev.cruding.engine.component.bouton.BoutonEnregistrer;
import dev.cruding.engine.component.bouton.BoutonGoToPage;
import dev.cruding.engine.component.bouton.actionUC.BoutonAjouter;
import dev.cruding.engine.component.bouton.actionUC.BoutonModifier;
import dev.cruding.engine.component.bouton.actionUC.BoutonRetour;
import dev.cruding.engine.component.bouton.actionUC.BoutonRetourConsulter;
import dev.cruding.engine.component.bouton.actionUC.BoutonRetourListe;
import dev.cruding.engine.component.bouton.actionUC.BoutonSupprimer;
import dev.cruding.engine.component.bouton.bloc.BlocAction;
import dev.cruding.engine.component.conteneur.Bloc;
import dev.cruding.engine.component.conteneur.BlocActionEncadre;
import dev.cruding.engine.component.conteneur.BlocActionPanneau;
import dev.cruding.engine.component.conteneur.FilAriane;
import dev.cruding.engine.component.conteneur.MenuOnglet;
import dev.cruding.engine.component.conteneur.PanneauEncadrePrimaire;
import dev.cruding.engine.component.conteneur.PanneauEncadreSecondaire;
import dev.cruding.engine.component.conteneur.PanneauMaitre;
import dev.cruding.engine.component.conteneur.PanneauPleinPrimaire;
import dev.cruding.engine.component.conteneur.PanneauPleinSecondaire;
import dev.cruding.engine.component.conteneur.PlaqueEtat;
import dev.cruding.engine.component.conteneur.Section;
import dev.cruding.engine.component.conteneur.Separateur;
import dev.cruding.engine.component.entite.BlocSpecifique;
import dev.cruding.engine.component.entite.Child;
import dev.cruding.engine.component.entite.Etat;
import dev.cruding.engine.component.entite.Filtre;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.component.entite.Panneau;
import dev.cruding.engine.component.entite.PanneauSpecifique;
import dev.cruding.engine.component.tableau.Tableau;
import dev.cruding.engine.component.tableau.TableauRefMany;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Code;
import dev.cruding.engine.field.impl.ColonneAction;
import dev.cruding.engine.field.impl.Custom;
import dev.cruding.engine.field.impl.Liste;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.Rendu;
import dev.cruding.engine.field.impl.Tag;
import dev.cruding.engine.gen.helper.Util;

public abstract class Page implements Comparable<Page> {
    public ArrayList<Component> componentList = new ArrayList<>();
    public ArrayList<Element> listeElement = new ArrayList<>();
    public ArrayList<Component> listeActionInView = new ArrayList<>();

    public String path;

    public String name;
    public String uc;
    public String route;

    public Module moduleDefinition;
    public String packge;
    public String actionUname;
    public String actionLname;
    public String entityUname;
    public String entityLname;

    public Page() {
        this(false);
    }

    public Page(boolean emptyPage) {
        name = this.getClass().getSimpleName();
        packge = StringUtils.substringAfter(this.getClass().getPackageName(), "modules.");
        this.uc = name.substring(4);
        if (!emptyPage) {
            int idx = Util.findThirdCapitalIndex(name);
            actionUname = name.substring(4, idx);
            entityUname = name.substring(idx);
            this.uc = name.substring(4);
            actionLname = StringUtils.uncapitalize(actionUname);
            entityLname = StringUtils.uncapitalize(entityUname);

            this.path = "modules/" + packge.replace('.', '/') + "/" + actionLname;

        }

    }

    public void addActionInView(Component bouton) {
        listeActionInView.add(bouton);
    }

    public void init() {
        moduleDefinition = Context.getInstance().getModule(packge);
        compose();
    }

    public abstract void compose();

    public int compareTo(Page p2) {
        return actionUname.compareTo(p2.actionUname) + entityUname.compareTo(p2.entityUname);
    }

    public Entity getEntity(String uname) {
        return Context.getInstance().getEntity(uname);
    }

    public boolean estReelle() {
        return componentList.size() > 0;
    }

    public void addBloc(Component rootComponent) {
        componentList.add(rootComponent);
    }

    public void addElement(Element element) {
        listeElement.add(element);
    }

    public Section section(Component... componentList) {
        return new Section(this, componentList);
    }

    public Bloc bloc(Component... componentList) {
        return new Bloc(this, componentList);
    }

    public BlocActionEncadre blocActionEncadre(Component... componentList) {
        return new BlocActionEncadre(this, componentList);
    }

    public Component panneauEncadrePrimaire(Component... componentList) {
        return new PanneauEncadrePrimaire(this, componentList);
    }

    public Component panneauEncadreSecondaire(Component... componentList) {
        return new PanneauEncadreSecondaire(this, componentList);
    }

    public PanneauPleinPrimaire panneauPleinPrimaire(Component... componentList) {
        return new PanneauPleinPrimaire(this, componentList);
    }

    public PanneauPleinSecondaire panneauPleinSecondaire(Component... componentList) {
        return new PanneauPleinSecondaire(this, componentList);
    }


    public Component plaqueEtat(Entity e) {
        return new PlaqueEtat(this, e);
    }

    public Component blocActionPanneau() {
        return new BlocActionPanneau(this);
    }

    public Action goToModule(String targetModule) {
        return new ActionGoToModule(this, targetModule);
    }

    public Action consulterElement(Entity e) {
        return new ActionConsulterElement(this, e);
    }

    public Action consulterDansDialogue(Entity e) {
        return new ActionConsulterDansDialogue(this, e);
    }

    public ActionAvecTable listerParIdPere() {
        return new ActionListerParIdPere();
    }

    public ActionAvecTable listerEnPageParIdPere() {
        return new ActionListerEnPageParIdPere();
    }

    public ActionAvecTable listerParChamp(Field f) {
        return new ActionListerParChamp(f);
    }

    public ActionAvecTable listerParParam(String parName) {
        return new ActionListerParParam(parName);
    }


    public ActionAvecTable listerTout() {
        return new ActionLister();
    }

    public ActionAvecTable listerTout(boolean avecIdPere) {
        return new ActionLister(avecIdPere);
    }

    public ActionAvecTable listerTout(boolean avecIdPere, boolean avecIdGrandPere) {
        return new ActionLister(avecIdPere, avecIdGrandPere);
    }

    public Action recupererParId() {
        return new ActionRecupererParId();
    }

    public ActionRecupererEnSession recupererEnSession() {
        return new ActionRecupererEnSession();
    }

    public ActionRecupererDepuisMdl recupererDepuisMdl(String lname) {
        return new ActionRecupererDepuisMdl(lname);
    }

    public ActionAvecTable listerEnPage() {
        return new ActionListerEnPage();
    }

    public Action chercher() {
        return new ActionChercher();
    }


    public Action action(Entity e, String actionType) {
        return new ActionVide(e, actionType);
    }

    public Component elementPanneau(Entity e, Component... componentList) {
        return new Panneau(this, e, componentList);
    }

    public PanneauMaitre panneauMaitre(Component... componentList) {
        return new PanneauMaitre(this, componentList);
    }

    public MenuOnglet menuOnglet(Component... componentList) {
        return new MenuOnglet(this, componentList);
    }

    public Filtre filtre(Entity e, Field... fieldList) {
        return new Filtre(this, e, false, fieldList);
    }

    public Filtre filtreInline(Entity e, Field... fieldList) {
        return new Filtre(this, e, true, fieldList);
    }

    public Etat etat(Entity e, Field... fieldList) {
        return new Etat(this, e, fieldList);
    }

    public BlocSpecifique bloc(String lname) {
        return new BlocSpecifique(this, lname);
    }

    public PanneauSpecifique panneau(String lname) {
        return new PanneauSpecifique(this, lname);
    }

    public Etat etat(Field f, Entity e, Field... fieldList) {
        return new Etat(this, f, e, fieldList);
    }

    public Formulaire formulaire(Entity e, Field... fieldList) {
        return new Formulaire(this, e, fieldList);
    }

    public Component child(Entity e, Field field) {
        return new Child(this, e, field);
    }

    public Component separateur() {
        return new Separateur(this);
    }

    public Component separateur(int height) {
        return new Separateur(this, height);
    }

    public Tableau tableauPagine(Entity e, Field... fieldList) {
        if (e.haveFather) {
            return new Tableau(this, e, new ActionChangerPageListerParIdPere(), fieldList);
        }
        return new Tableau(this, e, new ActionChangerPageLister(), fieldList);
    }

    public Tableau resultatPagine(Entity e, Field... fieldList) {
        return new Tableau(this, e, new ActionChangerPageChercher(), fieldList);
    }

    public Tableau tableau(Entity e, Field... fieldList) {
        return new Tableau(this, e, fieldList);
    }

    public TableauRefMany tableauRefMany(Entity e, Field... fieldList) {
        return new TableauRefMany(this, e, fieldList);
    }

    public Component boutonAjouter(Entity e) {
        return new BoutonAjouter(this, e);
    }

    public Component boutonAjouter(String targetPage) {
        return new BoutonAjouter(this, targetPage);
    }

    public Component boutonAjouter(Entity e, String targetPage) {
        return new BoutonAjouter(this, e, targetPage);
    }

    public BoutonCreer boutonCreer(Entity e) {
        return new BoutonCreer(this, e);
    }

    public Component boutonCreer(Entity e, String lname) {
        return new BoutonCreer(this, e, lname);
    }


    public Component boutonRetourConsulter(String targetPage) {
        return new BoutonRetourConsulter(this, targetPage);
    }

    public Component boutonModifier(Entity e, String targetPage) {
        return new BoutonModifier(this, e, targetPage);
    }

    public BoutonEnregistrer boutonEnregistrer(Entity e) {
        return new BoutonEnregistrer(this, e);
    }


    public Component boutonSupprimer(Entity e, String targetPage) {
        return new BoutonSupprimer(this, e, targetPage);
    }

    public Component boutonRetour(String targetPage) {
        return new BoutonRetour(this, targetPage);
    }

    public Component boutonRetourListe(String targetPage) {
        return new BoutonRetourListe(this, targetPage);
    }

    public Component boutonRetour(Entity e, String targetPage) {
        return new BoutonRetour(this, e, targetPage);
    }


    public BoutonGoToPage boutonGoToPage(Entity e, String targetPage, String actionType) {
        return new BoutonGoToPage(this, e, targetPage, actionType);
    }

    public BoutonActionLocale boutonActionLocale(Entity e, String actionType) {
        return new BoutonActionLocale(this, e, actionType);
    }

    public BoutonActionSpecifique boutonActionNormale(Entity e, String actionType) {
        return new BoutonActionSpecifique(this, e, actionType);
    }

    public Bouton boutonActionConfirmer(Entity e, String actionType) {
        return (new BoutonActionSpecifique(this, e, actionType)).confirmer(true);
    }

    public Bouton boutonActionWorkflow(Entity e, String actionType) {
        return new BoutonActionWorkflow(this, e, actionType);
    }

    public BoutonActionDialogue boutonActionCtrlDialogue(Entity e, String actionType) {
        return new BoutonActionDialogue(this, e, actionType);
    }

    public BoutonActionDialogue boutonActionDialogue(Entity e, String actionType, Field... field) {
        return new BoutonActionDialogue(this, e, actionType, false, field);
    }

    public BoutonActionDialogue boutonActionCtrlDialogue(Entity e, String actionType, Field... field) {
        return new BoutonActionDialogue(this, e, actionType, field);
    }

    public ActionEvent actionEvent(Entity e, String actionType) {
        return new ActionEvent(e, actionType);
    }

    public ActionEvent actionEvent(String actionType) {
        return new ActionEvent(actionType);
    }

    public Component blocAction(Component... componentList) {
        return new BlocAction(this, componentList);
    }

    public void initData(Entity entity, Action action) {
        Context.getInstance().addAction(action, this, entity);
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

    public Field action(Bouton b) {
        return new ColonneAction(b);
    }

    public Field rendu(String lname) {
        return new Rendu(lname);
    }

    public FilAriane filAriane(String uname, Component... componentList) {
        return new FilAriane(this, uname, componentList);
    }

}
