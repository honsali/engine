package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;

public class EtatConge extends ElementComposer {


    public EtatConge() {
        super("EtatConge", "/element");
    }

    public Component rootComponent() {
        Conge e = (Conge) getEntity("Conge");
        getByFieldAction(e, e.id_).inInit();

        return block(//
                detail(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge //
                ).columnNumber(1), actionBlock(//
                        button(editAction(e, "PageModifierConge")), //
                        button(backToListAction(e, "PageConsulterEmploye")), //
                        button(deleteAction(e).onSuccess(goToPage(e, "PageConsulterEmploye")))//
                )////
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
