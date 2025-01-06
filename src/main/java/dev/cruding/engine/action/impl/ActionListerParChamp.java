package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class ActionListerParChamp extends Action {

    public Champ par;
    public Champ ordonnerPar;
    public String parName;
    public String parNameSuffixed;



    public ActionListerParChamp(Champ par) {
        this.par = par;
    }

    public ActionListerParChamp ordonnerPar(Champ ordonnerPar) {
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
        if (par.isRef || par.isFather) {
            f.addMdlRequestAttribute("id" + par.uname + parName, "string");
        } else {
            f.addMdlRequestAttribute(par.lname + parName, "string");
        }
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
        if (par.isRef || par.isFather) {
            f.L____("resultat.", sourceDonnee(), " = await Service", entite().uname, ".listerParId", par.uname, parName, "(requete.id", par.uname, parName, ");");
        } else {
            f.L____("resultat.", sourceDonnee(), " = await Service", entite().uname, ".listerPar", par.uname, parName, "(requete.", par.lname, parName, ");");
        }
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
        if (uc().endsWith(par.containingEntite) && !"id".equals(par.lname)) {
            f.totalScript().L____________("execute(Ctrl", uc(), ".", lname(), ", {");
            if (par.isRef || par.isFather) {
                f.totalScript().__(" ", par.uname + parName);
            } else {
                f.totalScript().__(" ", par.lname + parName);
            }
            f.totalScript().__(": ", StringUtils.uncapitalize(par.containingEntite), ".", par.lname, " ");
            f.totalScript().__("});");
            f.totalScript().L________("}");
            f.totalScript().L____("}, []);");
            f.totalScript().L____("}, [", StringUtils.uncapitalize(par.containingEntite), "]);");
        } else {
            f.totalScript().L________("execute(Ctrl", uc(), ".", lname(), ", {");
            f.totalScript().__(" ", par.lname, par.containingEntite, " ");
            f.totalScript().__("});");
            f.totalScript().L____("}, [", par.lname, par.containingEntite, "]);");
        }

        if (uc().endsWith(par.containingEntite) && !"id".equals(par.lname)) {
            f.addSpecificSelector(par.containingEntite, mvcPath() + "/Mdl" + uc());
        } else {
            f.addParam(par.lname + par.containingEntite);
        }
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        if (par.isRef || par.isFather) {
            f.L("const listerParId", par.uname, parName, " = async (id", par.uname, parName, ": string) => {");
            f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/listerParId", par.uname, parName, "/${id", par.uname, parName, "}`)).data;");

        } else {
            f.L("const listerPar", par.uname, parName, " = async (", par.lname, parName, ": string) => {");
            f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/listerPar", par.uname, parName, "/${", par.lname, parName, "}`)).data;");

        }

        f.L____("return liste", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        if (par.isRef || par.isFather) {
            f.L____("listerParId", par.uname, parName, ",");

        } else {
            f.L____("listerPar", par.uname, parName, ",");
        }
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        Entite parEntite = Context.getInstance().getEntite(par.uname);

        f.L("");
        if (par.isRef || par.isFather) {
            f.L____("List<", entite().uname, "> findAllBy", parNameSuffixed, "IdOrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(", parEntite.id_.jtype, " id", parName, ");");
        } else {
            f.L____("List<", entite().uname, "> findAllBy", parNameSuffixed, par.uname, "OrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(", par.jtype, " ", par.lname, parName, ");");
        }
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {

        if (par.isRef || par.isFather) {
            Entite parEntite = Context.getInstance().getEntite(par.uname);
            f.L("");
            f.L____("@GetMapping(\"/listerParId", par.uname, parName, "/{id", par.uname, parName, "}\")");
            f.L____("public List<", entite().uname, "> listerParId", par.uname, parName, "(@PathVariable ", parEntite.id_.jtype, " id", par.uname, parName, ") {");
            f.L________("return ", entite().lname, "Repository.findAllBy", parNameSuffixed, "IdOrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(id", par.lname, parName, ");");
            f.L____("}");

        } else {
            f.L("");
            f.L____("@GetMapping(\"/listerPar", par.uname, parName, "/{", par.lname, parName, "}\")");
            f.L____("public List<", entite().uname, "> listerPar", par.uname, parName, "(@PathVariable ", par.jtype, " ", par.lname, parName, ") {");
            f.L________("return ", entite().lname, "Repository.findAllBy", parNameSuffixed, par.uname, "OrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(", par.lname, parName, ");");
            f.L____("}");
        }
    }

}
