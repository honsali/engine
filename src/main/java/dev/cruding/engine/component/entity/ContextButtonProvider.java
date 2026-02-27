package dev.cruding.engine.component.entity;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class ContextButtonProvider extends Component {

    private DialogAction dialogAction;
    private String typeButton;

    public ContextButtonProvider(Element element, String typeButton, DialogAction dialogAction) {
        super(element, dialogAction);
        this.dialogAction = dialogAction;
        this.typeButton = typeButton;

    }

    public ContextButtonProvider action(Action action) {
        dialogAction.action(action);
        return this;
    }

    public ContextButtonProvider columnNumber(int columnNumber) {
        dialogAction.columnNumber(columnNumber);
        return this;
    }

    public ContextButtonProvider width(String width) {
        dialogAction.width(width);
        return this;
    }

    public ContextButtonProvider initFormByEntity() {
        dialogAction.initFormByEntity();
        return this;
    }

    public ContextButtonProvider typeButton(String typeButton) {
        this.typeButton = typeButton;
        return this;
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<ContexteButtonProvider type=\"").append(typeButton).append("\">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</ContexteButtonProvider>");
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ ContexteButtonProvider }", "waxant");
    }
}
