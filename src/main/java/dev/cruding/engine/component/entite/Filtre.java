
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.component.bouton.Actionnable.ActionType;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.gen.helper.Util;

public class Filtre extends Component {

    public int colNumber = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public String lname;
    public String uname;
    public Action fillFrom;
    public boolean inView = true;
    public boolean inLine = true;

    public Filtre(Element element, Entity entity, Field... fieldList) {
        super(element, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        Actionnable actionnable = new Actionnable(ActionType.NOUI, "chercher", entity, element);
        actionnable.action(new ActionChercher());
        new Actionnable(ActionType.UCA, "appliquerFiltre", entity, element).action(new ActionVide()).inViewOnly();
        new Actionnable(ActionType.UCA, "initialiserFiltre", entity, element).action(new ActionVide()).inViewOnly();
    }

    public Filtre nom(String uname) {
        this.uname = uname;
        return this;
    }

    public Filtre inView(boolean inView) {
        this.inView = inView;
        return this;
    }

    public Filtre inLine() {
        this.inLine = true;
        return this;
    }

    public String getTitle() {
        String key = "filtre." + lname;
        addLabel("Uc" + element.page.uc + "." + key, "Filtre " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        if (inLine) {
            flow.useInLineForm();
        } else {
            flow.useForm();
        }
        flow.useExecute();
        flow.useEffect();
        flow.addJsImport("{ BlocAction }", "waxant");
        flow.addJsImport("{ ActionUcInitialiserFiltre }", "waxant");
        flow.addJsImport("{ ActionUcAppliquerFiltre }", "waxant");
        flow.addJsImport("Ctrl" + element.page.uc, "../Ctrl" + element.page.uc);
        for (Field c : fieldList) {
            if (c.siChange != null) {
                if (c.siChange.length() > 0) {
                    flow.addJsImport("{ useState }", "react");
                    flow.addState(c.siChange, "false");
                }
                flow.addJsImport("{ useOnChange }", "waxant");
            }
            if (c.avecVariable) {
                flow.addJsImport("{ Form }", "antd");
            }
            if (c.videSi != null) {
                flow.addJsImport("{ ChampVide }", "waxant");
            }
        }

        StringBuilder fieldImportList = Util.processListeField(fieldList, ElementPrinter.FORM);

        flow.addJsImport("{ FormulaireInline }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addViewScript(flow, element.page.uc, "..");
        }
        for (Field c : fieldList) {
            if (c.readOnlyIf != null) {
                flow.totalScript().L____("const " + c.readOnlyIf + ";");
                flow.totalScript().L("");
            }
            if (c.avecVariable) {
                flow.totalScript().L____("const " + c.lname + " = Form.useWatch('" + c.lname + "', form);");
                flow.totalScript().L("");
            }
        }
        for (Field c : fieldList) {
            if (c.siChange != null) {
                flow.totalScript().L____("useOnChange('" + c.lname + "', form, (valeur) => {");
                if (c.siChange.length() > 0) {
                    flow.totalScript().L________("set").append(StringUtils.capitalize(c.siChange)).append("(valeur);");
                }
                flow.totalScript().L____("});");
                flow.totalScript().L("");
            }
        }
        flow.totalScript().L____("useEffect(() => {");
        flow.totalScript().L________("initialiserFiltre();");
        flow.totalScript().L____("}, []);");
        flow.totalScript().L("");
        flow.totalScript().L____("const initialiserFiltre = () => {");
        flow.totalScript().L________("form.resetFields();");
        flow.totalScript().L________("appliquerFiltre();");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
        flow.totalScript().L____("const appliquerFiltre = () => {");
        flow.totalScript().L________("execute(Ctrl", element.page.uc, ".chercher", entity.uname, ", { form });");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
        if (inLine) {
            flow.totalScript().L____("const getActionBloc = () => {");
            flow.totalScript().L________("return (");
            flow.totalScript().L____________("<BlocAction>");
            flow.totalScript().L________________("<ActionUcInitialiserFiltre action={initialiserFiltre} />");
            flow.totalScript().L________________("<ActionUcAppliquerFiltre action={appliquerFiltre} />");
            flow.totalScript().L____________("</BlocAction>");
            flow.totalScript().L________(");");
            flow.totalScript().L____("};");
            flow.totalScript().L("");
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (inLine) {
            indent(flow, level).append("<FormulaireInline form={form} actionBloc={getActionBloc()}");
        } else {
            indent(flow, level).append("<Formulaire form={form}");
            if (colNumber != 1) {
                flow.addToUi(" nombreColonne={" + colNumber + "}");
            }
        }
        flow.addToUi(">");
        for (Field c : fieldList) {
            indent(flow, level + 1).append("<" + c.ui(ElementPrinter.FORM) + " nom=\"" + c.lname + "\"");
            if (c.libelle != null) {
                flow.addToUi(" libelle=\"" + c.libelle + "\"");
            }
            if (c.width > 0) {
                flow.addToUi(" width={" + c.width + "}");
            }
            flow.addToUi(c.getExtension());
            if (c.required) {
                flow.addToUi(" requis=\"true\"");
            }
            if (c.readOnlyIf != null) {
                flow.addToUi(" disabled={" + c.readOnlyIf + "}");
            } else if (c.readOnly) {
                flow.addToUi(" disabled");
            }
            if (c.invisibleSi != null) {
                flow.addToUi(" invisible={" + c.invisibleSi + "}");
            }
            if (c.videSi != null) {
                flow.addToUi(" invisible={" + c.videSi + "}");
            }
            if (c.seulDansLaLigne) {
                flow.addToUi(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                flow.addToUi(" surTouteLaLigne");
            }
            flow.addToUi(" />");
            if (c.videSi != null && colNumber == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.videSi + "} />");
            }
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        if (inLine) {
            indent(flow, level).append("</FormulaireInline>");
        } else {
            flow.totalScript().L____________("<BlocAction>");
            flow.totalScript().L________________("<ActionUcInitialiserFiltre action={initialiserFiltre} />");
            flow.totalScript().L________________("<ActionUcAppliquerFiltre action={appliquerFiltre} />");
            flow.totalScript().L____________("</BlocAction>");
            indent(flow, level).append("</Formulaire>");
        }
    }

    public Filtre colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Filtre largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Filtre enModification() {
        this.enModification = true;
        return this;
    }


}
