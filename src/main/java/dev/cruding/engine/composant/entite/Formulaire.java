
package dev.cruding.engine.composant.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.helper.Util;

public class Formulaire extends Composant {

    public int nombreColonne = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public Action fillFrom;
    public String type = "Formulaire";
    public boolean initFormByEntite;

    public Formulaire(Element element, Entite entite, Champ... listeChamp) {
        super(element, entite, listeChamp);
    }

    public Formulaire inline() {
        this.type = "FormulaireInline";
        this.nombreColonne = 1;
        return this;
    }

    public Formulaire initFormByEntite() {
        this.initFormByEntite = true;
        return this;
    }

    public Formulaire horizontal() {
        this.type = "FormulaireHorizontal";
        this.nombreColonne = 1;
        return this;
    }

    public void addImport(ViewFlow flow) {
        if (!element.parForm) {
            if (type.equals("FormulaireInline")) {
                flow.useInLineForm();
            } else if (type.equals("FormulaireHorizontal")) {
                flow.useHorizontalForm();
            } else {
                flow.useForm();
            }
        }
        for (Champ c : listeChamp) {
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

        StringBuilder fieldImportList = Util.processListeChamp(listeChamp, Element.FORM);

        flow.addJsImport("{ " + type + " }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addViewScript(flow, element.page.uc, "..");
            }

            if (c.avecVariable) {
                flow.totalScript().L____("const " + c.lname + " = Form.useWatch('" + c.lname + "', form);");
                flow.totalScript().L("");
            }
            if (c.siChange != null) {
                flow.totalScript().L____("useOnChange('" + c.lname + "', form, (valeur) => {");
                if (c.siChange.length() > 0) {
                    flow.totalScript().L________("set").append(StringUtils.capitalize(c.siChange)).append("(valeur);");
                } else if (c.siChangeAction != null) {
                    flow.totalScript().L________("if (valeur) {");
                    flow.totalScript().L____________(c.siChangeAction.lnameAvecEntite, "(valeur);");
                    flow.totalScript().L________("}");
                    flow.addSelector(c.siChangeAction.lnameAvecEntite);
                }
                flow.totalScript().L____("});");
                flow.totalScript().L("");
            }
        }
        if (initFormByEntite) {
            flow.totalScript().L____("useEffect(() => {");
            flow.totalScript().L________("form.setFieldsValue(", entite.lname, ");");
            flow.totalScript().L____("}, [", entite.lname, "]);");
            flow.totalScript().L("");
            flow.useEffect();
            flow.addSelector(entite.lname);
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<").append(type).append(" form={form}");
        if (nombreColonne != 1) {
            flow.totalUi().__(" nombreColonne={" + nombreColonne + "}");
        }
        flow.totalUi().__(">");
        for (Champ c : listeChamp) {
            indent(flow, level + 1).append("<" + c.ui(Element.FORM) + " nom=\"" + c.lname + "\"");
            if (c.libelle != null) {
                flow.totalUi().__(" libelle=\"" + c.libelle + "\"");
            }
            if (c.largeur > 0) {
                flow.totalUi().__(" width={" + c.largeur + "}");
            }
            flow.totalUi().__(c.getExtension());
            if (c.requis) {
                flow.totalUi().__(" requis=\"true\"");
            }
            if (c.lectureSeuleSi != null) {
                flow.totalUi().__(" disabled={" + c.lectureSeuleSi + "}");
                if (c.lectureSeuleSi.indexOf("util.") > -1) {
                    flow.addJsImport("{ util }", "waxant");
                }
            } else if (c.lectureSeule) {
                flow.totalUi().__(" disabled");
            }
            if (c.invisibleSi != null) {
                flow.totalUi().__(" invisible={" + c.invisibleSi + "}");
            }
            if (c.videSi != null) {
                flow.totalUi().__(" invisible={" + c.videSi + "}");
            }
            if (c.seulDansLaLigne) {
                flow.totalUi().__(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                flow.totalUi().__(" surTouteLaLigne");
            }
            flow.totalUi().__(" />");
            if (c.videSi != null && nombreColonne == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.videSi + "} />");
            }
            Contexte.getInstance().addLabelPourChamp(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</").append(type).append(">");
    }

    public Formulaire nombreColonne(int nombreColonne) {
        this.nombreColonne = nombreColonne;
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
