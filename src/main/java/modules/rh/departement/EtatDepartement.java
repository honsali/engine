package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;

public class EtatDepartement extends ElementComposer {


    public EtatDepartement() {
        super("EtatDepartement", "/element");
    }

    public Component rootComponent() {
        Departement e = (Departement) getEntity("Departement");
        getByFieldAction(e, e.id_).inInit();
        return detail(e, //
                e.nom, //
                e.description//
        ).columnNumber(1);//
    }

}
//
