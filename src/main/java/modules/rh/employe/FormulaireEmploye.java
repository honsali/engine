package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;
import modules.rh.RhModule;

public class FormulaireEmploye extends ElementComposer {

        private boolean enModification;

        public FormulaireEmploye(boolean enModification) {
                this.enModification = enModification;
        }

    public Component rootComponent() {
        Employe e = entity(Employe.class);
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_)).inInit();
        }

        return block()
            .margin("40px").background("blanc")
            .content(
                block()
                    .width("1000px")
                    .content(
                        simplePanel()
                            .title("employe")
                            .content(
                                block()
                                    .width("400px")
                                    .content(
                                        form(e,
                                            e.matricule,
                                            e.dateEntree,
                                            e.departement,
                                            e.fonction,
                                            e.description.wholeRow(),
                                            enModification ? hidden(e.id_) : null
                                        )
                                    )
                            ),
                        simplePanel()
                            .title("personnelle")
                            .content(
                                block()
                                    .width("400px")
                                    .content(
                                        form(e,
                                            e.nom.required(),
                                            e.prenom.required(),
                                            e.dateNaissance.required(),
                                            e.sexe,
                                            e.situationFamiliale
                                        )
                                    )
                            ),
                        simplePanel()
                            .title("contact")
                            .content(
                                block()
                                    .width("400px")
                                    .content(
                                        form(e,
                                            e.email,
                                            e.telephone,
                                            e.ville.aloneInRow(),
                                            e.adresse.wholeRow()
                                        )
                                    )
                            ),
                        actionBlock(
                            enModification
                                ? element(updateAction(e).onSuccess(goToPage(e, RhModule.pageConsulterEmploye))).byForm()
                                : element(createAction(e).onSuccess(goToPage(e, RhModule.pageConsulterEmploye).byField(e.id_))).byForm(),
                            enModification
                                ? button(backToDetailAction(e, RhModule.pageConsulterEmploye))
                                : button(backToListAction(e, RhModule.pageFiltrerEmploye))
                        )
                    )
            );
    }

}
//
