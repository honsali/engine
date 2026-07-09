package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Employe;
import modules.rh.RhProject;

public class ViewFiltrerEmploye extends ViewComposer<Employe> {

        public Component rootComponent() {
                Employe e = entity(Employe.class);
                FiltreEmploye filtre = new FiltreEmploye();
                Component elementFiltre = element(filtre);

                return block(//
                                inColumn(//
                                                section( //
                                                                primaryPanel(//
                                                                                element(new TableauEmploye(filtre.action))//
                                                                ).title("listeEmploye")).actionBlock(button(addAction(e, RhProject.pageCreerEmploye))//
                                                ).margin("0"), //
                                                block(elementFiltre).margin("62px 0px")//
                                ).width(16, 8)//
                ).margin("20px 40px");
        }

}
