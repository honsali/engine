
package dev.cruding.engine.composant.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.Action.ActionType;
import dev.cruding.engine.action.chercher.ActionChercher;
import dev.cruding.engine.action.impl.ActionVide;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Filtre extends Composant {

    public int colNumber = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public Action fillFrom;
    public boolean inLine = true;

    public Filtre(Element element, Entite entite, Champ... fieldList) {
        super(element, entite, fieldList);
        new ActionChercher(entite, element);
        new ActionVide(ActionType.UCA, "appliquerFiltre", entite, element).inViewOnly();
        new ActionVide(ActionType.UCA, "initialiserFiltre", entite, element).inViewOnly();
    }

    public Filtre inLine() {
        this.inLine = true;
        return this;
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
        for (Champ c : fieldList) {
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

        StringBuilder fieldImportList = Util.processListeChamp(fieldList, Element.FORM);

        flow.addJsImport("{ FormulaireInline }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addViewScript(flow, element.page.uc, "..");
            }
            if (c.readOnlyIf != null) {
                flow.totalScript().L____("const " + c.readOnlyIf + ";");
                flow.totalScript().L("");
            }
            if (c.avecVariable) {
                flow.totalScript().L____("const " + c.lname + " = Form.useWatch('" + c.lname + "', form);");
                flow.totalScript().L("");
            }
        }
        for (Champ c : fieldList) {
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
        flow.totalScript().L________("form.resetChamps();");
        flow.totalScript().L________("appliquerFiltre();");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
        flow.totalScript().L____("const appliquerFiltre = () => {");
        flow.totalScript().L________("execute(Ctrl", element.page.uc, ".chercher", entite.uname, ", { form });");
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
        for (Champ c : fieldList) {
            indent(flow, level + 1).append("<" + c.ui(Element.FORM) + " nom=\"" + c.lname + "\"");
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
