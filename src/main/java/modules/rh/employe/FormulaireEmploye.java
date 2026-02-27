package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class FormulaireEmploye extends ElementComposer {

        private boolean enModification;

        public FormulaireEmploye(boolean enModification) {
                super("FormulaireEmploye", "/element");
                this.enModification = enModification;
        }

        public Component rootComponent() {
                Employe e = (Employe) getEntity("Employe");
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
                                                                enModification ? element(updateAction(e).onSuccess(goToPage(e, "PageConsulterEmploye"))).byForm() : //
                                                                                element(createAction(e).onSuccess(goToPage(e, "PageConsulterEmploye").byField(e.id_))).byForm(), //

                                                                enModification ? button(backToDetailAction(e, "PageConsulterEmploye")) : button(backToListAction(e, "PageFiltrerEmploye"))//
                                                )//
                                ).width("1000px")//
                ).margin("40px").background("blanc");//
        }

}
//
