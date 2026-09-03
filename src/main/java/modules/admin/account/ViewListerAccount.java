package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.admin.Account;
import modules.admin.AdminModule;

public class ViewListerAccount extends ViewComposer<Account> {

    public Component rootComponent() {
        Account e = entity(Account.class);
        return section(element(new TableauAccount()))
                .actionBlock(button(addAction(e, AdminModule.pageCreerAccount)));
    }
}
