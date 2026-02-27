package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;

public class TableauDepartement extends ElementComposer {


    public TableauDepartement() {
        super("TableauDepartement", "/element");
    }

    public Component rootComponent() {
        Departement e = (Departement) getEntity("Departement");
        return block(//
                table(e, //
                        e.nom, //
                        e.description//
                ).fillWith(listAll(e)).onRowClick(goToPage(e, "PageConsulterDepartement")) //
        );
    }

}
//
