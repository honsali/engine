
package dev.cruding.engine.component.entity;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Util;

public class Form extends Component {

    private static final int COL_NUMBER = 2;
    public int columnNumber = COL_NUMBER;
    public int width = 0;
    public Action fillFrom;
    public String type = "Formulaire";
    public boolean initFormByEntity;

    public Form(Element element, Entity entity, Field... fieldList) {
        super(element, entity, fieldList);
    }

    public Form inline() {
        this.type = "FormulaireInline";
        this.columnNumber = 1;
        return this;
    }

    public Form initFormByEntity() {
        this.initFormByEntity = true;
        return this;
    }

    public Form horizontal() {
        this.type = "FormulaireHorizontal";
        this.columnNumber = 1;
        return this;
    }

    public void addImport(ViewFlow flow) {
        if (!element.byForm) {
            if (type.equals("FormulaireInline")) {
                flow.useInLineForm();
            } else if (type.equals("FormulaireHorizontal")) {
                flow.useHorizontalForm();
            } else {
                flow.useForm();
            }
        }
        for (Field c : fieldList) {
            if (c.onChange != null) {
                if (c.onChange.length() > 0) {
                    flow.addJsImport("{ useState }", "react");
                    flow.addState(c.onChange, c.defaultValue);
                }
                flow.addJsImport("{ useOnChange }", "waxant");

            }
            if (c.watched) {
                flow.addJsImport("{ Form }", "antd");
            }
            if (c.emptyIf != null) {
                flow.addJsImport("{ ChampVide }", "waxant");
            }
        }

        StringBuilder fieldImportList = Util.processFieldList(fieldList, Element.FORM);

        flow.addJsImport("{ " + type + " }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addViewScript(flow, element.page.uc, "..");
            }
            if (c.watched) {
                flow.totalScript().L____("const " + c.lname + " = Form.useWatch('" + c.lname + "', form);");
            }
            if (c.onChange != null) {
                flow.totalScript().L____("useOnChange('" + c.lname + "', form, (valeur) => {");
                if (c.onChange.length() > 0) {
                    flow.totalScript().L________("set").append(StringUtils.capitalize(c.onChange)).append("(valeur);");
                } else if (c.onChangeAction != null) {
                    flow.totalScript().L________("if (valeur) {");
                    flow.totalScript().L____________(c.onChangeAction.lnameWithEntity, "(valeur);");
                    flow.totalScript().L________("}");
                    flow.addSelector(c.onChangeAction.lnameWithEntity);
                }
                flow.totalScript().L____("});");
                flow.totalScript().L("");
            }
        }
        if (initFormByEntity) {
            flow.totalScript().L____("useEffect(() => {");
            flow.totalScript().L________("form.setFieldsValue(", entity.lname, ");");
            flow.totalScript().L____("}, [", entity.lname, "]);");
            flow.totalScript().L("");
            flow.useEffect();
            flow.addSelector(entity.lname);
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<").append(type).append(" form={form}");
        if (columnNumber != COL_NUMBER) {
            flow.totalUi().__(" nombreColonne={" + columnNumber + "}");
        }
        flow.totalUi().__(">");
        for (Field c : fieldList) {
            indent(flow, level + 1).append("<" + c.ui(Element.FORM) + " nom=\"" + c.lname + "\"");
            if (c.label != null) {
                flow.totalUi().__(" libelle=\"" + c.label + "\"");
            }
            if (c.width > 0) {
                flow.totalUi().__(" largeur={" + c.width + "}");
            }
            flow.totalUi().__(c.getExtension());
            if (c.required) {
                flow.totalUi().__(" requis=\"true\"");
            } else if (c.requiredIf != null) {
                flow.totalUi().__(" requis={" + c.requiredIf + "}");
            }
            if (c.readOnlyIf != null) {
                flow.totalUi().__(" disabled={" + c.readOnlyIf + "}");
                if (c.readOnlyIf.indexOf("util.") > -1) {
                    flow.addJsImport("{ util }", "waxant");
                }
            } else if (c.readOnly) {
                flow.totalUi().__(" disabled");
            }
            if (c.hiddenIf != null) {
                flow.totalUi().__(" invisible={" + c.hiddenIf + "}");
            }
            if (c.emptyIf != null) {
                flow.totalUi().__(" invisible={" + c.emptyIf + "}");
            }
            if (c.aloneInRow) {
                flow.totalUi().__(" seulDansLaLigne");
            }
            if (c.wholeRow) {
                flow.totalUi().__(" surTouteLaLigne");
            }
            flow.totalUi().__(" />");
            if (c.emptyIf != null && columnNumber == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.emptyIf + "} />");
            }
            Context.getInstance().addLabelForField(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</").append(type).append(">");
    }

    public Form columnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        return this;
    }

    public Form width(int width) {
        this.width = width;
        return this;
    }



}
