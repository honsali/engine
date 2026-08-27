package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;

public class EtatAccount extends ElementComposer {

    public Component rootComponent() {
        Account e = entity(Account.class);
        getByFieldAction(e, e.id_).inInit();
        return detail(e, //
                e.username, //
                e.role, //
                e.activated//
        ).columnNumber(1);
    }
}
