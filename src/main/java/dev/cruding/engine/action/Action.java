package dev.cruding.engine.action;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public abstract class Action implements Comparable<Action> {
    public static final String standardActionList = "#initCreation#initModification#changerPage#chercher#lister#refuser#ajouter#creer#modifier#enregistrer#valider#annuler#rejeter#imprimer#verrouiller#deverrouiller#accepter#rejeter#confirmer#retourListe#retourConsulter#";


    public String id;
    public String lname;
    public Entity entity;
    public String actionType;
    protected String uc;
    protected boolean inViewOnly = false;
    public boolean withService = false;
    protected Field child;
    public String sourceDonnee;
    public boolean recharger = false;
    public boolean confirmer = false;
    public boolean byFatherId = false;
    public boolean byGrandFatherId = false;
    public boolean byId = false;
    public boolean byForm = false;
    public Field byField = null;
    public boolean byEntity = false;
    public Formulaire formulaire;
    public boolean resultatInId = false;
    public String lrest = "get";
    public String urest = "Get";

    protected boolean inElement = false;

    protected String mvcPath = ".";

    public Action(String actionType) {
        this.actionType = actionType;
    }

    public Action entity(Entity entity) {
        this.entity = entity;

        this.lname = actionType;
        if (standardActionList.indexOf(actionType) > 0) {
            this.lname = actionType + entity.uname;
        }
        return this;
    }

    public Action uc(String uc) {
        this.uc = uc;
        return this;
    }

    public Action child(Field child) {
        this.child = child;
        return this;
    }

    public boolean isInViewOnly() {
        return inViewOnly;
    }

    public boolean isInElement() {
        return inElement;
    }

    public Action byEntity() {
        this.byEntity = true;
        return this;
    }

    public Action lrest(String lrest) {
        this.lrest = lrest;
        this.urest = StringUtils.capitalize(lrest);
        return this;
    }

    public Action resultatInId() {
        this.resultatInId = true;
        return this;
    }

    public Action confirmer() {
        this.confirmer = true;
        return this;
    }

    public void setInElement(boolean inElement) {
        mvcPath = inElement ? ".." : ".";
        this.inElement = inElement;
    }

    public String actionName() {
        return lname;
    }

    public Action byId() {
        this.byId = true;
        return this;
    }

    public Action byForm() {
        this.byForm = true;
        return this;
    }

    public Action byGrandFatherId() {
        this.byGrandFatherId = true;
        return this;
    }

    public Action byFatherId() {
        this.byFatherId = true;
        return this;
    }

    public void addCtrlImport(MCFlow flow) {}

    public void addMdlImport(MCFlow flow) {}

    public void addMdlRequestAttribute(MCFlow flow) {}

    public void addMdlResultAttribute(MCFlow flow) {}

    public void addMdlStateAttribute(MCFlow flow) {}

    public void addMdlSelector(MCFlow flow, String uc) {}

    public void addCtrlImplementation(MCFlow flow) {}

    public void addViewScript(ViewFlow flow) {}

    public void addCtrlDeclaration(MCFlow flow) {}

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow flow) {}

    public void addRepositoryDeclaration(JavaFlow flow) {}

    public void addRepositoryImport(JavaFlow flow) {}

    public void addResourceDeclaration(JavaFlow flow) {}

    public void addResourceImport(JavaFlow flow) {}

    public void addServiceImport(Flow flow) {}

    public void addServiceDeclaration(Flow flow) {}

    public void addServicImplementation(Flow flow) {}

    public void addI18n(Flow flow, Page page) {}

    public void addActionModule(Flow flow, Page page) {}

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Action other = (Action) obj;

        return lname.equals(other.lname);
    }

    public int hashCode() {
        return Objects.hash(lname);
    }

    @Override
    public int compareTo(Action o) {
        if (this.lname == null)
            System.out.println("this.lname is null");
        return this.lname.compareTo(o.lname);
    }

}
