package dev.cruding.engine.action;

import java.util.ArrayList;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Page;



public class ActionWrapper {

    private static final Field[] emptyFieldList = new Field[] {};

    public Action action;

    public void action(Action action) {
        this.action = action;
    };

    public void setInElement(boolean inElement) {
        action.inElement(inElement);
    }

    public ActionType type() {
        return action.type;
    };

    public String lcoreName() {
        return action.lcoreName;
    };

    public boolean byRow() {
        return action.byRow;
    }

    public String ucoreName() {
        return action.ucoreName;
    };

    public String lnameWithEntity() {
        return action.lnameWithEntity;
    };

    public String lnameWithoutEntity() {
        return action.lnameWithoutEntity;
    };


    public String unameWithEntity() {
        return action.unameWithEntity;
    };


    public String unameWithoutEntity() {
        return action.unameWithoutEntity;
    };

    public String icon() {
        return action.icon;
    };

    public boolean byId() {
        return action.byId;
    };

    public boolean byFatherId() {
        return action.byFatherId;
    };

    public boolean byForm() {
        return action.byForm;
    };

    public boolean byEntity() {
        return action.byEntity;
    };

    public String byProp() {
        return action.byProp;
    };

    public Field[] byField() {
        return action.byField == null ? emptyFieldList : action.byField;
    };

    public boolean reload() {
        return action.reload;
    };

    public boolean confirm() {
        return action.confirm;
    };

    public ArrayList<Action> onSuccess() {
        return action.onSuccess;
    };


    public boolean hasSuccess() {
        return action.hasSuccess;
    };


    public boolean asList() {
        return action.asList;
    };

    public boolean hasSuccessInViewOnly() {
        return action.hasSuccessInViewOnly;
    };

    public boolean onSuccessAction() {
        return action.onSuccessAction;
    };

    public String actionKey() {
        return action.actionKey;
    };

    public Element element() {
        return action.element;
    };

    public Page page() {
        return action.page;
    };

    public Entity entity() {
        return action.entity;
    };

    public Page targetPage() {
        return action.targetPage;
    };

    public String uc() {
        return action.uc;
    };

    public boolean inViewOnly() {
        return action.inViewOnly;
    };

    public String dataSource() {
        return action.dataSource;
    };

    public Field resultIn() {
        return action.resultIn;
    };

    public String orderBy() {
        return action.orderBy;
    };


    public String mvcPath() {
        return action.element.path.endsWith("element") ? ".." : ".";
    };

    public boolean inElement() {
        return action.inElement;
    }

    public String lrest() {
        return action.lrest;
    }

    public String urest() {
        return action.urest;
    }

    public boolean flow() {
        return action.flow();
    }

    public boolean noUi() {
        return action.noUi();
    }

    public boolean ucDialogue() {
        return action.ucDialogue();
    }

    public boolean paginated() {
        return action.paginationAction != null || action.paginated;
    };

    public boolean waitUntilReady() {
        return action.waitUntilReady;
    }


    public boolean inInit() {
        return action.inInit;
    }


    public boolean filterOnLoad() {
        return action.filterOnLoad;
    }


    public boolean delayedCall() {
        return action.delayedCall;
    }
}
