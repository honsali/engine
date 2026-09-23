package dev.cruding.engine.flow;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.helper.HookBinding;
import dev.cruding.engine.gen.Context;

public class ViewFlow extends JsFlow {

    private HashSet<String> paramSet = new HashSet<>();
    private HashSet<String> propSet = new HashSet<>();
    private HashMap<String, String> propTypeMap = new HashMap<>();
    private HashSet<String> selectorSet = new HashSet<>();
    private Map<Action, HookBinding> initHooks = new TreeMap<>(Action.ORDER_BY_NAME);
    private HashMap<String, String> stateSet = new HashMap<>();
    private Flow totalScript = new Flow();
    private Flow totalUi = new Flow();

    private boolean navigate;
    private boolean goToPage;
    private boolean goToModule;
    private boolean dispatch;
    private boolean ready;
    private String execute;
    private String form;
    private String formType;
    private boolean effect;
    private boolean inlineForm;
    private boolean horizontalForm;
    private boolean eventBus;
    private boolean addImportForm = true;
    private boolean afterInit = false;
    private boolean onChange = false;
    private String faIcon = null;

    private Element element;

    public ViewFlow() {}

    public ViewFlow(Element element) {
        this.element = element;
    }

    public static ViewFlow create(Element element) {
        ViewFlow flow = new ViewFlow(element);
        if (element.byForm) {
            flow.addJsImport("{ FormInstance }", "antd");
            flow.addProp("form", "FormInstance");
        }
        flow.addProp(element.byProp);
        List<Action> actions = Context.getInstance().actionElement(element);
        for (int i = 0; i < actions.size(); i++) {
            boolean newLine = actions.get(i).viewActionInjection.addViewScript(flow);
            if (newLine && i < actions.size() - 1) {
                flow.totalScript().L();
            }
        }
        element.addContent(flow);
        return flow;
    }

    public void flushScriptBlock() {
        totalScript.clean();
        String ts = totalScript.toString();
        if (ts != null && ts.length() > 0) {
            if (afterInit && !ts.startsWith("\n\n")) {
                L("");
            }
            __(ts);
        }
        afterInit = false;
    }

    public void flushUiBlock() {
        totalUi.clean();
        String ts = totalUi.toString();
        if (ts != null && ts.length() > 0) {

            __(ts);
        }

    }

    public void flushInitBlock() {
        Flow initFlow = new Flow();
        if (hasNavigate()) {
            initFlow.L____("const navigate = useNavigate();");
        }
        if (hasGoToPage()) {
            initFlow.L____("const goToPage = useGoToPage();");
        }
        if (hasGoToModule()) {
            initFlow.L____("const goToModule = useGoToModule();");
        }
        if (hasDispatch()) {
            initFlow.L____("const dispatch = useAppDispatch();");
        }
        if (hasEventBus()) {
            initFlow.L____("const { emit } = useEventBus();");
        }

        if (hasForm()) {
            initFlow.L____("const [", form, "] = Form.useForm", formType == null ? "" : "<" + formType + ">", "();");
        }
        for (String state : stateSet.keySet()) {
            initFlow.L____("const [", state, ", set", StringUtils.capitalize(state), "] = useState(", stateSet.get(state), ");");
        }

        for (HookBinding hook : hookBindings().values()) {
            String arguments = String.join(", ", hook.parameters);
            if (hook.members.isEmpty()) {
                initFlow.L____(hook.name(), "(", arguments, ");");
            } else {
                initFlow.L____("const { ", String.join(", ", hook.members), " } = ", hook.name(), "(", arguments, ");");
            }
        }
        for (String selector : readSelectors()) {
            initFlow.L____("const ", selector, " = useSelector(select", StringUtils.capitalize(selector), ");");
        }

        initFlow.clean();
        String ts = initFlow.toString();
        if (ts != null && ts.length() > 0) {
            __(ts);
            afterInit = true;
        }
    }

