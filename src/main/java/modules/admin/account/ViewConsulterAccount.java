package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.admin.Account;
import modules.rh.RhProject;

public class ViewConsulterAccount extends ViewComposer<Account> {

        public Component rootComponent() {
                Account e = entity(Account.class);
                return section(//
                                block(//
                                                element(new EtatAccount()), //
                                                actionBlock(//
                                                                button(editAction(e, RhProject.pageModifierAccount)), //
                                                                element(new ActionReinitialiserMotDePasseAccount()), //
                                                                button(backToListAction(e, RhProject.pageListerAccount))//
                                                )//
                                ).width("600px").margin("20px").background("blanc")//
                );
        }
}
