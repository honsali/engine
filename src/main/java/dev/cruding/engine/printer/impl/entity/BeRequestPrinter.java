package dev.cruding.engine.printer.impl.entity;

import java.util.HashSet;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeRequestPrinter extends Printer {

    public void print(Entity entity) {
        HashSet<String> requestNames = new HashSet<>();

        for (Action action : Context.getInstance().actionEntity(entity)) {
            String requestName = action.requestActionInjection.name();
            if (!requestName.isEmpty() && requestNames.add(requestName)) {
                printFile(action.requestActionInjection.content(), getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + requestName + ".java");
            }
        }
    }
}
