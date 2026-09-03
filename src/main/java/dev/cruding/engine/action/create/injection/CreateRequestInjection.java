package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.injection.ActionRequestInjection;

public class CreateRequestInjection extends ActionRequestInjection {

    @Override
    public String name() {
        return entity().uname + "CreateRequest";
    }

    @Override
    public String content() {
        return recordContent(requestFields());
    }
}
