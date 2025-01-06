package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerParParam extends Action {

    public String lparName;
    public String uparName;
    public Champ ordonnerPar;



    public ActionListerParParam(String lparName) {
        this.lparName = lparName;
        this.uparName = StringUtils.capitalize(lparName);

    }

    public ActionListerParParam ordonnerPar(Champ ordonnerPar) {
        this.ordonnerPar = ordonnerPar;
        return this;
    }


    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute(lparName, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(sourceDonnee(), "I" + entite().uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(sourceDonnee(), "I" + entite().uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", sourceDonnee(), ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");

        f.L____("resultat.", sourceDonnee(), " = await Service", entite().uname, ".listerPar", uparName, "(requete.", lparName, ");");
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", sourceDonnee(), " = action.payload.", sourceDonnee(), ";");
        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, []);");


        f.addParam(lparName);
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addServiceImplementation(Flow f) {
        f.L("");

        f.L("const listerPar", uparName, " = async (", lparName, ": string) => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/listerPar", uparName, "/${", lparName, "}`)).data;");


        f.L____("return liste", entite().uname, ";");
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

        f.L____("List<", entite().uname, "> findAllBy", lparName, "OrderBy");
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
        f.L____("public List<", entite().uname, "> listerPar", uparName, "(@PathVariable ", lparName, " ", lparName, ") {");
        f.L________("return ", entite().lname, "Repository.findAllBy", lparName, "OrderBy");
        if (ordonnerPar != null) {
            f.__(ordonnerPar.uname);
        }
        f.__("(", lparName, ");");
        f.L____("}");
    }

}
