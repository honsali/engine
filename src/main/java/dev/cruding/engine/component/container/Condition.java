package dev.cruding.engine.component.container;

import java.util.Arrays;
import java.util.Objects;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Condition extends Component {

    public String condition = null;
    public String type = "siVrai";
    public boolean childInLine = false;
    public String nameVariable;

    public Condition(String nameVariable, Element element, String condition, String type, boolean childInLine, Component... componentList) {
        super(element, componentList);
        if (componentList == null || Arrays.stream(componentList).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Condition cannot contain null components: branch positions must be preserved.");
        }
        int expectedBranches = "siVraiFaux".equals(type) ? 2 : 1;
        if (componentList.length != expectedBranches) {
            throw new IllegalArgumentException("Condition '" + type + "' requires exactly "
                    + expectedBranches + " branch(es), but got " + componentList.length + ".");
        }
        this.condition = condition;
        this.type = type;
        this.childInLine = childInLine;
        this.nameVariable = nameVariable;
    }

    public Condition(Element element, String condition, String type, boolean childInLine, Component... componentList) {
        this(null, element, condition, type, childInLine, componentList);
    }

    @Override
    protected void addBody(ViewFlow flow, int level) {
        if (!isElement) {
            if (componentList != null) {
                if (componentList.length == 1) {
                    addOpenTag(flow, level);
                    componentList[0].addContent(this, flow, childInLine, level + 1);
                    addCloseTag(flow, level);

                } else if (componentList.length == 2) {

                    addOpenTag(flow, level);
                    componentList[0].addContent(this, flow, childInLine, level + 1);
                    if (childInLine) {
                        flow.totalUi().__(" : ");
                    } else {
                        indent(flow, level);
                        flow.totalUi().L(")");
                        flow.totalUi().L(":");
                        flow.totalUi().L("(");
                    }
                    componentList[1].addContent(this, flow, childInLine, level + 1);
                    addCloseTag(flow, level);

                }
            }
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("{");
        String open = childInLine ? "" : "(";
        if (type.equals("siVrai")) {
            flow.totalUi().__(condition).append(" && " + open);
        } else if (type.equals("siFaux")) {
            flow.totalUi().__("!").append(condition).append(" && " + open);
        } else if (type.equals("siVraiFaux")) {
            flow.totalUi().__(condition).append(" ? " + open);
        } else {
            flow.totalUi().__("util." + type + "(").append(condition).append(") && " + open);
            flow.addJsImport("{ util }", "waxant");
        }
        if (nameVariable != null) {
            flow.addSelector(nameVariable);
        }
        return childInLine;
    }

    public void addCloseTag(ViewFlow flow, int level) {

        if (childInLine) {
            flow.totalUi().__("}");
        } else {
            indent(flow, level);
            flow.totalUi().__(")}");
        }
    }
}
