package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewGoToPageInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;

public class ActionGoToPage extends Action {


    private Page targetPage;

    public ActionGoToPage(Entite entite, Element element, String targetPage) {
        super(ActionType.NOUI, "goToPage" + targetPage, entite, element);
        this.targetPage = Contexte.getInstance().getPage(targetPage);
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewGoToPageInjection(targetPage);
    }



}
