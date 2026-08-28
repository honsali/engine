package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;

public class ActionReinitialiserMotDePasseAccount extends ElementComposer {
    public ActionReinitialiserMotDePasseAccount() {

    }

    public Component rootComponent() {
        Account e = entity(Account.class);
        return dialogAction(e, //
                e.username.readOnly(), //
                e.passwordHash, hidden(e.id_)//
        )//
                .action(updateAction(e).lcoreName("reinitialiserMotDePasseAccount").onSuccess(localAction(e, "apresSucces").inViewOnly()));
    }
}
