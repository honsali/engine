package modules.rh.employe;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Employe;

public class FormulaireEmploye extends RhElementComposer {

        private boolean enModification;

        public FormulaireEmploye(boolean enModification) {
                super();
        isElement();
                this.enModification = enModification;
        }

        public Component rootComponent() {
                Employe e = entity(Employe.class);
                if (enModification) {
                        initUpdate(e, getByFieldAction(e, e.id_)).inInit();
                }

                return block(//
                                block(//
                                                simplePanel(//
                                                                block(//
                                                                                form(e, //
                                                                                                e.matricule, //
                                                                                                e.dateEntree, //
                                                                                                e.departement, //
                                                                                                e.fonction, //
                                                                                                e.description.wholeRow(), //
                                                                                                enModification ? hidden(e.id_) : null //
                                                                                )//
                                                                ).width("400px")//
                                                ).title("employe"), //
                                                simplePanel(//
                                                                block(//
                                                                                form(e, //
                                                                                                e.nom, //
                                                                                                e.prenom, //
                                                                                                e.dateNaissance, //
                                                                                                e.sexe, //
                                                                                                e.situationFamiliale//
                                                                                )//
                                                                ).width("400px")//
                                                ).title("personnelle"), //
                                                simplePanel(//
                                                                block(//
                                                                                form(e, //
                                                                                                e.email, //
                                                                                                e.telephone, //
                                                                                                e.ville.aloneInRow(), //
                                                                                                e.adresse.wholeRow()//
                                                                                )//
                                                                ).width("400px")//
                                                ).title("contact"), //
                                                actionBlock(//
                                                                enModification ? element(updateAction(e).onSuccess(goToPage(e, pageConsulterEmploye))).byForm() : //
                                                                                element(createAction(e).onSuccess(goToPage(e, pageConsulterEmploye).byField(e.id_))).byForm(), //

                                                                enModification ? button(backToDetailAction(e, pageConsulterEmploye)) : button(backToListAction(e, pageFiltrerEmploye))//
                                                )//
                                ).width("1000px")//
                ).margin("40px").background("blanc");//
        }

}
//
