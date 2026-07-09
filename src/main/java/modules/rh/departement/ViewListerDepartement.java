package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Departement;

public class ViewListerDepartement extends RhElementComposer {
    public Component rootComponent() {

        Departement e = entity(Departement.class);
        return //
        section( //
                element(new TableauDepartement()) //
        ).actionBlock(button(addAction(e, pageCreerDepartement)));
    }

}
