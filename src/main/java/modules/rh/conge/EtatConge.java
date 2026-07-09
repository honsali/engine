package modules.rh.conge;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Conge;

public class EtatConge extends RhElementComposer {


    public EtatConge() {
        super();
        isElement();
    }

    public Component rootComponent() {
        Conge e = entity(Conge.class);
        getByFieldAction(e, e.id_).inInit();

        return block(//
                detail(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge //
                ).columnNumber(1), actionBlock(//
                        button(editAction(e, pageModifierConge)), //
                        button(backToListAction(e, pageConsulterEmploye)), //
                        button(deleteAction(e).onSuccess(goToPage(e, pageConsulterEmploye)))//
                )////
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
