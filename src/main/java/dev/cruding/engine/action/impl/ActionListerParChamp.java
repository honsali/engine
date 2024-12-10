package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class ActionListerParChamp extends Action {

    public Field par;
    public Field ordonnerPar;
    public String parName;
    public String parNameSuffixed;



    public ActionListerParChamp(Field par) {
        this.par = par;
    }

    public ActionListerParChamp ordonnerPar(Field ordonnerPar) {
        this.ordonnerPar = ordonnerPar;
        return this;
    }



    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (par.isRef || par.isFather) {
            f.addMdlRequestAttribute("id" + par.uname + parName, "string");
        } else {
            f.addMdlRequestAttribute(par.lname + parName, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(sourceDonnee(), "I" + entity().uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(sourceDonnee(), "I" + entity().uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entity().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", sourceDonnee(), ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        if (par.isRef || par.isFather) {
            f.L____("resultat.", sourceDonnee(), " = await Service", entity().uname, ".listerParId", par.uname, parName, "(requete.id", par.uname, parName, ");");
        } else {
            f.L____("resultat.", sourceDonnee(), " = await Service", entity().uname, ".listerPar", par.uname, parName, "(requete.", par.lname, parName, ");");
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
        if (uc().endsWith(par.containingEntity) && !"id".equals(par.lname)) {
            f.totalScript().L____________("execute(Ctrl", uc(), ".", lname(), ", {");
            if (par.isRef || par.isFather) {
                f.totalScript().__(" ", par.uname + parName);
            } else {
                f.totalScript().__(" ", par.lname + parName);
            }
            f.totalScript().__(": ", StringUtils.uncapitalize(par.containingEntity), ".", par.lname, " ");
            f.totalScript().__("});");
            f.totalScript().L________("}");
            f.totalScript().L____("}, []);");
            f.totalScript().L____("}, [", StringUtils.uncapitalize(par.containingEntity), "]);");
        } else {
            f.totalScript().L________("execute(Ctrl", uc(), ".", lname(), ", {");
            f.totalScript().__(" ", par.lname, par.containingEntity, " ");
            f.totalScript().__("});");
            f.totalScript().L____("}, [", par.lname, par.containingEntity, "]);");
        }

        if (uc().endsWith(par.containingEntity) && !"id".equals(par.lname)) {
            f.addSpecificSelector(par.containingEntity, mvcPath() + "/Mdl" + uc());
        } else {
            f.addParam(par.lname + par.containingEntity);
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
            f.L____("const liste", entity().uname, ": I", entity().uname, "[] = (await axios.get<I", entity().uname, "[]>(`${resourceUri}/listerParId", par.uname, parName, "/${id", par.uname, parName, "}`)).data;");

        } else {
            f.L("const listerPar", par.uname, parName, " = async (", par.lname, parName, ": string) => {");
            f.L____("const liste", entity().uname, ": I", entity().uname, "[] = (await axios.get<I", entity().uname, "[]>(`${resourceUri}/listerPar", par.uname, parName, "/${", par.lname, parName, "}`)).data;");

        }

        f.L____("return liste", entity().uname, ";");
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
        Entity parEntity = Context.getInstance().getEntity(par.uname);

        f.L("");
        if (par.isRef || par.isFather) {
            f.L____("List<", entity().uname, "> findAllBy", parNameSuffixed, "IdOrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(", parEntity.id_.jtype, " id", parName, ");");
        } else {
            f.L____("List<", entity().uname, "> findAllBy", parNameSuffixed, par.uname, "OrderBy");
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
            Entity parEntity = Context.getInstance().getEntity(par.uname);
            f.L("");
            f.L____("@GetMapping(\"/listerParId", par.uname, parName, "/{id", par.uname, parName, "}\")");
            f.L____("public List<", entity().uname, "> listerParId", par.uname, parName, "(@PathVariable ", parEntity.id_.jtype, " id", par.uname, parName, ") {");
            f.L________("return ", entity().lname, "Repository.findAllBy", parNameSuffixed, "IdOrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(id", par.lname, parName, ");");
            f.L____("}");

        } else {
            f.L("");
            f.L____("@GetMapping(\"/listerPar", par.uname, parName, "/{", par.lname, parName, "}\")");
            f.L____("public List<", entity().uname, "> listerPar", par.uname, parName, "(@PathVariable ", par.jtype, " ", par.lname, parName, ") {");
            f.L________("return ", entity().lname, "Repository.findAllBy", parNameSuffixed, par.uname, "OrderBy");
            if (ordonnerPar != null) {
                f.__(ordonnerPar.uname);
            }
            f.__("(", par.lname, parName, ");");
            f.L____("}");
        }
    }

}
