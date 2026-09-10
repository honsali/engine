package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Conge;
import model.rh.Employe;
import modules.rh.RhModule;
import modules.rh.conge.TableauConge;

public class ViewConsulterEmploye extends ViewComposer<Employe> {

    public Component rootComponent() {
        Employe e = entity(Employe.class);
        Conge c = entity(Conge.class);
        return section(
            tabMenu(
                block()
                    .name("employe").margin("20px")
                    .content(
                        element(new EtatEmploye()),
                        actionBlock(
                            button(editAction(e, RhModule.pageModifierEmploye)),
                            button(backToListAction(e, RhModule.pageFiltrerEmploye)),
                            button(deleteAction(e).onSuccess(goToPage(e, RhModule.pageFiltrerEmploye)))
                        )
                    ),
                block()
                    .name("conge").margin("20px")
                    .content(
                        element(new TableauConge()),
                        actionBlock(
                            button(addAction(c, RhModule.pageCreerConge))
                        )
                    )
            )
        );
    }

}
