package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;

public class FormulaireDepartement extends ElementComposer {

    private boolean enModification;

    public FormulaireDepartement(boolean enModification) {
        super("FormulaireDepartement", "/element");
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Departement e = (Departement) getEntity("Departement");
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_));
        }

        return block(//
                form(e, //
                        e.nom, //
                        e.description, //
                        enModification ? hidden(e.id_) : null //
                ).columnNumber(1), //
                actionBlock(//
                        enModification ? element(updateAction(e).onSuccess(goToPage(e, "PageConsulterDepartement"))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, "PageConsulterDepartement").byField(e.id_))).byForm(), //

                        enModification ? button(backToDetailAction(e, "PageConsulterDepartement")) : button(backToListAction(e, "PageListerDepartement"))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
