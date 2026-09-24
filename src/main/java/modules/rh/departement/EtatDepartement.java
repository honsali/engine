package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.core.ElementComposer;
import model.rh.Departement;

public class EtatDepartement extends ElementComposer {

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
