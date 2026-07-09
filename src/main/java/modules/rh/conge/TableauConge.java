package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import modules.rh.RhProject;

public class TableauConge extends ElementComposer {

    public Component rootComponent() {
        Conge e = entity(Conge.class);
        return block(//
                table(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire//
                ).fillWith(listAll(e).byFatherId()).onRowClick(goToPage(e, RhProject.pageConsulterConge)) //
        );
    }

}
//
