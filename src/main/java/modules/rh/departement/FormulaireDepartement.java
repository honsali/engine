package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Departement;
import modules.rh.RhModule;

public class FormulaireDepartement extends ElementComposer {

    private boolean enModification;

    public FormulaireDepartement(boolean enModification) {
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
                        enModification ? element(updateAction(e).onSuccess(goToPage(e, RhModule.pageConsulterDepartement))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, RhModule.pageConsulterDepartement).byField(e.id_))).byForm(), //

                        enModification ? button(backToDetailAction(e, RhModule.pageConsulterDepartement)) : button(backToListAction(e, RhModule.pageListerDepartement))//
                )//
        ).width("600px").margin("20px").background("blanc");//
    }

}
//
