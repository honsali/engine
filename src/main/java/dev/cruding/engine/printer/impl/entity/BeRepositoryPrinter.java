package dev.cruding.engine.printer.impl.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeRepositoryPrinter extends Printer {

    public BeRepositoryPrinter(Context context) {
        super(context);
    }

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        List<Action> actionList = context().actionEntity(entity);
        LinkedHashSet<String> repositoryExtensions = new LinkedHashSet<>();

        for (Action action : actionList) {
            String extension = action.repoActionInjection.repositoryExtension();
            if (!extension.isEmpty()) {
                repositoryExtensions.add(extension);
            }
        }

        f.addJavaImport("org.springframework.data.jpa.repository.JpaRepository");

        for (Action action : actionList) {
            action.repoActionInjection.addRepositoryImport(f);
        }

        f.__("package app.domain.", entity.javaPackage(), ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("public interface ", entity.uname, "Repository extends JpaRepository<", entity.uname, ", ", entity.id_.jtype, ">");
        for (String extension : repositoryExtensions) {
            f.__(", ", extension);
        }
        f.__(" {");

        List<Action> declarationActions = actionList.stream()
                .sorted(Comparator.comparingInt(action -> action.repoActionInjection.repositoryDeclarationOrder()))
                .toList();
        HashSet<String> actionNames = new HashSet<>();
        for (Action action : declarationActions) {
            if (actionNames.add(action.lnameWithoutEntity)) {
                action.repoActionInjection.addRepositoryDeclaration(f);
            }
        }

        f.L("}");

        printFile(f.toString(), getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + entity.uname + "Repository.java");
    }
}
