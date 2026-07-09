package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import modules.rh.RhProject;

public class EtatConge extends ElementComposer {


    public Component rootComponent() {
        Conge e = entity(Conge.class);
        getByFieldAction(e, e.id_).inInit();

        return block(//
                detail(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge //
                ).columnNumber(1), actionBlock(//
                        button(editAction(e, RhProject.pageModifierConge)), //
                        button(backToListAction(e, RhProject.pageConsulterEmploye)), //
                        button(deleteAction(e).onSuccess(goToPage(e, RhProject.pageConsulterEmploye)))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
