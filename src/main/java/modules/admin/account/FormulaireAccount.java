package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;
import modules.admin.AdminModule;

public class FormulaireAccount extends ElementComposer {

    private final boolean enModification;

    public FormulaireAccount(boolean enModification) {
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Account e = entity(Account.class);
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_)).inInit();
        }

        return block(
                form(e,
                        enModification ? e.username.readOnly() : e.username,
                        e.role,
                        e.activated.yesValue("Oui").noValue("Non"),
                        enModification ? hidden(e.id_) : null)
                        .columnNumber(1),
                actionBlock(
                        enModification
                                ? element(updateAction(e).onSuccess(goToPage(e, AdminModule.pageConsulterAccount))).byForm()
                                : element(createAction(e).onSuccess(
                                        goToPage(e, AdminModule.pageConsulterAccount).byField(e.id_))).byForm(),
                        enModification
                                ? button(backToDetailAction(e, AdminModule.pageConsulterAccount))
                                : button(backToListAction(e, AdminModule.pageListerAccount))))
                .width("600px")
                .margin("20px")
                .background("blanc");
    }
}
