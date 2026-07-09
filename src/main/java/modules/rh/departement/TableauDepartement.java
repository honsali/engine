package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Departement;

public class TableauDepartement extends RhElementComposer {


    public TableauDepartement() {
        super();
        isElement();
    }

    public Component rootComponent() {
        Departement e = entity(Departement.class);
        return block(//
                table(e, //
                        e.nom, //
                        e.description//
                ).fillWith(listAll(e)).onRowClick(goToPage(e, pageConsulterDepartement)) //
        );
    }

}
//
