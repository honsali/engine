package dev.cruding.engine.action.list;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.list.injection.ListBusinessInjection;
import dev.cruding.engine.action.list.injection.ListCtrlInjection;
import dev.cruding.engine.action.list.injection.ListMdlInjection;
import dev.cruding.engine.action.list.injection.ListRepoInjection;
import dev.cruding.engine.action.list.injection.ListResourceInjection;
import dev.cruding.engine.action.list.injection.ListServiceInjection;
import dev.cruding.engine.action.list.injection.ListViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class ListAction extends Action {

    public ListAction(Entity entity, Element element) {
        super(ActionType.NOUI, "lister", entity, element);
    }

    public void overrideActionInjection() {
        String lcn = "";

        if (byFatherId && entity.haveFather) {
            lcn = lcn + "ParId" + entity.ufather;
        }
        lcoreName("lister");
        lnameWithEntity("lister" + entity.uname + lcn);
        lnameWithoutEntity("lister" + lcn);

        ctrlActionInjection = new ListCtrlInjection();
        mdlActionInjection = new ListMdlInjection();
        repoActionInjection = new ListRepoInjection();
        resourceActionInjection = new ListResourceInjection();
        businessActionInjection = new ListBusinessInjection();
        serviceActionInjection = new ListServiceInjection();
        viewActionInjection = new ListViewInjection();
    }
}
