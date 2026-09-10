package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class EtatEmploye extends ElementComposer {



    public Component rootComponent() {
        Employe e = entity(Employe.class);
        getByFieldAction(e, e.id_).inInit();
        return inlineBlock()
            .content(
                primaryPanel()
                    .title("employe").width("500px")
                    .content(
                        detail(e,
                            e.matricule,
                            e.dateEntree,
                            e.departement,
                            e.fonction,
                            e.description.wholeRow()
                        )
                    ),
                primaryPanel()
                    .title("personnelle").width("500px")
                    .content(
                        detail(e,
                            e.nom,
                            e.prenom,
                            e.dateNaissance,
                            e.sexe,
                            e.situationFamiliale
                        )
                    ),
                primaryPanel()
                    .title("contact").width("500px")
                    .content(
                        detail(e,
                            e.email,
                            e.telephone,
                            e.ville.aloneInRow(),
                            e.adresse.wholeRow()
                        )
                    )
            );
    }

}
//
