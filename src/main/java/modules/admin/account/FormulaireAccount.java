package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;
import modules.rh.RhProject;

public class FormulaireAccount extends ElementComposer {

    private final boolean enModification;

    public FormulaireAccount(boolean enModification) {
        this.enModification = enModification;
    }

    public Component rootComponent() {
        Account e = entity(Account.class);
        Field password = custom("password").label("Mot de passe initial").required();
        if (enModification) {
            initUpdate(e, getByFieldAction(e, e.id_));
        }

        return block(//
                form(e, //
                        enModification ? e.username.readOnly() : e.username, //
                        enModification ? null : password, //
                        e.role, //
                        enModification ? e.activated.yesValue("Oui").noValue("Non").defaultValue("true") : null, //
                        enModification ? hidden(e.id_) : null //
                ).columnNumber(1), //
                actionBlock(//
                        enModification ? element(updateAction(e).onSuccess(goToPage(e, RhProject.pageConsulterAccount))).byForm() : //
                                element(createAction(e).onSuccess(goToPage(e, RhProject.pageConsulterAccount).byField(e.id_))).byForm(), //
                        enModification ? button(backToDetailAction(e, RhProject.pageConsulterAccount)) : button(backToListAction(e, RhProject.pageListerAccount))//
                )//
        ).width("600px").margin("20px").background("blanc");
    }
}
