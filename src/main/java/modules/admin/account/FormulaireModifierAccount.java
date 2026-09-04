package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;
import modules.admin.AdminModule;

public class FormulaireModifierAccount extends ElementComposer {

    public Component rootComponent() {
        Account e = entity(Account.class);
        initUpdate(e, getByFieldAction(e, e.id_)).inInit();

        return block(
                detail(e, e.username),
                form(e,
                        e.role,
                        e.activated.yesValue("Oui").noValue("Non"),
                        hidden(e.id_)).columnNumber(1),
                actionBlock(
                        element(updateAction(e).onSuccess(goToPage(e, AdminModule.pageConsulterAccount))).byForm(),
                        button(backToDetailAction(e, AdminModule.pageConsulterAccount)))).width("600px").margin("20px").background("blanc");
    }
}
