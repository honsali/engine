package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class TableauEmploye extends ElementComposer {


    private Action action;

    public TableauEmploye(Action action) {
        super("TableauEmploye", "/element");
        this.action = action;
    }

    public Component rootComponent() {
        Employe e = (Employe) getEntity("Employe");
        return block(//
                table(e, //
                        e.matricule, //
                        e.nom, //
                        e.prenom, //
                        e.fonction, //
                        e.departement//
                ).fillWith(action).onRowClick(goToPage(e, "PageConsulterEmploye")) //
        );
    }

}
//
