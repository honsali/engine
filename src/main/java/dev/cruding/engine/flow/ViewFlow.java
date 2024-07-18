package dev.cruding.engine.flow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.Page;

public class ViewFlow extends JsFlow {

    private HashSet<String> paramSet = new HashSet<>();
    private HashSet<String> propSet = new HashSet<>();
    private HashSet<String> selectorSet = new HashSet<>();
    private HashMap<String, String> stateSet = new HashMap<>();
    private HashMap<String, String> specificSelectorSet = new HashMap<>();
    private HashMap<String, String> specificSelectorImportSet = new HashMap<>();
    private StringBuilder scriptBuilder = new StringBuilder();
    private Flow totalScript = new Flow();
    private StringBuilder uiBuilder = new StringBuilder();
    private StringBuilder uiTotalBuilder = new StringBuilder();

    private boolean navigate;
    private boolean goToPage;
    private boolean goToModule;
    private boolean dispatch;
    private boolean pret;
    private String execute;
    private String form;
    private boolean effect;
    private boolean selector;
    private boolean inlineForm;
    private boolean addImportForm = true;

    StringBuilder sb = new StringBuilder();

    public void flush(Component component) {
        String sui = uiBuilder.toString();
        uiTotalBuilder.append(sui);
        uiBuilder = new StringBuilder();

        totalScript.append(scriptBuilder);
        scriptBuilder = new StringBuilder();
    }

    public StringBuilder addToScript(String s) {
        return scriptBuilder.append(s);
    }

    public StringBuilder addToUi(String s) {
        return uiBuilder.append(s);
    }

    public void flushScriptBloc() {
        __(totalScript.toString());
    }

    public void flushUiBloc() {
        __(uiTotalBuilder);
    }

    public void flushInitBloc(Page page) {
        if (hasNavigate()) {
            L____("const navigate = useNavigate();");
        }
        if (hasGoToPage()) {
            L____("const goToPage = useGoToPage();");
        }
        if (hasGoToModule()) {
            L____("const goToModule = useGoToModule();");
        }
        if (hasDispatch()) {
            L____("const dispatch = useAppDispatch();");
        }
        for (String selector : selectorSet) {
            L____("const " + selector + " = useAppSelector((state) => state.mdl", page.uc, "." + selector + ");");
        }
        for (String varName : specificSelectorSet.keySet()) {
            L____("const ", varName, " = useSelector(select", specificSelectorSet.get(varName), ");");
        }
        if (hasExecute()) {
            L____("const { ", execute, " } = useRequete(", execute.indexOf("rid") > 0 || execute.indexOf("success") > 0 ? "resultat" : "", ");");
        }
        if (hasForm()) {
            L____("const [", form, "] = Form.useForm();");
        }
        for (String state : stateSet.keySet()) {
            L____("const [", state, ", set", StringUtils.capitalize(state), "] = useState(", stateSet.get(state), ");");
        }

    }

    public void flushJsImportBloc() {
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
            addJsImport("{ APP_MODULES }", "commun/config/constants.config");
        }
        if (hasNavigate()) {
            addJsImport("{ useNavigate }", "react-router");
        }
        if (hasSelector()) {
            addJsImport("{ useAppSelector }", "waxant");
        }
        if (hasSpecificSelector()) {
            addJsImport("{ useSelector }", "react-redux");

            for (String varName : specificSelectorImportSet.keySet()) {
                addJsImport("{ select" + varName + " }", specificSelectorImportSet.get(varName));
            }
        }
        if (hasForm()) {
            addJsImport("{ Form }", "antd");

            if (addImportForm && inlineForm) {
                addJsImport("{ FormulaireInline }", "waxant");
            } else if (addImportForm) {
                addJsImport("{ Formulaire }", "waxant");
            }
        }
        if (hasState()) {
            addJsImport("{ useState }", "react");
        }
        if (hasExecute()) {
            addJsImport("{ useRequete }", "waxant");
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

    public void usePret() {
        this.pret = true;
        addState("pret", "false");
    }

    public String getPretCondition() {
        return this.pret ? "pret && " : "";
    }

    public String getPretArray() {
        return this.pret ? "pret" : "";
    }

    public boolean hasEffect() {
        return effect;
    }

    public void useEffect() {
        this.effect = true;
    }

    public void useSelector() {
        this.selector = true;
    }

    public boolean hasSelector() {
        return selectorSet.size() > 0;
    }

    public boolean hasSpecificSelector() {
        return selector || specificSelectorSet.size() > 0;
    }

    public void addSelector(String selector) {
        selectorSet.add(selector);
    }

    public void addSpecificSelector(String varName, String importPath) {
        specificSelectorSet.put(varName, StringUtils.capitalize(varName));
        specificSelectorImportSet.put(StringUtils.capitalize(varName), importPath);
    }

    public void addSpecificSelector(String varName, String selectorName, String importPath) {
        specificSelectorSet.put(varName, selectorName);
        specificSelectorImportSet.put(selectorName, importPath);
    }

    public boolean hasState() {
        return stateSet.size() > 0;
    }

    public boolean hasExecute() {
        return execute != null;
    }

    public void useExecute() {
        this.execute = "execute";
    }

    public void useExecute(String type) {
        this.execute = type;
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
        propSet.add(prop);
    }

    public boolean hasProps() {
        return propSet.size() > 0;
    }

    public String joinProps() {
        if (hasProps()) {
            return "{ " + joinProps() + " }";
        }
        return "";
    }

    public Flow totalScript() {
        return totalScript;
    }
}
