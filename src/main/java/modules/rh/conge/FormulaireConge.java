package modules.rh.conge;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Conge;

public class FormulaireConge extends RhElementComposer {

    private boolean enModification;

    public FormulaireConge(boolean enModification) {
        super();
        isElement();
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Conge e = entity(Conge.class);
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_)).inInit();
        }

        return block(//
                form(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        enModification ? hidden(e.id_) : null, //
                        enModification ? hidden(e.father) : null //
                ).columnNumber(1), //
                actionBlock(//
                        enModification ? //
                                element(updateAction(e).onSuccess(goToPage(e, pageConsulterConge))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, pageConsulterConge).byField(e.id_)).byFatherId()).byForm(), //

                        enModification ? //
                                button(backToDetailAction(e, pageConsulterConge)) : //
                                button(backToListAction(e, pageConsulterEmploye))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
