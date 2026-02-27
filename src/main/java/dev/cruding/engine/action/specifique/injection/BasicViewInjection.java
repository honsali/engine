package dev.cruding.engine.action.specifique.injection;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.injection.ActionViewInjection;

public class BasicViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow flow) {
        List<Action> ifSuccessUiActionList = null;

        if (hasSuccess()) {
            ArrayList<Action> ifSuccessActionList = onSuccess();
            ifSuccessUiActionList = ifSuccessActionList;// .stream().filter(sr -> sr.inViewOnly).toList();
        }
        if (byEntity()) {
            flow.addProp(entity().lname);
        }


        if (!this.inViewOnly() && !this.noUi() && !this.ucDialogue()) {
            flow.addSelector(lnameWithEntity());

            if (hasSuccess() || confirm()) {
                flow.addSelector("etat" + unameWithEntity());
            }
            flow.totalScript().__("\n");

            if (this.byForm() || onSuccess().stream().anyMatch(a -> a.byForm)) {
                flow.addParam("form");
                // flow.useForm();
            }
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
            if (byEntity()) {
                if (byId()) {
                    flow.addParam("id" + entity().uname + ": " + entity().lname + ".id");
                } else {
                    flow.addParam(entity().lname);
                }
            }
            if (byField() != null) {
                for (Field c : byField()) {
                    flow.addParam(c.lname);
                }
            }
            if (flow.hasParams()) {
                flow.totalScript().L____("const ", lnameWithoutEntity(), " = () => {");
                flow.totalScript().L________(lnameWithEntity(), "({ ", flow.joinParams(), " });");
                flow.totalScript().L____("};");
            }

        } else if (this.inViewOnly()) {
            flow.totalScript().L____("const ", lnameWithEntity(), " = () => {");
            flow.totalScript().L____("};");
        }

        if (ifSuccessUiActionList != null && ifSuccessUiActionList.size() > 0) {
            String args = null;
            if (resultIn() != null) {
                flow.addSelector(resultIn().lname + entity().uname);
                args = resultIn().lname + entity().uname;
            }
            flow.useEffect();
            flow.totalScript().__("\n");
            flow.totalScript().L____("useEffect(() => {");
            flow.totalScript().L________("if (etat", unameWithEntity(), ".succes) {");
            flow.totalScript().L____________("resetEtat", unameWithEntity(), "();");
            for (Action ifSuccessAction : ifSuccessUiActionList) {
                if (ifSuccessAction.viewActionInjection != null) {
                    ifSuccessAction.viewActionInjection.addFlowScript(flow, 2, args);
                } else {
                    flow.totalScript().L____________(ifSuccessAction.lnameWithEntity);
                    System.out.println(ifSuccessAction.lnameWithEntity);
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
