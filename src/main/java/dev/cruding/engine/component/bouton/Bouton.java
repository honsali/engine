package dev.cruding.engine.component.bouton;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.ActionPart;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Page;

public abstract class Bouton extends Component {

    public static final HashMap<String, String> iconeMap = new HashMap<>();
    static {
        iconeMap.put("ajouter", "PlusCircleFilled");
        iconeMap.put("creer", "PlusCircleFilled");
        iconeMap.put("modifier", "EditOutlined");
        iconeMap.put("enregistrer", "EditOutlined");
        iconeMap.put("valider", "CheckOutlined");
        iconeMap.put("annuler", "CloseOutlined");
    }

    public String ltype;
    public String utype;
    public String lname;
    public String uname;
    public String cname;
    public String icone = null;
    public boolean byId = false;
    public boolean byFatherId = false;
    public boolean byGrandFatherId = false;
    public boolean byForm = false;
    public boolean byEntity = false;
    public Field byField = null;
    public boolean recharger = false;
    public Page targetPage;
    public String actionType;
    public Element element;
    public boolean confirmer;
    public String siReussiGoToPage;
    public ActionPart siReussiAction;

    public Action action;

    public Bouton(Page page) {
        super(page);
    }

    public Bouton(Page page, Entity entity) {
        super(page, entity);
    }

    public Bouton(Page page, Entity entity, String ltype) {
        super(page, entity);
        ltype(ltype);
    }

    public Bouton(Page page, Entity entity, Component... componentList) {
        super(page, entity, componentList);
    }

    public Bouton(Page page, Entity entity, Field... fieldList) {
        super(page, entity, fieldList);
    }

    public Bouton(Page page, Component... componentList) {
        super(page, componentList);
    }

    public Bouton(Page page, Field... fieldList) {
        super(page, fieldList);
    }

    public Bouton ltype(String ltype) {
        this.ltype = ltype;
        this.utype = StringUtils.capitalize(ltype);
        this.lname = ltype;
        if (Action.standardActionList.indexOf(ltype) > 0) {
            this.lname = ltype + entity.uname;
        }
        this.uname = StringUtils.capitalize(lname);
        this.cname = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(ltype), "_").toUpperCase();
        return this;
    }

    public Bouton byId() {
        this.byId = true;
        this.action.byId = true;
        return this;
    }

    public Bouton byFatherId() {
        this.byFatherId = true;
        this.action.byFatherId = true;
        return this;
    }

    public Bouton byGrandFatherId() {
        this.byGrandFatherId = true;
        this.action.byGrandFatherId = true;
        return this;
    }


    public Bouton byForm() {
        this.byForm = true;
        this.action.byForm = true;
        return this;
    }

    public Bouton byField(Field field) {
        this.byField = field;
        this.action.byField = field;
        return this;
    }

    public Bouton siReussiRecharger() {
        this.recharger = true;
        this.action.recharger = true;
        return this;
    }


    public Bouton icone(String icone) {
        this.icone = icone;
        return this;
    }

    public Bouton siReussiGoToPage(String page) {
        this.siReussiGoToPage = page;
        return this;
    }

    public Bouton confirmer(boolean confirmer) {
        this.confirmer = confirmer;
        this.action.confirmer();
        return this;
    }

    public Bouton lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.action.lname = lname;
        return this;
    }

    public Bouton byEntity() {
        this.byEntity = true;
        this.action.byEntity = true;
        return this;
    }

    public Bouton siReussiAction(ActionPart action) {
        this.siReussiAction = action;
        return this;
    }

}
