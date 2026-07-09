package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Employe;

public class TableauEmploye extends RhElementComposer {


    private Action action;

    public TableauEmploye(Action action) {
        super();
        isElement();
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
                ).fillWith(action).onRowClick(goToPage(e, pageConsulterEmploye)) //
        );
    }

}
//
