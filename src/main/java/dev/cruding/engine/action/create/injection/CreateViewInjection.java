package dev.cruding.engine.action.create.injection;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.injection.ActionViewInjection;

public class CreateViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow flow) {
        List<Action> ifSuccessUiActionList = null;

        if (hasSuccess()) {
            ArrayList<Action> ifSuccessActionList = onSuccess();
            ifSuccessUiActionList = ifSuccessActionList;// .stream().filter(sr -> sr.inViewOnly).toList();
        }

        flow.addSelector(lnameWithEntity());

        flow.addSelector("etat" + unameWithEntity());
        flow.totalScript().__("\n");

        flow.addParam("form");
        if (byProp() != null) {
            flow.addParam(byProp());
        }
        if (element().byProp != null) {
            String p = StringUtils.substringBefore(element().byProp, ":");
            if (byId()) {
                flow.addParam("id" + StringUtils.capitalize(p) + ": " + p + ".id");
            } else {
                flow.addParam(p);
            }
        }

        if (flow.hasParams()) {
            flow.totalScript().L____("const ", lnameWithoutEntity(), " = () => {");
            flow.totalScript().L________(lnameWithEntity(), "({ ", flow.joinParams(), " });");
            flow.totalScript().L____("};");
        }


        if (ifSuccessUiActionList != null && ifSuccessUiActionList.size() > 0) {
            String args = null;
            flow.addSelector("id" + entity().uname);
            args = "id" + entity().uname;
            flow.useEffect();
            flow.totalScript().L("");
            flow.totalScript().L____("useEffect(() => {");
            flow.totalScript().L________("if (etat", unameWithEntity(), ".succes) {");
            flow.totalScript().L____________("resetEtat", unameWithEntity(), "();");
            for (Action ifSuccessAction : ifSuccessUiActionList) {
                if (ifSuccessAction.viewActionInjection != null) {
                    ifSuccessAction.viewActionInjection.addFlowScript(flow, 2, args);
                } else {
                    flow.totalScript().L____________(ifSuccessAction.lnameWithEntity);
                }
            }
            flow.totalScript().L________("}");
            flow.totalScript().L____("}, [etat", unameWithEntity(), ".succes]);");

            flow.addSelector("resetEtat" + unameWithEntity());
        }
        return true;
    }

    public void addI18n(Flow f) {
        if (!noUi()) {

            Entity e = entity() == null ? Context.getInstance().getEntity(page().entityUname) : entity();
            if (e != null) {

                String nameAction = LabelMapper.getInstance().nameAction(lnameWithoutEntity(), e);
                String titleConfirmation = LabelMapper.getInstance().titleConfirmation(lnameWithoutEntity(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lnameWithoutEntity(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lnameWithoutEntity(), e);

                String key = "Action" + page().module.unameLast + "." + "Uc" + uc() + "." + actionKey();
                f.L____("[", key, "]", ": '", nameAction, "',");

                if (confirm()) {
                    f.L____("[titreConfirmation(", key, ")]: '", titleConfirmation, "',");
                    f.L____("[enteteConfirmation(", key, ")]: '", enteteConfirmation, "',");
                    f.L____("[messageSuccess(", key, ")]: '", messageSuccess, "',");
                }
            }
        }
    }
}
