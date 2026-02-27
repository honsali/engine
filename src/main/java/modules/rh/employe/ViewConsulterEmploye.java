package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.rh.Conge;
import model.rh.Employe;
import modules.rh.conge.TableauConge;

public class ViewConsulterEmploye extends ElementComposer {
        public ViewConsulterEmploye() {
                super("ViewConsulterEmploye", "/");
        }

        public Component rootComponent() {

                Employe e = (Employe) getEntity("Employe");
                Conge c = (Conge) getEntity("Conge");
                return section( //
                                tabMenu(//
                                                block(//
                                                                element(new EtatEmploye()), //
                                                                actionBlock(//
                                                                                button(editAction(e, "PageModifierEmploye")), //
                                                                                button(backToListAction(e, "PageFiltrerEmploye")), //
                                                                                button(deleteAction(e).onSuccess(goToPage(e, "PageFiltrerEmploye")))//
                                                                )//
                                                ).margin("20px").name("employe"), //
                                                block(//
                                                                element(new TableauConge()), //
                                                                actionBlock(//
                                                                                button(addAction(c, "PageCreerConge")) //
                                                                )//
                                                ).margin("20px").name("conge")//
                                )//
                );
        }

}
