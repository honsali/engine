package dev.cruding.engine.printer.impl.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeBusinessPrinter extends Printer {

    public BeBusinessPrinter(Context context) {
        super(context);
    }

    private static final int MAX_LOOKUP_LINE_LENGTH = 100;

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        List<Action> actionList = context().actionEntity(entity);
        LinkedHashMap<String, Field> repositoryDependencies = repositoryDependencies(actionList);

        for (Action action : actionList) {
            action.businessActionInjection.addBusinessImport(f);
        }
        if (!repositoryDependencies.isEmpty()) {
            f.addJavaImport("app.core.exception.ResourceNotFoundException");
            f.addJavaImport("app.core.reference.Reference");
        }
        for (Field relation : repositoryDependencies.values()) {
            Entity referenced = context().getEntity(relation.jtype);
            f.addJavaImport("app.domain." + referenced.javaPackage() + "." + referenced.uname);
            f.addJavaImport("app.domain." + referenced.javaPackage() + "." + referenced.uname + "Repository");
        }
        f.addJavaImport("org.springframework.stereotype.Service");
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");

        f.__("package app.domain.", entity.javaPackage(), ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@Service");
        f.L("public class ", entity.uname, "Service {");
        f.L("");
        f.L____("private final ", entity.uname, "Repository ", entity.lname, "Repository;");
        for (Field relation : repositoryDependencies.values()) {
            Entity referenced = context().getEntity(relation.jtype);
            f.L____("private final ", referenced.uname, "Repository ", referenced.lname, "Repository;");
        }
        f.L("");
        List<String> constructorParameters = new ArrayList<>();
        constructorParameters.add(entity.uname + "Repository " + entity.lname + "Repository");
        for (Field relation : repositoryDependencies.values()) {
            Entity referenced = context().getEntity(relation.jtype);
            constructorParameters.add(referenced.uname + "Repository " + referenced.lname + "Repository");
        }
        f.addMethodDeclaration(4, "public " + entity.uname + "Service(", constructorParameters);
        f.L________("this.", entity.lname, "Repository = ", entity.lname, "Repository;");
        for (Field relation : repositoryDependencies.values()) {
            Entity referenced = context().getEntity(relation.jtype);
            f.L________("this.", referenced.lname, "Repository = ", referenced.lname, "Repository;");
        }
        f.L____("}");

        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameWithoutEntity)) {
                action.businessActionInjection.addBusinessDeclaration(f);
                actionName.add(action.lnameWithoutEntity);
            }
        }

        for (Field relation : repositoryDependencies.values()) {
            addRelationResolver(f, relation);
        }
        if (!repositoryDependencies.isEmpty()) {
            f.L("");
        }
        f.L("}");

        printFile(f.toString(), getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + entity.uname + "Service.java");
    }

    private LinkedHashMap<String, Field> repositoryDependencies(List<Action> actionList) {
        LinkedHashMap<String, Field> dependencies = new LinkedHashMap<>();
        for (Action action : actionList) {
            for (Field field : action.businessActionInjection.businessRelationFields()) {
                dependencies.putIfAbsent(field.jtype, field);
            }
        }
        return dependencies;
    }

    private void addRelationResolver(JavaFlow f, Field relation) {
        Entity referenced = context().getEntity(relation.jtype);
        f.L("");
        if (relation.isFather) {
            f.L____("private ", referenced.uname, " recuperer", referenced.uname, "(Long id", relation.uname, ") {");
            f.L________("return ", referenced.lname, "Repository.findById(id", relation.uname, ")");
            f.L________________(".orElseThrow(() -> new ResourceNotFoundException(\"", referenced.uname, "\", id", relation.uname, "));");
            f.L____("}");
            return;
        }
        f.L____("private ", referenced.uname, " recuperer", referenced.uname, "(Reference reference) {");
        f.L________("if (reference == null) {");
        f.L____________("return null;");
        f.L________("}");
        f.L________("Long id = reference.id();");

        String lookup = "return " + referenced.lname + "Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(\"" + referenced.uname + "\", id));";
        if (lookup.length() <= MAX_LOOKUP_LINE_LENGTH) {
            f.L________(lookup);
        } else {
            f.L________("return ", referenced.lname, "Repository");
            f.L________________(".findById(id)");
            f.L________________(".orElseThrow(() -> new ResourceNotFoundException(\"", referenced.uname, "\", id));");
        }
        f.L____("}");
    }
}
