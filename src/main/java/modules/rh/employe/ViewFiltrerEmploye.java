package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Employe;

public class ViewFiltrerEmploye extends ElementComposer {
        public ViewFiltrerEmploye() {
                super("ViewFiltrerEmploye", "/");
        }

        public Component rootComponent() {
                Employe e = (Employe) getEntity("Employe");
                FiltreEmploye filtre = new FiltreEmploye();
                Component elementFiltre = element(filtre);

                return block(//
                                inColumn(//
                                                section( //
                                                                primaryPanel(//
                                                                                element(new TableauEmploye(filtre.action))//
                                                                ).title("listeEmploye")).actionBlock(button(addAction(e, "PageCreerEmploye"))//
                                                ).margin("0"), //
                                                block(elementFiltre).margin("62px 0px")//
                                ).width(16, 8)//
                ).margin("20px 40px");
        }

}
