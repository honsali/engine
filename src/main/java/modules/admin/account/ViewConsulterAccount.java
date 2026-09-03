package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.admin.Account;
import modules.admin.AdminModule;

public class ViewConsulterAccount extends ViewComposer<Account> {

    public Component rootComponent() {
        Account e = entity(Account.class);
        return section(
                block(
                        element(new EtatAccount()),
                        actionBlock(
                                button(editAction(e, AdminModule.pageModifierAccount)),
                                button(backToListAction(e, AdminModule.pageListerAccount))))
                        .width("600px")
                        .margin("20px")
                        .background("blanc"));
    }
}
