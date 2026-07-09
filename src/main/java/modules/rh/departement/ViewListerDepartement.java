package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Departement;
import modules.rh.RhProject;

public class ViewListerDepartement extends ViewComposer<Departement> {

    public Component rootComponent() {
        Departement e = entity(Departement.class);
        return //
        section( //
                element(new TableauDepartement()) //
        ).actionBlock(button(addAction(e, RhProject.pageCreerDepartement)));
    }

}
