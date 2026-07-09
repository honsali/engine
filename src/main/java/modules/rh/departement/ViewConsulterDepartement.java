package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Departement;

public class ViewConsulterDepartement extends RhElementComposer {
    public Component rootComponent() {

        Departement e = entity(Departement.class);
        return section( //
                block(//
                        element(new EtatDepartement()), //
                        actionBlock(//
                                button(editAction(e, pageModifierDepartement)), //
                                button(backToListAction(e, pageListerDepartement)), //
                                button(deleteAction(e).onSuccess(goToPage(e, pageListerDepartement)))//
                        )//
                ).width("600px").margin("20px").background("blanc")//
        );
    }

}
