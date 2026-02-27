package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewConsulterConge extends ElementComposer {
    public ViewConsulterConge() {
        super("ViewConsulterConge", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new EtatConge()) //
        );
    }

}
