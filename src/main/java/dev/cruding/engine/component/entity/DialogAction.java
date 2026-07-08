package dev.cruding.engine.component.entity;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Util;

public class DialogAction extends Component {

    public String width;
    public Form form;
    public Action action;
    public boolean initFormByEntity;

    public DialogAction(Element element, Entity entity, Field... fieldList) {
        super(element, entity);
        form = new Form(element, entity, fieldList);
        componentList = new Component[] {form};
    }

    public DialogAction(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public DialogAction action(Action action) {
        this.action = action;
        return this;
    }

    public DialogAction columnNumber(int columnNumber) {
        this.form.columnNumber = columnNumber;
        return this;
    }

    public DialogAction width(String width) {
        this.width = width;
        return this;
    }

    public DialogAction initFormByEntity() {
        this.initFormByEntity = true;
        return this;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ ActionUcDialogue }", "waxant");
    }

    public void addScript(ViewFlow flow) {
        if (initFormByEntity) {
            flow.totalScript().L____("const siInit = () => {");
            flow.totalScript().L________("form.setFieldsValue(", action.entity.lname, ");");
            flow.totalScript().L____("};");
            flow.totalScript().L("");
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        flow.addSelector(action.lnameWithEntity);
        String nameAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;

        indent(flow, level).append("<ActionUcDialogue");
        indent(flow, level + 1).append("nom={").append(nameAction).append("} //");
        if (action.icon != null) {
            indent(flow, level + 1).append("icone={<FontAwesomeIcon icon={").append(action.icon).append("} />}");
        }
        indent(flow, level + 1).append("action={").append(action.lnameWithEntity).append("}");
        indent(flow, level + 1).append("form={form}");
        indent(flow, level + 1).append("etat={etat").append(action.unameWithEntity).append("}");
        if (initFormByEntity) {
            indent(flow, level + 1).append("siInit={siInit}");
        }
        if (width != null) {
            indent(flow, level + 1).append("width=\"500px\"");
        }
        indent(flow, level).append(">");
        if (action.icon != null) {
            flow.useFontAwesome(action.icon);
        }
        flow.addSelector("etat" + action.unameWithEntity);
        String s = Util.getRelativePath(action.element.path, action.page.module.path, false);
        flow.addJsImport("{ Action" + action.page.module.unameLast + " }", s + "/Action" + action.page.module.unameLast);
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</ActionUcDialogue>");

    }
}
