package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.admin.Account;

public class ViewModifierAccount extends ViewComposer<Account> {

    public Component rootComponent() {
        return section(element(new FormulaireModifierAccount()));
    }
}
