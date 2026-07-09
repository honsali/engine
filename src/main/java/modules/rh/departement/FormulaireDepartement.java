package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Departement;

public class FormulaireDepartement extends RhElementComposer {

    private boolean enModification;

    public FormulaireDepartement(boolean enModification) {
        super();
        isElement();
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Departement e = entity(Departement.class);
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
                        enModification ? element(updateAction(e).onSuccess(goToPage(e, pageConsulterDepartement))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, pageConsulterDepartement).byField(e.id_))).byForm(), //

                        enModification ? button(backToDetailAction(e, pageConsulterDepartement)) : button(backToListAction(e, pageListerDepartement))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
