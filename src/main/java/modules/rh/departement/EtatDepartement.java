package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Departement;

public class EtatDepartement extends RhElementComposer {


    public EtatDepartement() {
        super();
        isElement();
    }

    public Component rootComponent() {
        Departement e = entity(Departement.class);
        getByFieldAction(e, e.id_).inInit();
        return detail(e, //
                e.nom, //
                e.description//
        ).columnNumber(1);//
    }

}
//
