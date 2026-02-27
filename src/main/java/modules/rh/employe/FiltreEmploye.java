package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class FiltreEmploye extends ElementComposer {

        public Action action;

        public FiltreEmploye() {
                super("FiltreEmploye", "/element");
        }

        public Component rootComponent() {
                Employe e = (Employe) getEntity("Employe");
                action = filter(e).filterOnLoad();

                return filterPanel(e, true, //
                                extendedPanel(//
                                                form(e, //
                                                                e.matricule.aloneInRow(), //
                                                                dateRangeBegin(e.dateEntree), //
                                                                dateRangeEnd(e.dateEntree), //
                                                                e.departement, //
                                                                e.fonction //
                                                )).open().title("employe"), //
                                extendedPanel(//
                                                form(e, //
                                                                e.nom, //
                                                                e.prenom, //
                                                                dateRangeBegin(e.dateNaissance), //
                                                                dateRangeEnd(e.dateNaissance), //
                                                                e.sexe, //
                                                                e.situationFamiliale//
                                                )).title("personnelle"), //
                                extendedPanel(//
                                                form(e, //
                                                                e.email, //
                                                                e.telephone, //
                                                                e.ville, //
                                                                e.adresse)//
                                ).title("contact"), //
                                separator(), //
                                actionBlock(//

                                                button(applyFilter(e, action)), //
                                                button(initFilter(e, action))//
                                )//
                ).title("filtreEmploye");
        }

}
