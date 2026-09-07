package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Departement;
import modules.rh.RhModule;

public class ViewConsulterDepartement extends ViewComposer<Departement> {

        public Component rootComponent() {
                Departement e = entity(Departement.class);
                return section( //
                                block(//
                                                element(new EtatDepartement()), //
                                                actionBlock(//
                                                                button(editAction(e, RhModule.pageModifierDepartement)), //
                                                                button(backToListAction(e, RhModule.pageListerDepartement)), //
                                                                button(deleteAction(e).onSuccess(goToPage(e, RhModule.pageListerDepartement)))//
                                                )//
                                ).width("600px").margin("20px").background("blanc")//
                );
        }

}
