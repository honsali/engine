package dev.cruding.engine.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.injection.ActionBusinessInjection;
import dev.cruding.engine.injection.ActionCtrlInjection;
import dev.cruding.engine.injection.ActionMdlInjection;
import dev.cruding.engine.injection.ActionRepoInjection;
import dev.cruding.engine.injection.ActionResourceInjection;
import dev.cruding.engine.injection.ActionServiceInjection;
import dev.cruding.engine.injection.ActionViewInjection;

public abstract class Action {

    public enum ActionType {
        NOUI, //
        UCA, //
        NORMAL, //
        PRIMARY, //
        FLOW, //
        CONFIRM, //
        MODAL,
    }

    public static final Comparator<Action> ORDER_BY_NAME = Action::compareByName;

    private static int rank = 0;
    public ActionCtrlInjection ctrlActionInjection;
    public ActionMdlInjection mdlActionInjection;
    public ActionRepoInjection repoActionInjection;
    public ActionResourceInjection resourceActionInjection;
    public ActionBusinessInjection businessActionInjection;
    public ActionServiceInjection serviceActionInjection;
    public ActionViewInjection viewActionInjection;
    public ActionType type;
    public String lcoreName;
    public String ucoreName;
    public String lnameWithEntity;
    public String unameWithEntity;
    public String lnameWithoutEntity;
    public String unameWithoutEntity;
    public String icon = null;
    public boolean byId = false;
    public boolean byFatherId = false;
    public boolean byForm = false;
    public boolean byEntity = false;
    public boolean byRow = false;
    public String byProp = null;
    public Field[] byField = null;
    public boolean reload = false;
    public boolean confirm;
    public ArrayList<Action> onSuccess = new ArrayList<>();
    public String actionKey = null;
    public Element element;
    public Page page;
    public Entity entity;
    public Page targetPage;
    public String uc;
    public boolean inViewOnly = false;
    public boolean byPassSecurity = false;
    public boolean inElement = false;
    public String dataSource;
    public Field resultIn = null;
    public String orderBy;
    public String mvcPath = ".";
    public String model;
    public boolean isEmpty = false;
    public boolean hasSuccess = false;
    public boolean hasSuccessInViewOnly = false;
    public String lrest = "get";
    public String urest = "Get";
    public Action paginationAction = null;
    public boolean paginated = false;
    public boolean onSuccessAction = false;
    public boolean asList = false;
    public boolean waitUntilReady = false;
    private String id;
    public String nameVariable;
    public boolean inInit;
    public boolean filterOnLoad;
    public boolean delayedCall;


    public Action(ActionType type, String lcoreName, Entity entity, Element element) {
        this.id = "" + rank++;
        this.type = type;
        this.entity = entity;
        if (this.entity != null) {
            this.orderBy = entity.uid;
        }
        this.page = element.page;
        this.uc = page.uc;
        lcoreName(lcoreName);
        if (ucConfirmer() || ucDialogue()) {
            this.confirm();
        }
        element(element);
        Context.getInstance().addAction(this);
    }

    public void init() {
        ctrlActionInjection = new ActionCtrlInjection();
        mdlActionInjection = new ActionMdlInjection();
        repoActionInjection = new ActionRepoInjection();
        resourceActionInjection = new ActionResourceInjection();
        businessActionInjection = new ActionBusinessInjection();
        serviceActionInjection = new ActionServiceInjection();
        viewActionInjection = new ActionViewInjection();
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
        if (this.entity != null && !n.endsWith(this.entity.uname)) {
            n = n + this.entity.uname;
        } else if (!n.endsWith(this.page.entityUname)) {
            n = n + this.page.entityUname;
        }
        this.lnameWithoutEntity(this.lcoreName);
        this.lnameWithEntity(n);
        return this;
    }

    public Action lnameWithoutEntity(String lnameWithoutEntity) {
        this.lnameWithoutEntity = lnameWithoutEntity;
        this.unameWithoutEntity = StringUtils.capitalize(this.lnameWithoutEntity);
        return this;
    }

    public Action lnameWithEntity(String lnameWithEntity) {
        this.lnameWithEntity = lnameWithEntity;
        this.unameWithEntity = StringUtils.capitalize(this.lnameWithEntity);
        this.actionKey = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.lnameWithEntity), "_").toUpperCase();
        return this;
    }

    public Action element(Element element) {
        this.element = element;
        for (Action a : onSuccess) {
            // if (a.element == null) {
            a.element(element);
            // }
        }
        return this;
    }

    public Action byId() {
        this.byId = true;
        return this;
    }

    public Action byFatherId() {
        this.byFatherId = true;
        if (paginationAction != null) {
            paginationAction.byFatherId();
        }
        return this;
    }

    public Action inElement(boolean inElement) {
        this.inElement = inElement;
        for (Action a : onSuccess) {
            a.inElement(inElement);
        }
        if (paginationAction != null) {
            paginationAction.inElement(inElement);
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

    public Action byPassSecurity() {
        this.byPassSecurity = true;
        return this;
    }

    public Action resultIn(Field resultIn) {
        this.resultIn = resultIn;
        return this;
    }

    public Action byForm(boolean byForm) {
        this.byForm = byForm;
        return this;
    }

    public Action byForm() {
        this.byForm = true;
        return this;
    }

    public Action byEntity() {
        this.byEntity = true;
        return this;
    }

    public Action byRow() {
        this.byRow = true;
        return this;
    }

    public Action byProp(String byProp) {
        this.byProp = byProp;
        return this;
    }

    public Action byField(Field... field) {
        this.byField = field;
        if (paginationAction != null) {
            paginationAction.byField(field);
        }
        return this;
    }

    public Action reloadOnSuccess() {
        this.reload = true;
        return this;
    }

    public Action icon(String icon) {
        this.icon = icon;
        return this;
    }

    public Action targetPage(Page targetPage) {
        this.targetPage = Objects.requireNonNull(targetPage, "Target page cannot be null");
        return this;
    }

    public Action onSuccess(Action... actionList) {
        for (Action action : actionList) {
            this.onSuccess.add(action);
            action.onSuccessAction = true;
            // action.type(ActionType.FLOW);
            if (action.element == null) {
                action.element(this.element);
            }
            hasSuccessInViewOnly = hasSuccessInViewOnly || action.inViewOnly;
            hasSuccess = true;
        }
        return this;
    }

    public Action confirm() {
        this.confirm = true;
        return this;
    }

    public Action uc(String uc) {
        this.uc = uc;
        return this;
    }

    public Action model(String model) {
        this.model = model;
        return this;
    }

    public Action orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Action dataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public boolean normal() {
        return type == ActionType.NORMAL;
    };

    public boolean primary() {
        return type == ActionType.PRIMARY;
    };

    public boolean ucConfirmer() {
        return type == ActionType.CONFIRM;
    };

    public boolean ucDialogue() {
        return type == ActionType.MODAL;
    };

    public boolean flow() {
        return type == ActionType.FLOW;
    };

    public Action isEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
        return this;
    };

    public Action asList() {
        asList = true;
        return this;
    };

    public boolean nfc() {
        return normal() || primary() || ucConfirmer();
    };

    public Action paginationAction(Action action) {
        this.paginationAction = action;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Action other = (Action) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private static int compareByName(Action left, Action right) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        int nameComparison = Strings.CS.compare(left.lnameWithEntity, right.lnameWithEntity);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Strings.CS.compare(left.id, right.id);
    }

    public Action waitUntilReady() {
        waitUntilReady = true;
        return this;
    };


    public Action inInit() {
        inInit = true;
        return this;
    };
}
