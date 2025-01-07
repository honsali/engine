
package dev.cruding.engine.composant.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Formulaire extends Composant {

    public int colNumber = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public Action fillFrom;

    public Formulaire(Element element, Entite entite, Champ... fieldList) {
        super(element, entite, fieldList);
    }

    public void addImport(ViewFlow flow) {
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

        flow.addJsImport("{ Formulaire }", "waxant");
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
