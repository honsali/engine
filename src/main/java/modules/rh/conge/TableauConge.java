package modules.rh.conge;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Conge;

public class TableauConge extends RhElementComposer {



    public TableauConge() {
        super();
        isElement();
    }

    public Component rootComponent() {
        Conge e = entity(Conge.class);
        return block(//
                table(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire//
                ).fillWith(listAll(e).byFatherId()).onRowClick(goToPage(e, pageConsulterConge)) //
        );
    }

}
//
