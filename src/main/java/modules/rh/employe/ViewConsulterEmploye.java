package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Conge;
import model.rh.Employe;
import modules.rh.RhProject;
import modules.rh.conge.TableauConge;

public class ViewConsulterEmploye extends ViewComposer<Employe> {

        public Component rootComponent() {
                Employe e = entity(Employe.class);
                Conge c = entity(Conge.class);
                return section( //
                                tabMenu(//
                                                block(//
                                                                element(new EtatEmploye()), //
                                                                actionBlock(//
                                                                                button(editAction(e, RhProject.pageModifierEmploye)), //
                                                                                button(backToListAction(e, RhProject.pageFiltrerEmploye)), //
                                                                                button(deleteAction(e).onSuccess(goToPage(e, RhProject.pageFiltrerEmploye)))//
                                                                )//
                                                ).margin("20px").name("employe"), //
                                                block(//
                                                                element(new TableauConge()), //
                                                                actionBlock(//
                                                                                button(addAction(c, RhProject.pageCreerConge)) //
                                                                )//
                                                ).margin("20px").name("conge")//
                                )//
                );
        }

}
