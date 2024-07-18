package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererParId extends Action {

    public ActionRecupererParId() {
        super("recupererParId");
        withService = true;
    }

    public ActionRecupererParId entity(Entity entity) {
        this.actionType = "recuperer" + entity.uname + "ParId";
        this.lname = actionType;
        this.entity = entity;
        return this;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("id" + entity.uname, "string");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        if (byFatherId && entity.haveFather) {
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entity.lname, "I" + entity.uname);
    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(entity.lname, "I" + entity.uname);
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const select", entity.uname, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.", entity.lname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.", entity.lname, " = await Service", entity.uname, ".recupererParId(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
        }
        if (byFatherId && entity.haveFather) {
            f.__("requete.id" + entity.ufather, ", ");
        }
        f.__("requete.id", entity.uname, ");");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____("", lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.", entity.lname, " = action.payload.", entity.lname, ";");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("        ", f.getPretCondition(), "execute(Ctrl", uc, ".", lname, ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

    public void addViewSelector(ViewFlow f) {
        f.addSpecificSelector(entity.uname, mvcPath + "/Mdl" + uc);
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.Optional");
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/{id}\")");
        f.L____("public ResponseEntity<", entity.uname, "> ", actionType, "(@PathVariable ", entity.id_.jtype, " id) {");
        f.L________("Optional<", entity.uname, "> ", entity.lname, " = ", entity.lname, "Repository.findById(id);");
        f.L________("return ", entity.lname, ".map(response -> ResponseEntity.ok().body(response)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const recupererParId = async (id", entity.uname, ": string) => {");
        f.L____("const ", entity.lname, ": I", entity.uname, " = (await axios.get<I", entity.uname, ">(`${resourceUri}/${id", entity.uname, "}`)).data;");
        f.L____("return ", entity.lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recupererParId,");
    }
}
