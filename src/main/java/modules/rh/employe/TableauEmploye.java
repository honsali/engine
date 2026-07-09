package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;
import modules.rh.RhProject;

public class TableauEmploye extends ElementComposer {


    private Action action;

    public TableauEmploye(Action action) {
        this.action = action;
    }

    public Component rootComponent() {
        Employe e = entity(Employe.class);
        return block(//
                table(e, //
                        e.matricule, //
                        e.nom, //
                        e.prenom, //
                        e.fonction, //
                        e.departement//
                ).fillWith(action).onRowClick(goToPage(e, RhProject.pageConsulterEmploye)) //
        );
    }

}
//
