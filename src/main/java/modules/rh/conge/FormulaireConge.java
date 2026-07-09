package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import modules.rh.RhProject;

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
                                element(updateAction(e).onSuccess(goToPage(e, RhProject.pageConsulterConge))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, RhProject.pageConsulterConge).byField(e.id_)).byFatherId()).byForm(), //

                        enModification ? //
                                button(backToDetailAction(e, RhProject.pageConsulterConge)) : //
                                button(backToListAction(e, RhProject.pageConsulterEmploye))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
