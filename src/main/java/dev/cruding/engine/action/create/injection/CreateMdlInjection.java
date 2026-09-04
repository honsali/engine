package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class CreateMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequiredRequestAttribute("request", "I" + entity().uname);
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequiredRequestAttribute("id" + entity().ufather, "string");
        }
    }

    @Override
    public void addHookImport(MdlFlow f) {
        f.addMdlImport("{ FormInstance }", "antd");
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ util }", "waxant");
        if (hasAdditionalHookParameters()) {
            f.addMdlImport("{ Req" + uc() + " }", "./Mdl" + uc());
        }
    }

    @Override
    public boolean usesDefaultHookAction() {
        return false;
    }

    @Override
    public void addHookAction(MdlFlow f) {
        f.L____("const ", lnameWithEntity(), " = async (form: FormInstance<I", entity().uname, ">");
        if (hasAdditionalHookParameters()) {
            f.__(", req: Partial<Req", uc(), ">");
        }
        f.__(") => {");
        f.L________("const request = util.removeNonSerialisable(await form.validateFields()) as I", entity().uname, ";");
        f.L________("return dispatch(Ctrl", uc(), ".", lnameWithEntity(), "({ ");
        if (hasAdditionalHookParameters()) {
            f.__("...req, ");
        }
        f.__("request, ...params");
        if (byFatherId() && entity().haveFather) {
            f.__(", id", entity().ufather, ": params.id", entity().ufather, "!");
        }
        f.__(" }));");
        f.L____("};");
    }

    private boolean hasAdditionalHookParameters() {
        return byProp() != null || element().byProp != null;
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("id" + entity().uname, "string");

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("id" + entity().uname, "string");
        f.addMdlSelectorAttribute("id" + entity().uname, "Id" + entity().uname);
    }


    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.id", entity().uname, " = action.payload.id", entity().uname, ";");
    }
}
