package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import modules.rh.RhModule;

public class EtatConge extends ElementComposer {


    public Component rootComponent() {
        Conge e = entity(Conge.class);
        getByFieldAction(e, e.id_).inInit();

        return block()
            .width("600px").margin("20px").background("blanc")
            .content(//
                detail(e, //
                        e.code, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire //
                ).columnNumber(1), actionBlock(//
                        button(editAction(e, RhModule.pageModifierConge)), //
                        button(backToListAction(e, RhModule.pageConsulterEmploye)), //
                        button(deleteAction(e).onSuccess(goToPage(e, RhModule.pageConsulterEmploye)))//
                )//
            );
    }

}
//
