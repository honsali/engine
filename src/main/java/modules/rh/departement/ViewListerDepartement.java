package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;

public class ViewListerDepartement extends ElementComposer {
    public ViewListerDepartement() {
        super("ViewListerDepartement", "/");
    }

    public Component rootComponent() {

        Departement e = (Departement) getEntity("Departement");
        return //
        section( //
                element(new TableauDepartement()) //
        ).actionBlock(button(addAction(e, "PageCreerDepartement")));
    }

}
