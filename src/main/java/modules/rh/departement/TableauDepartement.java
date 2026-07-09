package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;
import modules.rh.RhProject;

public class TableauDepartement extends ElementComposer {

    public Component rootComponent() {
        Departement e = entity(Departement.class);
        return block(//
                table(e, //
                        e.nom, //
                        e.description//
                ).fillWith(listAll(e)).onRowClick(goToPage(e, RhProject.pageConsulterDepartement)) //
        );
    }

}
//
