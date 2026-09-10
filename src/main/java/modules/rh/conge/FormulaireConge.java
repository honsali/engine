package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import modules.rh.RhModule;

public class FormulaireConge extends ElementComposer {

    private boolean enModification;

    public FormulaireConge(boolean enModification) {
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Conge e = entity(Conge.class);
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_)).inInit();
        }

        return block()
            .width("600px").margin("20px").background("blanc")
            .content(//
                form(e, //
                        e.code, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire, //
                        enModification ? hidden(e.id_) : null, //
                        enModification ? hidden(e.father) : null //
                ).columnNumber(1), //
                actionBlock(//
                        enModification ? //
                                element(updateAction(e).onSuccess(goToPage(e, RhModule.pageConsulterConge))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, RhModule.pageConsulterConge).byField(e.id_)).byFatherId()).byForm(), //

                        enModification ? //
                                button(backToDetailAction(e, RhModule.pageConsulterConge)) : //
                                button(backToListAction(e, RhModule.pageConsulterEmploye))//
                )//
            );
    }

}
//
