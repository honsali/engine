package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;

public class ViewConsulterDepartement extends ElementComposer {
    public ViewConsulterDepartement() {
        super("ViewConsulterDepartement", "/");
    }

    public Component rootComponent() {

        Departement e = (Departement) getEntity("Departement");
        return section( //
                block(//
                        element(new EtatDepartement()), //
                        actionBlock(//
                                button(editAction(e, "PageModifierDepartement")), //
                                button(backToListAction(e, "PageListerDepartement")), //
                                button(deleteAction(e).onSuccess(goToPage(e, "PageListerDepartement")))//
                        )//
                ).width("600px").margin("20px").background("blanc")//
        );
    }

}
