package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.injection.ActionRequestInjection;

public class UpdateRequestInjection extends ActionRequestInjection {

    @Override
    public String name() {
        return entity().uname + "UpdateRequest";
    }

    @Override
    public String content() {
        return recordContent(requestFields(), true);
    }
}
