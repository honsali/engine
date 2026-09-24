package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class GetByFieldMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        String lnameField = byField()[0].lname;
        if (lnameField.equals("id")) {
            f.addMdlRequiredRequestAttribute("id" + entity().uname, "string");
        } else {
            f.addMdlRequiredRequestAttribute(lnameField, "string");
        }

        if (byFatherId() && entity().haveFather) {
            f.addMdlRequiredRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
        f.addMdlSelectorAttribute(entity().lname, entity().uname);

    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");
    }

    @Override
    public void addHookAction(MdlFlow f) {
        String lnameField = byField()[0].lname;
        lnameField = lnameField.equals("id") ? "id" + entity().uname : lnameField;
        f.L("export const use", unameWithEntity(), " = () => {");
        f.L____("const dispatch = useAppDispatch();");
        f.L____("const { ", lnameField, " } = useParams();");
        f.L____("const ", entity().lname, " = useSelector(select", entity().uname, ");");
        f.L("");
        f.L____("useEffect(() => {");
        f.L________("dispatch(Ctrl", uc(), ".", lnameWithEntity(),
                "({ ", lnameField, " } as Req", uc(), "));");
        f.L____("}, [dispatch, ", lnameField, "]);");
        f.L("");
        f.L____("return {");
        f.L________(entity().lname);
        f.L____("};");
        f.L("};");
    }
}
