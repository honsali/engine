package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class EtatEmploye extends ElementComposer {



        public Component rootComponent() {
                Employe e = entity(Employe.class);
                getByFieldAction(e, e.id_).inInit();
                return inlineBlock(//
                                primaryPanel(//
                                                detail(e, //
                                                                e.matricule, //
                                                                e.dateEntree, //
                                                                e.departement, //
                                                                e.fonction, //
                                                                e.description.wholeRow() //
                                                )).title("employe").width("500px"), //
                                primaryPanel(//
                                                detail(e, //
                                                                e.nom, //
                                                                e.prenom, //
                                                                e.dateNaissance, //
                                                                e.sexe, //
                                                                e.situationFamiliale//
                                                )).title("personnelle").width("500px"), //
                                primaryPanel(//
                                                detail(e, //
                                                                e.email, //
                                                                e.telephone, //
                                                                e.ville.aloneInRow(), //
                                                                e.adresse.wholeRow()//
                                                )).title("contact").width("500px") //
                ).width("600px");//
        }

}
//
