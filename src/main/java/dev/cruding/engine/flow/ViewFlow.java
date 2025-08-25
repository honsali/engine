package dev.cruding.engine.flow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;

public class ViewFlow extends JsFlow {

    private HashSet<String> paramSet = new HashSet<>();
    private HashSet<String> propSet = new HashSet<>();
    private HashSet<String> selectorSet = new HashSet<>();
    private HashMap<String, String> stateSet = new HashMap<>();
    private Flow totalScript = new Flow();
    private Flow totalUi = new Flow();

    private boolean navigate;
    private boolean goToPage;
    private boolean goToModule;
    private boolean dispatch;
    private boolean pret;
    private String execute;
    private String form;
    private boolean effect;
    private boolean inlineForm;
    private boolean horizontalForm;
    private boolean eventBus;
    private boolean addImportForm = true;
    private boolean afterInit = false;
    private boolean onChange = false;
    private String faIcone = null;

    private Element element;

    public ViewFlow() {}

    public ViewFlow(Element element) {
        this.element = element;
    }

    public void flushScriptBloc() {
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

    public void flushUiBloc() {
        totalUi.clean();
        String ts = totalUi.toString();
        if (ts != null && ts.length() > 0) {

            __(ts);
        }

    }

    public void flushInitBloc() {
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

        if (selectorSet.size() > 0) {
            initFlow.L____("const { ");
            initFlow.__(selectorSet.stream().sorted().collect(Collectors.joining(", ")));
            initFlow.__(" } = use", element.page.uc, "();");
        }
        if (hasForm()) {
            initFlow.L____("const [", form, "] = Form.useForm();");
        }
        for (String state : stateSet.keySet()) {
            initFlow.L____("const [", state, ", set", StringUtils.capitalize(state), "] = useState(", stateSet.get(state), ");");
        }

        initFlow.clean();
        String ts = initFlow.toString();
        if (ts != null && ts.length() > 0) {
            __(ts);
            afterInit = true;
        }
    }

    public void flushViewImportBloc() {
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
        if (hasSelector()) {
            String relativePath = (element.path != null && element.path.endsWith("element") ? ".." : ".") + "/use" + element.page.uc;
            addJsImport("use" + element.page.uc, relativePath);
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

        if (faIcone != null) {
            addJsImport("{ " + faIcone + " }", "@fortawesome/free-solid-svg-icons");
            addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");

        }
        super.flushJsImportBloc();
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

    public void usePret() {
        this.pret = true;
        addState("pret", "false");
    }

    public boolean hasPret() {
        return this.pret;
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


    public void useFontAwesome(String faIcone) {
        this.faIcone = faIcone;
    }

    public boolean hasSelector() {
        return selectorSet.size() > 0;
    }

    public void addSelector(String selector) {
        selectorSet.add(selector);
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

    public void useInLineForm() {
        this.inlineForm = true;
        this.form = "form";
    }

    public void useHorizontalForm() {
        this.horizontalForm = true;
        this.form = "form";
    }

    public void useForm(boolean addImportForm) {
        this.addImportForm = addImportForm;
        this.form = "form";
    }

    public void useForm(String formName) {
        this.form = formName;
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

    public boolean hasProps() {
        return propSet.size() > 0;
    }

    public String joinProps() {
        if (propSet.isEmpty()) {
            return "";
        }
        return "{ " + propSet.stream().collect(Collectors.joining(", ")) + " }";
    }

    public Flow totalScript() {
        return totalScript;
    }

    public Flow totalUi() {
        return totalUi;
    }


}
