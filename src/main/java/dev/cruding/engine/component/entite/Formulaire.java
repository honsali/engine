
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.gen.helper.Util;

public class Formulaire extends Component {

    public int colNumber = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public String lname;
    public String uname;
    public Action fillFrom;
    public boolean inView = true;

    public Formulaire(Element element, Entity entity, Field... fieldList) {
        super(element, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
    }

    public Formulaire nom(String uname) {
        this.uname = uname;
        return this;
    }

    public Formulaire inView(boolean inView) {
        this.inView = inView;
        return this;
    }

    public String getTitle() {
        String key = "formulaire." + lname;
        addLabel("Uc" + element.page.uc + "." + key, "Formulaire " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
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

        flow.addJsImport("{ Formulaire }", "waxant");
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
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Formulaire form={form}");
        if (colNumber != 1) {
            flow.addToUi(" nombreColonne={" + colNumber + "}");
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
        indent(flow, level).append("</Formulaire>");
    }

    public Formulaire colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Formulaire largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Formulaire enModification() {
        this.enModification = true;
        return this;
    }


}
