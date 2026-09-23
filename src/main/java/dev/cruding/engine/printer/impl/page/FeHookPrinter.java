package dev.cruding.engine.printer.impl.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.EnginePaths;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.flow.helper.HookBinding;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeHookPrinter extends Printer {

    public void print(Page page) {
        Map<Action, HookBinding> hooks = new TreeMap<>(Action.ORDER_BY_NAME);
        for (Action action : Context.getInstance().actionPage(page)) {
            if (!action.inViewOnly && !action.isEmpty) {
                hooks.put(action, new HookBinding(action));
            }
        }
        // Components contribute the data/status they actually consume.
        for (var element : page.elementList) {
            if (!element.fake) {
                ViewFlow.create(element).hookBindings().forEach((action, binding) ->
                        hooks.computeIfAbsent(action, HookBinding::new).merge(binding));
            }
        }

        MdlFlow imports = new MdlFlow();
        MdlFlow body = new MdlFlow();
        List<String> routeParameters = routeParameters(page);
        for (HookBinding hook : hooks.values()) {
            printHook(page, hook, routeParameters, imports, body);
        }
        imports.flushMdlImportBlock();
        imports.__(body.toString());
        if (hooks.isEmpty()) {
            imports.L("export {};");
        }
        printFile(imports.toString(), EnginePaths.outputRoot + "/fe/src/" + page.path + "/use" + page.uc + ".ts");
    }

    private void printHook(Page page, HookBinding hook, List<String> routeParameters, MdlFlow imports, MdlFlow f) {
        routeParameters = routeParameters.stream().filter(parameter -> !hook.parameters.contains(parameter)).toList();
        Action action = hook.action;
        String name = action.lnameWithEntity;
        String reset = "resetEtat" + action.unameWithEntity;
        boolean callable = !hook.initialize || hook.members.contains(name);
        boolean resettable = hook.members.contains(reset);
        List<String> stateMembers = hook.members.stream().filter(member -> !member.equals(name) && !member.equals(reset)).toList();
        String routeArguments = routeParameters.isEmpty() ? "" : ", " + String.join(", ", routeParameters);
        String dependencies = "dispatch" + routeArguments;

        imports.addMdlImport("{ useAppDispatch }", "waxant");
        imports.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);
        imports.addMdlImport("{ Req" + page.uc + " }", "./Mdl" + page.uc);
        if (!routeParameters.isEmpty()) {
            imports.addMdlImport("{ useParams }", "react-router");
        }
        if (hook.initialize) {
            imports.addMdlImport("{ useEffect }", "react");
        }
        if (callable || resettable) {
            imports.addMdlImport("{ useCallback }", "react");
        }
        if (callable && !action.mdlActionInjection.usesDefaultHookAction()) {
            action.mdlActionInjection.addHookImport(imports);
        }
        if (resettable) {
            imports.addMdlImport("{ Mdl" + page.uc + " }", "./Mdl" + page.uc);
        }
        for (String member : stateMembers) {
            imports.addMdlImport("{ useSelector }", "react-redux");
            imports.addMdlImport("{ select" + StringUtils.capitalize(member) + " }", "./Mdl" + page.uc);
        }

        f.L("");
        String parameters = hook.parameters.stream().map(parameter -> parameter + ": " +
                (parameter.equals("pret") && action.waitUntilReady ? "boolean" : "Req" + page.uc + "['" + parameter + "']"))
                .collect(Collectors.joining(", "));
        f.L("export const ", hook.name(), " = (", parameters, ") => {");
        f.L____("const dispatch = useAppDispatch();");
        if (!routeParameters.isEmpty()) {
            f.L____("const { ", String.join(", ", routeParameters), " } = useParams();");
        }
        for (String member : stateMembers) {
            f.L____("const ", member, " = useSelector(select", StringUtils.capitalize(member), ");");
        }

        if (hook.initialize) {
            List<String> requestParameters = new ArrayList<>(routeParameters);
            hook.parameters.stream().filter(parameter -> !parameter.equals("pret") || !action.waitUntilReady)
                    .filter(parameter -> !requestParameters.contains(parameter)).forEach(requestParameters::add);
            String effectDependencies = dependencies + (hook.parameters.isEmpty() ? "" : ", " + String.join(", ", hook.parameters));
            f.L("");
            f.L____("useEffect(() => {");
            if (action.waitUntilReady) {
                f.L________("if (!pret) return;");
            }
            String request = requestParameters.isEmpty() ? "{}" : "{ " + String.join(", ", requestParameters) + " }";
            f.L________("dispatch(Ctrl", page.uc, ".", name, "(", request, " as Req", page.uc, "));");
            f.L____("}, [", effectDependencies, "]);");
        }
        if (callable) {
            f.L("");
            if (action.mdlActionInjection.usesDefaultHookAction()) {
                f.L____("const ", name, " = useCallback(");
                f.L________("(req?: Partial<Req", page.uc, ">) => dispatch(Ctrl", page.uc, ".", name, "({ ...req", routeArguments, " } as Req", page.uc, ")),");
                f.L________("[", dependencies, "],");
                f.L____(");");
            } else {
                action.mdlActionInjection.addHookAction(f, routeArguments, dependencies);
            }
        }
        if (resettable) {
            f.L("");
            f.L____("const ", reset, " = useCallback(");
            f.L________("() => dispatch(Mdl", page.uc, ".", reset, "()),");
            f.L________("[dispatch],");
            f.L____(");");
        }
        f.L("");
        f.L____("return {");
        if (callable) f.L________(name, ",");
        if (resettable) f.L________(reset, ",");
        for (String member : stateMembers) f.L________(member, ",");
        f.L____("};");
        f.L("};");
    }

    private List<String> routeParameters(Page page) {
        MdlFlow contract = new MdlFlow();
        Context.getInstance().actionPage(page).forEach(action -> action.mdlActionInjection.addMdlRequestAttribute(contract));
        List<String> parameters = page.route() == null
                ? (page.pathById ? List.of("id" + page.entityUname) : List.of())
                : Pattern.compile(":([A-Za-z][A-Za-z0-9_]*)").matcher(page.route()).results()
                        .map(match -> match.group(1)).distinct().toList();
        return parameters.stream().filter(contract::hasMdlRequestAttribute).toList();
    }
}