    public void flushViewImportBlock() {
        if (hasOnChange()) {
            addJsImport("{ useOnChange }", "waxant");
        }
        if (hasEffect()) {
            addJsImport("{ useEffect }", "react");
        }
        if (hasDispatch()) {
            addJsImport("{ useAppDispatch }", "waxant");
        }
        if (hasGoToPage()) {
            addJsImport("{ useGoToPage }", "waxant");
        }
        if (hasGoToModule()) {
            addJsImport("{ useGoToModule }", "waxant");
            addJsImport("{ APP_MODULES }", "commun");
        }
        if (hasNavigate()) {
            addJsImport("{ useNavigate }", "react-router");
        }
        if (!hookBindings().isEmpty()) {
            String relativePath = (element.path != null && element.path.endsWith("element") ? ".." : ".") + "/use" + element.page.uc;
            for (HookBinding hook : hookBindings().values()) {
                addJsImport("{ " + hook.name() + " }", relativePath);
            }
        }
        for (String selector : readSelectors()) {
            String relativePath = (element.path != null && element.path.endsWith("element") ? ".." : ".") + "/Mdl" + element.page.uc;
            addJsImport("{ useSelector }", "react-redux");
            addJsImport("{ select" + StringUtils.capitalize(selector) + " }", relativePath);
        }
        if (hasForm()) {
            addJsImport("{ Form }", "antd");

            if (addImportForm && inlineForm) {
                addJsImport("{ FormulaireInline }", "waxant");
            } else if (addImportForm && horizontalForm) {
                addJsImport("{ FormulaireHorizontal }", "waxant");
            } else if (addImportForm) {
                addJsImport("{ Formulaire }", "waxant");
            }
        }
        if (hasState()) {
            addJsImport("{ useState }", "react");
        }


        if (hasEventBus()) {
            addJsImport("{ useEventBus }", "waxant");
            addJsImport("{ APP_EVENT }", "commun");
        }

        if (faIcon != null) {
            addJsImport("{ " + faIcon + " }", "@fortawesome/free-solid-svg-icons");
            addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");

        }
        super.flushJsImportBlock();
    }

    public boolean hasNavigate() {
        return navigate;
    }

    public boolean hasGoToPage() {
        return goToPage;
    }

    public boolean hasGoToModule() {
        return goToModule;
    }

    public void useNavigate() {
        this.navigate = true;
    }

    public void useGoToPage() {
        this.goToPage = true;
    }

    public void useGoToModule() {
        this.goToModule = true;
    }

    public boolean hasDispatch() {
        return dispatch;
    }

    public void useDispatch() {
        this.dispatch = true;
    }

    public boolean hasOnChange() {
        return onChange;
    }

    public void useOnChange() {
        this.onChange = true;
    }

    public void useReady() {
        this.ready = true;
        addState("pret", "false");
    }

    public boolean hasReady() {
        return this.ready;
    }


    public boolean hasEventBus() {
        return eventBus;
    }

    public void useEventBus() {
        this.eventBus = true;
    }

    public boolean hasEffect() {
        return effect;
    }

    public void useEffect() {
        this.effect = true;
    }


    public void useFontAwesome(String faIcon) {
        this.faIcon = faIcon;
    }

    public boolean hasSelector() {
        return selectorSet.size() > 0;
    }

    public void addSelector(String selector) {
        selectorSet.add(selector);
    }

    public void useInitAction(Action action, String... parameters) {
        HookBinding hook = initHooks.computeIfAbsent(action, HookBinding::new);
        hook.initialize = true;
        hook.parameters.addAll(List.of(parameters));
        if (action.byProp != null) {
            hook.parameters.add(action.byProp);
        }
        if (action.waitUntilReady) {
            hook.parameters.add("pret");
        }
    }

