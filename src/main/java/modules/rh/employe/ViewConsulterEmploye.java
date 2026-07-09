package modules.rh.employe;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;
import model.rh.Conge;
import model.rh.Employe;
import modules.rh.conge.TableauConge;

public class ViewConsulterEmploye extends RhElementComposer {
        public Component rootComponent() {

                Employe e = entity(Employe.class);
                Conge c = entity(Conge.class);
                return section( //
                                tabMenu(//
                                                block(//
                                                                element(new EtatEmploye()), //
                                                                actionBlock(//
                                                                                button(editAction(e, pageModifierEmploye)), //
                                                                                button(backToListAction(e, pageFiltrerEmploye)), //
                                                                                button(deleteAction(e).onSuccess(goToPage(e, pageFiltrerEmploye)))//
                                                                )//
                                                ).margin("20px").name("employe"), //
                                                block(//
                                                                element(new TableauConge()), //
                                                                actionBlock(//
                                                                                button(addAction(c, pageCreerConge)) //
                                                                )//
                                                ).margin("20px").name("conge")//
                                )//
                );
        }

}
