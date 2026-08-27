package modules.admin.account;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;
import model.admin.Account;
import modules.rh.RhProject;

public class TableauAccount extends ElementComposer {

    public Component rootComponent() {
        Account e = entity(Account.class);
        return block(//
                table(e, //
                        e.username, //
                        e.role, //
                        e.activated//
                ).fillWith(listAll(e)).onRowClick(goToPage(e, RhProject.pageConsulterAccount)) //
        );
    }
}