    public Map<Action, HookBinding> hookBindings() {
        Map<Action, HookBinding> hooks = new TreeMap<>(Action.ORDER_BY_NAME);
        initHooks.forEach((action, binding) -> hooks.computeIfAbsent(action, HookBinding::new).merge(binding));
        if (element == null) {
            return hooks;
        }
        var actions = Context.getInstance().actionPage(element.page).stream()
                .filter(action -> !action.inViewOnly && !action.isEmpty).toList();
        Set<String> remaining = new TreeSet<>(selectorSet);
        for (Action action : actions) {
            for (String member : List.of(action.lnameWithEntity, "resetEtat" + action.unameWithEntity)) {
                if (remaining.remove(member)) {
                    hooks.computeIfAbsent(action, HookBinding::new).members.add(member);
                }
            }
            String status = "etat" + action.unameWithEntity;
            if (hooks.containsKey(action) && remaining.remove(status)) {
                hooks.get(action).members.add(status);
            }
        }
        // Bind data to an action already used here; reading elsewhere must not
        // call another component's initialization hook.
        for (String member : remaining) {
            hooks.values().stream()
                    .filter(hook -> hook.action.mdlActionInjection.hookStateMembers().contains(member))
                    .sorted(Comparator.comparing(hook -> !hook.initialize))
                    .findFirst().ifPresent(hook -> hook.members.add(member));
        }
        return hooks;
    }

    public Set<String> readSelectors() {
        Set<String> selectors = new TreeSet<>(selectorSet);
        hookBindings().values().forEach(hook -> selectors.removeAll(hook.members));
        return selectors;
    }

    public boolean hasState() {
        return stateSet.size() > 0;
    }

    public boolean hasExecute() {
        return execute != null;
    }


    public boolean hasForm() {
        return form != null;
    }

    public void useForm() {
        this.form = "form";
    }

    public void useForm(Entity entity) {
        useForm();
        typeForm(entity);
    }

    public void useInLineForm() {
        this.inlineForm = true;
        this.form = "form";
    }

    public void useInLineForm(Entity entity) {
        useInLineForm();
        typeForm(entity);
    }

    public void useHorizontalForm() {
        this.horizontalForm = true;
        this.form = "form";
    }

    public void useHorizontalForm(Entity entity) {
        useHorizontalForm();
        typeForm(entity);
    }

    public void useForm(boolean addImportForm) {
        this.addImportForm = addImportForm;
        this.form = "form";
    }

    public void useForm(boolean addImportForm, Entity entity) {
        useForm(addImportForm);
        typeForm(entity);
    }

    public void useForm(String formName) {
        this.form = formName;
    }

    private void typeForm(Entity entity) {
        if (entity != null) {
            this.formType = "I" + entity.uname;
            addJsImport("{ " + formType + " }", "modele/" + entity.path + "/Domaine" + entity.uname);
        }
    }

    public void addParam(String param) {
        paramSet.add(param);
    }

    public void addState(String state, String type) {
        stateSet.put(state, type);
    }

    public boolean hasParams() {
        return paramSet.size() > 0;
    }

    public String joinParams() {
        return paramSet.stream().collect(Collectors.joining(", "));
    }

    public void addProp(String prop) {
        if (prop != null) {
            propSet.add(StringUtils.substringBefore(prop, ":"));
        }
    }

    public void addProp(String prop, String type) {
        addProp(prop);
        if (prop != null && type != null) {
            propTypeMap.put(StringUtils.substringBefore(prop, ":"), type);
        }
    }

    public boolean hasProps() {
        return propSet.size() > 0;
    }

    public String joinProps() {
        if (propSet.isEmpty()) {
            return "";
        }
        return "{ " + propSet.stream().sorted().collect(Collectors.joining(", ")) + " }";
    }

    public String joinPropsTypeAnnotation() {
        if (propSet.isEmpty() || !propTypeMap.keySet().containsAll(propSet)) {
            return "";
        }
        return ": { " + propSet.stream()
                .sorted()
                .map(prop -> prop + ": " + propTypeMap.get(prop))
                .collect(Collectors.joining("; ")) + " }";
    }

    public Flow totalScript() {
        return totalScript;
    }

    public Flow totalUi() {
        return totalUi;
    }


}
