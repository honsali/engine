package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;

public class TableauConge extends ElementComposer {



    public TableauConge() {
        super("TableauConge", "/element");
    }

    public Component rootComponent() {
        Conge e = (Conge) getEntity("Conge");
        return block(//
                table(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire//
                ).fillWith(listAll(e).byFatherId()).onRowClick(goToPage(e, "PageConsulterConge")) //
        );
    }

}
//
