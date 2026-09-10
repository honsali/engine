package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Employe;
import modules.rh.RhModule;

public class ViewFiltrerEmploye extends ViewComposer<Employe> {

  public Component rootComponent() {
    Employe e = entity(Employe.class);
    FiltreEmploye filtre = new FiltreEmploye();
    Component elementFiltre = element(filtre);

    return //
    block().margin("20px 40px").content(
        inColumn().spans(16, 8)
            .column(
                section().margin("0")
                    .content(
                        primaryPanel().title("listeEmploye").content(
                            element(new TableauEmploye(filtre.action))//
                        ))
                    .actionBlock(//
                        button(addAction(e, RhModule.pageCreerEmploye))//
                    ))//
            .column(
                block().margin("62px 0px").content(elementFiltre)//
            )//
    );
  }

}
