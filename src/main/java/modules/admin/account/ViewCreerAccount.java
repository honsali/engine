package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.admin.Account;

public class ViewCreerAccount extends ViewComposer<Account> {

    public Component rootComponent() {
        return section(//
                element(new FormulaireAccount(false))//
        );
    }
}
