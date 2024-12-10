package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class SiCondition extends Component {

    public String condition = null;
    public String type = "siVrai";
    public boolean childInLine = false;



    public SiCondition(Element element, String condition, String type, boolean childInLine, Component... componentList) {
        super(element, componentList);
        this.condition = condition;
        this.type = type;
        this.childInLine = childInLine;
    }


    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("{");
        String open = childInLine ? "" : "(";
        if (type.equals("siVrai")) {
            flow.addToUi(condition).append(" && " + open);
        } else if (type.equals("siFaux")) {
            flow.addToUi("!").append(condition).append(" && (");
        } else {
            flow.addToUi("util." + type + "(").append(condition).append(") && (");
        }
        return childInLine;
    }

    public void addCloseTag(ViewFlow flow, int level) {

        if (childInLine) {
            flow.addToUi("}");
        } else {
            indent(flow, level);
            flow.addToUi(")}");
        }
    }

}
