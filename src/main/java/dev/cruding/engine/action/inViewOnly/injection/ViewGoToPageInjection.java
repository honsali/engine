package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewGoToPageInjection extends ViewActionInjection {

    private Page targetPage;

    public ViewGoToPageInjection(Page targetPage) {
        this.targetPage = targetPage;
    };

    public boolean addViewScript(ViewFlow f) {
        f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(element().path, inElement()));
        f.totalScript().L____("const goToPage", targetPage.name, " = (", entite().lname, ") => {");
        f.totalScript().L________("goToPage(", targetPage.name + ", ", entite().lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();
        return true;
    }

    public void addFlowScript(ViewFlow f, int level) {
        f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(element().path, inElement()));
        f.totalScript().__(Composant.indent[level]).__("goToPage(", targetPage.name, ", resultat);");
        f.useGoToPage();
    }

    public void addI18n(Flow f) {
        String key = "";
        String label = "";
        String uAction = LabelMapper.getInstance().nomAction(lnameSansEntite());
        if (entite() == null) {
            key = lnameSansEntite() + page().entiteUname;
            label = uAction;
        } else if (!entite().lname.equals(page().entiteLname)) {
            String entiteLabel = entite().setting.libelle;
            key = lnameSansEntite() + entite().uname;
            label = uAction + " " + entiteLabel;
        } else {
            String entiteLabel = entite().setting.libelle;
            key = lnameSansEntite() + entite().uname;
            label = uAction + " " + entiteLabel;
        }

        f.L____("[Action", page().module.unameLast, ".", "Uc", uc(), ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", label, "',");

    }
}
