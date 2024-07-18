package dev.cruding.engine.component.bouton;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class BoutonGoToPage extends Bouton {

    private Page targetPage;


    public BoutonGoToPage(Page page, Entity entity, String targetPageName, String lname) {
        super(page, entity);
        this.targetPage = Context.getInstance().getPage(targetPageName);
        this.lname = lname;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ ActionUcNormale }", "waxant");
        flow.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path, inElement));
    }

    public void addScript(ViewFlow f) {
        f.addSpecificSelector(entity.lname, "./Mdl" + page.uc);

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<ActionUcNormale  page={").append(targetPage.name).append("}");
        if (entity != null) {
            flow.addToUi(" modele={").append(entity.lname).append("}");
        }
        flow.addToUi(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public void addInlineTag(Flow f) {
        f.__("<ActionUcNormale ");
        if (lname != null) {
            f.__(" nom=\"").__(lname).__("\"");
        }
        f.__("page={").append(targetPage.name).append("}");
        if (entity != null) {
            f.__(" modele={").__(entity.lname).__("}");
        }
        f.__(" />");
    }



}
