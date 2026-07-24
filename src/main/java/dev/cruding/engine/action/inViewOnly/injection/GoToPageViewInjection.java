package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.TsLiteral;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.injection.ActionViewInjection;

public class GoToPageViewInjection extends ActionViewInjection {

    private Page targetPage;

    public GoToPageViewInjection(Page targetPage) {
        this.targetPage = targetPage;
    };

    public boolean addViewScript(ViewFlow f) {
        if (!onSuccessAction()) {
            f.addJsImport("{ " + targetPage.name + " }", targetPage.module.pageList(element().path, inElement()));
            f.totalScript().L____("const goTo", targetPage.name, " = (", typedEntityParameter(f), ") => {");
            f.totalScript().L________("goToPage(", targetPage.name + ", ", entity().lname, ");");
            f.totalScript().L____("};");
            f.useGoToPage();
            return true;
        }
        return false;
    }

    public void addFlowScript(ViewFlow f, int level, String args) {
        f.addJsImport("{ " + targetPage.name + " }", targetPage.module.pageList(element().path, false));
        f.totalScript().__(Component.indent[level]).__("goToPage(", targetPage.name);
        if (args != null) {
            f.totalScript().__(", { ", args, " }");
        }
        f.totalScript().__(");");
        f.useGoToPage();
    }

    public void addI18n(Flow f) {
        String key = "";
        String label = "";
        String uAction = LabelMapper.getInstance().nameAction(lnameWithoutEntity());
        if (entity() == null) {
            key = lnameWithoutEntity() + page().entityUname;
            label = uAction;
        } else if (!entity().lname.equals(page().entityLname)) {
            String entityLabel = entity().setting.label;
            key = lnameWithoutEntity() + entity().uname;
            label = uAction + " " + entityLabel;
        } else {
            String entityLabel = entity().setting.label;
            key = lnameWithoutEntity() + entity().uname;
            label = uAction + " " + entityLabel;
        }

        f.L____("[Action", page().module.unameLast, ".", "Uc", uc(), ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": ", TsLiteral.string(label), ",");

    }
}
