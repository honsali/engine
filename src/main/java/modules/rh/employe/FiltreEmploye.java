package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class FiltreEmploye extends ElementComposer {

        public Action action;


    public Component rootComponent() {
        Employe e = entity(Employe.class);
        action = filter(e).filterOnLoad();

        return filterPanel(e, true)
            .title("filtreEmploye")
            .content(
                extendedPanel()
                    .title("employe").open()
                    .content(
                        form(e,
                            e.matricule.required(false).aloneInRow(),
                            dateRangeBegin(e.dateEntree),
                            dateRangeEnd(e.dateEntree),
                            e.departement,
                            e.fonction
                        )
                    ),
                extendedPanel()
                    .title("personnelle")
                    .content(
                        form(e,
                            e.nom.required(false),
                            e.prenom.required(false),
                            dateRangeBegin(e.dateNaissance),
                            dateRangeEnd(e.dateNaissance),
                            e.sexe,
                            e.situationFamiliale
                        )
                    ),
                extendedPanel()
                    .title("contact")
                    .content(
                        form(e,
                            e.email,
                            e.telephone,
                            e.ville,
                            e.adresse
                        )
                    ),
                separator(),
                actionBlock(
                    button(applyFilter(e, action)),
                    button(initFilter(e, action))
                )
            );
    }

}
