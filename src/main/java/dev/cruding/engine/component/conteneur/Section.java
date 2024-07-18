package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.bloc.BlocActionDroit;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class Section extends Component {

    public String pageRetour = null;
    public boolean plaqueEtat = false;
    public BlocActionDroit blocAction = null;

    public Section(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Section}", "waxant");
        if (plaqueEtat) {
            flow.addJsImport("{PlaqueEtat}", "waxant");
        }
    }

    public void addScript(ViewFlow flow) {
        if (pageRetour != null) {
            Page pr = Context.getInstance().getPage(pageRetour);
            flow.addToScript("\n");
            flow.addToScript(indent[0]).append("const lister").append(entity.uname).append(" = () => {");
            flow.addToScript(indent[1]).append("navigate('/").append(pr.route).append("');");
            flow.addToScript(indent[0]).append("};");
            flow.useNavigate();
        }
        // if (plaqueEtat) {
        //   flow.addMdlResultAttribute(entity.lname, "I" + entity.uname);
        //}
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Section");
        String indt = (blocAction != null) ? indent[level + 1] : "";

        if (pageRetour != null) {
            flow.addToUi(indt).append(" actionRetour={lister").append(entity.uname).append("}");
        }
        if (plaqueEtat) {
            flow.addToUi(" blocAction={<PlaqueEtat entity={").append(entity.lname).append("} />}");
        }
        if (blocAction != null) {
            flow.addToUi(indent[level + 1]).append(" blocAction={");
            blocAction.addContent(this, flow, level + 2);
            flow.addToUi(indent[level + 1]).append("}");
            flow.addToUi(indent[level]).append(">");
        } else {
            flow.addToUi(">");
        }
    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</Section>");
    }

    public Section plaqueEtat() {
        this.plaqueEtat = true;
        return this;
    }

    public Section blocAction(Component... componentList) {
        this.blocAction = new BlocActionDroit(page, componentList);
        return this;
    }

    public Section actionRetour(String pageRetour) {
        this.pageRetour = pageRetour;
        return this;
    }

    public Section label(String label) {
        return this;
    }

    public Section init(Entity e, Action action) {

        Context.getInstance().addAction(action, page, e);

        return this;
    }
}
