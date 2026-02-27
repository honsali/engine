package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;

public class FormulaireConge extends ElementComposer {

    private boolean enModification;

    public FormulaireConge(boolean enModification) {
        super("FormulaireConge", "/element");
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Conge e = (Conge) getEntity("Conge");
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
                                element(updateAction(e).onSuccess(goToPage(e, "PageConsulterConge"))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, "PageConsulterConge").byField(e.id_)).byFatherId()).byForm(), //

                        enModification ? //
                                button(backToDetailAction(e, "PageConsulterConge")) : //
                                button(backToListAction(e, "PageConsulterEmploye"))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
