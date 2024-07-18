package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerParParam extends ActionAvecTable {

    public String lparName;
    public String uparName;
    public Field ordonnerPar;

    public ActionListerParParam() {
        super("listerPar");
        withService = true;
    }

    public ActionListerParParam(String lparName) {
        super("listerPar");
        withService = true;
        this.lparName = lparName;
        this.uparName = StringUtils.capitalize(lparName);

    }

    public ActionListerParParam ordonnerPar(Field ordonnerPar) {
        this.ordonnerPar = ordonnerPar;
        return this;
    }

    public ActionListerParParam entity(Entity entity) {
        this.entity = entity;
        this.actionType = "lister" + entity.uname + "Par" + uparName;
        this.lname = actionType;
        this.sourceDonnee = "liste" + entity.uname;
        return this;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute(lparName, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(sourceDonnee, "I" + entity.uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(sourceDonnee, "I" + entity.uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entity.uname, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.", sourceDonnee, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");

        f.L____("resultat.", sourceDonnee, " = await Service", entity.uname, ".listerPar", uparName, "(requete.", lparName, ");");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____(lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.", sourceDonnee, " = action.payload.", sourceDonnee, ";");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");

        f.totalScript().L____("    execute(Ctrl", uc, ".", lname, ");");
        f.totalScript().L____("}, []);");


        f.addParam(lparName);
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

    public void addServicImplementation(Flow f) {
        f.L("");

        f.L("const listerPar", uparName, " = async (", lparName, ": string) => {");
        f.L____("const liste", entity.uname, ": I", entity.uname, "[] = (await axios.get<I", entity.uname, "[]>(`${resourceUri}/listerPar", uparName, "/${", lparName, "}`)).data;");


        f.L____("return liste", entity.uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {

        f.L____("listerPar", uparName, ",");
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {

        f.L("");

        f.L____________("List<", entity.uname, "> findAllBy", lparName, "OrderBy");
        if (ordonnerPar != null) {
            f.__(ordonnerPar.uname);
        }
        f.__("(", lparName, " ", lparName, ");");

    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {


        f.L("");
        f.L____("@GetMapping(\"/listerPar", uparName, "/{", lparName, "}\")");
        f.L____("public List<", entity.uname, "> listerPar", uparName, "(@PathVariable ", lparName, " ", lparName, ") {");
        f.L________("return ", entity.lname, "Repository.findAllBy", lparName, "OrderBy");
        if (ordonnerPar != null) {
            f.__(ordonnerPar.uname);
        }
        f.__("(", lparName, ");");
        f.L____("}");
    }

}
