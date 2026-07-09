package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Employe;

public class FiltreEmploye extends RhElementComposer {

        public Action action;

        public FiltreEmploye() {
            super();
            isElement();
        }

        public Component rootComponent() {
                Employe e = entity(Employe.class);
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
