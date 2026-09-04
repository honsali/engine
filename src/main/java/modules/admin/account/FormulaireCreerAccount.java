package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;
import modules.admin.AdminModule;

public class FormulaireCreerAccount extends ElementComposer {

    public Component rootComponent() {
        Account e = entity(Account.class);
        Field password = e.Text("password").maxLength("256").minLength("8").required();

        return block(
                form(e,
                        e.username,
                        password,
                        e.role).columnNumber(1),
                actionBlock(
                        element(createAction(e).onSuccess(goToPage(e, AdminModule.pageConsulterAccount).byField(e.id_))).byForm(),
                        button(backToListAction(e, AdminModule.pageListerAccount)))).width("600px").margin("20px").background("blanc");
    }
}
